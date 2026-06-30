package com.oplus.graphics;

import android.graphics.Outline;
import android.graphics.Rect;

/* JADX INFO: loaded from: classes.dex */
public class OplusOutlineAdapter {
    public static final int NEW_OUTLINE_SMOOTH = 1;
    public static final int OLD_OUTLINE_SMOOTH = 0;
    private static final String TAG = "OplusOutlineAdapter";
    private IOplusOutline mOutline;

    public OplusOutlineAdapter(Outline outline, int styleType) {
        if (styleType == 0) {
            this.mOutline = new OplusOutline(outline);
        } else {
            if (styleType == 1) {
                this.mOutline = new OplusOutlineSmooth(outline);
                return;
            }
            throw new IllegalArgumentException("Invalid flag: " + styleType);
        }
    }

    public void setSmoothRoundRect(int left, int top, int right, int bottom, float radius, float weight) {
        this.mOutline.setSmoothRoundRect(left, top, right, bottom, radius, weight);
    }

    public void setSmoothRoundRect(int left, int top, int right, int bottom, float radius) {
        this.mOutline.setSmoothRoundRect(left, top, right, bottom, radius);
    }

    public void setSmoothRoundRect(Rect rect, float radius, float weight) {
        this.mOutline.setSmoothRoundRect(rect, radius, weight);
    }

    public void setSmoothRoundRect(Rect rect, float radius) {
        this.mOutline.setSmoothRoundRect(rect, radius);
    }
}
