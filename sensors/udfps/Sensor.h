/*
 * Copyright (C) 2019 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

#pragma once

#include <android/hardware/sensors/2.1/types.h>
#include <fcntl.h>
#include <poll.h>
#include <unistd.h>

#include <condition_variable>
#include <fstream>
#include <memory>
#include <mutex>
#include <thread>
#include <vector>

using ::android::hardware::sensors::V1_0::OperationMode;
using ::android::hardware::sensors::V1_0::Result;
using ::android::hardware::sensors::V2_1::Event;
using ::android::hardware::sensors::V2_1::SensorInfo;
using ::android::hardware::sensors::V2_1::SensorType;

namespace android {
namespace hardware {
namespace sensors {
namespace V2_1 {
namespace subhal {
namespace implementation {

extern std::string GetPollPath(const char** array);

class ISensorsEventCallback {
  public:
    virtual ~ISensorsEventCallback(){};
    virtual void postEvents(const std::vector<Event>& events, bool wakeup) = 0;
};

class Sensor {
  public:
    Sensor(int32_t sensorHandle, ISensorsEventCallback* callback);
    virtual ~Sensor();

    const SensorInfo& getSensorInfo() const;
    virtual void batch(int32_t samplingPeriodNs);
    virtual void activate(bool enable);
    virtual Result flush();

    virtual void setOperationMode(OperationMode mode);
    bool supportsDataInjection() const;
    Result injectEvent(const Event& event);

  protected:
    virtual void run();
    virtual std::vector<Event> readEvents();
    static void startThread(Sensor* sensor);

    bool isWakeUpSensor();

    bool mIsEnabled;
    int64_t mSamplingPeriodNs;
    int64_t mLastSampleTimeNs;
    SensorInfo mSensorInfo;

    std::atomic_bool mStopThread;
    std::condition_variable mWaitCV;
    std::mutex mRunMutex;
    std::thread mRunThread;

    ISensorsEventCallback* mCallback;

    OperationMode mMode;
};

class OneShotSensor : public Sensor {
  public:
    OneShotSensor(int32_t sensorHandle, ISensorsEventCallback* callback);

    virtual void batch(int32_t /* samplingPeriodNs */) override {}

    virtual Result flush() override { return Result::BAD_VALUE; }
};

class SysfsPollingOneShotSensor : public OneShotSensor {
  public:
    SysfsPollingOneShotSensor(int32_t sensorHandle, ISensorsEventCallback* callback,
                              const std::string& pollPath, const std::string& name,
                              const std::string& typeAsString, SensorType type);
    virtual ~SysfsPollingOneShotSensor() override;

    virtual void activate(bool enable) override;
    virtual void activate(bool enable, bool notify, bool lock);
    virtual void setOperationMode(OperationMode mode) override;
    virtual std::vector<Event> readEvents() override;
    virtual void fillEventData(Event& event);

  protected:
    virtual void run() override;

    std::ofstream mEnableStream;

  private:
    void interruptPoll();

    struct pollfd mPolls[2];
    int mWaitPipeFd[2];
    int mPollFd;
};

static const char* udfpsPaths[] = {
  "/sys/kernel/oplus_display/fp_state",
  "/sys/devices/platform/soc/ac0000.qcom,qupv3_1_geni_se/a90000.spi/spi_master/spi0/spi0.0/synaptics_tcm_hbp.0/fp_pressed",
  "/sys/devices/platform/soc/ac0000.qcom,qupv3_1_geni_se/a90000.spi/spi_master/spi1/spi1.0/synaptics_tcm_hbp.0/fp_pressed",
  "/proc/touchpanel/i2c/udfps_pressed",
  NULL
};

class UdfpsSensor : public SysfsPollingOneShotSensor {
  public:
    UdfpsSensor(int32_t sensorHandle, ISensorsEventCallback* callback)
        : SysfsPollingOneShotSensor(
              sensorHandle, callback, GetPollPath(udfpsPaths),
              "UDFPS Sensor", "org.lineageos.sensor.udfps",
              static_cast<SensorType>(static_cast<int32_t>(SensorType::DEVICE_PRIVATE_BASE) + 1)) {}
};

#ifdef USES_DOUBLE_TAP_SENSOR
static const char* doubleTapPaths[] = {
  "/sys/devices/platform/soc/ac0000.qcom,qupv3_1_geni_se/a90000.spi/spi_master/spi0/spi0.0/synaptics_tcm_hbp.0/double_tap_pressed",
  "/sys/devices/platform/soc/ac0000.qcom,qupv3_1_geni_se/a90000.spi/spi_master/spi1/spi1.0/synaptics_tcm_hbp.0/double_tap_pressed",
  "/proc/touchpanel/i2c/double_tap_pressed",
  NULL
};

class DoubleTapSensor : public SysfsPollingOneShotSensor {
  public:
    DoubleTapSensor(int32_t sensorHandle, ISensorsEventCallback* callback)
        : SysfsPollingOneShotSensor(
              sensorHandle, callback, GetPollPath(doubleTapPaths),
              "Double Tap Sensor", "org.yaap.sensor.double_tap",
              static_cast<SensorType>(static_cast<int32_t>(SensorType::DEVICE_PRIVATE_BASE) + 1)) {}
};
#endif

#ifdef USES_TAP_SENSOR
static const char* singleTapPaths[] = {
  "/sys/devices/platform/soc/ac0000.qcom,qupv3_1_geni_se/a90000.spi/spi_master/spi0/spi0.0/synaptics_tcm_hbp.0/single_tap_pressed",
  "/sys/devices/platform/soc/ac0000.qcom,qupv3_1_geni_se/a90000.spi/spi_master/spi1/spi1.0/synaptics_tcm_hbp.0/single_tap_pressed",
  "/proc/touchpanel/i2c/single_tap_pressed",
  NULL
};

class SingleTapSensor : public SysfsPollingOneShotSensor {
  public:
    SingleTapSensor(int32_t sensorHandle, ISensorsEventCallback* callback)
        : SysfsPollingOneShotSensor(
              sensorHandle, callback, GetPollPath(singleTapPaths),
              "Tap Sensor", "org.yaap.sensor.tap",
              static_cast<SensorType>(static_cast<int32_t>(SensorType::DEVICE_PRIVATE_BASE) + 1)) {}
};
#endif

}  // namespace implementation
}  // namespace subhal
}  // namespace V2_1
}  // namespace sensors
}  // namespace hardware
}  // namespace android
