package com.cjl.test.utils;

public class ThreadLocalUtil {
    private static final ThreadLocal thread_local = new ThreadLocal();
    public static <T> T get() {
        return (T) thread_local.get();
    }
    public static void set(Object value) {
        thread_local.set(value);
    }
    public static void remove() {
        thread_local.remove();
    }
}
