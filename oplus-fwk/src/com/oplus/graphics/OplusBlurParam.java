package com.oplus.graphics;

import android.os.SystemProperties;
import android.util.Log;
import com.oplus.view.OplusSmoothRoundedManager;
import java.util.Arrays;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class OplusBlurParam {
    private static final int BLUR_ARCYLIC_PARAMS_NUM = 4;
    public static final int BLUR_BLEND_MODE_COLORDODGE = 3;
    public static final int BLUR_BLEND_MODE_COLORMIX = 1;
    public static final int BLUR_BLEND_MODE_DEFAULT = 0;
    public static final int BLUR_BLEND_MODE_GLOW_COLORDODGE = 5;
    public static final int BLUR_BLEND_MODE_GLOW_OVERLAY = 4;
    public static final int BLUR_BLEND_MODE_OVERLAY = 2;
    private static final int BLUR_MATERIAL_PARAMS_NUM = 9;
    private static final int BLUR_MIRROR_PARAMS_NUM = 2;
    private static final int BLUR_OPT_PARAMS_NUM = 1;
    private static final int BLUR_PARAMS_NUM_SUM = 18;
    private static final int BLUR_SMOOTH_CORNER_TYPE_NUM = 1;
    private static final int BLUR_SMOOTH_CORNER_WEIGHT_NUM = 1;
    public static final int BLUR_TILE_MODE_CLAMP = 3;
    public static final int BLUR_TILE_MODE_DECAL = 4;
    public static final int BLUR_TILE_MODE_DEFAULT = 0;
    public static final int BLUR_TILE_MODE_MIRROR = 2;
    public static final int BLUR_TILE_MODE_REPEAT = 1;
    public static final int BLUR_TYPE_DEFAULT = 0;
    public static final int BLUR_TYPE_FAST_KAWASE = 2;
    public static final int BLUR_TYPE_GAUSSIAN = 4;
    public static final int BLUR_TYPE_ORIGINAL = 1;
    public static final int BLUR_TYPE_QUALITY_KAWASE = 3;
    private static final int COLOR_SIZE = 4;
    private static final int CORNER_TYPE = SystemProperties.getInt("persist.ux.debug.corner_type", 0);
    public static final float DEFAULT_SMOOTH_CORNER_WEIGHT = 2.0f;
    private static final String TAG = "OplusBlurParam";
    private int mBlurType = 0;
    private int mTileMode = 0;
    private float mZoomFactor = 1.0f;
    private int mBlendMode = 0;
    private float[] mBlendColor = {0.0f, 0.0f, 0.0f, 0.0f};
    private float[] mMixColor = {0.0f, 0.0f, 0.0f, 0.0f};
    private float[] mBlurColor = {0.0f, 0.0f, 0.0f, 0.0f};
    private float mSmoothCornerWeight = OplusSmoothRoundedManager.getNonWight();
    private int mSmoothCornerType = OplusSmoothRoundedManager.getDefaultSmoothType();

    public void setBlurType(int type) {
        this.mBlurType = type;
    }

    public void setMirrorParams(int mode, float factor) {
        this.mTileMode = mode;
        this.mZoomFactor = factor;
    }

    public void setMaterialParams(int mode, float[] blendC, float[] mixC) {
        this.mBlendMode = mode;
        this.mBlendColor[0] = blendC[0];
        this.mBlendColor[1] = blendC[1];
        this.mBlendColor[2] = blendC[2];
        this.mBlendColor[3] = blendC[3];
        this.mMixColor[0] = mixC[0];
        this.mMixColor[1] = mixC[1];
        this.mMixColor[2] = mixC[2];
        this.mMixColor[3] = mixC[3];
    }

    public void setArcylicParams(float[] arcylicColor) {
        this.mBlurColor[0] = arcylicColor[0];
        this.mBlurColor[1] = arcylicColor[1];
        this.mBlurColor[2] = arcylicColor[2];
        this.mBlurColor[3] = arcylicColor[3];
    }

    public void setSmoothCornerWeight(float weight) {
        if (weight <= 0.0f) {
            Log.e(TAG, "IllegalArgument for setSmoothCornerWeight " + weight);
        } else {
            this.mSmoothCornerWeight = CORNER_TYPE == 0 ? weight : OplusSmoothRoundedManager.getDefaultG2Weight();
            Log.d(TAG, "setSmoothCornerWeight " + weight);
        }
    }

    public float getSmoothCornerWeight() {
        return CORNER_TYPE == 0 ? this.mSmoothCornerWeight : OplusSmoothRoundedManager.getDefaultG2Weight();
    }

    public int getSmoothCornerType() {
        return CORNER_TYPE == 0 ? this.mSmoothCornerType : OplusSmoothRoundedManager.getG2CornerType();
    }

    public void setSmoothCornerType(int smoothCornerType) {
        this.mSmoothCornerType = smoothCornerType;
    }

    public int getParamsSize() {
        return 18;
    }

    public float[] toFloatArray() {
        float[] floatArray = {this.mBlurType, this.mTileMode, this.mZoomFactor, this.mBlendMode, this.mBlendColor[0], this.mBlendColor[1], this.mBlendColor[2], this.mBlendColor[3], this.mMixColor[0], this.mMixColor[1], this.mMixColor[2], this.mMixColor[3], this.mBlurColor[0], this.mBlurColor[1], this.mBlurColor[2], this.mBlurColor[3], this.mSmoothCornerWeight, this.mSmoothCornerType};
        return floatArray;
    }

    public void fromFloatArray(float[] floatArray) {
        if (floatArray.length != 18) {
            Log.w(TAG, "float array size is not equal params num in fromFloatArray");
            return;
        }
        this.mBlurType = Math.round(floatArray[0]);
        this.mTileMode = Math.round(floatArray[1]);
        this.mZoomFactor = floatArray[2];
        this.mBlendMode = Math.round(floatArray[3]);
        this.mBlendColor[0] = floatArray[4];
        this.mBlendColor[1] = floatArray[5];
        this.mBlendColor[2] = floatArray[6];
        this.mBlendColor[3] = floatArray[7];
        this.mMixColor[0] = floatArray[8];
        this.mMixColor[1] = floatArray[9];
        this.mMixColor[2] = floatArray[10];
        this.mMixColor[3] = floatArray[11];
        this.mBlurColor[0] = floatArray[12];
        this.mBlurColor[1] = floatArray[13];
        this.mBlurColor[2] = floatArray[14];
        this.mBlurColor[3] = floatArray[15];
        this.mSmoothCornerWeight = floatArray[16];
        this.mSmoothCornerType = Math.round(floatArray[17]);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof OplusBlurParam)) {
            return false;
        }
        OplusBlurParam paramsObj = (OplusBlurParam) obj;
        return Arrays.equals(toFloatArray(), paramsObj.toFloatArray());
    }

    public int hashCode() {
        return Objects.hash(Integer.valueOf(this.mBlurType), Integer.valueOf(this.mTileMode), Float.valueOf(this.mZoomFactor), Integer.valueOf(this.mBlendMode), this.mBlendColor, this.mMixColor, this.mBlurColor);
    }
}
