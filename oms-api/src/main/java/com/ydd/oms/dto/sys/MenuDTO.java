package com.ydd.oms.dto.sys;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * DTO - 菜单
 *
 * @author xingkong1221
 * @since 2017-10-13
 */
public class MenuDTO implements Serializable {

    private static final long serialVersionUID = -3276079783163701576L;

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
     * 是否选中
     */
    private Boolean checked = Boolean.FALSE;

    /**
     * 子菜单
     */
    private List<MenuDTO> childList = new ArrayList<>();

    /**
     * 权限点
     */
    private List<PermissionDTO> permissionList = new ArrayList<>();


    /**
     * 设置编号
     *
     * @param id 编号
     */
    public void setId(Integer id){
        this.id = id;
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
     * 是否选中
     *
     * @return 是否选中
     */
    public Boolean getChecked() {
        return checked;
    }

    /**
     * 设置是否选中
     *
     * @param checked 是否选中
     */
    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    /**
     * 获取子菜单
     *
     * @return 子菜单
     */
    public List<MenuDTO> getChildList() {
        return childList;
    }

    /**
     * 设置子菜单
     *
     * @param childList 子菜单
     */
    public void setChildList(List<MenuDTO> childList) {
        this.childList = childList;
    }

    /**
     * 获取权限点
     *
     * @return 权限点
     */
    public List<PermissionDTO> getPermissionList() {
        return permissionList;
    }

    /**
     * 设置权限点
     *
     * @param permissionList 权限点
     */
    public void setPermissionList(List<PermissionDTO> permissionList) {
        this.permissionList = permissionList;
    }
}
