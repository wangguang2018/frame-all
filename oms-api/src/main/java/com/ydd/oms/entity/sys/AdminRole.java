package com.ydd.oms.entity.sys;


/**
 * Entity - 管理员角色
 *
 * @author xingkong1221
 * @since 2017-09-26
 */
public class AdminRole {

    /**
     * 管理员编号
     */
    private Integer adminId;

    /**
     * 角色编号
     */
    private Integer roleId;

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}