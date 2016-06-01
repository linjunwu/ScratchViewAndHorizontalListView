package com.baidu.bdpactioncloud.common;

import java.util.List;

import timber.log.Timber;

/**
 * TimberWrapper
 *
 * @author linjunwu
 * @since 2016/5/24
 */
public class TimberWrapper {

    static {
        Timber.plant(new Timber.DebugTree());
    }

    public static void v(String tag, String message, Object... args) {
        Timber.tag(tag);
        Timber.v(message, args);
    }

    public static void v(String tag, Throwable t, String message, Object... args) {
        Timber.tag(tag);
        Timber.v(t, message, args);
    }

    public static void d(String tag, String message, Object... args) {
        Timber.tag(tag);
        Timber.d(message, args);
    }

    public static void d(String tag, Throwable t, String message, Object... args) {
        Timber.tag(tag);
        Timber.d(t, message, args);
    }

    public static void i(String tag, String message, Object... args) {
        Timber.tag(tag);
        Timber.i(message, args);
    }

    public static void i(String tag, Throwable t, String message, Object... args) {
        Timber.tag(tag);
        Timber.i(t, message, args);
    }

    public static void w(String tag, String message, Object... args) {
        Timber.tag(tag);
        Timber.w(message, args);
    }

    public static void w(String tag, Throwable t, String message, Object... args) {
        Timber.tag(tag);
        Timber.w(t, message, args);
    }

    public static void e(String tag, String message, Object... args) {
        Timber.tag(tag);
        Timber.e(message, args);
    }

    public static void e(String tag, Throwable t, String message, Object... args) {
        Timber.tag(tag);
        Timber.e(t, message, args);
    }

    public static void wtf(String tag, String message, Object... args) {
        Timber.tag(tag);
        Timber.wtf(message, args);
    }

    public static void wtf(String tag, Throwable t, String message, Object... args) {
        Timber.tag(tag);
        Timber.wtf(t, message, args);
    }

    public static void log(String tag, int priority, String message, Object... args) {
        Timber.tag(tag);
        Timber.log(priority, message, args);
    }

    public static void log(String tag, int priority, Throwable t, String message, Object... args) {
        Timber.tag(tag);
        Timber.log(priority, t, message, args);
    }


    public static Timber.Tree asTree() {
        return Timber.asTree();
    }


    public static void plant(Timber.Tree tree) {
        Timber.plant(tree);
    }

    public static void uproot(Timber.Tree tree) {
        Timber.uproot(tree);
    }

    public static void uprootAll() {
        Timber.uprootAll();
    }

    public static List<Timber.Tree> forest() {
        return Timber.forest();
    }

}
