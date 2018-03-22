package com.ydd.oms.entity.sys;

import java.io.Serializable;

/**
 * Entity - 角色菜单
 *
 * @author Demon
 * @since 2017-10-17
 */
public class RoleMenu implements Serializable {


    /**
     * 角色编号
     */
    private Integer roleId;

    /**
     * 菜单编号
     */
    private Integer menuId;



    /**
     * 设置角色编号
     *
     * @param roleId 角色编号
     */
    public void setRoleId(Integer roleId){
        this.roleId = roleId;
    }

    /**
     * 获取角色编号
     *
     * @return 角色编号
     */
    public Integer getRoleId(){
        return roleId;
    }


    /**
     * 设置菜单编号
     *
     * @param menuId 菜单编号
     */
    public void setMenuId(Integer menuId){
        this.menuId = menuId;
    }

    /**
     * 获取菜单编号
     *
     * @return 菜单编号
     */
    public Integer getMenuId(){
        return menuId;
    }

}