package com.ydd.oms.entity.sys;

import java.io.Serializable;
import java.util.Date;

/**
 * Entity - 权限点
 *
 * @author Demon
 * @since 2017-10-13
 */
public class Permission implements Serializable{

    private static final long serialVersionUID = 6283446799819846854L;

    /**
     * ID
     */
    private Integer id;

    /**
     * 权限点名称
     */
    private String name;

    /**
     * 权限点
     */
    private String key;

    /**
     * 菜单编号
     */
    private Integer menuId;

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
     * 设置权限点名称
     *
     * @param name 权限点名称
     */
    public void setName(String name){
        this.name=name;
    }

    /**
     * 获取权限点名称
     *
     * @return 权限点名称
     */
    public String getName(){
        return name;
    }


    /**
     * 设置权限点
     *
     * @param key 权限点
     */
    public void setKey(String key){
        this.key=key;
    }

    /**
     * 获取权限点
     *
     * @return 权限点
     */
    public String getKey(){
        return key;
    }


    /**
     * 设置菜单编号
     *
     * @param menuId 菜单编号
     */
    public void setMenuId(Integer menuId){
        this.menuId=menuId;
    }

    /**
     * 获取菜单编号
     *
     * @return 菜单编号
     */
    public Integer getMenuId(){
        return menuId;
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

}