package com.ydd.oms.entity.enums;

/**
 * 地区开放状态
 *
 * @author Demon
 * @since 26/01/2018
 */
public enum RegionOpenStatusEnum {
    CLOSE(0, "关闭"), OPEN(1, "开放")
    ;
    public Byte value;
    public String name;

    RegionOpenStatusEnum(Integer value, String name) {
        this.value = value.byteValue();
        this.name = name;
    }

    public Byte getValue() {
        return value;
    }

    public void setValue(Byte value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
