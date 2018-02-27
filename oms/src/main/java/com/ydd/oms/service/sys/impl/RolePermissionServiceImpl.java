package com.ydd.oms.service.sys.impl;

import com.ydd.framework.core.service.impl.BaseServiceImpl;
import com.ydd.oms.entity.sys.RolePermission;
import com.ydd.oms.mapper.sys.RolePermissionMapper;
import com.ydd.oms.service.sys.RolePermissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Service - 角色权限点
 *
 * @author Demon
 * @since 2017-10-17
 */
@Service
@Transactional(readOnly = true)
public class RolePermissionServiceImpl extends BaseServiceImpl implements RolePermissionService {

	private final Logger logger = LoggerFactory.getLogger(RolePermissionServiceImpl.class);

	@Resource
	private RolePermissionMapper rolePermissionMapper;

	/**
	 * 创建角色权限点
	 *
	 * @param rolePermission 角色权限点
	 */
	@Override
	@Transactional
    public void save(RolePermission rolePermission) {
		rolePermissionMapper.insert(rolePermission);
	}

	/**
	 * 删除角色权限点
	 *
	 * @param roleId 角色编号
	 * @param permissionId 权限点编号
   	 * @return 删除数量
	 */
	@Override
	@Transactional
    public Integer delete(Integer roleId, Integer permissionId) {
		return rolePermissionMapper.delete(roleId, permissionId);
	}

	/**
	 * 删除角色权限点
	 *
	 * @param roleId 角色编号
	 * @return 删除数量
	 */
	@Override
	@Transactional
    public Integer deleteByRoleId(Integer roleId) {
		if (roleId == null || roleId <= 0) {
			return 0;
		}
		return rolePermissionMapper.deleteByRoleId(roleId);
	}

	/**
	 * 查询角色权限点
	 *
	 * @param roleId 角色编号
	 * @return 角色权限点
	 */
	@Override
	public List<RolePermission> findByRoleId(Integer roleId) {
		return rolePermissionMapper.findByRoleId(roleId);
	}

	/**
	 * 查询权限点编号集合
	 *
	 * @param roleId 角色编号
	 * @return 权限点编号集合
	 */
	@Override
	public List<Integer> findPermissionIdListByRoleId(Integer roleId) {
		return rolePermissionMapper.findPermissionIdListByRoleId(roleId);
	}
}

