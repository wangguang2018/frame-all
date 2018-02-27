package com.ydd.oms.controller;

import com.ydd.framework.core.controller.BaseController;
import com.ydd.oms.config.shiro.ShiroAdmin;
import org.apache.shiro.SecurityUtils;

/**
 * Controller - oms基类
 *
 * @author xingkong1221
 * @since 2017-09-27
 */
public class OmsController extends BaseController {

    /**
     * 获取登录管理员
     *
     * @return 管理员
     */
    public ShiroAdmin getLoginAdmin() {
        return (ShiroAdmin) SecurityUtils.getSubject().getPrincipal();
    }

}
