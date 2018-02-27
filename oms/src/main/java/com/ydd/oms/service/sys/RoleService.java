package com.ydd.oms.service.sys;

import com.ydd.framework.core.common.Pagination;
import com.ydd.framework.core.entity.enums.StatusEnum;
import com.ydd.framework.core.service.BaseService;
import com.ydd.oms.dto.sys.RoleDTO;
import com.ydd.oms.entity.sys.Role;

import java.util.List;

/**
 * Service - 角色
 *
 * @author Demon
 * @since 2017-10-13
 */
public interface RoleService extends BaseService {

    /**
     * 保存角色
     *
     * @param role 角色
     * @param menuIds 菜单编号
     * @param permissionIds 权限点编号
     */
    void save(Role role, Integer[] menuIds, Integer[] permissionIds);

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
    Integer delete(Integer id);

    /**
     * 删除角色
     *
     * @param ids 编号数组
     * @return 删除数量
     */
    Integer deleteByIds(Integer[] ids);

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
     * @param pagination 分页信息
     * @return 分页结果
     */
    Pagination findPage(Pagination pagination);

    /**
     * 查询角色列表
     *
     * @param status 状态
     * @return 角色列表
     */
    List<RoleDTO> findDTOList(StatusEnum status);

    /**
     * 查询角色列表
     *
     * @param adminId 管理员编号
     * @param status 状态
     * @return 角色列表
     */
    List<RoleDTO> findDTOListWithCheckedStatus(Integer adminId, StatusEnum status);

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
     * 更新状态
     *
     * @param ids 编号
     * @param status 状态
     * @return 更新数量
     */
    Integer updateStatus(Integer[] ids, Byte status);
}

