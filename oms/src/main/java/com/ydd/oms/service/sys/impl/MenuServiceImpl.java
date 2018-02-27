package com.ydd.oms.service.sys.impl;

import com.github.pagehelper.PageHelper;
import com.ydd.framework.core.common.Pagination;
import com.ydd.framework.core.common.utils.ValidationUtils;
import com.ydd.framework.core.entity.enums.StatusEnum;
import com.ydd.framework.core.service.impl.BaseServiceImpl;
import com.ydd.oms.dto.sys.MenuDTO;
import com.ydd.oms.dto.sys.PermissionDTO;
import com.ydd.oms.entity.sys.Menu;
import com.ydd.oms.mapper.sys.MenuMapper;
import com.ydd.oms.service.sys.MenuService;
import com.ydd.oms.service.sys.PermissionService;
import com.ydd.oms.service.sys.RoleMenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * Service - 系统菜单
 *
 * @author Demon
 * @since 2017-10-11
 */
@Service
@Transactional(readOnly = true)
public class MenuServiceImpl extends BaseServiceImpl implements MenuService {

	private final Logger logger = LoggerFactory.getLogger(MenuServiceImpl.class);

	@Resource
	private MenuMapper menuMapper;

	@Resource
	private PermissionService permissionService;

	@Resource
	private RoleMenuService roleMenuService;

	/**
	 * 创建系统菜单
	 *
	 * @param menu 系统菜单
	 */
	@Override
	@Transactional
    public void save(Menu menu) {
		if (menu.getId() != null && menu.getId() > 0) {
			// 更新
			menuMapper.update(menu);
		} else {
			// 新建
			menuMapper.insert(menu);
		}
	}

	/**
	 * 更新系统菜单
 	 *
	 * @param menu 系统菜单
	 */
	@Override
	@Transactional
    public void update(Menu menu) {
		menuMapper.update(menu);
	}

	/**
	 * 删除系统菜单
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
		return menuMapper.delete(id);
	}

	/**
	 * 删除系统菜单
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
		return menuMapper.deleteByIds(ids);
	}

	/**
	 * 查询系统菜单
	 *
	 * @param id 编号
	 * @return 系统菜单
	 */
	public Menu findById(Integer id) {
		return menuMapper.findById(id);
	}

	/**
	 * 查询系统菜单
	 *
	 * @param pagination 分页信息
	 * @return 分页结果
	 */
	@Override
	public Pagination findPage(Pagination pagination) {
		PageHelper.startPage(pagination.getPage(), pagination.getPageSize());
		PageHelper.orderBy("sort desc");
		pagination.setQueryResult(menuMapper.findAll());
		return pagination;
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

		return menuMapper.updateStatus(ids, status);
	}

	/**
	 * 查询一级菜单
	 *
	 * @return 一级菜单
	 */
	@Override
	public List<Menu> findFirstLevelList() {
		return menuMapper.findFirstLevelList();
	}

	/**
	 * 查询菜单
	 *
	 * @param roleId 角色编号
	 * @return 菜单
	 */
	@Override
	public List<MenuDTO> findByRoleId(Integer roleId) {

		// 选中菜单编号集合
		List<Integer> checkedMenuIdList = roleMenuService.findMenuIdListByRoleId(roleId);

		// 查询全部权限点
		Map<Integer, List<PermissionDTO>> menuPermissionMap = permissionService.findMapByRoleId(roleId);

		// 查询全部菜单
		List<Menu> menuList = menuMapper.findAll();

		Map<Integer, MenuDTO> parentMenuMap = new HashMap<>();
		Iterator<Menu> iterator =  menuList.iterator();
		while (iterator.hasNext()) {
			Menu menu = iterator.next();
			if (isFirstLevelMenu(menu)) {
				iterator.remove();

				MenuDTO menuDTO = new MenuDTO();
				BeanUtils.copyProperties(menu, menuDTO);
				menuDTO.setChecked(checkedMenuIdList.contains(menuDTO.getId()));
				parentMenuMap.put(menu.getId(), menuDTO);
			}
		}

		for (Menu menu : menuList) {
			MenuDTO menuDTO = new MenuDTO();
			BeanUtils.copyProperties(menu, menuDTO);

			// 设置菜单的权限点
			menuDTO.setPermissionList(menuPermissionMap.get(menuDTO.getId()) == null ? new ArrayList<>() : menuPermissionMap.get(menuDTO.getId()));
			menuDTO.setChecked(checkedMenuIdList.contains(menuDTO.getId()));

			parentMenuMap.get(menu.getParentId()).getChildList().add(menuDTO);
		}


		return new ArrayList<>(parentMenuMap.values());
	}

	/**
	 * 查询菜单集合
	 *
	 * @param adminId 管理员编号
	 * @return 菜单集合
	 */
	@Override
	public List<MenuDTO> findListByAdminId(Integer adminId) {
		ValidationUtils.assertNotNull(adminId);

		// 菜单列表
		List<MenuDTO> menuList = menuMapper.findListByAdminId(adminId);

		// 筛选一级菜单
		Map<Integer, MenuDTO> parentMap = new HashMap<>();
		Iterator<MenuDTO> iterator = menuList.iterator();
		while (iterator.hasNext()) {
			MenuDTO menuDTO = iterator.next();
			if (isFirstLevelMenu(menuDTO)) {
				iterator.remove();

				parentMap.put(menuDTO.getId(), menuDTO);
			}
		}

		for (MenuDTO menuDTO : menuList) {
			parentMap.get(menuDTO.getParentId()).getChildList().add(menuDTO);
		}

		return new ArrayList<>(parentMap.values());
	}

	/**
	 * 判断是否一级菜单
	 *
	 * @param menu 一级菜单
	 * @return 一级菜单返回 {@code true}，反之 {@code false}
	 */
	private Boolean isFirstLevelMenu(Menu menu) {
		return menu.getParentId() == null || menu.getParentId() == 0;
	}

	/**
	 * 判断是否一级菜单
	 *
	 * @param menu 一级菜单
	 * @return 一级菜单返回 {@code true}，反之 {@code false}
	 */
	private Boolean isFirstLevelMenu(MenuDTO menu) {
		return menu.getParentId() == null || menu.getParentId() == 0;
	}
}

