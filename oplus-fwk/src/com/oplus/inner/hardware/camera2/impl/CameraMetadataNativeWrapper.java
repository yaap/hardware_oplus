package com.oplus.inner.hardware.camera2.impl;

import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.CaptureResult;
import android.util.Log;
import java.lang.reflect.Field;

public class CameraMetadataNativeWrapper {
    private static final String TAG = "CameraMetadataNativeWrapper";

    public CameraMetadataNativeWrapper() {
    }

    /**
     * Returns the native camera_metadata* of the given CameraCharacteristics / CaptureRequest /
     * CaptureResult, for the OnePlus APS native side.
     *
     * IMPORTANT — lifetime: we return the metadata pointer of the source object's OWN
     * CameraMetadataNative (its mProperties / mLogicalCameraSettings / mResults field), whose
     * native buffer lives exactly as long as that source object (which the caller/SDK keeps
     * alive while APS uses it).
     *
     * We must NOT use getNativeCopy(): that allocates a throwaway CameraMetadataNative copy with
     * no surviving Java reference, so ART's GC frees its native buffer (NativeAllocationRegistry
     * -> free_camera_metadata -> munmap) while APS still holds the raw pointer across its deferred
     * capture job -> use-after-free crash in APSMetadata::copyMetadata (the OnePlus13/dodge photo
     * capture crash). See android_media analysis; confirmed via Frida.
     */
    public static long getMetadataPtr(Object obj) {
        if (obj == null) return 0L;

        try {
            final Object nativeMeta;   // the source object's own CameraMetadataNative
            final String field;
            if (obj instanceof CameraCharacteristics) {
                field = "mProperties";
                Field f = CameraCharacteristics.class.getDeclaredField(field);
                f.setAccessible(true);
                nativeMeta = f.get(obj);
            } else if (obj instanceof CaptureRequest) {
                field = "mLogicalCameraSettings";
                Field f = CaptureRequest.class.getDeclaredField(field);
                f.setAccessible(true);
                nativeMeta = f.get(obj);
            } else if (obj instanceof CaptureResult) {
                field = "mResults";
                Field f = CaptureResult.class.getDeclaredField(field);
                f.setAccessible(true);
                nativeMeta = f.get(obj);
            } else {
                // assume it already IS a CameraMetadataNative
                nativeMeta = obj;
            }

            if (nativeMeta == null) {
                Log.e(TAG, "Unwrapped metadata is null!");
                return 0L;
            }

            Field ptrField = nativeMeta.getClass().getDeclaredField("mMetadataPtr");
            ptrField.setAccessible(true);
            return ptrField.getLong(nativeMeta);

        } catch (Exception e) {
            Log.e(TAG, "Failed to get ptr from " + obj.getClass().getName(), e);
            return 0L;
        }
    }
}
