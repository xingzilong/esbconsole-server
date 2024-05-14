package org.talend.esbconsole.server.domain.core.jmx;

import javax.management.MBeanServerConnection;

/**
 * 抽象类
 *
 * @author xingzilong
 * @date 2021/10/13
 */
public abstract class AbstractJMXBaseMBean implements JMXBaseMBean {

    /**
     * 获取MbeanServerConnection
     *
     * @return
     */
    public MBeanServerConnection getMbeanServerConnection() {
        return MBeanServerUtil.getMbeanServerConnection();
    }

}
