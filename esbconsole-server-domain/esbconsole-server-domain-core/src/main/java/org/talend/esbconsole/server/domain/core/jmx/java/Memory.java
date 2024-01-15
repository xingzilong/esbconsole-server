package org.talend.esbconsole.server.domain.core.jmx.java;

import org.talend.esbconsole.server.domain.api.model.JVMMemoryInfo;
import org.talend.esbconsole.server.domain.core.jmx.AbstractJMXBaseMBean;
import org.talend.esbconsole.server.domain.core.jmx.JMXBaseMBean;
import org.talend.esbconsole.server.tools.base.exception.jmx.JMXOperationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.management.*;
import javax.management.openmbean.CompositeData;
import java.io.IOException;

/**
 * JMX MBean对象相关操作 java.lang:type=Memory
 *
 * @author xingzilong
 * @date 2023/05/04
 */
@Slf4j
@Component
public class Memory extends AbstractJMXBaseMBean implements JMXBaseMBean {

    private static final String OBJECT_NAME = "java.lang:type=Memory";

    /**
     * 获取JVM堆内存使用情况
     */
    public JVMMemoryInfo getHeapMemoryInfo() {
        JVMMemoryInfo jvmMemoryInfo = new JVMMemoryInfo();
        try {
            ObjectName objectName = new ObjectName(OBJECT_NAME);
            CompositeData usage = (CompositeData) getMbeanServerConnection().getAttribute(objectName, "HeapMemoryUsage");
            jvmMemoryInfo.setCommitted((Long) usage.get("committed"));
            jvmMemoryInfo.setInit((Long) usage.get("init"));
            jvmMemoryInfo.setMax((Long) usage.get("max"));
            jvmMemoryInfo.setUsed((Long) usage.get("used"));
        } catch (MalformedObjectNameException e) {
            log.error("MalformedObjectNameException when call getHeapMemoryInfo().", e);
            throw new JMXOperationException("MalformedObjectNameException");
        } catch (ReflectionException e) {
            log.error("ReflectionException when call getHeapMemoryInfo().", e);
            throw new JMXOperationException("ReflectionException");
        } catch (InstanceNotFoundException e) {
            log.error("InstanceNotFoundException when call getHeapMemoryInfo().", e);
            throw new JMXOperationException("InstanceNotFoundException");
        } catch (IOException e) {
            log.error("IOException when call getHeapMemoryInfo().", e);
            throw new JMXOperationException("IOException");
        } catch (AttributeNotFoundException e) {
            log.error("IOException when call getHeapMemoryInfo().", e);
            throw new JMXOperationException("AttributeNotFoundException");
        } catch (MBeanException e) {
            log.error("MBeanException when call getHeapMemoryInfo().", e);
            throw new JMXOperationException("MBeanException");
        }
        return jvmMemoryInfo;
    }

    /**
     * 获取JVM非堆内存使用情况
     */
    public JVMMemoryInfo getNoHeapMemoryInfo() {
        JVMMemoryInfo jvmMemoryInfo = new JVMMemoryInfo();
        try {
            ObjectName objectName = new ObjectName(OBJECT_NAME);
            CompositeData usage = (CompositeData) getMbeanServerConnection().getAttribute(objectName, "NonHeapMemoryUsage");
            jvmMemoryInfo.setCommitted((Long) usage.get("committed"));
            jvmMemoryInfo.setInit((Long) usage.get("init"));
            jvmMemoryInfo.setMax((Long) usage.get("max"));
            jvmMemoryInfo.setUsed((Long) usage.get("used"));
        } catch (MalformedObjectNameException e) {
            log.error("MalformedObjectNameException when call getNoHeapMemoryInfo().", e);
            throw new JMXOperationException("MalformedObjectNameException");
        } catch (ReflectionException e) {
            log.error("ReflectionException when call getNoHeapMemoryInfo().", e);
            throw new JMXOperationException("ReflectionException");
        } catch (InstanceNotFoundException e) {
            log.error("InstanceNotFoundException when call getNoHeapMemoryInfo().", e);
            throw new JMXOperationException("InstanceNotFoundException");
        } catch (IOException e) {
            log.error("IOException when call getNoHeapMemoryInfo().", e);
            throw new JMXOperationException("IOException");
        } catch (AttributeNotFoundException e) {
            log.error("IOException when call getNoHeapMemoryInfo().", e);
            throw new JMXOperationException("AttributeNotFoundException");
        } catch (MBeanException e) {
            log.error("MBeanException when call getNoHeapMemoryInfo().", e);
            throw new JMXOperationException("MBeanException");
        }
        return jvmMemoryInfo;
    }

}
