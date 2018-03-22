package com.ydd.oms.entity.enums;

/**
 * Enum - 订单支付状态
 *
 * @author xingkong1221
 * @since 2017-07-06
 */
public enum PayStatusEnum {

    NOT_PAY(0, "未支付"), PAYED(1, "已支付");

    public Byte value;
    public String name;

    PayStatusEnum(Integer value, String name) {
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
