package org.talend.esbconsole.server.tools.base.exception.file;

/**
 * 文件类型非法异常
 *
 * @author xingzilong
 * @date 2023/05/04
 */
public class FileTypeIllegalException extends FileException {
    private static final long serialVersionUID = 1L;

    public FileTypeIllegalException(String cause) {
        super("未找到相关类型信息，File：" + cause);
    }
}