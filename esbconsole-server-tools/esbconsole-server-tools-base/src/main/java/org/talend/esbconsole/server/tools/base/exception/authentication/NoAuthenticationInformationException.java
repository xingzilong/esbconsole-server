package org.talend.esbconsole.server.tools.base.exception.authentication;

/**
 * 无身份验证信息异常
 *
 * @author xingzilong
 * @date 2021/05/04
 */
public class NoAuthenticationInformationException extends AuthenticationException {
    private static final long serialVersionUID = 1L;

    public NoAuthenticationInformationException(String cause) {
        super(cause);
    }
}