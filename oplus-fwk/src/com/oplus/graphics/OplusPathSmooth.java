package com.oplus.graphics;

import android.graphics.Path;
import android.graphics.RectF;
import com.oplus.view.OplusSmoothRoundedManager;

/* JADX INFO: loaded from: classes.dex */
public class OplusPathSmooth implements IOplusPath {
    private final Path mPath;

    public OplusPathSmooth(Path path) {
        this.mPath = path;
    }

    @Override // com.oplus.graphics.IOplusPath
    public void addSmoothRoundRect(float left, float top, float right, float bottom, float rx, float ry, float weight, Path.Direction dir) {/*
        if (OplusSmoothRoundedManager.isFullSmoothOn()) {
            if (OplusSmoothRoundedManager.isFullyRoundedToCircle(left, top, right, bottom, rx)) {
                this.mPath.getWrapper().getPathExt().addSmoothRoundRect16(left, top, right, bottom, rx, ry, weight, dir);
                return;
            } else {
                this.mPath.getWrapper().getPathExt().addSmoothRoundRect16(left, top, right, bottom, rx, ry, Math.max(weight, OplusSmoothRoundedManager.getNonWight()), dir);
                return;
            }
        }*/
        this.mPath.addRoundRect(left, top, right, bottom, rx, ry, dir);
    }

    @Override // com.oplus.graphics.IOplusPath
    public void addSmoothRoundRect(float left, float top, float right, float bottom, float rx, float ry, Path.Direction dir) {
        if (OplusSmoothRoundedManager.isFullyRoundedToCircle(left, top, right, bottom, rx)) {
            addSmoothRoundRect(left, top, right, bottom, rx, ry, 1.0f, dir);
        } else {
            addSmoothRoundRect(left, top, right, bottom, rx, ry, OplusSmoothRoundedManager.getDefaultG2Weight(), dir);
        }
    }

    @Override // com.oplus.graphics.IOplusPath
    public void addSmoothRoundRect(RectF rect, float rx, float ry, Path.Direction dir) {
        addSmoothRoundRect(rect.left, rect.top, rect.right, rect.bottom, rx, ry, dir);
    }

    @Override // com.oplus.graphics.IOplusPath
    public void addSmoothRoundRect(RectF rect, float rx, float ry, float weight, Path.Direction dir) {
        addSmoothRoundRect(rect.left, rect.top, rect.right, rect.bottom, rx, ry, weight, dir);
    }

    @Override // com.oplus.graphics.IOplusPath
    public void addSmoothRoundRect(RectF rect, float[] radii, Path.Direction dir, float weight) {/*
        if (OplusSmoothRoundedManager.isFullSmoothOn()) {
            if (OplusSmoothRoundedManager.isFullyRoundedToCircle(rect.left, rect.top, rect.right, rect.bottom, radii)) {
                this.mPath.getWrapper().getPathExt().addSmoothRoundRect16(rect, radii, dir, weight);
                return;
            } else {
                this.mPath.getWrapper().getPathExt().addSmoothRoundRect16(rect, radii, dir, Math.max(weight, OplusSmoothRoundedManager.getNonWight()));
                return;
            }
        }*/
        this.mPath.addRoundRect(rect, radii, dir);
    }

    @Override // com.oplus.graphics.IOplusPath
    public void addSmoothRoundRect(RectF rect, float[] radii, Path.Direction dir) {
        if (OplusSmoothRoundedManager.isFullyRoundedToCircle(rect.left, rect.top, rect.right, rect.bottom, radii)) {
            addSmoothRoundRect(rect, radii, dir, 1.0f);
        } else {
            addSmoothRoundRect(rect, radii, dir, OplusSmoothRoundedManager.getDefaultG2Weight());
        }
    }
}
