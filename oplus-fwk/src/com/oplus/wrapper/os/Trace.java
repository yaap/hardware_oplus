package com.oplus.wrapper.os;

/* loaded from: classes2.dex */
public class Trace {
    public static final long TRACE_TAG_GRAPHICS = getTraceTagGraphics();
    public static final long TRACE_TAG_HAL = getTraceTagHal();
    public static final long TRACE_TAG_ACTIVITY_MANAGER = getTraceTagActivityManager();
    public static final long TRACE_TAG_VIEW = getTraceTagView();

    private static long getTraceTagGraphics() {
        return 2L;
    }

    private static final long getTraceTagHal() {
        return 4L;
    }

    private static final long getTraceTagActivityManager() {
        return 64L;
    }

    private static final long getTraceTagView() {
        return 8L;
    }

    public static void traceBegin(long traceTag, String methodName) {
        android.os.Trace.traceBegin(traceTag, methodName);
    }

    public static void traceEnd(long traceTag) {
        android.os.Trace.traceEnd(traceTag);
    }

    public static void asyncTraceBegin(long traceTag, String methodName, int cookie) {
        android.os.Trace.asyncTraceBegin(traceTag, methodName, cookie);
    }

    public static void asyncTraceEnd(long traceTag, String methodName, int cookie) {
        android.os.Trace.asyncTraceEnd(traceTag, methodName, cookie);
    }

    public static void traceCounter(long traceTag, String counterName, int counterValue) {
        android.os.Trace.traceCounter(traceTag, counterName, counterValue);
    }
}
