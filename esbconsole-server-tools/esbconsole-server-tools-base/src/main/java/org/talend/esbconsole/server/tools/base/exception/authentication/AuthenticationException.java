package org.talend.esbconsole.server.tools.base.exception.authentication;

import org.talend.esbconsole.server.tools.base.exception.base.BaseException;

/**
 * 认证相关异常
 *
 * @author xingzilong
 * @date 2021/05/04
 */
public class AuthenticationException extends BaseException {
    private static final long serialVersionUID = 1L;

    public AuthenticationException(String code) {
        super("认证异常", code, null, null);
    }
}
