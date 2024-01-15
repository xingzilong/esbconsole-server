package org.talend.esbconsole.server.tools.base.exception;

import org.talend.esbconsole.server.tools.base.exception.base.BaseException;

/**
 * 数据操作异常
 *
 * @author xingzilong
 * @date 2023/11/23
 */
public class DataOperationException extends BaseException {
    private static final long serialVersionUID = 1L;

    public DataOperationException(String cause) {
        super("数据操作异常", cause, null, null);
    }
}
