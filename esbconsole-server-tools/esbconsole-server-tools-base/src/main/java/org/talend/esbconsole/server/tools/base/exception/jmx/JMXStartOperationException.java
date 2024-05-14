package org.talend.esbconsole.server.tools.base.exception.jmx;

/**
 * 通过JMX进行启动操作时异常
 *
 * @author xingzilong
 * @date 2021/05/04
 */
public class JMXStartOperationException extends JMXException {
    private static final long serialVersionUID = 1L;

    public JMXStartOperationException(String cause) {
        super("JMX启动操作发生异常，原因：" + cause);
    }
}
