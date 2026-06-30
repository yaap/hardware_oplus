package com.oplus.view;

import android.os.SystemProperties;
import android.util.MathUtils;
import com.oplus.os.WaveformEffect;

/* JADX INFO: loaded from: classes.dex */
public class OplusSmoothRoundedManager {
    private static final int DEFAULT_SMOOTH_TYPE = 0;
    private static final float EPSILON = 0.01f;
    public static final float FULL_RRECT_G2_WEIGHT = 1.0f;
    private static final int G2_TYPE = 1;
    private static final float LERP_AMOUNT_MIX = 0.0f;
    private static final float LERP_START = 1.33f;
    private static final float LERP_STOP = 1.0f;
    private static final int LIGHT_ANIM = 3;
    private static final float NON_WEIGHT = 2.0f;
    private static final boolean SMOOTH_ON;
    private static final float START_WEIGHT = 0.99f;
    private static final float DEFAULT_G1_WEIGHT = 170 / 100.0f;
    private static final float DEFAULT_G2_WEIGHT = SystemProperties.getInt("persist.sys.oplus.default_g2_weight", WaveformEffect.EFFECT_ALARM_INTERESTING) / 100.0f;
    private static final int ANIM_LEVEL = 3;
    private static final int UPGRADE_ANIM_LEVEL = SystemProperties.getInt("persist.sys.oplus.upgrade_anim_level", 3);

    static {
        SMOOTH_ON = (ANIM_LEVEL < 3 || UPGRADE_ANIM_LEVEL < 3) && DEFAULT_G1_WEIGHT != 2.0f;
    }

    private OplusSmoothRoundedManager() {
    }

    public static float getDefaultWeight() {
        return DEFAULT_G1_WEIGHT;
    }

    public static float getDefaultG2Weight() {
        return DEFAULT_G2_WEIGHT;
    }

    public static int getDefaultSmoothType() {
        return 0;
    }

    public static int getG2CornerType() {
        return 1;
    }

    public static float getNonWight() {
        return 2.0f;
    }

    public static boolean isSmoothRadiusOn() {
        return SMOOTH_ON;
    }

    public static boolean isFullSmoothOn() {
        return SMOOTH_ON;
    }

    public static float reverseNoWeightRadius(float radius, float weight) {
        if (weight >= 0.99f && weight <= 2.0f) {
            float amount = (weight - 0.99f) / 1.01f;
            return MathUtils.lerp(LERP_START, 1.0f, Math.max(0.0f, Math.min(amount, 1.0f))) * radius;
        }
        return radius;
    }

    public static float[] reverseNoWeightRadius(float[] radiusG, float weight) {
        if (radiusG != null && weight >= 0.99f && weight <= 2.0f) {
            float[] revereR = (float[]) radiusG.clone();
            float amount = (weight - 0.99f) / 1.01f;
            for (int i = 0; i < radiusG.length; i++) {
                revereR[i] = radiusG[i] * MathUtils.lerp(LERP_START, 1.0f, Math.max(0.0f, Math.min(amount, 1.0f)));
            }
            return revereR;
        }
        return radiusG;
    }

    public static boolean isFullyRoundedToCircle(float left, float top, float right, float bottom, float radius) {
        float maxRadius = Math.min(Math.abs(left - right), Math.abs(top - bottom)) / 2.0f;
        return radius >= maxRadius;
    }

    public static boolean isFullyRoundedToCircle(float left, float top, float right, float bottom, float[] radii) {
        if (radii == null || radii.length < 8) {
            return false;
        }
        for (int i = 0; i < 8; i += 2) {
            if (Math.abs(radii[i] - radii[0]) > EPSILON || Math.abs(radii[i + 1] - radii[1]) > EPSILON) {
                return false;
            }
        }
        return isFullyRoundedToCircle(left, top, right, bottom, radii[0]);
    }
}
