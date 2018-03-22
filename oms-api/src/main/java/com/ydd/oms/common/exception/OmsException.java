package com.ydd.oms.common.exception;

import com.ydd.framework.core.exception.ExceptionCode;
import com.ydd.framework.core.exception.ServiceException;

/**
 * Exception - 管理系统
 *
 * @author xingkong1221
 * @since 2017-10-08
 */
public class OmsException extends ServiceException {

    private static final long serialVersionUID = -6930658130975044140L;

    public OmsException(String message) {
        super(message);
    }

    public OmsException(ExceptionCode exceptionCode) {
        super(exceptionCode);
    }

    public OmsException(String message, Throwable cause) {
        super(message, cause);
    }
}
