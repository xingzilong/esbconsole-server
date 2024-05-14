package org.talend.esbconsole.server.domain.core.jmx.osgi;

import org.talend.esbconsole.server.domain.core.jmx.AbstractJMXBaseMBean;
import org.talend.esbconsole.server.domain.core.jmx.JMXBaseMBean;
import org.talend.esbconsole.server.tools.base.exception.jmx.JMXOperationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.management.*;
import javax.management.openmbean.TabularData;
import java.io.IOException;
import java.util.Set;

/**
 * JMX MBean对象相关操作 osgi.core:type=bundleState
 *
 * @author xingzilong
 * @date 2021/05/04
 */
@Slf4j
@Component
public class BundleState extends AbstractJMXBaseMBean implements JMXBaseMBean {

    private static final String OBJECT_NAME = "osgi.core:type=bundleState,version=1.7,framework=org.eclipse.osgi,uuid=*";

    /**
     * 获取所有的 bundle 的详细信息
     *
     * @return 其内包含容器内所有 bundle 的详细信息
     */
    public TabularData listBundles() {
        TabularData tabularData = null;
        try {
            ObjectName bundleStateObjectName = new ObjectName(OBJECT_NAME);
            Set<ObjectName> objectNames = getMbeanServerConnection().queryNames(bundleStateObjectName, null);
            ObjectName realBundleStateObjectName = objectNames.iterator().next();
            tabularData =
                    (TabularData) getMbeanServerConnection().invoke(realBundleStateObjectName, "listBundles", null, null);
        } catch (MalformedObjectNameException e) {
            log.error("MalformedObjectNameException when call listBundles().", e);
            throw new JMXOperationException("MalformedObjectNameException");
        } catch (ReflectionException e) {
            log.error("ReflectionException when call listBundles().");
            throw new JMXOperationException("ReflectionException");
        } catch (InstanceNotFoundException e) {
            log.error("InstanceNotFoundException when call listBundles().", e);
            throw new JMXOperationException("InstanceNotFoundException");
        } catch (MBeanException e) {
            log.error("MBeanException when call listBundles().", e);
            throw new JMXOperationException("MBeanException");
        } catch (IOException e) {
            log.error("IOException when call listBundles().", e);
            throw new JMXOperationException("IOException");
        }
        return tabularData;
    }
}
