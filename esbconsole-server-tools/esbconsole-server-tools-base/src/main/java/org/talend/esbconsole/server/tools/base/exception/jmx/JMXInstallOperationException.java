package org.talend.esbconsole.server.tools.base.exception.jmx;

/**
 * 通过JMX进行安装操作时异常
 *
 * @author xingzilong
 * @date 2023/05/04
 */
public class JMXInstallOperationException extends JMXException {
    private static final long serialVersionUID = 1L;

    public JMXInstallOperationException(String cause) {
        super("JMX安装操作发生异常，原因：" + cause);
    }
}
