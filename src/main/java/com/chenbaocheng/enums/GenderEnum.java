package com.chenbaocheng.enums;

/**
 * Created by CBC on 2015-04-10 16:52.
 */
public enum GenderEnum {
    MALE(0), FEMALE(1);

    private int value;

    GenderEnum(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }
}
