package com.ydd.oms.controller.sys;

import com.ydd.framework.core.common.Pagination;
import com.ydd.framework.core.common.dto.ResponseDTO;
import com.ydd.framework.core.entity.enums.StatusEnum;
import com.ydd.oms.controller.OmsController;
import com.ydd.oms.entity.sys.Admin;
import com.ydd.oms.service.sys.AdminService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Controller - 管理员
 *
 * @author xingkong1221
 * @since 2017-09-28
 */
@RestController
public class AdminController extends OmsController {

    @Resource
    private AdminService adminService;

    /**
     * 查询登录管理员信息
     */
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public ResponseDTO findLoginAdmin() {
        return ResponseDTO.ok()
                .addAttribute("admin", adminService.findById(getLoginAdmin().getId()));
    }

    /**
     * 查询管理员信息
     */
    @RequiresPermissions("admin:view")
    @RequestMapping(value = "/admin/{id}", method = RequestMethod.GET)
    public ResponseDTO findById(@PathVariable("id") Integer id) {
        return ResponseDTO.ok()
                .addAttribute("admin", adminService.findById(id));
    }

    /**
     * 分页查询管理员信息
     */
    @RequiresPermissions("admin:view")
    @RequestMapping(value = "/admins", method = RequestMethod.GET)
    public ResponseDTO pageAdmin(Pagination pagination) {
        return ResponseDTO.ok()
                .setPagination(adminService.findPage(pagination));
    }

    /**
     * 保存管理员信息
     */
    @RequiresPermissions("admin:edit")
    @RequestMapping(value = "/admin", method = RequestMethod.POST)
    public ResponseDTO save(Admin admin, @RequestParam(value = "roleIds[]", required = false) Integer[] roleIds) {
        adminService.save(admin, roleIds);
        return ResponseDTO.ok("保存成功");
    }

    /**
     * 变更管理员状态
     */
    @RequiresPermissions("admin:edit")
    @RequestMapping(value = "/admin/status", method = RequestMethod.POST)
    public ResponseDTO updateStatus(@RequestParam("id[]") Integer[] ids, Byte status) {
        adminService.updateStatus(ids, status);
        String operationName = (StatusEnum.ENABLE.value.equals(status) ? "启用" : "禁用") + "成功";
        return ResponseDTO.ok(operationName);
    }

    /**
     * 删除管理员
     */
    @RequiresPermissions("admin:delete")
    @RequestMapping(value = "/admin/delete", method = RequestMethod.POST)
    public ResponseDTO delete(@RequestParam("id[]") Integer[] ids) {
        adminService.delete(ids);
        return ResponseDTO.ok("删除成功");
    }

    /**
     * 查询手机号码是否存在
     */
    @RequestMapping(value = "/admin/exists/mobile", method = RequestMethod.GET)
    public ResponseDTO isMobileExists(String mobile, Integer excludeId) {
        return ResponseDTO.ok()
                .addAttribute("exists", adminService.isMobileExists(mobile, excludeId));
    }

    /**
     * 查询手机号码是否存在
     */
    @RequestMapping(value = "/admin/exists/email", method = RequestMethod.GET)
    public ResponseDTO isEmailExists(String email, Integer excludeId) {
        return ResponseDTO.ok()
                .addAttribute("exists", adminService.isEmailExists(email, excludeId));
    }

}
