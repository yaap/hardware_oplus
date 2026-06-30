package com.oplus.wrapper.view;

import android.graphics.HardwareRenderer;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceControl;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ViewRootImpl {
    private static final String TAG = "ViewRootImpl";
    private final Map<SurfaceChangedCallback, android.view.ViewRootImpl.SurfaceChangedCallback> mCallBackMap = new ConcurrentHashMap();
    private final android.view.ViewRootImpl mViewRootImpl;

    public interface SurfaceChangedCallback {
        void surfaceCreated(SurfaceControl.Transaction transaction);

        void surfaceDestroyed();

        void surfaceReplaced(SurfaceControl.Transaction transaction);
    }

    ViewRootImpl(android.view.ViewRootImpl viewRoot) {
        this.mViewRootImpl = viewRoot;
    }

    public void addSurfaceChangedCallback(final SurfaceChangedCallback changedCallback) {
        if (changedCallback == null) {
            return;
        }
        SurfaceChangedCallbackProxy proxy = new SurfaceChangedCallbackProxy(changedCallback);
        this.mCallBackMap.put(changedCallback, proxy);
        this.mViewRootImpl.addSurfaceChangedCallback(proxy);
    }

    public void removeSurfaceChangedCallback(SurfaceChangedCallback changedCallback) {
        if (changedCallback == null) {
            Log.w(TAG, "removeSurfaceChangedCallback SurfaceChangedCallback is null");
            return;
        }
        android.view.ViewRootImpl.SurfaceChangedCallback proxy = this.mCallBackMap.get(changedCallback);
        if (proxy == null) {
            return;
        }
        this.mViewRootImpl.removeSurfaceChangedCallback(proxy);
        this.mCallBackMap.remove(changedCallback);
    }

    public Drawable createBackgroundBlurDrawable() {
        return this.mViewRootImpl.createBackgroundBlurDrawable();
    }

    public void mergeWithNextTransaction(SurfaceControl.Transaction t, long frameNumber) {
        this.mViewRootImpl.mergeWithNextTransaction(t, frameNumber);
    }

    public android.view.SurfaceControl getSurfaceControl() {
        return this.mViewRootImpl.getSurfaceControl();
    }

    public android.view.View getView() {
        if (this.mViewRootImpl == null) {
            return null;
        }
        return this.mViewRootImpl.getView();
    }

    public void registerRtFrameCallback(HardwareRenderer.FrameDrawingCallback callback) {
        FrameDrawingCallbackImpl frameDrawingCallback = new FrameDrawingCallbackImpl(callback);
        this.mViewRootImpl.registerRtFrameCallback(frameDrawingCallback);
    }

    public static void setCustomVriFlags(android.view.ViewRootImpl vri, Bundle bundle) {
        // Oplus extension not available on custom ROM, no-op
    }

    public void setCustomVriFlags(Bundle bundle) {
        // Oplus extension not available on custom ROM, no-op
    }

    private static class SurfaceChangedCallbackProxy
            implements android.view.ViewRootImpl.SurfaceChangedCallback {
        private final SurfaceChangedCallback mCallback;

        SurfaceChangedCallbackProxy(SurfaceChangedCallback callback) {
            this.mCallback = callback;
        }

        @Override
        public void surfaceCreated(SurfaceControl.Transaction t) {
            mCallback.surfaceCreated(t);
        }

        @Override
        public void surfaceReplaced(SurfaceControl.Transaction t) {
            mCallback.surfaceReplaced(t);
        }

        @Override
        public void surfaceDestroyed() {
            mCallback.surfaceDestroyed();
        }
    }

    private static class FrameDrawingCallbackImpl implements HardwareRenderer.FrameDrawingCallback {
        private final HardwareRenderer.FrameDrawingCallback mFrameDrawingCallback;

        FrameDrawingCallbackImpl(HardwareRenderer.FrameDrawingCallback frameDrawingCallback) {
            this.mFrameDrawingCallback = frameDrawingCallback;
        }

        @Override
        public void onFrameDraw(long l) {
            this.mFrameDrawingCallback.onFrameDraw(l);
        }
    }
}
