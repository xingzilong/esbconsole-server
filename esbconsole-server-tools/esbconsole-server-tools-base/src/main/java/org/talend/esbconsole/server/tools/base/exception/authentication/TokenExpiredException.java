package org.talend.esbconsole.server.tools.base.exception.authentication;

/**
 * 令牌过期异常
 *
 * @author xingzilong
 * @date 2023/05/04
 */
public class TokenExpiredException extends AuthenticationException {
    private static final long serialVersionUID = 1L;

    public TokenExpiredException(String cause) {
        super(cause);
    }
}
