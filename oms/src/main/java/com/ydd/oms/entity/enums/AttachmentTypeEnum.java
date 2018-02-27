package com.ydd.oms.entity.enums;

/**
 * Enum - 附件类型
 *
 * @author xingkong1221
 * @since 2017-07-06
 */
public enum AttachmentTypeEnum {
    UNKNOW(0, "其他"), IMAGE(1, "图片"), VIDEO(2, "视频"), AUDIO(3, "音频"), PACKAGE(4, "压缩包"), WORD(5, "Word"),
    EXCEL(6, "Excel"), PPT(7, "PowerPoint"), TXT(8, "文本文档"),PDF(9, "PDF")
    ;
    public Byte value;
    public String name;

    AttachmentTypeEnum(Integer value, String name) {
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
