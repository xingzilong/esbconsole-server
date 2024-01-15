package org.talend.esbconsole.server.tools.base.exception;

import org.talend.esbconsole.server.tools.base.exception.base.BaseException;

/**
 * 服务器信息读取异常
 *
 * @author xingzilong
 * @date 2023/05/04
 */
public class ServerInfoReadException extends BaseException {
    private static final long serialVersionUID = 1L;

    public ServerInfoReadException(String cause) {
        super("服务器信息读取异常", cause, null, null);
    }
}
