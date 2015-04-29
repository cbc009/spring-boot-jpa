package com.chenbaocheng.enums;

/**
 * Created by CBC on 2015-04-11 15:58.
 */
public enum StatusEnum {

    DEFAULT(0), APPROVED(1), DEL(2);

    private int value;

    StatusEnum(int value) {
        this.value = value;
    }

    public int value() {
        return this.value;
    }
}
