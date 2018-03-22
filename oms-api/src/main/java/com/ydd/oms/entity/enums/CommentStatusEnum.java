package com.ydd.oms.entity.enums;

public enum CommentStatusEnum {
    SHOW(1, "显示"), HIDE(0, "隐藏");

    public Byte value;
    public String name;

    CommentStatusEnum(Integer value, String name) {
        this.value = value.byteValue();
        this.name = name;
    }

    public Byte getValue() {
        return value;
    }

    public String getName() {
        return name;
    }
}
