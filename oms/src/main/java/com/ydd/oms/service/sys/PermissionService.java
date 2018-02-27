package com.ydd.oms.service.sys;

import com.ydd.framework.core.common.Pagination;
import com.ydd.framework.core.service.BaseService;
import com.ydd.oms.dto.sys.PermissionDTO;
import com.ydd.oms.entity.sys.Permission;

import java.util.List;
import java.util.Map;

/**
 * Service - 权限点
 *
 * @author Demon
 * @since 2017-10-13
 */
public interface PermissionService extends BaseService {

    /**
     * 保存权限点
     *
     * @param permission 权限点
     */
    void save(Permission permission);

    /**
     * 更新权限点
     *
     * @param permission 权限点
     */
    void update(Permission permission);

    /**
     * 删除权限点
     *
     * @param id 编号
     * @return 删除数量
     */
    Integer delete(Integer id);

    /**
     * 删除权限点
     *
     * @param ids 编号数组
     * @return 删除数量
     */
    Integer deleteByIds(Integer[] ids);

    /**
     * 查询权限点
     *
     * @param id 编号
     * @return 权限点
     */
    Permission findById(Integer id);

    /**
     * 查询权限点
     *
     * @param pagination 分页信息
     * @return 分页结果
     */
    Pagination findPage(Pagination pagination);

    /**
     * 查询权限点键
     *
     * @param adminId 管理员编号
     * @return 权限点键
     */
    List<String> findPermissionKeyListByAdminId(Integer adminId);

    /**
     * 权限点是否存在
     *
     * @param key 权限点
     * @param excludeId 排除编号
     * @return 存在返回 {@code true}，反之 {@code false}
     */
    Boolean isKeyExists(String key, Integer excludeId);

    /**
     * 查询权限点
     *
     * @param roleId 角色编号
     * @return 权限点
     */
    Map<Integer, List<PermissionDTO>> findMapByRoleId(Integer roleId);

    /**
     * 查询权限点
     *
     * @param adminId 管理员编号
     * @return 权限点
     */
    List<PermissionDTO> findListByAdminId(Integer adminId);
}

