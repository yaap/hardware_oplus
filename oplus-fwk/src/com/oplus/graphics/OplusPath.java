package com.oplus.graphics;

import android.graphics.Path;
import android.graphics.RectF;
import android.os.SystemProperties;
import com.oplus.view.OplusSmoothRoundedManager;

/* JADX INFO: loaded from: classes.dex */
public class OplusPath implements IOplusPath {
    private static final int CORNER_TYPE = SystemProperties.getInt("persist.ux.debug.corner_type", 0);
    private static final String TAG = "OplusPath";
    private final boolean mIsSmoothOn = OplusSmoothRoundedManager.isSmoothRadiusOn();
    private final Path mPath;

    public OplusPath(Path path) {
        this.mPath = path;
    }

    @Override // com.oplus.graphics.IOplusPath
    public void addSmoothRoundRect(float left, float top, float right, float bottom, float rx, float ry, float weight, Path.Direction dir) {/*
        if (this.mIsSmoothOn) {
            if (CORNER_TYPE == 0) {
                this.mPath.getWrapper().getPathExt().addSmoothRoundRect(left, top, right, bottom, rx, ry, weight, dir);
                return;
            } else {
                this.mPath.getWrapper().getPathExt().addSmoothRoundRect16(left, top, right, bottom, rx, ry, OplusSmoothRoundedManager.getDefaultG2Weight(), dir);
                return;
            }
        }*/
        float tmpRx = OplusSmoothRoundedManager.reverseNoWeightRadius(rx, weight);
        float tmpRy = OplusSmoothRoundedManager.reverseNoWeightRadius(ry, weight);
        this.mPath.addRoundRect(left, top, right, bottom, tmpRx, tmpRy, dir);
    }

    @Override // com.oplus.graphics.IOplusPath
    public void addSmoothRoundRect(float left, float top, float right, float bottom, float rx, float ry, Path.Direction dir) {/*
        if (this.mIsSmoothOn) {
            if (CORNER_TYPE == 0) {
                this.mPath.getWrapper().getPathExt().addSmoothRoundRect(left, top, right, bottom, rx, ry, dir);
                return;
            } else {
                this.mPath.getWrapper().getPathExt().addSmoothRoundRect16(left, top, right, bottom, rx, ry, OplusSmoothRoundedManager.getDefaultG2Weight(), dir);
                return;
            }
        }*/
        this.mPath.addRoundRect(left, top, right, bottom, rx, ry, dir);
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
        if (this.mIsSmoothOn) {
            if (CORNER_TYPE == 0) {
                this.mPath.getWrapper().getPathExt().addSmoothRoundRect(rect, radii, dir, weight);
                return;
            } else {
                this.mPath.getWrapper().getPathExt().addSmoothRoundRect16(rect, radii, dir, OplusSmoothRoundedManager.getDefaultG2Weight());
                return;
            }
        }*/
        float[] reverseR = OplusSmoothRoundedManager.reverseNoWeightRadius(radii, weight);
        this.mPath.addRoundRect(rect, reverseR, dir);
    }
}
