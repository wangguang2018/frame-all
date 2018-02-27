package com.ydd.oms.mapper.sys;

import com.ydd.oms.entity.sys.RolePermission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Mapper - 角色权限点
 *
 * @author Demon
 * @since 2017-10-17
 */
public interface RolePermissionMapper {

    /**
     * 新建角色权限点
     *
     * @param rolePermission 角色权限点
     */
    void insert(RolePermission rolePermission);

    /**
     * 删除角色权限点
     *
     * @param roleId 角色编号
     * @param permissionId 权限点编号
     * @return 删除数量
     */
    int delete(@Param("roleId") Integer roleId, @Param("permissionId") Integer permissionId);

    /**
     * 删除角色权限点
     *
     * @param roleId 角色编号
     * @return 删除数量
     */
    int deleteByRoleId(Integer roleId);

    /**
     * 查询角色权限点
     *
     * @param roleId 角色编号
     * @return 角色权限点
     */
    List<RolePermission> findByRoleId(Integer roleId);

    /**
     * 查询权限点编号集合
     *
     * @param roleId 角色编号
     * @return 权限点编号集合
     */
    List<Integer> findPermissionIdListByRoleId(Integer roleId);

}