/*
 * SPDX-FileCopyrightText: 2026 The YAAP Project
 * SPDX-License-Identifier: Apache-2.0
 */

#define LOG_TAG "OPlusFaceSensorPropsShim"

#include <aidl/android/hardware/biometrics/face/SensorProps.h>
#include <dlfcn.h>

using aidl::android::hardware::biometrics::face::SensorProps;

namespace {

SensorProps SensorPropsInit(SensorProps props) {
    props.halControlsPreview = true;
    return props;
}

}  // namespace

extern "C" void
ZNK4aidl7android8hardware10biometrics4face11SensorProps13writeToParcelEP7AParcel(
        SensorProps* thisptr, AParcel* parcel) {
    static auto props = SensorPropsInit(*thisptr);
    static auto writeToParcel = reinterpret_cast<
            void (*)(SensorProps*, AParcel*) >(
            dlsym(RTLD_NEXT, __func__));

    writeToParcel(&props, parcel);
}
