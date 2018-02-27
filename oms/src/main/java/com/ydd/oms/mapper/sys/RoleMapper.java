package com.ydd.oms.mapper.sys;

import com.ydd.oms.dto.sys.RoleDTO;
import com.ydd.oms.entity.sys.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Mapper - 角色
 *
 * @author Demon
 * @since 2017-10-13
 */
public interface RoleMapper {

    /**
     * 新建角色
     *
     * @param role 角色
     */
    void insert(Role role);

    /**
     * 更新角色
     *
     * @param role 角色
     */
    void update(Role role);

    /**
     * 删除角色
     *
     * @param id 编号
     * @return 删除数量
     */
    int delete(Integer id);

    /**
     * 删除角色
     *
     * @param ids 编号数组
     * @return 删除数量
     */
    int deleteByIds(Integer[] ids);

    /**
     * 查询角色
     *
     * @param id 编号
     * @return 角色
     */
    Role findById(Integer id);

    /**
     * 查询角色
     *
     * @return 角色集合
     */
    List<Role> findAll();

    /**
     * 查询角色
     *
     * @return 角色
     */
    List<Role> findListWithMemberCount();

    /**
     * 查询角色列表
     *
     * @param status 状态
     * @return 角色列表
     */
    List<RoleDTO> findDTOList(@Param("status") Byte status);

    /**
     * 查询角色
     *
     * @param adminId 管理员编号
     * @return 角色
     */
    List<Role> findByAdminId(Integer adminId);

    /**
     * 查询角色名称
     *
     * @param adminId 管理员编号
     * @return 角色名称
     */
    List<String> findRoleNameByAdminId(Integer adminId);

    /**
     * 保存菜单
     *
     * @param roleId 角色编号
     * @param menuIds 菜单编号
     */
    void saveMenu(@Param("roleId") Integer roleId,
                  @Param("menuIds") Integer[] menuIds);

    /**
     * 删除菜单
     *
     * @param roleId 角色编号
     */
    void deleteMenu(Integer roleId);

    /**
     * 保存权限点
     *
     * @param roleId 角色编号
     * @param permissionIds 权限点编号
     */
    void savePermission(@Param("roleId") Integer roleId,
                        @Param("permissionIds") Integer[] permissionIds);

    /**
     * 删除权限点
     *
     * @param roleId 角色编号
     */
    void deletePermission(Integer roleId);

    /**
     * 更新状态
     *
     * @param ids 编号
     * @param status 状态
     * @return 更新数量
     */
    int updateStatus(@Param("ids") Integer[] ids, @Param("status") Byte status);
}