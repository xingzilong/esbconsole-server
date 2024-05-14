package org.talend.esbconsole.server.tools.base.exception.jmx;

/**
 * 通过JMX进行卸载操作时异常
 *
 * @author xingzilong
 * @date 2021/05/04
 */
public class JMXUninstallOperationException extends JMXException {
    private static final long serialVersionUID = 1L;

    public JMXUninstallOperationException(String cause) {
        super("JMX安卸载作发生异常，原因：" + cause);
    }
}
