package com.oplus.graphics;

import android.graphics.Rect;

/* JADX INFO: loaded from: classes.dex */
public interface IOplusOutline {
    default void setSmoothRoundRect(int left, int top, int right, int bottom, float radius, float weight) {
    }

    default void setSmoothRoundRect(int left, int top, int right, int bottom, float radius) {
    }

    default void setSmoothRoundRect(Rect rect, float radius, float weight) {
    }

    default void setSmoothRoundRect(Rect rect, float radius) {
    }
}
