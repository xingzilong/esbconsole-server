package org.talend.esbconsole.server.domain.core.jmx.karaf;

import org.talend.esbconsole.server.domain.core.jmx.AbstractJMXBaseMBean;
import org.talend.esbconsole.server.domain.core.jmx.JMXBaseMBean;
import org.talend.esbconsole.server.tools.base.exception.jmx.JMXInstallOperationException;
import org.talend.esbconsole.server.tools.base.exception.jmx.JMXUninstallOperationException;
import org.talend.esbconsole.server.tools.common.constant.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.management.*;
import java.io.IOException;

/**
 * JMX MBean对象相关操作 org.apache.karaf:type=Kar
 *
 * @author xingzilong
 * @date 2021/05/04
 */
@Slf4j
@Component
public class Kar extends AbstractJMXBaseMBean implements JMXBaseMBean {

    private static final String MONE = "MalformedObjectNameException";

    private static final String RE = "ReflectionException";

    private static final String INFE = "InstanceNotFoundException";

    private static final String IOE = "IOException";

    private static final String MBE = "MBeanException";

    private static final String PARAMETER_TYPE_STRING = "java.lang.String";

    private static final String OBJECT_NAME = "org.apache.karaf:type=kar,name=" + Constants.KARAF_NAME;


    /**
     * 部署 Kar
     *
     * @param url 要部署的Kar的文件路径。 例如：file:/D:/talend/bundles/db_timer_task_0.1.kar
     */
    public void install(String url) {
        try {
            ObjectName KarObjectName = new ObjectName(OBJECT_NAME);
            Object[] params = {url};
            String[] signature = {PARAMETER_TYPE_STRING};
            getMbeanServerConnection().invoke(KarObjectName, "install", params, signature);
        } catch (MalformedObjectNameException e) {
            log.error("MalformedObjectNameException when call install({}).", url, e);
            throw new JMXInstallOperationException(MONE);
        } catch (ReflectionException e) {
            log.error("ReflectionException when call install({}).", url, e);
            throw new JMXInstallOperationException(RE);
        } catch (InstanceNotFoundException e) {
            log.error("InstanceNotFoundException when call install({}).", url, e);
            throw new JMXInstallOperationException(INFE);
        } catch (MBeanException e) {
            log.error("MBeanException when call install({}).", url, e);
            throw new JMXInstallOperationException(MBE);
        } catch (IOException e) {
            log.error("IOException when call install({}).", url, e);
            throw new JMXInstallOperationException(IOE);
        }
    }

    /**
     * 部署 Kar
     *
     * @param url                要部署的Kar的文件路径。 例如：file:/D:/talend/bundles/db_timer_task_0.1.kar
     * @param noAutoStartBundles 不自动启动bundles，当值为false时 kar 中的 bundles 将全部设置为启动状态
     */
    public Long install(String url, boolean noAutoStartBundles) {
        Long rs = -1L;
        try {
            ObjectName KarObjectName = new ObjectName(OBJECT_NAME);
            Object[] params = {url, noAutoStartBundles};
            String[] signature = {PARAMETER_TYPE_STRING, "boolean"};
            rs = (Long) getMbeanServerConnection().invoke(KarObjectName, "install", params, signature);
        } catch (MalformedObjectNameException e) {
            log.error("MalformedObjectNameException when call install({}, {}).", url, noAutoStartBundles, e);
            throw new JMXInstallOperationException(MONE);
        } catch (ReflectionException e) {
            log.error("ReflectionException when call install({}, {}).", url, noAutoStartBundles, e);
            throw new JMXInstallOperationException(RE);
        } catch (InstanceNotFoundException e) {
            log.error("InstanceNotFoundException when call install({}, {}).", url, noAutoStartBundles, e);
            throw new JMXInstallOperationException(INFE);
        } catch (MBeanException e) {
            log.error("MBeanException when call install({}, {}).", url, noAutoStartBundles, e);
            throw new JMXInstallOperationException(MBE);
        } catch (IOException e) {
            log.error("IOException when call install({}, {}).", url, noAutoStartBundles, e);
            throw new JMXInstallOperationException(IOE);
        }
        return rs;
    }

    /**
     * 卸载 Kar
     *
     * @param name 值为 kar 部署时的文件名称。例如：若部署时的kar为file:/D:/talend/bundles/db_timer_task_0.1.kar， 则卸载此kar的时候，name应为
     *             db_timer_task_0.1
     */
    public void uninstall(String name) {
        try {
            ObjectName KarObjectName = new ObjectName(OBJECT_NAME);
            Object[] params = {name};
            String[] signature = {PARAMETER_TYPE_STRING};
            getMbeanServerConnection().invoke(KarObjectName, "uninstall", params, signature);
        } catch (MalformedObjectNameException e) {
            log.error("MalformedObjectNameException when call uninstall({}).", name, e);
            throw new JMXUninstallOperationException(MONE);
        } catch (ReflectionException e) {
            log.error("ReflectionException when call uninstall({}).", name, e);
            throw new JMXUninstallOperationException(RE);
        } catch (InstanceNotFoundException e) {
            log.error("InstanceNotFoundException when call uninstall({}).", name, e);
            throw new JMXUninstallOperationException(INFE);
        } catch (MBeanException e) {
            log.error("MBeanException when call uninstall({}).", name, e);
            throw new JMXUninstallOperationException(MBE);
        } catch (IOException e) {
            log.error("IOException when call uninstall({}).", name, e);
            throw new JMXUninstallOperationException(IOE);
        }
    }

}
