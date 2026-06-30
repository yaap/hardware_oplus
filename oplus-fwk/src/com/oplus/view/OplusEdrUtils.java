package com.oplus.view;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.SystemProperties;
import android.util.Size;
import android.view.SurfaceControl;
import android.view.SurfaceView;
import android.view.View;

/* Stub implementation - OxygenOS SurfaceControl EDR extensions not available */
public final class OplusEdrUtils {
    public static final int DOLBY_OFF_WITHOUT_ANIMATION = 131073;
    public static final int DOLBY_OFF_WITH_ANIMATION = 131072;
    public static final int DOLBY_ON_WITHOUT_ANIMATION = 131329;
    public static final int DOLBY_ON_WITH_ANIMATION = 131328;
    public static final int EDR_FEATURE_DOLBY_VERSION = 1;
    public static final int EDR_FEATURE_HDR_VERSION = 5;
    public static final int EDR_FEATURE_LOCALHDR_VERSION = 0;
    public static final int LOCALHDR_OFF_WITHOUT_ANIMATION = 65536;
    public static final int LOCALHDR_OFF_WITH_ANIMATION = 65537;
    public static final int LOCALHDR_ON_WITHOUT_ANIMATION = 65792;
    public static final int LOCALHDR_ON_WITH_ANIMATION = 65793;
    public static final int SIZE_DISPLAY_MAX = 16;
    public static final int SIZE_POSITION_MAX = 2;

    public static class OplusEdrParameters {
        public int[] displayPosition;
        public Rect displayRect;
        public float[] displayTransform;
        public int imageRotation;
    }

    public static class OplusSkGainmapInfo {
        public static final int SIZE_RGBA_MAX = 4;
        public int fType = 0;
        public int fBaseImageType = 0;
        public float scale = 1.0f;
        public float fDisplayRatioSdr = 1.0f;
        public float fDisplayRatioHdr = 2.0f;
        public float[] fEpsilonSdr = {0.0f, 0.0f, 0.0f, 1.0f};
        public float[] fEpsilonHdr = {0.0f, 0.0f, 0.0f, 1.0f};
        public float[] fGainmapRatioMin = {1.0f, 1.0f, 1.0f, 1.0f};
        public float[] fGainmapRatioMax = {2.0f, 2.0f, 2.0f, 1.0f};
        public float[] fGainmapGamma = {1.0f, 1.0f, 1.0f, 1.0f};
    }

    public static SurfaceControl getSurfaceControl(View view) {
        return null;
    }

    public static SurfaceControl getBlastSurfaceControl(SurfaceView view) {
        return null;
    }

    public static int getLocalHdrVersion() {
        return SystemProperties.getInt("persist.sys.feature.localhdr_version", -1);
    }

    public static boolean getLocalHdrSubDisplaySupport() {
        return false;
    }

    public static int getDolbyEdrVersion() {
        return SystemProperties.getInt("persist.sys.feature.dolby_vision_app", -1);
    }

    public static int getEdrFeatureVersion(int feature) {
        switch (feature) {
            case 0: return SystemProperties.getInt("persist.sys.feature.localhdr_version", -1);
            case 1: return SystemProperties.getInt("persist.sys.feature.dolby_vision_app", -1);
            case 5: return SystemProperties.getInt("persist.sys.feature.hdr_vision_app", -1);
            default: return -1;
        }
    }

    public static boolean setEdrFlags(SurfaceControl sc, SurfaceControl.Transaction transaction, int flags) {
        return false;
    }

    public static boolean setEdrImageSize(SurfaceControl sc, SurfaceControl.Transaction transaction, Size imageSize, int index) {
        return false;
    }

    public static boolean setEdrImageMetadata(SurfaceControl sc, SurfaceControl.Transaction transaction, byte[] metadata, int index) {
        return false;
    }

    public static boolean setEdrAuxiliaryImage(SurfaceControl sc, SurfaceControl.Transaction transaction, Bitmap bitmap, int index) {
        return false;
    }

    public static boolean setEdrViewTransform(SurfaceControl sc, SurfaceControl.Transaction transaction, OplusEdrParameters para, int index) {
        return false;
    }

    public static boolean isUHDRSupport() {
        return SystemProperties.getBoolean("persist.sys.feature.uhdr.support", false);
    }

    public static boolean setEdrGainmapInfo(SurfaceControl sc, SurfaceControl.Transaction transaction, OplusSkGainmapInfo info, int index) {
        return false;
    }

    public static boolean setEdrAnimDuration(SurfaceControl sc, SurfaceControl.Transaction transaction, int enterDuration, int exitDuration) {
        return false;
    }

    public static boolean setEdrSdrRatio(SurfaceControl sc, SurfaceControl.Transaction transaction, float edrSdrRatio) {
        return false;
    }
}
