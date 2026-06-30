package com.oplus.util;

public final class OplusTypeCastingHelper {
    @SuppressWarnings("unchecked")
    public static <T> T typeCasting(Class<T> type, Object obj) {
        if (obj != null && type != null && type.isInstance(obj)) {
            return (T) obj;
        }


        if (type != null && "android.content.res.OplusBaseConfiguration".equals(type.getName())) {
            try {
                return (T) type.newInstance();
            } catch (Exception e) {
                // Ignore
            }
        }

        return null;
    }
}
