/*
 * SPDX-FileCopyrightText: The LineageOS Project
 * SPDX-License-Identifier: Apache-2.0
 */

#include "AlsSampler.h"

#include <aidl/vendor/lineage/oplus_als/IAreaCapture.h>
#include <android-base/logging.h>

#include <time.h>

namespace android {
namespace hardware {
namespace sensors {
namespace V2_1 {
namespace subhal {
namespace implementation {
namespace fusionlight {

using aidl::vendor::lineage::oplus_als::AreaRgbCaptureResult;
using aidl::vendor::lineage::oplus_als::IAreaCapture;
using namespace std::chrono_literals;

namespace {

constexpr auto kServiceRetryPeriod = 2s;

std::chrono::nanoseconds GetBootTime() {
    timespec time;
    if (clock_gettime(CLOCK_BOOTTIME, &time) != 0) {
        return 0ns;
    }
    return std::chrono::seconds(time.tv_sec) + std::chrono::nanoseconds(time.tv_nsec);
}

}  // namespace

struct AlsSampler::SharedState {
    explicit SharedState(SampleCallback callback) : sample_callback(std::move(callback)) {}

    std::mutex mutex;
    std::condition_variable condition;
    const SampleCallback sample_callback;
    bool active = false;
    bool immediate = false;
};

AlsSampler::AlsSampler(SampleCallback sample_callback)
    : state_(std::make_shared<SharedState>(std::move(sample_callback))) {}

AlsSampler::~AlsSampler() {
    stop();
}

void AlsSampler::setConfig(CwbConfig config) {
    config_ = std::move(config);
}

void AlsSampler::start() {
    {
        std::lock_guard lock(state_->mutex);
        if (state_->active) {
            return;
        }
        state_->active = true;
        state_->immediate = true;
    }
    thread_ = std::thread(&AlsSampler::threadLoop, this);
}

void AlsSampler::stop() {
    {
        std::lock_guard lock(state_->mutex);
        if (!state_->active) {
            return;
        }
        state_->active = false;
        state_->immediate = false;
    }
    state_->condition.notify_all();
    if (thread_.joinable()) {
        thread_.join();
    }
}

void AlsSampler::requestSample() {
    {
        std::lock_guard lock(state_->mutex);
        if (!state_->active) {
            return;
        }
        state_->immediate = true;
    }
    state_->condition.notify_all();
}

void AlsSampler::threadLoop() {
    const auto& state = state_;
    const auto period = config_.screenshot_period;
    auto next_sample = std::chrono::steady_clock::now();
    auto service = GetService<IAreaCapture>();

    for (;;) {
        {
            std::unique_lock lock(state->mutex);
            state->condition.wait_until(lock, next_sample,
                                        [&] { return !state->active || state->immediate; });
            if (!state->active) {
                return;
            }
            state->immediate = false;
        }

        if (service == nullptr) {
            service = GetService<IAreaCapture>();
        }

        std::optional<CwbSample> sample;
        if (service == nullptr) {
            LOG(WARNING) << "IAreaCapture is unavailable";
            next_sample = std::chrono::steady_clock::now() + kServiceRetryPeriod;
        } else {
            const auto frame_start = GetBootTime();
            AreaRgbCaptureResult result = {};
            if (service->getAreaBrightness(&result).isOk()) {
                sample = CwbSample{static_cast<int32_t>(result.r), static_cast<int32_t>(result.g),
                                   static_cast<int32_t>(result.b), frame_start, GetBootTime()};
            } else {
                LOG(WARNING) << "IAreaCapture::getAreaBrightness failed";
            }
            next_sample += period;
            if (next_sample < std::chrono::steady_clock::now()) {
                next_sample = std::chrono::steady_clock::now();
            }
        }

        if (state->sample_callback) {
            state->sample_callback(std::move(sample));
        }
    }
}

}  // namespace fusionlight
}  // namespace implementation
}  // namespace subhal
}  // namespace V2_1
}  // namespace sensors
}  // namespace hardware
}  // namespace android
