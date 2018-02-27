package com.ydd.oms.controller.sys;

import com.ydd.framework.core.common.Pagination;
import com.ydd.framework.core.common.dto.ResponseDTO;
import com.ydd.oms.controller.OmsController;
import com.ydd.oms.entity.sys.Permission;
import com.ydd.oms.service.sys.PermissionService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Controller - 权限点
 *
 * @author Demon
 * @since 2017-10-13
 */
@RestController
public class PermissionController extends OmsController {

	private final Logger logger = LoggerFactory.getLogger(PermissionController.class);
	
	@Resource
	private PermissionService permissionService;

	/**
	 * 分页查询权限点
	 */
	@RequiresPermissions("permission:view")
	@RequestMapping(value = "/permissions", method = RequestMethod.GET)
	public ResponseDTO findPage(Pagination pagination) {
		return ResponseDTO.ok()
				.setPagination(permissionService.findPage(pagination));
	}

	/**
	 * 查询权限点信息
	 */
	@RequiresPermissions("permission:view")
	@RequestMapping(value = "/permission/{id}", method = RequestMethod.GET)
	public ResponseDTO findById(@PathVariable("id") Integer id) {
		return ResponseDTO.ok()
				.addAttribute("permission", permissionService.findById(id));
	}

	/**
	 * 保存权限点
	 */
	@RequiresPermissions("permission:edit")
	@RequestMapping(value = "/permission", method = RequestMethod.POST)
	public ResponseDTO save(Permission permission) {
		permissionService.save(permission);
		return ResponseDTO.ok("保存成功");
	}

	/**
	 * 删除权限点
	 */
	@RequiresPermissions("permission:delete")
	@RequestMapping(value = "/permission/delete", method = RequestMethod.POST)
	public ResponseDTO delete(@RequestParam("id[]") Integer[] ids) {
		permissionService.deleteByIds(ids);
		return ResponseDTO.ok("删除成功");
	}

	/**
	 * 查询权限点是否存在
	 */
	@RequestMapping(value = "/permission/exists/key", method = RequestMethod.GET)
	public ResponseDTO existsKey(String key, Integer excludeId) {
		return ResponseDTO.ok()
				.addAttribute("exists", permissionService.isKeyExists(key, excludeId));
	}

}
