package org.talend.esbconsole.server.tools.base.exception;

import org.talend.esbconsole.server.tools.base.exception.base.BaseException;

/**
 * HTTP请求失败异常
 *
 * @author xingzilong
 * @date 2023/05/04
 */
public class HTTPRequestFailedException extends BaseException {
    private static final long serialVersionUID = 1L;

    public HTTPRequestFailedException(String cause) {
        super("HTTP请求失败：", cause, null, null);
    }
}
