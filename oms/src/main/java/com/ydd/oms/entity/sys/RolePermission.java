package com.ydd.oms.entity.sys;

import java.io.Serializable;

/**
 * Entity - 角色权限点
 *
 * @author Demon
 * @since 2017-10-17
 */
public class RolePermission implements Serializable {

    private static final long serialVersionUID = 4676644239775309329L;

    /**
     * 角色编号
     */
    private Integer roleId;

    /**
     * 权限点编号
     */
    private Integer permissionId;



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
     * 设置权限点编号
     *
     * @param permissionId 权限点编号
     */
    public void setPermissionId(Integer permissionId){
        this.permissionId = permissionId;
    }

    /**
     * 获取权限点编号
     *
     * @return 权限点编号
     */
    public Integer getPermissionId(){
        return permissionId;
    }

}