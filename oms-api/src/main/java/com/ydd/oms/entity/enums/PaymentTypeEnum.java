package com.ydd.oms.entity.enums;

/**
 * Enum - 支付类型
 *
 * @author xingkong1221
 * @since 2017-07-06
 */
public enum PaymentTypeEnum {

    ALIPAY(1, "支付宝支付"), WECHAT(2, "微信支付"), UNIONPAY(3, "银联支付"),
    CASH(4, "现金支付"), OFFLINE(5, "线下打款"), LOAN(6, "农资贷");
    public Byte value;
    public String name;

    PaymentTypeEnum(Integer value, String name) {
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
