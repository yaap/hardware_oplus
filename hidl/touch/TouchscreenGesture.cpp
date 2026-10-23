/*
 * Copyright (C) 2022 The LineageOS Project
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

#define LOG_TAG "vendor.lineage.touch@1.0-service.oplus"

#include <android-base/file.h>
#include <android-base/logging.h>
#include <android-base/strings.h>
#include <android/binder_manager.h>

#include <cerrno>
#include <cstdint>
#include <cstdlib>
#include <limits>

#include <TouchscreenGestureConfig.h>

using ::android::base::Trim;

namespace {}  // anonymous namespace

namespace vendor {
namespace lineage {
namespace touch {
namespace V1_0 {
namespace implementation {

Return<void> TouchscreenGesture::getSupportedGestures(getSupportedGestures_cb resultCb) {
    std::vector<Gesture> gestures;

    for (const auto& [id, name] : kGestureNames) {
        if (kSupportedGestures & (1 << id)) {
            gestures.push_back({static_cast<int>(gestures.size()), name, kGestureStartKey + id});
        }
    }

    resultCb(gestures);

    return Void();
}

Return<bool> TouchscreenGesture::setGestureEnabled(const Gesture& gesture, bool enabled) {
    const int gestureId = gesture.keycode - kGestureStartKey;
    if (gestureId < 0 || gestureId >= 32) {
        LOG(ERROR) << "invalid gesture keycode " << gesture.keycode;
        return false;
    }

    // Connect to IOplusTouch
    const std::string instance = std::string() + IOplusTouch::descriptor + "/default";
    mTouchService = IOplusTouch::fromBinder(
            ndk::SpAIBinder(AServiceManager_waitForService(instance.c_str())));
    if (mTouchService == nullptr) {
        LOG(WARNING) << "IOplusTouch service is unavailable; keeping software gesture state";
        return true;
    }

    // Read current value. HBP does not provide the legacy double_tap_enable_indep
    // node, so an empty value is expected on this platform.
    std::string tmp;
    const auto readStatus = mTouchService->touchReadNodeFile(0, 21, &tmp);
    tmp = Trim(tmp);
    if (!readStatus.isOk() || tmp.empty()) {
        LOG(INFO) << "gesture mask node unavailable; accepting software gesture state";
        return true;
    }

    char* end = nullptr;
    errno = 0;
    const long parsed = std::strtol(tmp.c_str(), &end, 16);
    if (errno != 0 || end == tmp.c_str() || *end != '\0' ||
        parsed < 0 || parsed > std::numeric_limits<uint32_t>::max()) {
        LOG(WARNING) << "invalid gesture mask value '" << tmp << "'; accepting software state";
        return true;
    }

    uint32_t contents = static_cast<uint32_t>(parsed);
    if (enabled) {
        contents |= (1U << gestureId);
    } else {
        contents &= ~(1U << gestureId);
    }

    int result = 0;
    // Always keep gestures enabled and persist the mask.
    mTouchService->touchWriteNodeFile(0, 1, "1", &result);
    mTouchService->touchWriteNodeFile(0, 21, std::to_string(contents), &result);

    return true;
}

}  // namespace implementation
}  // namespace V1_0
}  // namespace touch
}  // namespace lineage
}  // namespace vendor
