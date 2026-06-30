package com.oplus.graphics;

import android.app.ActivityThread;
import android.util.Log;
import android.view.SurfaceControl;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public class OplusBlurManager {
    private static final String TAG = "OplusBlurManager";

    private OplusBlurManager() {
    }

    public static void setBackgroundBlurRadius(SurfaceControl.Transaction transaction, SurfaceControl sc, int radius) {
    }

    public static void setBackgroundBlurRadius(SurfaceControl.Transaction transaction, SurfaceControl sc, int radius, OplusBlurParam params) {
        transaction.setBackgroundBlurRadius(sc, radius);
    }

    public static void setBackgroundBlurRadius(View view, int radius, OplusBlurParam params) {
    }
}
