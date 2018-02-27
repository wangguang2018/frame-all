package com.ydd.oms.service.sys;

import com.ydd.framework.core.service.BaseService;
import com.ydd.oms.entity.sys.RolePermission;

import java.util.List;

/**
 * Service - 角色权限点
 *
 * @author Demon
 * @since 2017-10-17
 */
public interface RolePermissionService extends BaseService {

    /**
     * 保存角色权限点
     *
     * @param rolePermission 角色权限点
     */
    void save(RolePermission rolePermission);

    /**
     * 删除角色权限点
     *
     * @param roleId 角色编号
     * @param permissionId 权限点编号
     * @return 删除数量
     */
    Integer delete(Integer roleId, Integer permissionId);

    /**
     * 删除角色权限点
     *
     * @param roleId 角色数组
     * @return 删除数量
     */
    Integer deleteByRoleId(Integer roleId);

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
     * @return 角色权限点
     */
    List<Integer> findPermissionIdListByRoleId(Integer roleId);


}

