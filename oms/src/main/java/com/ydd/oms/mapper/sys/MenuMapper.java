package com.ydd.oms.mapper.sys;

import com.ydd.oms.dto.sys.MenuDTO;
import com.ydd.oms.entity.sys.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Mapper - 系统菜单
 *
 * @author Demon
 * @since 2017-10-11
 */
public interface MenuMapper {

    /**
     * 新建系统菜单
     *
     * @param menu 系统菜单
     */
    void insert(Menu menu);

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
    int delete(Integer id);

    /**
     * 删除系统菜单
     *
     * @param ids 编号数组
     * @return 删除数量
     */
    int deleteByIds(Integer[] ids);

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
     * @return 系统菜单集合
     */
    List<Menu> findAll();

    /**
     * 更新状态
     *
     * @param ids 编号
     * @param status 状态
     * @return 更新数量
     */
    int updateStatus(@Param("ids") Integer[] ids, @Param("status") Byte status);

    /**
     * 查询一级菜单
     *
     * @return 一级菜单
     */
    List<Menu> findFirstLevelList();

    /**
     * 查询菜单集合
     *
     * @param adminId 管理员编号
     * @return 菜单集合
     */
    List<MenuDTO> findListByAdminId(Integer adminId);
}