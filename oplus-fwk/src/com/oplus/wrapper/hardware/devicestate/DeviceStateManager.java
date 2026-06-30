package com.oplus.wrapper.hardware.devicestate;

import android.content.Context;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.function.Consumer;

public class DeviceStateManager {
    private final Map<DeviceStateCallback, DeviceStateCallbackProxy> mCallbackProxyCache = new ConcurrentHashMap(0);
    private final android.hardware.devicestate.DeviceStateManager mDeviceStateManager;

    public DeviceStateManager(Context context) {
        this.mDeviceStateManager = (android.hardware.devicestate.DeviceStateManager) context.getSystemService(android.hardware.devicestate.DeviceStateManager.class);
    }

    public void registerCallback(Executor executor, DeviceStateCallback callback) {
        if (callback == null) {
            return;
        }
        DeviceStateCallbackProxy stateCallbackProxy = this.mCallbackProxyCache.get(callback);
        if (stateCallbackProxy == null) {
            stateCallbackProxy = new DeviceStateCallbackProxy(callback);
            this.mCallbackProxyCache.put(callback, stateCallbackProxy);
        }
        this.mDeviceStateManager.registerCallback(executor, stateCallbackProxy.getDeviceStateCallback());
    }

    public void unregisterCallback(DeviceStateCallback callback) {
        DeviceStateCallbackProxy stateCallbackProxy;
        if (callback == null || (stateCallbackProxy = this.mCallbackProxyCache.get(callback)) == null) {
            return;
        }
        this.mDeviceStateManager.unregisterCallback(stateCallbackProxy.getDeviceStateCallback());
        this.mCallbackProxyCache.remove(callback);
    }

    public interface DeviceStateCallback {
        void onDeviceStateChanged(DeviceState deviceState);

        @Deprecated
        void onStateChanged(int i);

        @Deprecated
        default void onSupportedStatesChanged(int[] supportedStates) {
        }

        @Deprecated
        default void onBaseStateChanged(int state) {
        }

        default void onSupportedStatesChanged(List<DeviceState> supportedStates) {
        }
    }

    public static class FoldStateListener implements DeviceStateCallback {
        private final android.hardware.devicestate.DeviceStateManager.FoldStateListener mFoldStateListener;

        public FoldStateListener(Context context, Consumer<Boolean> listener) {
            this.mFoldStateListener = new android.hardware.devicestate.DeviceStateManager.FoldStateListener(context, listener);
        }

        @Override
        @Deprecated
        public void onSupportedStatesChanged(int[] supportedStates) {
        }

        @Override
        @Deprecated
        public void onBaseStateChanged(int state) {
        }

        @Override
        @Deprecated
        public void onStateChanged(int state) {
        }

        @Override
        public void onDeviceStateChanged(DeviceState deviceState) {
            this.mFoldStateListener.onDeviceStateChanged(deviceState.getDeviceState());
        }

        @Override
        public void onSupportedStatesChanged(List<DeviceState> supportedStates) {
            List<android.hardware.devicestate.DeviceState> supportedDeviceStates = new ArrayList<>();
            for (DeviceState ostate : supportedStates) {
                supportedDeviceStates.add(ostate.getDeviceState());
            }
            this.mFoldStateListener.onSupportedStatesChanged(supportedDeviceStates);
        }

        public Boolean getFolded() {
            return this.mFoldStateListener.getFolded();
        }
    }

    private static class DeviceStateCallbackProxy {
        private final android.hardware.devicestate.DeviceStateManager.DeviceStateCallback mDeviceStateCallback;

        private DeviceStateCallbackProxy(final DeviceStateCallback deviceStateCallback) {
            this.mDeviceStateCallback = new android.hardware.devicestate.DeviceStateManager.DeviceStateCallback() {
                @Override
                public void onDeviceStateChanged(android.hardware.devicestate.DeviceState state) {
                    deviceStateCallback.onDeviceStateChanged(new DeviceState(state));
                }

                @Override
                public void onSupportedStatesChanged(List<android.hardware.devicestate.DeviceState> supportedStates) {
                    List<DeviceState> supportedODeviceStates = new ArrayList<>();
                    for (android.hardware.devicestate.DeviceState state : supportedStates) {
                        supportedODeviceStates.add(new DeviceState(state));
                    }
                    deviceStateCallback.onSupportedStatesChanged(supportedODeviceStates);
                }
            };
        }

        public android.hardware.devicestate.DeviceStateManager.DeviceStateCallback getDeviceStateCallback() {
            return this.mDeviceStateCallback;
        }
    }
}
