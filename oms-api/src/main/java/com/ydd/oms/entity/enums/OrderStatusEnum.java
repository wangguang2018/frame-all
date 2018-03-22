package com.ydd.oms.entity.enums;

/**
 * Enum - 附件类型
 *
 * @author xingkong1221
 * @since 2017-07-06
 */
public enum OrderStatusEnum {

    CLOSED(-2, "已关闭"), RESUED(-1, "已驳回"), READY_PAY(1, "待付款"), READY_AUDIT(2, "待审核"), READY_SEND(3, "待发货"),
    READY_TAKE(4, "待收货"), READY_EVALUATE(5, "待评价"), FINISH(6, "已完成");
    public Byte value;
    public String name;

    OrderStatusEnum(Integer value, String name) {
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
