package org.talend.esbconsole.server.tools.base.exception;

/**
 * 服务验证异常
 *
 * @author xingzilong
 * @date 2023/11/23
 */
public class ServiceVerifyException extends Exception {
    private static final long serialVersionUID = 1L;

    public ServiceVerifyException(String messsage) {
        super(messsage);
    }
}
