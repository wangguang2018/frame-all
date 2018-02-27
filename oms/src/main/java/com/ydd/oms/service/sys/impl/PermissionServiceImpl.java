package com.ydd.oms.service.sys.impl;

import com.github.pagehelper.PageHelper;
import com.ydd.framework.core.common.Pagination;
import com.ydd.framework.core.common.utils.ValidationUtils;
import com.ydd.framework.core.service.impl.BaseServiceImpl;
import com.ydd.oms.dto.sys.PermissionDTO;
import com.ydd.oms.entity.sys.Permission;
import com.ydd.oms.mapper.sys.PermissionMapper;
import com.ydd.oms.service.sys.PermissionService;
import com.ydd.oms.service.sys.RolePermissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Service - 权限点
 *
 * @author Demon
 * @since 2017-10-13
 */
@Service
@Transactional(readOnly = true)
public class PermissionServiceImpl extends BaseServiceImpl implements PermissionService {

	private final Logger logger = LoggerFactory.getLogger(PermissionServiceImpl.class);

	@Resource
	private PermissionMapper permissionMapper;

	@Resource
	private RolePermissionService rolePermissionService;

	/**
	 * 创建权限点
	 *
	 * @param permission 权限点
	 */
	@Override
	@Transactional
    public void save(Permission permission) {
		if (permission.getId() != null && permission.getId() > 0) {
			// 更新
			permissionMapper.update(permission);
		} else {
			// 新建
			permissionMapper.insert(permission);
		}
	}

	/**
	 * 更新权限点
 	 *
	 * @param permission 权限点
	 */
	@Override
	@Transactional
    public void update(Permission permission) {
		permissionMapper.update(permission);
	}

	/**
	 * 删除权限点
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
		return permissionMapper.delete(id);
	}

	/**
	 * 删除权限点
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
		return permissionMapper.deleteByIds(ids);
	}

	/**
	 * 查询权限点
	 *
	 * @param id 编号
	 * @return 权限点
	 */
	public Permission findById(Integer id) {
		return permissionMapper.findById(id);
	}

	/**
	 * 查询权限点
	 *
	 * @param pagination 分页信息
	 * @return 分页结果
	 */
	@Override
	public Pagination findPage(Pagination pagination) {
		PageHelper.startPage(pagination.getPage(), pagination.getPageSize());
		PageHelper.orderBy("id desc");
		pagination.setQueryResult(permissionMapper.findAll());
		return pagination;
	}

	/**
	 * 查询权限点键
	 *
	 * @param adminId 管理员编号
	 * @return 权限点键
	 */
	@Override
	public List<String> findPermissionKeyListByAdminId(Integer adminId) {
		return permissionMapper.findPermissionKeyListByAdminId(adminId);
	}

	/**
	 * 权限点是否存在
	 *
	 * @param key 权限点
	 * @param excludeId 排除编号
	 * @return 存在返回 {@code true}，反之 {@code false}
	 */
	@Override
	public Boolean isKeyExists(String key, Integer excludeId) {
		return permissionMapper.countByKeyAndExcludeId(key, excludeId) != 0;
	}

	/**
	 * 查询权限点
	 *
	 * @param roleId 角色编号
	 * @return 权限点
	 */
	@Override
	public Map<Integer, List<PermissionDTO>> findMapByRoleId(Integer roleId) {

		// 查询角色权限点
		List<Integer> checkedPermissionIdList = rolePermissionService.findPermissionIdListByRoleId(roleId);

		// 查询全部权限点
		List<Permission> permissionList = permissionMapper.findAll();

		Map<Integer, List<PermissionDTO>> map = new HashMap<>();
		for (Permission permission : permissionList) {
			List<PermissionDTO> cateList = map.get(permission.getMenuId());
			if (cateList == null) {
				cateList = new ArrayList<>();
			}
			PermissionDTO dto = new PermissionDTO();
			BeanUtils.copyProperties(permission, dto);

			dto.setChecked(checkedPermissionIdList.contains(permission.getId()));
			cateList.add(dto);
			map.put(permission.getMenuId(), cateList);
		}

		return map;
	}

	/**
	 * 查询权限点
	 *
	 * @param adminId 管理员编号
	 * @return 权限点
	 */
	@Override
	public List<PermissionDTO> findListByAdminId(Integer adminId) {
		ValidationUtils.assertNotNull(adminId);

		return permissionMapper.findListByAdminId(adminId);
	}
}

