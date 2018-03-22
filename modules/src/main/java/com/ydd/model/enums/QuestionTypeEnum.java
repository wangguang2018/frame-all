package com.ydd.model.enums;

public enum QuestionTypeEnum {
    CHECKBOX(1, "多选"), RADIO(2, "单选"), TEXT(3, "文本")
    ;
    public Byte value;
    public String name;

    QuestionTypeEnum(Integer value, String name) {
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
