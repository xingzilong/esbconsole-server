package org.talend.esbconsole.server.tools.base.exception.jmx;

import org.talend.esbconsole.server.tools.base.exception.base.BaseException;

/**
 * JMX相关异常
 *
 * @author xingzilong
 * @date 2023/05/04
 */
public class JMXException extends BaseException {
    private static final long serialVersionUID = 1L;

    public JMXException(String code) {
        super("JMX", code, null, null);
    }
}
