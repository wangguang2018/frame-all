package com.ydd.oms.entity.enums;

/**
 * Enum - 区域等级
 *
 * @author xingkong1221
 * @since 2017-07-06
 */
public enum RegionLevelEnum {
    PROVINCE(1, "省"), CITY(2, "市"), COUNTY(3, "区／县"), TOWN(4, "镇"), VILLAGE(5, "村")
    ;
    public Byte value;
    public String name;

    RegionLevelEnum(Integer value, String name) {
        this.value = value.byteValue();
        this.name = name;
    }

    public Byte getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public static RegionLevelEnum get(Byte level) {
        for (RegionLevelEnum regionLevelEnum : RegionLevelEnum.values()) {
            if (regionLevelEnum.value.equals(level)) {
                return regionLevelEnum;
            }
        }

        return null;
    }
}
