package com.ydd.oms.common.exception;

import com.ydd.framework.core.exception.ExceptionCode;
import com.ydd.framework.core.exception.ExceptionCodeTemplate;

/**
 * com.ydd.oms 错误码
 *
 * @author xingkong1221
 * @since 2017-09-27
 */
public class OmsExceptionCodeTemplate extends ExceptionCodeTemplate {

    public final static ExceptionCode LOGIN_STATUS_EXPIRED =
            ExceptionCode.init(20001, "登录状态失效");

    public final static ExceptionCode NONE_ADMIN_ACCOUNT =
            ExceptionCode.init(20002, "账户不存在");

    public final static ExceptionCode INVALID_ADMIN_PASSWORD =
            ExceptionCode.init(20003, "密码错误");

    public final static ExceptionCode DISABLE_ADMIN_ACCOUNT =
            ExceptionCode.init(20003, "账号被禁用");

    public final static ExceptionCode MOBILE_EXIST =
            ExceptionCode.init(20004, "手机号已被注册");

    public final static ExceptionCode GOODS_IS_PROMOTION =
            ExceptionCode.init(30001, "该商品正在参加促销，无法设置特价");

    public final static ExceptionCode GOODS_IS_SPECIALOFFER =
            ExceptionCode.init(30002, "该商品正在参加特价，无法添加促销");

    public final static ExceptionCode GOODS_SN_IS_EXIST =
            ExceptionCode.init(30003, "该商品编号已存在");

    public final static ExceptionCode GOODS_UNIQUE_SN_IS_EXIST =
            ExceptionCode.init(30004, "该商品防伪码已存在");
}
