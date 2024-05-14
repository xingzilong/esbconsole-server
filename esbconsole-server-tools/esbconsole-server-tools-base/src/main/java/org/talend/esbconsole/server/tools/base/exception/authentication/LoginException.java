package org.talend.esbconsole.server.tools.base.exception.authentication;

/**
 * 登录异常
 *
 * @author xingzilong
 * @date 2021/05/04
 */
public class LoginException extends AuthenticationException {
    private static final long serialVersionUID = 1L;

    public LoginException(String cause) {
        super(cause);
    }
}
