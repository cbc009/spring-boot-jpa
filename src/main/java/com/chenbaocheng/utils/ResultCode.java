package com.chenbaocheng.utils;

/**
 * Created by CBC on 2015-02-03 16:13.
 */
public enum ResultCode {
    SUCCESS(1), FAIL(0);

    int code = 0;

    ResultCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}