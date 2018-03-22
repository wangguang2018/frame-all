package com.ydd.oms.entity.enums;

public enum CommentTypeEnum {
    ARTICLE(1, "文章"), ANSWER(2, "互动问答"), GOODS(3, "商品评价"),GOODS_REPLY(4, "商品评价回复");

    public Byte value;
    public String name;

    CommentTypeEnum(Integer value, String name) {
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
