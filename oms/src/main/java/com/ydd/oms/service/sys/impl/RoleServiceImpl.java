package com.ydd.oms.service.sys.impl;

import com.github.pagehelper.PageHelper;
import com.ydd.framework.core.common.Pagination;
import com.ydd.framework.core.common.utils.ValidationUtils;
import com.ydd.framework.core.entity.enums.StatusEnum;
import com.ydd.framework.core.service.impl.BaseServiceImpl;
import com.ydd.oms.dto.sys.RoleDTO;
import com.ydd.oms.entity.sys.Role;
import com.ydd.oms.mapper.sys.RoleMapper;
import com.ydd.oms.service.sys.AdminService;
import com.ydd.oms.service.sys.MenuService;
import com.ydd.oms.service.sys.PermissionService;
import com.ydd.oms.service.sys.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Service - 角色
 *
 * @author Demon
 * @since 2017-10-13
 */
@Service
@Transactional(readOnly = true)
public class RoleServiceImpl extends BaseServiceImpl implements RoleService {

	private final Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);

	@Resource
	private RoleMapper roleMapper;

	@Resource
	private MenuService menuService;

	@Resource
	private PermissionService permissionService;

	@Resource
	private AdminService adminService;

	/**
	 * 创建角色
	 *
	 * @param role 角色
	 * @param menuIds 菜单编号
	 * @param permissionIds 权限点编号
	 */
	@Override
	@Transactional
    public void save(Role role, Integer[] menuIds, Integer[] permissionIds) {
		if (role.getId() != null && role.getId() > 0) {
			// 更新
			roleMapper.update(role);
		} else {
			// 新建
			roleMapper.insert(role);
		}

		// 保存菜单
		roleMapper.deleteMenu(role.getId());
		if (menuIds != null && menuIds.length > 0) {
			roleMapper.saveMenu(role.getId(), menuIds);
		}

		// 保存权限点
		roleMapper.deletePermission(role.getId());
		if (permissionIds != null && permissionIds.length > 0) {
			roleMapper.savePermission(role.getId(), permissionIds);
		}
	}

	/**
	 * 更新角色
 	 *
	 * @param role 角色
	 */
	@Override
	@Transactional
    public void update(Role role) {
		roleMapper.update(role);
	}

	/**
	 * 删除角色
	 *
	 * @param id 编号
   	 * @return 删除数量
	 */
	@Override
	@Transactional
    public Integer delete(Integer id) {
		if (id == null || id <= 0) {
			return 0;
		}
		return roleMapper.delete(id);
	}

	/**
	 * 删除角色
	 *
	 * @param ids 编号数组
	 * @return 删除数量
	 */
	@Override
	@Transactional
    public Integer deleteByIds(Integer[] ids) {
		if (ids == null || ids.length == 0) {
			return 0;
		}
		return roleMapper.deleteByIds(ids);
	}

	/**
	 * 查询角色
	 *
	 * @param id 编号
	 * @return 角色
	 */
	public Role findById(Integer id) {
		return roleMapper.findById(id);
	}

	/**
	 * 查询角色
	 *
	 * @param pagination 分页信息
	 * @return 分页结果
	 */
	@Override
	public Pagination findPage(Pagination pagination) {
		PageHelper.startPage(pagination.getPage(), pagination.getPageSize());
		PageHelper.orderBy("r.id desc");
		pagination.setQueryResult(roleMapper.findListWithMemberCount());
		return pagination;
	}

	/**
	 * 查询角色列表
	 *
	 * @param status 状态
	 * @return 角色列表
	 */
	@Override
	public List<RoleDTO> findDTOList(StatusEnum status) {
		return roleMapper.findDTOList(status.value);
	}

	/**
	 * 查询角色列表
	 *
	 * @param adminId 管理员编号
	 * @param status 状态
	 * @return 角色列表
	 */
	@Override
	public List<RoleDTO> findDTOListWithCheckedStatus(Integer adminId, StatusEnum status) {
		ValidationUtils.assertNotNull(status);

		List<RoleDTO> roleList = findDTOList(status);

		if (adminId != null && adminId > 0) {
			List<Integer> roleIdList = adminService.findRoleIdList(adminId);
			for (RoleDTO role : roleList) {
				role.setChecked(roleIdList.contains(role.getRoleId()));
			}
		}

		return roleList;
	}

	/**
	 * 查询角色
	 *
	 * @param adminId 管理员编号
	 * @return 角色
	 */
	@Override
	public List<Role> findByAdminId(Integer adminId) {
		return roleMapper.findByAdminId(adminId);
	}

	/**
	 * 查询角色名称
	 *
	 * @param adminId 管理员编号
	 * @return 角色名称
	 */
	@Override
	public List<String> findRoleNameByAdminId(Integer adminId) {
		return roleMapper.findRoleNameByAdminId(adminId);
	}

	/**
	 * 更新状态
	 *
	 * @param ids 编号
	 * @param status 状态
	 * @return 更新数量
	 */
	@Override
	@Transactional
	public Integer updateStatus(Integer[] ids, Byte status) {
		if (ids == null) {
			return 0;
		}
		if (status == null) {
			status = StatusEnum.DISABLE.value;
		}

		return roleMapper.updateStatus(ids, status);
	}
}

