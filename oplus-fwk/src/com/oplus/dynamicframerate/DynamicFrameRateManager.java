package com.oplus.dynamicframerate;

import android.view.SurfaceControl;
import android.view.View;
import com.oplus.wrapper.os.Bundle;

/* JADX INFO: loaded from: classes.dex */
public class DynamicFrameRateManager {
    public static final int END_VELOCITY = -2;
    public static final int FRAME_RATE_END = 0;
    public static final int LEVEL_DEFAULT = 0;
    public static final int LEVEL_HIGH_PRECISION = 2;
    public static final int LEVEL_LOW_PRECISION = 1;
    public static final int LOW_PRECISION_FRAME_RATE = 120;
    public static final int START_VELOCITY = -1;

    private DynamicFrameRateManager() {
    }

    public static boolean setFrameRate(View view, int frameRate, Bundle bundle) {
        return true;
    }

    public static boolean setFrameRate(SurfaceControl surfaceControl, SurfaceControl.Transaction transaction, int frameRate, Bundle bundle) {
        return true;
    }

    public static int getSuggestFrameRate(float velocity, int type) {
        return 120;
    }

    public static int getDynamicFrameRateType() {
        return 0;
    }

    public static boolean setFrameRate(Object object, int typeId, int velocity, android.os.Bundle bundle) {
        return true;
    }

    public static boolean setFrameRate(SurfaceControl surfaceControl, SurfaceControl.Transaction transaction, int typeId, int velocity, android.os.Bundle bundle) {
        return true;
    }

    public static boolean setFrameRateNoContext(Object object, SurfaceControl.Transaction transaction, int typeId, int velocity, android.os.Bundle bundle) {
        return true;
    }

    public static boolean isTypeEnable(int type) {
        return false;
    }
}
