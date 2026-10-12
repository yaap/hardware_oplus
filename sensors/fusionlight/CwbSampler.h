/*
 * SPDX-FileCopyrightText: The LineageOS Project
 * SPDX-License-Identifier: Apache-2.0
 */

#pragma once

#include "ScreenSampler.h"

#include <android-base/macros.h>

#include <thread>

namespace android {
namespace hardware {
namespace sensors {
namespace V2_1 {
namespace subhal {
namespace implementation {
namespace fusionlight {

class CwbSampler final : public ScreenSampler {
  public:
    explicit CwbSampler(SampleCallback sample_callback);
    ~CwbSampler() override;

    DISALLOW_COPY_AND_ASSIGN(CwbSampler);

    void setConfig(CwbConfig config) override;
    void start() override;
    void stop() override;
    void requestSample() override;

  private:
    struct SharedState;
    class DisplayCallback;

    void threadLoop();

    std::shared_ptr<SharedState> state_;
    CwbConfig config_;
    std::thread thread_;
};

}  // namespace fusionlight
}  // namespace implementation
}  // namespace subhal
}  // namespace V2_1
}  // namespace sensors
}  // namespace hardware
}  // namespace android
