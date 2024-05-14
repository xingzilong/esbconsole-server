package org.talend.esbconsole.server.tools.base.exception.file;

import org.talend.esbconsole.server.tools.base.exception.base.BaseException;

/**
 * 文件相关异常
 *
 * @author xingzilong
 * @date 2021/05/04
 */
public class FileException extends BaseException {
    private static final long serialVersionUID = 1L;

    public FileException(String code) {
        super("File", code, null, null);
    }
}
