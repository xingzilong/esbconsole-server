package org.talend.esbconsole.server.tools.base.exception;

/**
 * 服务冲突异常
 *
 * @author xingzilong
 * @date 2021/11/23
 */
public class ServiceConflictException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ServiceConflictException(String messsage) {
        super(messsage);
    }
}
