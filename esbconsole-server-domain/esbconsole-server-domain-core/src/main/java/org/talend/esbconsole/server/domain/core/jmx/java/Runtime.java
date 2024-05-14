package org.talend.esbconsole.server.domain.core.jmx.java;

import org.talend.esbconsole.server.domain.api.model.JVMDetails;
import org.talend.esbconsole.server.domain.core.jmx.AbstractJMXBaseMBean;
import org.talend.esbconsole.server.domain.core.jmx.JMXBaseMBean;
import org.talend.esbconsole.server.tools.base.exception.jmx.JMXOperationException;
import org.talend.esbconsole.server.tools.common.constant.Constants;
import org.talend.esbconsole.server.tools.common.utils.DateUtil;
import org.talend.esbconsole.server.tools.common.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.management.*;
import javax.management.openmbean.CompositeData;
import javax.management.openmbean.TabularData;
import java.io.IOException;
import java.util.*;

/**
 * JMX MBean对象相关操作 java.lang:type=Runtime
 *
 * @author xingzilong
 * @date 2021/05/04
 */
@Slf4j
@Component
public class Runtime extends AbstractJMXBaseMBean implements JMXBaseMBean {

    private static final String OBJECT_NAME = "java.lang:type=Runtime";

    /**
     * 获取JVM运行详细信息
     */
    public JVMDetails getJVMDetails() {
        JVMDetails jvmDetails = new JVMDetails();
        try {
            ObjectName objectName = new ObjectName(OBJECT_NAME);
            Long startTime = (Long) getMbeanServerConnection().getAttribute(objectName, "StartTime");
            String[] inputArgs = (String[]) getMbeanServerConnection().getAttribute(objectName, "InputArguments");
            String name = (String) getMbeanServerConnection().getAttribute(objectName, "VmName");
            TabularData systemProperties = (TabularData) getMbeanServerConnection().getAttribute(objectName, "SystemProperties");
            Set<?> ketSet = systemProperties.keySet();
            Iterator<?> iterator = ketSet.iterator();
            while (iterator.hasNext()) {
                CompositeData systemPropertie = systemProperties.get(((List) iterator.next()).toArray());
                String key = (String) systemPropertie.get("key");
                if ("java.version".equals(key)) {
                    jvmDetails.setVersion((String) systemPropertie.get(Constants.VALUE));
                } else if ("java.home".equals(key)) {
                    jvmDetails.setHome((String) systemPropertie.get(Constants.VALUE));
                } else if ("user.dir".equals(key)) {
                    jvmDetails.setUserDir((String) systemPropertie.get(Constants.VALUE));
                }
                if (StringUtil.isNotEmpty(jvmDetails.getVersion()) && StringUtil.isNotEmpty(jvmDetails.getHome()) && StringUtil.isNotEmpty(jvmDetails.getUserDir())) {
                    break;
                }
            }
            jvmDetails.setName(name);
            jvmDetails.setInputArgs(Arrays.deepToString(inputArgs));
            jvmDetails.setStartTime(DateUtil.parseDateToStr(DateUtil.YYYY_MM_DD_HH_MM_SS, new Date(startTime)));
            jvmDetails.setRunTime(DateUtil.timeDistance(DateUtil.getNowDate(), new Date(startTime)));
        } catch (MalformedObjectNameException e) {
            log.error("MalformedObjectNameException when call getJVMDetails().", e);
            throw new JMXOperationException("MalformedObjectNameException");
        } catch (ReflectionException e) {
            log.error("ReflectionException when call getJVMDetails().", e);
            throw new JMXOperationException("ReflectionException");
        } catch (InstanceNotFoundException e) {
            log.error("InstanceNotFoundException when call getJVMDetails().", e);
            throw new JMXOperationException("InstanceNotFoundException");
        } catch (IOException e) {
            log.error("IOException when call getJVMDetails().", e);
            throw new JMXOperationException("IOException");
        } catch (AttributeNotFoundException e) {
            log.error("IOException when call getJVMDetails().", e);
            throw new JMXOperationException("AttributeNotFoundException");
        } catch (MBeanException e) {
            log.error("MBeanException when call getJVMDetails().", e);
            throw new JMXOperationException("MBeanException");
        }
        return jvmDetails;
    }

}
