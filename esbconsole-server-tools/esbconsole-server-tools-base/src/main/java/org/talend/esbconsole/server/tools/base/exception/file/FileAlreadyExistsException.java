package org.talend.esbconsole.server.tools.base.exception.file;

/**
 * 文件已存在 异常
 *
 * @author xingzilong
 * @date 2023/05/04
 */
public class FileAlreadyExistsException extends FileException {
    private static final long serialVersionUID = 1L;

    public FileAlreadyExistsException(String cause) {
        super("文件已存在，File：" + cause);
    }
}
