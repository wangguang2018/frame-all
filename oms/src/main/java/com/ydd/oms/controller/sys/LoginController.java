package com.ydd.oms.controller.sys;

import com.ydd.framework.core.common.dto.ResponseDTO;
import com.ydd.framework.core.controller.BaseController;
import com.ydd.oms.common.exception.OmsExceptionCodeTemplate;
import com.ydd.oms.service.sys.AdminService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Controller - 登录
 *
 * @author xingkong1221
 * @since 2017-09-26
 */
@RestController
public class LoginController extends BaseController {

    @Resource
    private AdminService adminService;

    /**
     * 未登录
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ResponseDTO login() {
        return new ResponseDTO(OmsExceptionCodeTemplate.LOGIN_STATUS_EXPIRED);
    }

    /**
     * 登录
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseDTO login(String username, String password, boolean rememberMe) {
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password, rememberMe);

        ResponseDTO ret = ResponseDTO.ok("登录成功");
        try {
            SecurityUtils.getSubject().login(usernamePasswordToken);
            ret.put("admin", SecurityUtils.getSubject().getPrincipal());

        } catch (IncorrectCredentialsException e) {
            ret.setExceptionCode(OmsExceptionCodeTemplate.INVALID_ADMIN_PASSWORD);
        } catch (DisabledAccountException e) {
            ret.setExceptionCode(OmsExceptionCodeTemplate.DISABLE_ADMIN_ACCOUNT);
        } catch (UnknownAccountException e) {
            ret.setExceptionCode(OmsExceptionCodeTemplate.NONE_ADMIN_ACCOUNT);
        }

        return ret;
    }

    /**
     * 退出登录
     */
    @RequestMapping(value = "/logout", method = {RequestMethod.POST, RequestMethod.GET})
    public ResponseDTO logout() {
        SecurityUtils.getSubject().logout();
        return ResponseDTO.ok();
    }
}
