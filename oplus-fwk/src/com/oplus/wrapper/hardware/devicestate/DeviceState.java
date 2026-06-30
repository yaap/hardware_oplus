package com.oplus.wrapper.hardware.devicestate;

/* loaded from: classes.dex */
public final class DeviceState {
    private android.hardware.devicestate.DeviceState mInnerDeviceState;

    DeviceState(android.hardware.devicestate.DeviceState deviceState) {
        this.mInnerDeviceState = null;
        this.mInnerDeviceState = deviceState;
    }

    public int getIdentifier() {
        return this.mInnerDeviceState.getIdentifier();
    }

    android.hardware.devicestate.DeviceState getDeviceState() {
        return this.mInnerDeviceState;
    }
}
