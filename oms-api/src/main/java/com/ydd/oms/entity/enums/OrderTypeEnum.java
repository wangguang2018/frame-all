package com.ydd.oms.entity.enums;

/**
 * Enum - 订单类型
 *
 * @author xingkong1221
 * @since 2017-07-06
 */
public enum OrderTypeEnum {

    FIRST_EXTENDER(1, "一级推广商订单"), SECOND_EXTENDER(2, "二级推广商订单"), FARMER(3, "农户订单");
    public Byte value;
    public String name;

    OrderTypeEnum(Integer value, String name) {
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
