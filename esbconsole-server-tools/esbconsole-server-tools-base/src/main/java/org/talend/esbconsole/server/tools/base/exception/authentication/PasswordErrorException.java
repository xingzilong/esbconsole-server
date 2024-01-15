package org.talend.esbconsole.server.tools.base.exception.authentication;

import org.talend.esbconsole.server.tools.base.exception.base.BaseException;

/**
 * 密码校验相关异常
 *
 * @author xingzilong
 * @date 2023/05/04
 */
public class PasswordErrorException extends BaseException {
    private static final long serialVersionUID = 1L;

    public PasswordErrorException(String code) {
        super("密码校验异常：", code, null, null);
    }
}
