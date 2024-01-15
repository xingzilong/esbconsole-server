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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * JMX MBean对象相关操作 java.lang:type=MemoryPool,*
 *
 * @author xingzilong
 * @date 2023/05/04
 */
@Slf4j
@Component
public class MemoryPool extends AbstractJMXBaseMBean implements JMXBaseMBean {

    private static final String OBJECT_NAME = "java.lang:type=MemoryPool,*";

    private List<ObjectName> memoryObjectNames = new ArrayList<>();

    /**
     * 获取memorypool下的内存的objectname。
     * 不是固定的，会随着内存回收算法的改变而改变
     */
    public void getMemoryNames() {
        try {
            ObjectName memoryPoolObjectName = new ObjectName(OBJECT_NAME);
            Set<ObjectName> objectNames =
                    getMbeanServerConnection().queryNames(memoryPoolObjectName, null);
            for (Iterator<ObjectName> iterator = objectNames.iterator(); iterator.hasNext(); ) {
                ObjectName objectName = iterator.next();
                if (objectName != null) {
                    memoryObjectNames.add(objectName);
                }
            }
        } catch (MalformedObjectNameException e) {
            log.error("MalformedObjectNameException when call getMemoryNames().", e);
            throw new JMXOperationException("MalformedObjectNameException");
        } catch (IOException e) {
            log.error("IOException when call getMemoryNames().", e);
            throw new JMXOperationException("IOException");
        }
    }

    /**
     * 获取JVM内存详细信息
     */
    public List<JVMMemoryInfo> getMemoryInfo() {
        List<JVMMemoryInfo> jvmMemoryInfos = new ArrayList<>();
        if (memoryObjectNames.size() == 0) {
            getMemoryNames();
        }
        try {
            for (ObjectName objectName : memoryObjectNames) {
                JVMMemoryInfo jvmMemoryInfo = new JVMMemoryInfo();
                String name = (String) getMbeanServerConnection().getAttribute(objectName, "Name");
                String type = (String) getMbeanServerConnection().getAttribute(objectName, "Type");
                CompositeData usage = (CompositeData) getMbeanServerConnection().getAttribute(objectName, "Usage");
                jvmMemoryInfo.setName(name);
                jvmMemoryInfo.setType(type);
                jvmMemoryInfo.setCommitted((Long) usage.get("committed"));
                jvmMemoryInfo.setInit((Long) usage.get("init"));
                jvmMemoryInfo.setMax((Long) usage.get("max"));
                jvmMemoryInfo.setUsed((Long) usage.get("used"));
                jvmMemoryInfos.add(jvmMemoryInfo);
            }
        } catch (ReflectionException e) {
            log.error("ReflectionException when call getMemoryInfo().", e);
            throw new JMXOperationException("ReflectionException");
        } catch (InstanceNotFoundException e) {
            log.error("InstanceNotFoundException when call getMemoryInfo().", e);
            throw new JMXOperationException("InstanceNotFoundException");
        } catch (IOException e) {
            log.error("IOException when call getMemoryInfo().", e);
            throw new JMXOperationException("IOException");
        } catch (AttributeNotFoundException e) {
            log.error("IOException when call getMemoryInfo().", e);
            throw new JMXOperationException("AttributeNotFoundException");
        } catch (MBeanException e) {
            log.error("MBeanException when call getMemoryInfo().", e);
            throw new JMXOperationException("MBeanException");
        }
        return jvmMemoryInfos;
    }


}
