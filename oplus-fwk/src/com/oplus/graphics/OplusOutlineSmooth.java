package com.oplus.graphics;

import android.graphics.Outline;
import android.graphics.Rect;
import com.oplus.view.OplusSmoothRoundedManager;

/* JADX INFO: loaded from: classes.dex */
public class OplusOutlineSmooth implements IOplusOutline {
    private final Outline mOutline;

    public OplusOutlineSmooth(Outline outline) {
        this.mOutline = outline;
    }

    @Override // com.oplus.graphics.IOplusOutline
    public void setSmoothRoundRect(int left, int top, int right, int bottom, float radius, float weight) {/*
        if (OplusSmoothRoundedManager.isFullSmoothOn()) {
            if (OplusSmoothRoundedManager.isFullyRoundedToCircle(left, top, right, bottom, radius)) {
                this.mOutline.setSmoothRoundRect16(left, top, right, bottom, radius, weight);
                return;
            } else {
                this.mOutline.setSmoothRoundRect16(left, top, right, bottom, radius, Math.max(weight, OplusSmoothRoundedManager.getNonWight()));
                return;
            }
        }*/
        this.mOutline.setRoundRect(left, top, right, bottom, radius);
    }

    @Override // com.oplus.graphics.IOplusOutline
    public void setSmoothRoundRect(int left, int top, int right, int bottom, float radius) {
        if (OplusSmoothRoundedManager.isFullyRoundedToCircle(left, top, right, bottom, radius)) {
            setSmoothRoundRect(left, top, right, bottom, radius, 1.0f);
        } else {
            setSmoothRoundRect(left, top, right, bottom, radius, OplusSmoothRoundedManager.getDefaultG2Weight());
        }
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
