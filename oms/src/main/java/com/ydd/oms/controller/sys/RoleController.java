package com.ydd.oms.controller.sys;

import com.ydd.framework.core.common.Pagination;
import com.ydd.framework.core.common.dto.ResponseDTO;
import com.ydd.framework.core.entity.enums.StatusEnum;
import com.ydd.oms.controller.OmsController;
import com.ydd.oms.entity.sys.Role;
import com.ydd.oms.service.sys.MenuService;
import com.ydd.oms.service.sys.RoleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Controller - 角色
 *
 * @author Demon
 * @since 2017-10-13
 */
@RestController
public class RoleController extends OmsController {

	private final Logger logger = LoggerFactory.getLogger(RoleController.class);
	
	@Resource
	private RoleService roleService;

	@Resource
	private MenuService menuService;

	/**
	 * 分页查询角色
	 */
	@RequiresPermissions("role:view")
	@RequestMapping(value = "/roles", method = RequestMethod.GET)
	public ResponseDTO findPage(Pagination pagination) {
		return ResponseDTO.ok()
				.setPagination(roleService.findPage(pagination));
	}

	/**
	 * 查询全部可用角色
	 */
	@GetMapping("/roles/enable")
	public ResponseDTO findList(Integer adminId) {
		return ResponseDTO.ok().addAttribute("roles",
				roleService.findDTOListWithCheckedStatus(adminId, StatusEnum.ENABLE));
	}

	/**
	 * 查询角色信息
	 */
	@RequiresPermissions("role:view")
	@RequestMapping(value = "/role/{id}", method = RequestMethod.GET)
	public ResponseDTO findById(@PathVariable("id") Integer id) {
		return ResponseDTO.ok()
				.addAttribute("role", roleService.findById(id));
	}

	/**
	 * 保存角色
	 */
	@RequiresPermissions("role:edit")
	@RequestMapping(value = "/role", method = RequestMethod.POST)
	public ResponseDTO save(Role role,
							@RequestParam(value = "menuId[]", required = false) Integer[] menuIds,
							@RequestParam(value = "permissionId[]", required = false) Integer[] permissionIds) {
		roleService.save(role, menuIds, permissionIds);
		return ResponseDTO.ok("保存成功");
	}

	/**
	 * 删除角色
	 */
	@RequiresPermissions("role:delete")
	@RequestMapping(value = "/role/delete", method = RequestMethod.POST)
	public ResponseDTO delete(@RequestParam("id[]") Integer[] ids) {
		roleService.deleteByIds(ids);
		return ResponseDTO.ok("删除成功");
	}

	/**
	 * 变更状态
	 */
	@RequiresPermissions("role:edit")
	@RequestMapping(value = "/role/status", method = RequestMethod.POST)
	public ResponseDTO updateStatus(@RequestParam("id[]") Integer[] ids, Byte status) {
		roleService.updateStatus(ids, status);
		String operationName = (StatusEnum.ENABLE.value.equals(status) ? "启用" : "禁用") + "成功";
		return ResponseDTO.ok(operationName);
	}

	/**
	 * 查询角色菜单
	 */
	@RequestMapping(value = "/role/menus", method = RequestMethod.GET)
	public ResponseDTO findMenus(Integer roleId) {
		return ResponseDTO.ok().addAttribute("menus", menuService.findByRoleId(roleId));
	}

}
