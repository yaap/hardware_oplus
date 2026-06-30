package com.oplus.view;

import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.view.ViewRootImpl;
import com.android.internal.graphics.drawable.BackgroundBlurDrawable;
import com.oplus.graphics.OplusBlurParam;

/* Stub implementation - OplusBlurParam not available in AOSP/LineageOS */
public class ViewRootManager {
    private static final String TAG = "ViewRootManager";
    private BackgroundBlurDrawable mBackgroundBlurDrawable;

    public ViewRootManager(View view) {
        this.mBackgroundBlurDrawable = null;
        ViewRootImpl viewRootImpl = view.getViewRootImpl();
        if (viewRootImpl != null) {
            this.mBackgroundBlurDrawable = viewRootImpl.createBackgroundBlurDrawable();
        } else {
            Log.d(TAG, "viewRootImpl is null return null");
        }
    }

    public Drawable getBackgroundBlurDrawable() {
        return this.mBackgroundBlurDrawable;
    }

    public void setBlurRadius(int blurRadius) {
        if (this.mBackgroundBlurDrawable == null) {
            Log.d(TAG, "BackgroundBlurDrawable is null return null");
            return;
        }
        this.mBackgroundBlurDrawable.setBlurRadius(blurRadius);
    }

    public void setCornerRadius(float cornerRadius) {
        if (this.mBackgroundBlurDrawable == null) {
            Log.d(TAG, "BackgroundBlurDrawable is null return null");
            return;
        }
        this.mBackgroundBlurDrawable.setCornerRadius(cornerRadius);
    }

    public void setCornerRadius(float cornerRadiusTL, float cornerRadiusTR,
            float cornerRadiusBL, float cornerRadiusBR) {
        if (this.mBackgroundBlurDrawable == null) {
            Log.d(TAG, "BackgroundBlurDrawable is null return null by four");
            return;
        }
        this.mBackgroundBlurDrawable.setCornerRadius(
                cornerRadiusTL, cornerRadiusTR, cornerRadiusBL, cornerRadiusBR);
    }

    public void setColor(int color) {
        if (this.mBackgroundBlurDrawable == null) {
            Log.d(TAG, "BackgroundBlurDrawable is null return null");
            return;
        }
        this.mBackgroundBlurDrawable.setColor(color);
    }

    /** Stub - OplusBlurParam not available on AOSP/LineageOS */
    public void setBlurParams(Object params) {
        // no-op
    }

    public void setBlurParams(OplusBlurParam params) {
    }
}
