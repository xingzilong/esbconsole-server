package org.talend.esbconsole.server.tools.base.exception.authentication;

/**
 * 无效token异常
 *
 * @author xingzilong
 * @date 2023/05/04
 */
public class InvalidTokenException extends AuthenticationException {
    private static final long serialVersionUID = 1L;

    public InvalidTokenException(String cause) {
        super(cause);
    }
}
