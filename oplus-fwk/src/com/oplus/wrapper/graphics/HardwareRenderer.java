package com.oplus.wrapper.graphics;

import android.graphics.Bitmap;

/* loaded from: classes.dex */
public class HardwareRenderer {

    public interface FrameDrawingCallback {
        void onFrameDraw(long j);
    }

    private HardwareRenderer() {
    }

    public static Bitmap createHardwareBitmap(android.graphics.RenderNode node, int width, int height) {
        return android.graphics.HardwareRenderer.createHardwareBitmap(node, width, height);
    }
}
