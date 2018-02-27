package com.ydd.oms.mapper.sys;

import com.ydd.oms.dto.sys.PermissionDTO;
import com.ydd.oms.entity.sys.Permission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Mapper - 权限点
 *
 * @author Demon
 * @since 2017-10-13
 */
public interface PermissionMapper {

    /**
     * 新建权限点
     *
     * @param permission 权限点
     */
    void insert(Permission permission);

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
    int delete(Integer id);

    /**
     * 删除权限点
     *
     * @param ids 编号数组
     * @return 删除数量
     */
    int deleteByIds(Integer[] ids);

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
     * @return 权限点集合
     */
    List<Permission> findAll();

    /**
     * 查询权限点集合
     *
     * @param adminId 管理员编号
     * @return 权限点集合
     */
    List<PermissionDTO> findListByAdminId(Integer adminId);

    /**
     * 查询权限点键
     *
     * @param adminId  管理员编号
     * @return 权限点键
     */
    List<String> findPermissionKeyListByAdminId(Integer adminId);

    /**
     * 查询权限点数量
     *
     * @param key 键
     * @param excludeId 排除编号
     * @return 数量
     */
    Integer countByKeyAndExcludeId(@Param("key") String key, @Param("excludeId") Integer excludeId);
}