package org.talend.esbconsole.server.domain.core.jmx.karaf;

import org.talend.esbconsole.server.domain.core.jmx.MBeanServerUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.talend.esbconsole.server.tools.base.exception.jmx.JMXInstallOperationException;
import org.talend.esbconsole.server.tools.base.exception.jmx.JMXOperationException;
import org.talend.esbconsole.server.tools.base.exception.jmx.JMXStartOperationException;
import org.talend.esbconsole.server.tools.base.exception.jmx.JMXStopOperationException;
import org.talend.esbconsole.server.tools.base.exception.jmx.JMXUninstallOperationException;

import javax.management.*;
import javax.management.openmbean.TabularData;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * {@link Bundle} 单元测试
 *
 * @author xingzilong
 * @date 2021/10/16
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class BundleTest {

    @InjectMocks
    private Bundle bundle;

    @Test
    public void bundlesAttributeTest() throws IOException, ReflectionException, InstanceNotFoundException {
        MBeanServerConnection mBeanServerConnection = Mockito.mock(MBeanServerConnection.class);
        AttributeList attributeList = Mockito.mock(AttributeList.class);
        Attribute attribute = Mockito.mock(Attribute.class);
        TabularData tabularData = Mockito.mock(TabularData.class);
        MockedStatic<MBeanServerUtil> mBeanServerUtil = Mockito.mockStatic(MBeanServerUtil.class);
        mBeanServerUtil.when(() -> MBeanServerUtil.getMbeanServerConnection()).thenReturn(mBeanServerConnection);
        Mockito.doReturn(attributeList)
                .doThrow(new ReflectionException(new Exception()))
                .doThrow(new InstanceNotFoundException("test"))
                .doThrow(new IOException("test"))
                .when(mBeanServerConnection).getAttributes(Mockito.any(ObjectName.class), Mockito.any(String[].class));
        Mockito.doReturn(attribute).when(attributeList).get(0);
        Mockito.doReturn(tabularData).when(attribute).getValue();

        TabularData tabularDataRS = bundle.bundlesAttribute();
        assertNotNull(tabularDataRS);

        // ReflectionException
        try {
            TabularData tabularDataRS1 = bundle.bundlesAttribute();
        } catch (Exception e) {
            assertTrue(e instanceof JMXOperationException);
        }
        // InstanceNotFoundException
        try {
            TabularData tabularDataRS1 = bundle.bundlesAttribute();
        } catch (Exception e) {
            assertTrue(e instanceof JMXOperationException);
        }
        // IOException
        try {
            TabularData tabularDataRS1 = bundle.bundlesAttribute();
        } catch (Exception e) {
            assertTrue(e instanceof JMXOperationException);
        }

        // MalformedObjectNameException
        try {
            Mockito.doAnswer(invocation -> {
                throw new MalformedObjectNameException();
            }).when(mBeanServerConnection).getAttributes(Mockito.any(ObjectName.class), Mockito.any(String[].class));
            TabularData tabularDataRS1 = bundle.bundlesAttribute();
        } catch (Exception e) {
            assertTrue(e instanceof JMXOperationException);
        }

        Mockito.verify(mBeanServerConnection, Mockito.times(5)).getAttributes(Mockito.any(ObjectName.class), Mockito.any(String[].class));
        Mockito.verify(attributeList).get(0);
        Mockito.verify(attribute).getValue();

        mBeanServerUtil.close();
    }

    @Test
    public void install4OneParamTest() throws IOException, ReflectionException, InstanceNotFoundException, MBeanException {
        MBeanServerConnection mBeanServerConnection = Mockito.mock(MBeanServerConnection.class);
        MockedStatic<MBeanServerUtil> mBeanServerUtil = Mockito.mockStatic(MBeanServerUtil.class);
        mBeanServerUtil.when(() -> MBeanServerUtil.getMbeanServerConnection()).thenReturn(mBeanServerConnection);
        Mockito.doReturn(1L)
                .doThrow(new ReflectionException(new Exception()))
                .doThrow(new InstanceNotFoundException("test"))
                .doThrow(new MBeanException(new Exception()))
                .doThrow(new IOException("test"))
                .when(mBeanServerConnection).invoke(Mockito.any(ObjectName.class),
                        Mockito.anyString(),
                        Mockito.any(Object[].class),
                        Mockito.any(String[].class));

        Long rsRS = bundle.install("file:/D:/talend/bundles/db_timer_task_0.1.jar");
        assertNotNull(rsRS);

        // ReflectionException
        try {
            Long rsRS1 = bundle.install("file:/D:/talend/bundles/db_timer_task_0.1.jar");
        } catch (Exception e) {
            assertTrue(e instanceof JMXInstallOperationException);
        }
        // InstanceNotFoundException
        try {
            Long rsRS1 = bundle.install("file:/D:/talend/bundles/db_timer_task_0.1.jar");
        } catch (Exception e) {
            assertTrue(e instanceof JMXInstallOperationException);
        }
        // MBeanException
        try {
            Long rsRS1 = bundle.install("file:/D:/talend/bundles/db_timer_task_0.1.jar");
        } catch (Exception e) {
            assertTrue(e instanceof JMXInstallOperationException);
        }
        // IOException
        try {
            Long rsRS1 = bundle.install("file:/D:/talend/bundles/db_timer_task_0.1.jar");
        } catch (Exception e) {
            assertTrue(e instanceof JMXInstallOperationException);
        }

        // MalformedObjectNameException
        try {
            Mockito.doAnswer(invocation -> {
                throw new MalformedObjectNameException();
            }).when(mBeanServerConnection).invoke(Mockito.any(ObjectName.class),
                    Mockito.anyString(),
                    Mockito.any(Object[].class),
                    Mockito.any(String[].class));
            Long rsRS1 = bundle.install("file:/D:/talend/bundles/db_timer_task_0.1.jar");
        } catch (Exception e) {
            assertTrue(e instanceof JMXInstallOperationException);
        }

        Mockito.verify(mBeanServerConnection, Mockito.times(6)).invoke(Mockito.any(ObjectName.class),
                Mockito.anyString(),
                Mockito.any(Object[].class),
                Mockito.any(String[].class));
        mBeanServerUtil.close();
    }

    @Test
    public void install4TwoParamTest() throws IOException, ReflectionException, InstanceNotFoundException, MBeanException {
        MBeanServerConnection mBeanServerConnection = Mockito.mock(MBeanServerConnection.class);
        MockedStatic<MBeanServerUtil> mBeanServerUtil = Mockito.mockStatic(MBeanServerUtil.class);
        mBeanServerUtil.when(() -> MBeanServerUtil.getMbeanServerConnection()).thenReturn(mBeanServerConnection);
        Mockito.doReturn(1L)
                .doThrow(new ReflectionException(new Exception()))
                .doThrow(new InstanceNotFoundException("test"))
                .doThrow(new MBeanException(new Exception()))
                .doThrow(new IOException("test"))
                .when(mBeanServerConnection).invoke(Mockito.any(ObjectName.class),
                        Mockito.anyString(),
                        Mockito.any(Object[].class),
                        Mockito.any(String[].class));

        Long rsRS = bundle.install("file:/D:/talend/bundles/db_timer_task_0.1.jar", true);
        assertNotNull(rsRS);

        // ReflectionException
        try {
            Long rsRS1 = bundle.install("file:/D:/talend/bundles/db_timer_task_0.1.jar", true);
        } catch (Exception e) {
            assertTrue(e instanceof JMXInstallOperationException);
        }
        // InstanceNotFoundException
        try {
            Long rsRS1 = bundle.install("file:/D:/talend/bundles/db_timer_task_0.1.jar", true);
        } catch (Exception e) {
            assertTrue(e instanceof JMXInstallOperationException);
        }
        // MBeanException
        try {
            Long rsRS1 = bundle.install("file:/D:/talend/bundles/db_timer_task_0.1.jar", true);
        } catch (Exception e) {
            assertTrue(e instanceof JMXInstallOperationException);
        }
        // IOException
        try {
            Long rsRS1 = bundle.install("file:/D:/talend/bundles/db_timer_task_0.1.jar", true);
        } catch (Exception e) {
            assertTrue(e instanceof JMXInstallOperationException);
        }

        // MalformedObjectNameException
        try {
            Mockito.doAnswer(invocation -> {
                throw new MalformedObjectNameException();
            }).when(mBeanServerConnection).invoke(Mockito.any(ObjectName.class),
                    Mockito.anyString(),
                    Mockito.any(Object[].class),
                    Mockito.any(String[].class));
            Long rsRS1 = bundle.install("file:/D:/talend/bundles/db_timer_task_0.1.jar", true);
        } catch (Exception e) {
            assertTrue(e instanceof JMXInstallOperationException);
        }

        Mockito.verify(mBeanServerConnection, Mockito.times(6)).invoke(Mockito.any(ObjectName.class),
                Mockito.anyString(),
                Mockito.any(Object[].class),
                Mockito.any(String[].class));
        mBeanServerUtil.close();
    }

    @Test
    public void uninstallTest() throws IOException, ReflectionException, InstanceNotFoundException, MBeanException {
        MBeanServerConnection mBeanServerConnection = Mockito.mock(MBeanServerConnection.class);
        MockedStatic<MBeanServerUtil> mBeanServerUtil = Mockito.mockStatic(MBeanServerUtil.class);
        mBeanServerUtil.when(() -> MBeanServerUtil.getMbeanServerConnection()).thenReturn(mBeanServerConnection);
        Mockito.doReturn(Mockito.mock(Object.class))
                .doThrow(new ReflectionException(new Exception()))
                .doThrow(new InstanceNotFoundException("test"))
                .doThrow(new MBeanException(new Exception()))
                .doThrow(new IOException("test"))
                .when(mBeanServerConnection).invoke(Mockito.any(ObjectName.class),
                        Mockito.anyString(),
                        Mockito.any(Object[].class),
                        Mockito.any(String[].class));

        bundle.uninstall("mvn:org.talend.esb.test_project/db_timer_task/0.1.0");

        // ReflectionException
        try {
            bundle.uninstall("mvn:org.talend.esb.test_project/db_timer_task/0.1.0");
        } catch (Exception e) {
            assertTrue(e instanceof JMXUninstallOperationException);
        }
        // InstanceNotFoundException
        try {
            bundle.uninstall("mvn:org.talend.esb.test_project/db_timer_task/0.1.0");
        } catch (Exception e) {
            assertTrue(e instanceof JMXUninstallOperationException);
        }
        // MBeanException
        try {
            bundle.uninstall("mvn:org.talend.esb.test_project/db_timer_task/0.1.0");
        } catch (Exception e) {
            assertTrue(e instanceof JMXUninstallOperationException);
        }
        // IOException
        try {
            bundle.uninstall("mvn:org.talend.esb.test_project/db_timer_task/0.1.0");
        } catch (Exception e) {
            assertTrue(e instanceof JMXUninstallOperationException);
        }

        // MalformedObjectNameException
        try {
            Mockito.doAnswer(invocation -> {
                throw new MalformedObjectNameException();
            }).when(mBeanServerConnection).invoke(Mockito.any(ObjectName.class),
                    Mockito.anyString(),
                    Mockito.any(Object[].class),
                    Mockito.any(String[].class));
            bundle.uninstall("mvn:org.talend.esb.test_project/db_timer_task/0.1.0");
        } catch (Exception e) {
            assertTrue(e instanceof JMXUninstallOperationException);
        }

        Mockito.verify(mBeanServerConnection, Mockito.times(6)).invoke(Mockito.any(ObjectName.class),
                Mockito.anyString(),
                Mockito.any(Object[].class),
                Mockito.any(String[].class));
        mBeanServerUtil.close();
    }

    @Test
    public void startTest() throws IOException, ReflectionException, InstanceNotFoundException, MBeanException {
        MBeanServerConnection mBeanServerConnection = Mockito.mock(MBeanServerConnection.class);
        MockedStatic<MBeanServerUtil> mBeanServerUtil = Mockito.mockStatic(MBeanServerUtil.class);
        mBeanServerUtil.when(() -> MBeanServerUtil.getMbeanServerConnection()).thenReturn(mBeanServerConnection);
        Mockito.doReturn(Mockito.mock(Object.class))
                .doThrow(new ReflectionException(new Exception()))
                .doThrow(new InstanceNotFoundException("test"))
                .doThrow(new MBeanException(new Exception()))
                .doThrow(new IOException("test"))
                .when(mBeanServerConnection).invoke(Mockito.any(ObjectName.class),
                        Mockito.anyString(),
                        Mockito.any(Object[].class),
                        Mockito.any(String[].class));

        bundle.start("mvn:org.talend.esb.test_project/db_timer_task/0.1.0");

        // ReflectionException
        try {
            bundle.start("mvn:org.talend.esb.test_project/db_timer_task/0.1.0");
        } catch (Exception e) {
            assertTrue(e instanceof JMXStartOperationException);
        }
        // InstanceNotFoundException
        try {
            bundle.start("mvn:org.talend.esb.test_project/db_timer_task/0.1.0");
        } catch (Exception e) {
            assertTrue(e instanceof JMXStartOperationException);
        }
        // MBeanException
        try {
            bundle.start("mvn:org.talend.esb.test_project/db_timer_task/0.1.0");
        } catch (Exception e) {
            assertTrue(e instanceof JMXStartOperationException);
        }
        // IOException
        try {
            bundle.start("mvn:org.talend.esb.test_project/db_timer_task/0.1.0");
        } catch (Exception e) {
            assertTrue(e instanceof JMXStartOperationException);
        }

        // MalformedObjectNameException
        try {
            Mockito.doAnswer(invocation -> {
                throw new MalformedObjectNameException();
            }).when(mBeanServerConnection).invoke(Mockito.any(ObjectName.class),
                    Mockito.anyString(),
                    Mockito.any(Object[].class),
                    Mockito.any(String[].class));
            bundle.start("mvn:org.talend.esb.test_project/db_timer_task/0.1.0");
        } catch (Exception e) {
            assertTrue(e instanceof JMXStartOperationException);
        }

        Mockito.verify(mBeanServerConnection, Mockito.times(6)).invoke(Mockito.any(ObjectName.class),
                Mockito.anyString(),
                Mockito.any(Object[].class),
                Mockito.any(String[].class));
        mBeanServerUtil.close();
    }

    @Test
    public void stopTest() throws IOException, ReflectionException, InstanceNotFoundException, MBeanException {
        MBeanServerConnection mBeanServerConnection = Mockito.mock(MBeanServerConnection.class);
        MockedStatic<MBeanServerUtil> mBeanServerUtil = Mockito.mockStatic(MBeanServerUtil.class);
        mBeanServerUtil.when(() -> MBeanServerUtil.getMbeanServerConnection()).thenReturn(mBeanServerConnection);
        Mockito.doReturn(Mockito.mock(Object.class))
                .doThrow(new ReflectionException(new Exception()))
                .doThrow(new InstanceNotFoundException("test"))
                .doThrow(new MBeanException(new Exception()))
                .doThrow(new IOException("test"))
                .when(mBeanServerConnection).invoke(Mockito.any(ObjectName.class),
                        Mockito.anyString(),
                        Mockito.any(Object[].class),
                        Mockito.any(String[].class));

        bundle.stop("mvn:org.talend.esb.test_project/db_timer_task/0.1.0");

        // ReflectionException
        try {
            bundle.stop("mvn:org.talend.esb.test_project/db_timer_task/0.1.0");
        } catch (Exception e) {
            assertTrue(e instanceof JMXStopOperationException);
        }
        // InstanceNotFoundException
        try {
            bundle.stop("mvn:org.talend.esb.test_project/db_timer_task/0.1.0");
        } catch (Exception e) {
            assertTrue(e instanceof JMXStopOperationException);
        }
        // MBeanException
        try {
            bundle.stop("mvn:org.talend.esb.test_project/db_timer_task/0.1.0");
        } catch (Exception e) {
            assertTrue(e instanceof JMXStopOperationException);
        }
        // IOException
        try {
            bundle.stop("mvn:org.talend.esb.test_project/db_timer_task/0.1.0");
        } catch (Exception e) {
            assertTrue(e instanceof JMXStopOperationException);
        }

        // MalformedObjectNameException
        try {
            Mockito.doAnswer(invocation -> {
                throw new MalformedObjectNameException();
            }).when(mBeanServerConnection).invoke(Mockito.any(ObjectName.class),
                    Mockito.anyString(),
                    Mockito.any(Object[].class),
                    Mockito.any(String[].class));
            bundle.stop("mvn:org.talend.esb.test_project/db_timer_task/0.1.0");
        } catch (Exception e) {
            assertTrue(e instanceof JMXStopOperationException);
        }

        Mockito.verify(mBeanServerConnection, Mockito.times(6)).invoke(Mockito.any(ObjectName.class),
                Mockito.anyString(),
                Mockito.any(Object[].class),
                Mockito.any(String[].class));
        mBeanServerUtil.close();
    }
}
