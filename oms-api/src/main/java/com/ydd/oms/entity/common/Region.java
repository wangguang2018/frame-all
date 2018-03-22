package com.ydd.oms.entity.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ydd.framework.core.entity.enums.DeletedEnum;
import com.ydd.oms.entity.enums.RegionLevelEnum;

import java.io.Serializable;
import java.util.Date;

/**
 * Entity - 区域
 *
 * @author xingkong1221
 * @since 2017-07-06
 */
public class Region implements Serializable {

    private static final long serialVersionUID = -9152743592669571442L;

    /**
     * 编号
     */
    private Integer id;

    /**
     * 地区代码
     */
    private String code;

    /**
     * 等级
     *
     * @see RegionLevelEnum
     */
    private Byte level;

    /**
     * 名称
     */
    private String name;

    /**
     * 上级编号
     */
    private Integer parentId;

    /**
     * 开放状态
     *
     * @see com.ydd.oms.entity.enums.RegionOpenStatusEnum
     */
    private Byte openStatus;

    /**
     * 创建者编号
     */
    private Integer createdId;

    /**
     * 修改者编号
     */
    private Integer modifiedId;

    /**
     * 创建日期
     */
    private Date createdTime;

    /**
     * 修改日期
     */
    private Date modifiedTime;

    /**
     * 删除标志位
     *
     * @see DeletedEnum
     */
    private Byte deleted = DeletedEnum.NO.value;

    /**
     * 获取编号
     *
     * @return 编号
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置编号
     *
     * @param id 编号
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取地区代码
     *
     * @return 地区代码
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置地区代码
     *
     * @param code 地区代码
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 获取地区等级
     *
     * @see RegionLevelEnum
     * @return 地区等级
     */
    @JsonIgnore
    public Byte getLevel() {
        return level;
    }

    /**
     * 设置地区等级
     *
     * @see RegionLevelEnum
     * @param level 地区等级
     */
    public void setLevel(Byte level) {
        this.level = level;
    }

    /**
     * 获取地区名称
     *
     * @return 地区名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置地区名称
     *
     * @param name 地区名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取上级编号
     *
     * @return 上级编号
     */
    @JsonIgnore
    public Integer getParentId() {
        return parentId;
    }

    /**
     * 设置上级编号
     *
     * @param parentId 上级编号
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取开放状态
     *
     * @return 开放状态
     */
    public Byte getOpenStatus() {
        return openStatus;
    }

    /**
     * 设置开放状态
     *
     * @param openStatus 开放状态
     */
    public void setOpenStatus(Byte openStatus) {
        this.openStatus = openStatus;
    }

    /**
     * 获取创建者编号
     *
     * @return 创建者编号
     */
    @JsonIgnore
    public Integer getCreatedId() {
        return createdId;
    }

    /**
     * 设置创建者编号
     *
     * @param createdId 创建者编号
     */
    public void setCreatedId(Integer createdId) {
        this.createdId = createdId;
    }

    /**
     * 获取修改者编号
     *
     * @return 修改者编号
     */
    @JsonIgnore
    public Integer getModifiedId() {
        return modifiedId;
    }

    /**
     * 设置修改者编号
     *
     * @param modifiedId 修改者编号
     */
    public void setModifiedId(Integer modifiedId) {
        this.modifiedId = modifiedId;
    }

    /**
     * 获取创建时间
     *
     * @return 创建时间
     */
    @JsonIgnore
    public Date getCreatedTime() {
        return createdTime;
    }

    /**
     * 设置创建时间
     *
     * @param createdTime 创建时间
     */
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    /**
     * 获取修改时间
     *
     * @return 修改时间
     */
    @JsonIgnore
    public Date getModifiedTime() {
        return modifiedTime;
    }

    /**
     * 设置修改时间
     *
     * @param modifiedTime 修改时间
     */
    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    /**
     * 获取删除标志位
     *
     * @see DeletedEnum
     * @return 删除标志位
     */
    @JsonIgnore
    public Byte getDeleted() {
        return deleted;
    }

    /**
     * 设置删除标志位
     *
     * @see DeletedEnum
     * @param deleted 删除标志位
     */
    public void setDeleted(Byte deleted) {
        this.deleted = deleted;
    }
}
