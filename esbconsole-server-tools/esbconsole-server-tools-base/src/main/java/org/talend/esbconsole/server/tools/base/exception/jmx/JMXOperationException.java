package org.talend.esbconsole.server.tools.base.exception.jmx;

/**
 * 通过JMX进行安装操作时异常
 *
 * @author xingzilong
 * @date 2021/05/04
 */
public class JMXOperationException extends JMXException {
    private static final long serialVersionUID = 1L;

    public JMXOperationException(String cause) {
        super("JMX操作发生异常，原因：" + cause);
    }
}
