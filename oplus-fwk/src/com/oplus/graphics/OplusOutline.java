package com.oplus.graphics;

import android.graphics.Outline;
import android.graphics.Rect;
import android.os.SystemProperties;
import com.oplus.view.OplusSmoothRoundedManager;

/* JADX INFO: loaded from: classes.dex */
public class OplusOutline implements IOplusOutline {
    private static final int CORNER_TYPE = SystemProperties.getInt("persist.ux.debug.corner_type", 0);
    private static final String TAG = "OplusOutline";
    private final boolean mIsSmoothOn = OplusSmoothRoundedManager.isSmoothRadiusOn();
    private final Outline mOutline;

    public OplusOutline(Outline outline) {
        this.mOutline = outline;
    }

    @Override // com.oplus.graphics.IOplusOutline
    public void setSmoothRoundRect(int left, int top, int right, int bottom, float radius, float weight) {/*
        if (this.mIsSmoothOn) {
            if (CORNER_TYPE == 0) {
                this.mOutline.setSmoothRoundRect(left, top, right, bottom, radius, weight);
                return;
            } else {
                this.mOutline.setSmoothRoundRect16(left, top, right, bottom, radius, OplusSmoothRoundedManager.getDefaultG2Weight());
                return;
            }
        }*/
        float tmpRadius = OplusSmoothRoundedManager.reverseNoWeightRadius(radius, weight);
        this.mOutline.setRoundRect(left, top, right, bottom, tmpRadius);
    }

    @Override // com.oplus.graphics.IOplusOutline
    public void setSmoothRoundRect(int left, int top, int right, int bottom, float radius) {/*
        if (this.mIsSmoothOn) {
            if (CORNER_TYPE == 0) {
                this.mOutline.setSmoothRoundRect(left, top, right, bottom, radius);
                return;
            } else {
                this.mOutline.setSmoothRoundRect16(left, top, right, bottom, radius, OplusSmoothRoundedManager.getDefaultG2Weight());
                return;
            }
        }*/
        this.mOutline.setRoundRect(left, top, right, bottom, radius);
    }

    @Override // com.oplus.graphics.IOplusOutline
    public void setSmoothRoundRect(Rect rect, float radius, float weight) {
        setSmoothRoundRect(rect.left, rect.top, rect.right, rect.bottom, radius, weight);
    }

    @Override // com.oplus.graphics.IOplusOutline
    public void setSmoothRoundRect(Rect rect, float radius) {
        setSmoothRoundRect(rect.left, rect.top, rect.right, rect.bottom, radius);
    }
}
