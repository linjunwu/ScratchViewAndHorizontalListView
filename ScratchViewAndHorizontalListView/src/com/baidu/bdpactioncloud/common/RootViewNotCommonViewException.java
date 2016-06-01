package com.baidu.bdpactioncloud.common;

/**
 * RootViewNotCommonViewException
 *
 * @author linjunwu
 * @since 2016/5/30
 */
public class RootViewNotCommonViewException extends RuntimeException{

    public RootViewNotCommonViewException() {
        this("RootViewNotCommonViewException");
    }

    public RootViewNotCommonViewException(String detailMessage) {
        super("RootViewNotCommonViewException");
    }

    public RootViewNotCommonViewException(String detailMessage, Throwable throwable) {
        super("RootViewNotCommonViewException", throwable);
    }

    public RootViewNotCommonViewException(Throwable throwable) {
        super(throwable);
    }
}
