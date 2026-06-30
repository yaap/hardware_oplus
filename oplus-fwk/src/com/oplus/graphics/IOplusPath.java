package com.oplus.graphics;

import android.graphics.Path;
import android.graphics.RectF;

/* JADX INFO: loaded from: classes.dex */
public interface IOplusPath {
    default void addSmoothRoundRect(float left, float top, float right, float bottom, float rx, float ry, float weight, Path.Direction dir) {
    }

    default void addSmoothRoundRect(float left, float top, float right, float bottom, float rx, float ry, Path.Direction dir) {
    }

    default void addSmoothRoundRect(RectF rect, float rx, float ry, Path.Direction dir) {
    }

    default void addSmoothRoundRect(RectF rect, float rx, float ry, float weight, Path.Direction dir) {
    }

    default void addSmoothRoundRect(RectF rect, float[] radii, Path.Direction dir, float weight) {
    }

    default void addSmoothRoundRect(RectF rect, float[] radii, Path.Direction dir) {
    }
}
