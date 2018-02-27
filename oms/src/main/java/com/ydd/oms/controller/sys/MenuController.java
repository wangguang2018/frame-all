package com.ydd.oms.controller.sys;

import com.ydd.framework.core.common.Pagination;
import com.ydd.framework.core.common.dto.ResponseDTO;
import com.ydd.framework.core.entity.enums.StatusEnum;
import com.ydd.oms.controller.OmsController;
import com.ydd.oms.entity.sys.Menu;
import com.ydd.oms.service.sys.MenuService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Controller - 系统菜单
 *
 * @author Demon
 * @since 2017-10-11 11:54
 */
@RestController
public class MenuController extends OmsController {

	private final Logger logger = LoggerFactory.getLogger(MenuController.class);
	
	@Resource
	private MenuService menuService;

	/**
	 * 分页查询系统菜单
	 */
	@RequiresPermissions("menu:view")
	@RequestMapping(value = "/menus", method = RequestMethod.GET)
	public ResponseDTO findPage(Pagination pagination) {
		return ResponseDTO.ok()
				.setPagination(menuService.findPage(pagination));
	}

	/**
	 * 查询系统菜单信息
	 */
	@RequiresPermissions("menu:view")
	@RequestMapping(value = "/menu/{id}", method = RequestMethod.GET)
	public ResponseDTO findById(@PathVariable("id") Integer id) {
		return ResponseDTO.ok()
				.addAttribute("menu", menuService.findById(id));
	}

	/**
	 * 保存系统菜单
	 */
	@RequiresPermissions("menu:edit")
	@RequestMapping(value = "/menu", method = RequestMethod.POST)
	public ResponseDTO save(Menu menu) {
		menuService.save(menu);
		return ResponseDTO.ok("保存成功");
	}

	/**
	 * 删除系统菜单
	 */
	@RequiresPermissions("menu:delete")
	@RequestMapping(value = "/menu/delete", method = RequestMethod.POST)
	public ResponseDTO delete(@RequestParam("id[]") Integer[] ids) {
		menuService.deleteByIds(ids);
		return ResponseDTO.ok("删除成功");
	}

	/**
	 * 变更管理员状态
	 */
	@RequiresPermissions("menu:edit")
	@RequestMapping(value = "/menu/status", method = RequestMethod.POST)
	public ResponseDTO updateStatus(@RequestParam("id[]") Integer[] ids, Byte status) {
		menuService.updateStatus(ids, status);
		String operationName = (StatusEnum.ENABLE.value.equals(status) ? "启用" : "禁用") + "成功";
		return ResponseDTO.ok(operationName);
	}

	/**
	 * 查询一级菜单
	 */
	@RequestMapping(value = "/menu/level/first", method = RequestMethod.GET)
	public ResponseDTO findRootMenus() {
		return ResponseDTO.ok()
				.addAttribute("menus", menuService.findFirstLevelList());
	}

}
