package com.quzhao.Util;

import android.util.Log;

import java.util.Locale;

/**
 * Created by Administrator on 2016/12/13.
 */
public class LogUtil {
    private static boolean isDebug=true;

    public static void setIsDebug(boolean isDebug) {
        LogUtil.isDebug = isDebug;
    }

    private static boolean LOGV = isDebug;
    private static boolean LOGD = isDebug;
    private static boolean LOGI = isDebug;
    private static boolean LOGW = isDebug;
    private static boolean LOGE = isDebug;

    public static void v(String mess) {
        if (LOGV) {
            Log.v(getTag(), buildMessage(mess));
        }
    }

    public static void d(String mess) {
        if (LOGD) {
            Log.d(getTag(), buildMessage(mess));
        }
    }

    public static void i(String mess) {
        if (LOGI) {
            Log.i(getTag(), buildMessage(mess));
        }
    }

    public static void w(String mess) {
        if (LOGW) {
            Log.w(getTag(), buildMessage(mess));
        }
    }

    public static void e(String mess) {
        if (LOGE) {
            Log.e(getTag(), buildMessage(mess));
        }
    }

    private static String getTag() {
        StackTraceElement[] trace = new Throwable().fillInStackTrace()
                .getStackTrace();
        String callingClass = "";
        for (int i = 2; i < trace.length; i++) {
            Class<?> clazz = trace[i].getClass();
            if (!clazz.equals(LogUtil.class)) {
                callingClass = trace[i].getClassName();
                callingClass = callingClass.substring(callingClass
                        .lastIndexOf('.') + 1);
                break;
            }
        }
        return callingClass;
    }

    private static String buildMessage(String msg) {
        StackTraceElement[] trace = new Throwable().fillInStackTrace()
                .getStackTrace();
        String caller = "";
        for (int i = 2; i < trace.length; i++) {
            Class<?> clazz = trace[i].getClass();
            if (!clazz.equals(LogUtil.class)) {
                caller = trace[i].getMethodName();
                break;
            }
        }
        return String.format(Locale.US, "[%d] %s: %s", Thread.currentThread()
                .getId(), caller, msg);
    }
}
