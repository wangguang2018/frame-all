package com.ydd.oms.controller.sys;

import com.ydd.framework.core.common.dto.ResponseDTO;
import com.ydd.framework.core.controller.BaseController;
import com.ydd.oms.common.exception.OmsExceptionCodeTemplate;
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

        ResponseDTO ret = ResponseDTO.ok("登录成功");
        return ret;
    }

    /**
     * 退出登录
     */
    @RequestMapping(value = "/logout", method = {RequestMethod.POST, RequestMethod.GET})
    public ResponseDTO logout() {
        return ResponseDTO.ok();
    }
}
