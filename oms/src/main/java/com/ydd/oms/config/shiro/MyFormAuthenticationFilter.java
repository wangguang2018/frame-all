package com.ydd.oms.config.shiro;

import com.ydd.framework.core.common.dto.ResponseDTO;
import com.ydd.framework.core.common.utils.JsonMapper;
import com.ydd.oms.common.exception.OmsExceptionCodeTemplate;
import com.ydd.oms.service.sys.MenuService;
import com.ydd.oms.service.sys.PermissionService;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Shiro 表单登录认证
 *
 * @author xingkong1221
 * @since 2017-09-27
 */
public class MyFormAuthenticationFilter extends FormAuthenticationFilter {

    @Resource
    private MenuService menuService;

    @Resource
    private PermissionService permissionService;

    /**
     * 登录成功
     */
    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request,
                                     ServletResponse response) throws Exception {

        Integer adminId = ((ShiroAdmin) subject.getPrincipal()).getId();

        ResponseDTO ret = ResponseDTO.ok("登录成功");
        ret.put("admin", subject.getPrincipal());
        ret.put("menus", menuService.findListByAdminId(adminId));
        ret.put("permissions", permissionService.findListByAdminId(adminId));
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(JsonMapper.nonEmptyMapper().toJson(ret));
        return false;
    }

    /**
     * 登录失败
     */
    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e,
                                     ServletRequest request, ServletResponse response) {
        ResponseDTO ret = new ResponseDTO();
        if (e instanceof IncorrectCredentialsException) {
            ret.setExceptionCode(OmsExceptionCodeTemplate.INVALID_ADMIN_PASSWORD);
        } else if (e instanceof DisabledAccountException) {
            ret.setExceptionCode(OmsExceptionCodeTemplate.DISABLE_ADMIN_ACCOUNT);
        } else if (e instanceof UnknownAccountException) {
            ret.setExceptionCode(OmsExceptionCodeTemplate.NONE_ADMIN_ACCOUNT);
        }
        try {
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(JsonMapper.nonEmptyMapper().toJson(ret));
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return false;
    }


    /**
     * 登录失效
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        // 排除登录页面
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        if (httpServletRequest.getRequestURI().equals(getLoginUrl())) {
            return super.onAccessDenied(request, response);
        }

        ResponseDTO ret = new ResponseDTO();
        ret.setExceptionCode(OmsExceptionCodeTemplate.LOGIN_STATUS_EXPIRED);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(JsonMapper.nonEmptyMapper().toJson(ret));
        return false;
    }
}
