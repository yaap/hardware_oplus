/*
 * SPDX-FileCopyrightText: The LineageOS Project
 * SPDX-License-Identifier: Apache-2.0
 */

#pragma once

#include "FusionLightUtils.h"

namespace android {
namespace hardware {
namespace sensors {
namespace V2_1 {
namespace subhal {
namespace implementation {
namespace fusionlight {

struct CwbSample {
    int32_t r = 0;
    int32_t g = 0;
    int32_t b = 0;
    std::chrono::nanoseconds frame_start{0};
    std::chrono::nanoseconds frame_end{0};
};

class ScreenSampler {
  public:
    using SampleCallback = std::function<void(std::optional<CwbSample>)>;

    virtual ~ScreenSampler() = default;

    virtual void setConfig(CwbConfig config) = 0;
    virtual void start() = 0;
    virtual void stop() = 0;
    virtual void requestSample() = 0;
};

}  // namespace fusionlight
}  // namespace implementation
}  // namespace subhal
}  // namespace V2_1
}  // namespace sensors
}  // namespace hardware
}  // namespace android
