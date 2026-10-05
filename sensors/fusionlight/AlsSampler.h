/*
 * SPDX-FileCopyrightText: The LineageOS Project
 * SPDX-License-Identifier: Apache-2.0
 */

#pragma once

#include "CwbSampler.h"

#include <android-base/macros.h>

#include <memory>
#include <thread>

namespace android {
namespace hardware {
namespace sensors {
namespace V2_1 {
namespace subhal {
namespace implementation {
namespace fusionlight {

// Samples the screen colour through the ALS capture service instead of CWB.
class AlsSampler final : public ScreenSampler {
  public:
    explicit AlsSampler(SampleCallback sample_callback);
    ~AlsSampler() override;

    DISALLOW_COPY_AND_ASSIGN(AlsSampler);

    void setConfig(CwbConfig config) override;
    void start() override;
    void stop() override;
    void requestSample() override;

  private:
    struct SharedState;

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
