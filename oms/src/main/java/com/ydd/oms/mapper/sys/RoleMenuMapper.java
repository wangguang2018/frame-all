package com.ydd.oms.mapper.sys;

import com.ydd.oms.entity.sys.RoleMenu;

import java.util.List;

/**
 * Mapper - 角色菜单
 *
 * @author Demon
 * @since 2017-10-17
 */
public interface RoleMenuMapper {

    /**
     * 新建角色菜单
     *
     * @param roleMenu 角色菜单
     */
    void insert(RoleMenu roleMenu);

    /**
     * 删除角色菜单
     *
     * @param roleId 角色编号
     * @param menuId 菜单编号
     * @return 删除数量
     */
    int delete(Integer roleId, Integer menuId);

    /**
     * 删除角色菜单
     *
     * @param roleId 角色编号
     * @return 删除数量
     */
    int deleteByRoleId(Integer roleId);

    /**
     * 查询角色菜单
     *
     * @param roleId 角色编号
     * @return 角色菜单
     */
    List<RoleMenu> findByRoleId(Integer roleId);

    /**
     * 查询菜单编号集合
     *
     * @param roleId 角色编号
     * @return 菜单编号集合
     */
    List<Integer> findMenuIdListByRoleId(Integer roleId);

}