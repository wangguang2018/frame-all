package com.ydd.oms.service.sys;

import com.ydd.framework.core.common.Pagination;
import com.ydd.framework.core.service.BaseService;
import com.ydd.oms.dto.sys.MenuDTO;
import com.ydd.oms.entity.sys.Menu;

import java.util.List;

/**
 * Service - 系统菜单
 *
 * @author Demon
 * @since 2017-10-11
 */
public interface MenuService extends BaseService {

    /**
     * 保存系统菜单
     *
     * @param menu 系统菜单
     */
    void save(Menu menu);

    /**
     * 更新系统菜单
     *
     * @param menu 系统菜单
     */
    void update(Menu menu);

    /**
     * 删除系统菜单
     *
     * @param id 编号
     * @return 删除数量
     */
    Integer delete(Integer id);

    /**
     * 删除系统菜单
     *
     * @param ids 编号数组
     * @return 删除数量
     */
    Integer deleteByIds(Integer[] ids);

    /**
     * 查询系统菜单
     *
     * @param id 编号
     * @return 系统菜单
     */
    Menu findById(Integer id);

    /**
     * 查询系统菜单
     *
     * @param pagination 分页信息
     * @return 分页结果
     */
    Pagination findPage(Pagination pagination);

    /**
     * 更新状态
     *
     * @param ids 编号
     * @param status 状态
     * @return 更新数量
     */
    Integer updateStatus(Integer[] ids, Byte status);

    /**
     * 查询一级菜单
     *
     * @return 一级菜单
     */
    List<Menu> findFirstLevelList();

    /**
     * 查询菜单
     *
     * @param roleId 角色编号
     * @return 菜单
     */
    List<MenuDTO> findByRoleId(Integer roleId);

    /**
     * 查询菜单集合
     *
     * @param adminId 管理员编号
     * @return 菜单集合
     */
    List<MenuDTO> findListByAdminId(Integer adminId);
}

