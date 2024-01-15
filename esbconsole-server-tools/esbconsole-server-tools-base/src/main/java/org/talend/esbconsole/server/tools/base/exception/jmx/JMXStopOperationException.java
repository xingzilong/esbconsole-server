package org.talend.esbconsole.server.tools.base.exception.jmx;

/**
 * 通过JMX进行停止操作时异常
 *
 * @author xingzilong
 * @date 2023/05/04
 */
public class JMXStopOperationException extends JMXException {
    private static final long serialVersionUID = 1L;

    public JMXStopOperationException(String cause) {
        super("JMX停止操作发生异常，原因：" + cause);
    }
}
