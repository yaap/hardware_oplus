package com.oplus.graphics;

import android.graphics.Path;
import android.graphics.RectF;
import com.oplus.view.OplusSmoothRoundedManager;

/* JADX INFO: loaded from: classes.dex */
public class OplusPathAdapter {
    public static final int NEW_PATH_SMOOTH = 1;
    public static final int OLD_PATH_SMOOTH = 0;
    private static final String TAG = "OplusPathAdapter";
    private IOplusPath mPath;

    public OplusPathAdapter(Path path, int styleType) {
        if (styleType == 0) {
            this.mPath = new OplusPath(path);
        } else {
            if (styleType == 1) {
                this.mPath = new OplusPathSmooth(path);
                return;
            }
            throw new IllegalArgumentException("Invalid flag: " + styleType);
        }
    }

    public void addSmoothRoundRect(float left, float top, float right, float bottom, float rx, float ry, float weight, Path.Direction dir) {
        this.mPath.addSmoothRoundRect(left, top, right, bottom, rx, ry, weight, dir);
    }

    public void addSmoothRoundRect(float left, float top, float right, float bottom, float rx, float ry, Path.Direction dir) {
        if (OplusSmoothRoundedManager.isFullyRoundedToCircle(left, top, right, bottom, rx)) {
            addSmoothRoundRect(left, top, right, bottom, rx, ry, 1.0f, dir);
        } else {
            addSmoothRoundRect(left, top, right, bottom, rx, ry, OplusSmoothRoundedManager.getDefaultG2Weight(), dir);
        }
    }

    public void addSmoothRoundRect(RectF rect, float rx, float ry, Path.Direction dir) {
        addSmoothRoundRect(rect.left, rect.top, rect.right, rect.bottom, rx, ry, dir);
    }

    public void addSmoothRoundRect(RectF rect, float rx, float ry, float weight, Path.Direction dir) {
        addSmoothRoundRect(rect.left, rect.top, rect.right, rect.bottom, rx, ry, weight, dir);
    }

    public void addSmoothRoundRect(RectF rect, float[] radii, Path.Direction dir, float weight) {
        this.mPath.addSmoothRoundRect(rect, radii, dir, weight);
    }

    public void addSmoothRoundRect(RectF rect, float[] radii, Path.Direction dir) {
        this.mPath.addSmoothRoundRect(rect, radii, dir);
    }
}
