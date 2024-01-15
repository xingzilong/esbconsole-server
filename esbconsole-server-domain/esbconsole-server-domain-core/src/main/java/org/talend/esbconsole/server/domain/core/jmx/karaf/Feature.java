package org.talend.esbconsole.server.domain.core.jmx.karaf;

import org.talend.esbconsole.server.domain.core.jmx.AbstractJMXBaseMBean;
import org.talend.esbconsole.server.domain.core.jmx.JMXBaseMBean;
import org.talend.esbconsole.server.tools.base.exception.jmx.JMXInstallOperationException;
import org.talend.esbconsole.server.tools.base.exception.jmx.JMXOperationException;
import org.talend.esbconsole.server.tools.base.exception.jmx.JMXUninstallOperationException;
import org.talend.esbconsole.server.tools.common.constant.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.management.*;
import javax.management.openmbean.TabularData;
import java.io.IOException;

/**
 * JMX MBean对象相关操作 org.apache.karaf:type=feature
 *
 * @author xingzilong
 * @date 2023/05/04
 */
@Slf4j
@Component
public class Feature extends AbstractJMXBaseMBean implements JMXBaseMBean {

    private static final String MONE = "MalformedObjectNameException";

    private static final String RE = "ReflectionException";

    private static final String INFE = "InstanceNotFoundException";

    private static final String IOE = "IOException";

    private static final String MBE = "MBeanException";

    private static final String PARAMETER_TYPE_STRING = "java.lang.String";

    private static final String OBJECT_NAME = "org.apache.karaf:type=feature,name=" + Constants.KARAF_NAME;

    /**
     * 获取属性 Features
     *
     * @return 返回 Features 的详细信息，其内包含容器内所有 feature 的信息
     */
    public TabularData featuresAttribute() {
        TabularData tabularData = null;
        try {
            ObjectName featureObjectName = new ObjectName(OBJECT_NAME);
            String[] params = {"Features"};
            AttributeList attributeList =
                    getMbeanServerConnection().getAttributes(featureObjectName, params);
            Attribute attribute = (Attribute) attributeList.get(0);
            tabularData = (TabularData) attribute.getValue();
        } catch (MalformedObjectNameException e) {
            log.error("MalformedObjectNameException when call bundlesAttribute().", e);
            throw new JMXOperationException(MONE);
        } catch (ReflectionException e) {
            log.error("ReflectionException when call bundlesAttribute().", e);
            throw new JMXOperationException(RE);
        } catch (InstanceNotFoundException e) {
            log.error("InstanceNotFoundException when call bundlesAttribute().", e);
            throw new JMXOperationException(INFE);
        } catch (IOException e) {
            log.error("IOException when call bundlesAttribute().", e);
            throw new JMXOperationException(IOE);
        }
        return tabularData;
    }

    /**
     * 部署 feature
     *
     * @param name 要部署的feature的名字
     */
    public void installFeature(String name) {
        try {
            ObjectName featureObjectName = new ObjectName(OBJECT_NAME);
            Object[] params = {name};
            String[] signature = {PARAMETER_TYPE_STRING};
            getMbeanServerConnection().invoke(featureObjectName, "installFeature", params, signature);
        } catch (MalformedObjectNameException e) {
            log.error("MalformedObjectNameException when call installFeature({}).", name, e);
            throw new JMXInstallOperationException(MONE);
        } catch (ReflectionException e) {
            log.error("ReflectionException when call installFeature({}).", name, e);
            throw new JMXInstallOperationException(RE);
        } catch (InstanceNotFoundException e) {
            log.error("InstanceNotFoundException when call installFeature({}).", name, e);
            throw new JMXInstallOperationException(INFE);
        } catch (MBeanException e) {
            log.error("MBeanException when call installFeature({}).", name, e);
            throw new JMXInstallOperationException(MBE);
        } catch (IOException e) {
            log.error("IOException when call installFeature({}).", name, e);
            throw new JMXInstallOperationException(IOE);
        }
    }

    /**
     * 卸载 feature
     *
     * @param name 要卸载的feature的名字
     */
    public void uninstallFeature(String name) {
        try {
            ObjectName featureObjectName = new ObjectName(OBJECT_NAME);
            Object[] params = {name};
            String[] signature = {PARAMETER_TYPE_STRING};
            getMbeanServerConnection().invoke(featureObjectName, "uninstallFeature", params, signature);
        } catch (MalformedObjectNameException e) {
            log.error("MalformedObjectNameException when call uninstallFeature({}).", name, e);
            throw new JMXUninstallOperationException(MONE);
        } catch (ReflectionException e) {
            log.error("ReflectionException when call uninstallFeature({}).", name, e);
            throw new JMXUninstallOperationException(RE);
        } catch (InstanceNotFoundException e) {
            log.error("InstanceNotFoundException when call uninstallFeature({}).", name, e);
            throw new JMXUninstallOperationException(INFE);
        } catch (MBeanException e) {
            log.error("MBeanException when call uninstallFeature({}).", name, e);
            throw new JMXUninstallOperationException(MBE);
        } catch (IOException e) {
            log.error("IOException when call uninstallFeature({}).", name, e);
            throw new JMXUninstallOperationException(IOE);
        }
    }

    /**
     * 获取 feature 详细信息
     *
     * @param name 获取 feature 详细信息的feature的名字
     * @return 返回 feature 详细信息
     */
    public TabularData infoFeature(String name) {
        TabularData tabularData = null;
        try {
            ObjectName featureObjectName = new ObjectName(OBJECT_NAME);
            Object[] params = {name};
            String[] signature = {PARAMETER_TYPE_STRING};
            tabularData = (TabularData) getMbeanServerConnection().invoke(featureObjectName, "infoFeature",
                    params, signature);
        } catch (MalformedObjectNameException e) {
            log.error("MalformedObjectNameException when call infoFeature({}).", name, e);
            throw new JMXOperationException(MONE);
        } catch (ReflectionException e) {
            log.error("ReflectionException when call infoFeature({}).", name, e);
            throw new JMXOperationException(RE);
        } catch (InstanceNotFoundException e) {
            log.error("InstanceNotFoundException when call infoFeature({}).", name, e);
            throw new JMXOperationException(INFE);
        } catch (MBeanException e) {
            log.error("MBeanException when call infoFeature({}).", name, e);
            throw new JMXOperationException(MBE);
        } catch (IOException e) {
            log.error("IOException when call infoFeature({}).", name, e);
            throw new JMXOperationException(IOE);
        }
        return tabularData;
    }

}
