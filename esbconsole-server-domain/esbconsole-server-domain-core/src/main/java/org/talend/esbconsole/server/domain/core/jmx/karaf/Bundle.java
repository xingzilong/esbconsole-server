package org.talend.esbconsole.server.domain.core.jmx.karaf;

import org.talend.esbconsole.server.domain.core.jmx.AbstractJMXBaseMBean;
import org.talend.esbconsole.server.domain.core.jmx.JMXBaseMBean;
import org.talend.esbconsole.server.tools.common.constant.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.talend.esbconsole.server.tools.base.exception.jmx.JMXInstallOperationException;
import org.talend.esbconsole.server.tools.base.exception.jmx.JMXOperationException;
import org.talend.esbconsole.server.tools.base.exception.jmx.JMXStartOperationException;
import org.talend.esbconsole.server.tools.base.exception.jmx.JMXStopOperationException;
import org.talend.esbconsole.server.tools.base.exception.jmx.JMXUninstallOperationException;

import javax.management.*;
import javax.management.openmbean.TabularData;
import java.io.IOException;

/**
 * JMX MBean对象相关操作 org.apache.karaf:type=bundle
 *
 * @author xingzilong
 * @date 2021/05/04
 */
@Slf4j
@Component
public class Bundle extends AbstractJMXBaseMBean implements JMXBaseMBean {

    private static final String MONE = "MalformedObjectNameException";

    private static final String RE = "ReflectionException";

    private static final String INFE = "InstanceNotFoundException";

    private static final String IOE = "IOException";

    private static final String MBE = "MBeanException";

    private static final String PARAMETER_TYPE_STRING = "java.lang.String";

    private static String OBJECT_NAME = "org.apache.karaf:type=bundle,name=" + Constants.KARAF_NAME;


    /**
     * 获取属性 Bundles
     *
     * @return 返回 Bundles 的详细信息，其内包含容器内所有 bundle 的信息
     */
    public TabularData bundlesAttribute() {
        TabularData tabularData = null;
        try {
            ObjectName featureObjectName = new ObjectName(OBJECT_NAME);
            String[] params = {"Bundles"};
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
     * 部署 bundle
     *
     * @param url 可以是部署的bundle的文件路径。 例如：file:/D:/talend/bundles/db_timer_task_0.1.jar
     *            可以是bundle在mvn本地库的坐标。例如：mvn:org.talend.esb/db_timer_task/0.1.0
     * @return 部署成功返回bundleId且数值大于 0L 失败则返回 -1L
     */
    public Long install(String url) {
        Long rs = -1L;
        try {
            ObjectName bundleObjectName = new ObjectName(OBJECT_NAME);
            Object[] params = {url};
            String[] signature = {PARAMETER_TYPE_STRING};
            rs = (Long) getMbeanServerConnection().invoke(bundleObjectName, "install", params, signature);
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
        return rs;
    }

    /**
     * 部署 bundle
     *
     * @param url   可以是部署的bundle的文件路径。 例如：file:/D:/talend/bundles/db_timer_task_0.1.jar
     *              可以是bundle在mvn本地库的坐标。例如：mvn:org.talend.esb/db_timer_task/0.1.0
     * @param start 是否将部署的bundle的状态设置启动，当值为true则是将状态设置启动
     * @return 部署成功返回bundleId且数值大于 0L 失败则返回 -1L
     */
    public Long install(String url, boolean start) {
        Long rs = -1L;
        try {
            ObjectName bundleObjectName = new ObjectName(OBJECT_NAME);
            Object[] params = {url, start};
            String[] signature = {PARAMETER_TYPE_STRING, "boolean"};
            rs = (Long) getMbeanServerConnection().invoke(bundleObjectName, "install", params, signature);
        } catch (MalformedObjectNameException e) {
            log.error("MalformedObjectNameException when call install({}, {}).", url, start, e);
            throw new JMXInstallOperationException(MONE);
        } catch (ReflectionException e) {
            log.error("ReflectionException when call install({}, {}).", url, start, e);
            throw new JMXInstallOperationException(RE);
        } catch (InstanceNotFoundException e) {
            log.error("InstanceNotFoundException when call install({}, {}).", url, start, e);
            throw new JMXInstallOperationException(INFE);
        } catch (MBeanException e) {
            log.error("MBeanException when call install({}, {}).", url, start, e);
            throw new JMXInstallOperationException(MBE);
        } catch (IOException e) {
            log.error("IOException when call install({}, {}).", url, start, e);
            throw new JMXInstallOperationException(IOE);
        }
        return rs;
    }

    /**
     * 卸载 bundle
     *
     * @param bundleId 可以是部署的bundle的bundleID。 例如：500 一定是大于O的值 可以是部署的bundle的文件路径。
     *                 例如：file:/D:/talend/bundles/db_timer_task_0.1.jar
     *                 可以是bundle在mvn本地库的坐标。例如：mvn:org.talend.esb.test_project/db_timer_task/0.1.0
     *                 可以是bundle的SymbolicName属性值。 例如：test_project.db_timer_task 可以是bundle的Headers中的Bundle-Name属性值。 例如：
     *                 db_timer_task
     */
    public void uninstall(String bundleId) {
        try {
            ObjectName bundleObjectName = new ObjectName(OBJECT_NAME);
            Object[] params = {bundleId};
            String[] signature = {PARAMETER_TYPE_STRING};
            getMbeanServerConnection().invoke(bundleObjectName, "uninstall", params, signature);
        } catch (MalformedObjectNameException e) {
            log.error("MalformedObjectNameException when call uninstall({}).", bundleId, e);
            throw new JMXUninstallOperationException(MONE);
        } catch (ReflectionException e) {
            log.error("ReflectionException when call uninstall({}).", bundleId, e);
            throw new JMXUninstallOperationException(RE);
        } catch (InstanceNotFoundException e) {
            log.error("InstanceNotFoundException when call uninstall({}).", bundleId, e);
            throw new JMXUninstallOperationException(INFE);
        } catch (MBeanException e) {
            log.error("MBeanException when call uninstall({}).", bundleId, e);
            throw new JMXUninstallOperationException(MBE);
        } catch (IOException e) {
            log.error("IOException when call uninstall({}).", bundleId, e);
            throw new JMXUninstallOperationException(IOE);
        }
    }

    /**
     * 启动 bundle
     *
     * @param bundleId 可以是部署的bundle的bundleID。 例如：500 一定是大于O的值 可以是部署的bundle的文件路径。
     *                 例如：file:/D:/talend/bundles/db_timer_task_0.1.jar
     *                 可以是bundle在mvn本地库的坐标。例如：mvn:org.talend.esb.test_project/db_timer_task/0.1.0
     *                 可以是bundle的SymbolicName属性值。 例如：test_project.db_timer_task 可以是bundle的Headers中的Bundle-Name属性值。 例如：
     *                 db_timer_task
     */
    public void start(String bundleId) {
        try {
            ObjectName bundleObjectName = new ObjectName(OBJECT_NAME);
            Object[] params = {bundleId};
            String[] signature = {PARAMETER_TYPE_STRING};
            getMbeanServerConnection().invoke(bundleObjectName, "start", params, signature);
        } catch (MalformedObjectNameException e) {
            log.error("MalformedObjectNameException when call start({}).", bundleId, e);
            throw new JMXStartOperationException(MONE);
        } catch (ReflectionException e) {
            log.error("ReflectionException when call start({}).", bundleId, e);
            throw new JMXStartOperationException(RE);
        } catch (InstanceNotFoundException e) {
            log.error("InstanceNotFoundException when call start({}).", bundleId, e);
            throw new JMXStartOperationException(INFE);
        } catch (MBeanException e) {
            log.error("MBeanException when call start({}).", bundleId, e);
            throw new JMXStartOperationException(MBE);
        } catch (IOException e) {
            log.error("IOException when call start({}).", bundleId, e);
            throw new JMXStartOperationException(IOE);
        }
    }

    /**
     * 停止 bundle
     *
     * @param bundleId 可以是部署的bundle的bundleID。 例如：500 一定是大于O的值 可以是部署的bundle的文件路径。
     *                 例如：file:/D:/talend/bundles/db_timer_task_0.1.jar
     *                 可以是bundle在mvn本地库的坐标。例如：mvn:org.talend.esb.test_project/db_timer_task/0.1.0
     *                 可以是bundle的SymbolicName属性值。 例如：test_project.db_timer_task 可以是bundle的Headers中的Bundle-Name属性值。 例如：
     *                 db_timer_task
     */
    public void stop(String bundleId) {
        try {
            ObjectName bundleObjectName = new ObjectName(OBJECT_NAME);
            Object[] params = {bundleId};
            String[] signature = {PARAMETER_TYPE_STRING};
            getMbeanServerConnection().invoke(bundleObjectName, "stop", params, signature);
        } catch (MalformedObjectNameException e) {
            log.error("MalformedObjectNameException when call stop({}).", bundleId, e);
            throw new JMXStopOperationException(MONE);
        } catch (ReflectionException e) {
            log.error("ReflectionException when call stop({}).", bundleId, e);
            throw new JMXStopOperationException(RE);
        } catch (InstanceNotFoundException e) {
            log.error("InstanceNotFoundException when call stop({}).", bundleId, e);
            throw new JMXStopOperationException(INFE);
        } catch (MBeanException e) {
            log.error("MBeanException when call stop({}).", bundleId, e);
            throw new JMXStopOperationException(MBE);
        } catch (IOException e) {
            log.error("IOException when call stop({}).", bundleId, e);
            throw new JMXStopOperationException(IOE);
        }
    }

}
