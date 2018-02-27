package com.ydd.oms.service.sys.impl;

import com.ydd.framework.core.service.impl.BaseServiceImpl;
import com.ydd.oms.entity.sys.RoleMenu;
import com.ydd.oms.mapper.sys.RoleMenuMapper;
import com.ydd.oms.service.sys.RoleMenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Service - 角色菜单
 *
 * @author Demon
 * @since 2017-10-17
 */
@Service
@Transactional(readOnly = true)
public class RoleMenuServiceImpl extends BaseServiceImpl implements RoleMenuService {

	private final Logger logger = LoggerFactory.getLogger(RoleMenuServiceImpl.class);

	@Resource
	private RoleMenuMapper roleMenuMapper;

	/**
	 * 创建角色菜单
	 *
	 * @param roleMenu 角色菜单
	 */
	@Override
	@Transactional
    public void save(RoleMenu roleMenu) {
		// 新建
		roleMenuMapper.insert(roleMenu);
	}

	/**
	 * 删除角色菜单
	 *
	 * @param roleId 角色编号
	 * @param menuId 菜单编号
	 * @return 删除数量
	 */
	@Override
	public Integer delete(Integer roleId, Integer menuId) {
		return roleMenuMapper.delete(roleId, menuId);
	}

	/**
	 * 删除角色菜单
	 *
	 * @param roleId 角色编号
	 * @return 删除数量
	 */
	@Override
	public Integer deleteByRoleId(Integer roleId) {
		return roleMenuMapper.deleteByRoleId(roleId);
	}

	/**
	 * 查询角色菜单
	 *
	 * @param roleId 角色编号
	 * @return 角色菜单
	 */
	@Override
	public List<RoleMenu> findByRoleId(Integer roleId) {
		return roleMenuMapper.findByRoleId(roleId);
	}

	/**
	 * 查询菜单编号集合
	 *
	 * @param roleId 角色编号
	 * @return 菜单编号集合
	 */
	@Override
	public List<Integer> findMenuIdListByRoleId(Integer roleId) {
		return roleMenuMapper.findMenuIdListByRoleId(roleId);
	}
}

