package com.ydd.oms.entity.sys;

import java.io.Serializable;
import java.util.Date;

/**
 * Entity - 系统菜单
 *
 * @author Demon
 * @since 2017-10-11
 */
public class Menu implements Serializable{

    private static final long serialVersionUID = -9070562900901108707L;

    /**
     * 编号
     */
    private Integer id;

    /**
     * 状态：0禁用 1启用
     *
     * @see com.ydd.framework.core.entity.enums.StatusEnum
     */
    private Integer status;

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 地址
     */
    private String url;

    /**
     * 菜单图标
     */
    private String icon;

    /**
     * 上级菜单编号
     */
    private Integer parentId;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 备注说明
     */
    private String remark;

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
     *
     * @see com.ydd.framework.core.entity.enums.DeletedEnum
     */
    private Integer deleted;


    /**
     * 设置编号
     *
     * @param id 编号
     */
    public void setId(Integer id){
        this.id=id;
    }

    /**
     * 获取编号
     *
     * @return 编号
     */
    public Integer getId(){
        return id;
    }


    /**
     * 设置状态：0禁用 1启用
     *
     * @see com.ydd.framework.core.entity.enums.StatusEnum
     * @param status 状态：0禁用 1启用
     */
    public void setStatus(Integer status){
        this.status=status;
    }

    /**
     * 获取状态：0禁用 1启用
     *
     * @see com.ydd.framework.core.entity.enums.StatusEnum
     * @return 状态：0禁用 1启用
     */
    public Integer getStatus(){
        return status;
    }


    /**
     * 设置菜单名称
     *
     * @param name 菜单名称
     */
    public void setName(String name){
        this.name=name;
    }

    /**
     * 获取菜单名称
     *
     * @return 菜单名称
     */
    public String getName(){
        return name;
    }


    /**
     * 设置地址
     *
     * @param url 地址
     */
    public void setUrl(String url){
        this.url=url;
    }

    /**
     * 获取地址
     *
     * @return 地址
     */
    public String getUrl(){
        return url;
    }


    /**
     * 设置菜单图标
     *
     * @param icon 菜单图标
     */
    public void setIcon(String icon){
        this.icon=icon;
    }

    /**
     * 获取菜单图标
     *
     * @return 菜单图标
     */
    public String getIcon(){
        return icon;
    }


    /**
     * 设置上级菜单编号
     *
     * @param parentId 上级菜单编号
     */
    public void setParentId(Integer parentId){
        this.parentId=parentId;
    }

    /**
     * 获取上级菜单编号
     *
     * @return 上级菜单编号
     */
    public Integer getParentId(){
        return parentId;
    }


    /**
     * 设置排序
     *
     * @param sort 排序
     */
    public void setSort(Integer sort){
        this.sort=sort;
    }

    /**
     * 获取排序
     *
     * @return 排序
     */
    public Integer getSort(){
        return sort;
    }


    /**
     * 设置备注说明
     *
     * @param remark 备注说明
     */
    public void setRemark(String remark){
        this.remark=remark;
    }

    /**
     * 获取备注说明
     *
     * @return 备注说明
     */
    public String getRemark(){
        return remark;
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
     * @see com.ydd.framework.core.entity.enums.DeletedEnum
     * @param deleted 删除标识位: 0正常 1删除
     */
    public void setDeleted(Integer deleted){
        this.deleted=deleted;
    }

    /**
     * 获取删除标识位: 0正常 1删除
     *
     * @see com.ydd.framework.core.entity.enums.DeletedEnum
     * @return 删除标识位: 0正常 1删除
     */
    public Integer getDeleted(){
        return deleted;
    }

}