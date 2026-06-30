package android.hardware.display;

import android.os.Bundle;
import android.os.RemoteException;

/* Stub implementation - OxygenOS display manager not available on AOSP/LineageOS */
public class OplusosDisplayManager {
    public static final String KEY_GAME_LOCK_SWITCH = "game_lock_switch";
    public static final int MSG_GAME_SPACE = 0;

    public boolean setStateChanged(int msgId, Bundle extraData) throws RemoteException {
        return false;
    }

    public boolean setAutoBrightnessAdj(float value) {
        return false;
    }

    public void connectWifiDisplay(String address) {}

    public void startWifiDisplayScan() {}

    public void stopWifiDisplayScan() {}

    public void setTemporaryAutoBrightnessAdjustment(float adjustment) {}

    public void setTemporaryBrightness(int displayId, float brightness) {}

    public void setDisplayPowerMode(int mode) {}

    public void setGameColorSpaceData(String pkgName, int status, int mode, String data) {}

    public boolean isGameColorSpaceBlackList(String pkgName) {
        return false;
    }

    public boolean isGameSceneBeautifyWhiteList(String pkgName) {
        return false;
    }

    public boolean isSupportLiveBrightness() {
        return false;
    }

    public boolean isSupportCameraBrightnessCurve() {
        return false;
    }

    public boolean setCameraBrightnessCurveEnabled(boolean enabled) {
        return false;
    }
}
