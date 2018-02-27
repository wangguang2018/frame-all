package com.ydd.oms.service.sys;

import com.ydd.framework.core.service.BaseService;
import com.ydd.oms.entity.sys.RoleMenu;

import java.util.List;

/**
 * Service - 角色菜单
 *
 * @author Demon
 * @since 2017-10-17
 */
public interface RoleMenuService extends BaseService {

    /**
     * 保存角色菜单
     *
     * @param roleMenu 角色菜单
     */
    void save(RoleMenu roleMenu);

    /**
     * 删除角色菜单
     *
     * @param roleId 角色编号
     * @param menuId 菜单编号
     * @return 删除数量
     */
    Integer delete(Integer roleId, Integer menuId);

    /**
     * 删除角色菜单
     *
     * @param roleId 角色编号
     * @return 删除数量
     */
    Integer deleteByRoleId(Integer roleId);

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

