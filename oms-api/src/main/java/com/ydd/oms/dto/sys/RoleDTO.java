package com.ydd.oms.dto.sys;

import java.io.Serializable;

/**
 * DTO - 角色
 *
 * @author Demon
 * @since 18/12/2017
 */
public class RoleDTO implements Serializable {

    private static final long serialVersionUID = -8676309940809932211L;

    /**
     * 角色编号
     */
    private Integer roleId;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 是否选中
     */
    private Boolean checked = Boolean.FALSE;

    /**
     * 获取角色编号
     *
     * @return 角色编号
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     * 设置角色编号
     *
     * @param roleId 角色编号
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    /**
     * 获取角色名称
     *
     * @return 角色名称
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * 设置角色名称
     *
     * @param roleName 角色名称
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    /**
     * 获取选中状态
     *
     * @return 选中状态
     */
    public Boolean getChecked() {
        return checked;
    }

    /**
     * 设置选中状态
     *
     * @param checked 选中状态
     */
    public void setChecked(Boolean checked) {
        this.checked = checked;
    }
}
