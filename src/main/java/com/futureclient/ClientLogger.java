package com.futureclient;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Simple logger used by FutureClient - minimal overhead.
 */
public final class ClientLogger {
    private static final SimpleDateFormat fmt = new SimpleDateFormat("HH:mm:ss");

    public static void info(String msg) {
        System.out.println("[FutureClient] [" + fmt.format(new Date()) + "] INFO: " + msg);
    }

    public static void warn(String msg) {
        System.out.println("[FutureClient] [" + fmt.format(new Date()) + "] WARN: " + msg);
    }

    public static void error(String msg, Throwable t) {
        System.err.println("[FutureClient] [" + fmt.format(new Date()) + "] ERROR: " + msg);
        if (t != null) t.printStackTrace();
    }
}