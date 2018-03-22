package com.ydd.oms.entity.sys;

import java.io.Serializable;
import java.util.Date;

/**
 * Entity - 角色
 *
 * @author Demon
 * @since 2017-10-13
 */
public class Role implements Serializable{


    /**
     * ID
     */
    private Integer id;

    /**
     * 状态：0禁用 1启用
     */
    private Integer status;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 创建者编号
     */
    private Integer createdId;

    /**
     * 创建者编号
     */
    private Integer modifiedId;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 更新时间
     */
    private Date modifiedTime;

    /**
     * 删除标识位: 0正常 1删除
     */
    private Integer deleted;

    /**
     * 人数
     */
    private Integer adminCount;


    /**
     * 设置ID
     *
     * @param id ID
     */
    public void setId(Integer id){
        this.id=id;
    }

    /**
     * 获取ID
     *
     * @return ID
     */
    public Integer getId(){
        return id;
    }


    /**
     * 设置状态：0禁用 1启用
     *
     * @param status 状态：0禁用 1启用
     */
    public void setStatus(Integer status){
        this.status=status;
    }

    /**
     * 获取状态：0禁用 1启用
     *
     * @return 状态：0禁用 1启用
     */
    public Integer getStatus(){
        return status;
    }


    /**
     * 设置角色名称
     *
     * @param name 角色名称
     */
    public void setName(String name){
        this.name=name;
    }

    /**
     * 获取角色名称
     *
     * @return 角色名称
     */
    public String getName(){
        return name;
    }


    /**
     * 设置创建者编号
     *
     * @param createdId 创建者编号
     */
    public void setCreatedId(Integer createdId){
        this.createdId=createdId;
    }

    /**
     * 获取创建者编号
     *
     * @return 创建者编号
     */
    public Integer getCreatedId(){
        return createdId;
    }


    /**
     * 设置创建者编号
     *
     * @param modifiedId 创建者编号
     */
    public void setModifiedId(Integer modifiedId){
        this.modifiedId=modifiedId;
    }

    /**
     * 获取创建者编号
     *
     * @return 创建者编号
     */
    public Integer getModifiedId(){
        return modifiedId;
    }


    /**
     * 设置创建时间
     *
     * @param createdTime 创建时间
     */
    public void setCreatedTime(Date createdTime){
        this.createdTime=createdTime;
    }

    /**
     * 获取创建时间
     *
     * @return 创建时间
     */
    public Date getCreatedTime(){
        return createdTime;
    }


    /**
     * 设置更新时间
     *
     * @param modifiedTime 更新时间
     */
    public void setModifiedTime(Date modifiedTime){
        this.modifiedTime=modifiedTime;
    }

    /**
     * 获取更新时间
     *
     * @return 更新时间
     */
    public Date getModifiedTime(){
        return modifiedTime;
    }


    /**
     * 设置删除标识位: 0正常 1删除
     *
     * @param deleted 删除标识位: 0正常 1删除
     */
    public void setDeleted(Integer deleted){
        this.deleted=deleted;
    }

    /**
     * 获取删除标识位: 0正常 1删除
     *
     * @return 删除标识位: 0正常 1删除
     */
    public Integer getDeleted(){
        return deleted;
    }

    /**
     * 获取人数
     *
     * @return 人数
     */
    public Integer getAdminCount() {
        return adminCount;
    }

    /**
     * 设置人数
     *
     * @param adminCount 人数
     */
    public void setAdminCount(Integer adminCount) {
        this.adminCount = adminCount;
    }
}