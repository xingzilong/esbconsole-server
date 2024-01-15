package org.talend.esbconsole.server.domain.core.jmx.karaf;

import org.talend.esbconsole.server.domain.core.jmx.MBeanServerUtil;
import org.talend.esbconsole.server.tools.base.exception.jmx.JMXInstallOperationException;
import org.talend.esbconsole.server.tools.base.exception.jmx.JMXUninstallOperationException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import javax.management.*;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * {@link Kar} 单元测试
 *
 * @author xingzilong
 * @date 2023/10/16
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class KarTest {

    @InjectMocks
    private Kar kar;

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

        kar.install("file:/D:/talend/bundles/db_timer_task_0.1.kar");

        // ReflectionException
        try {
            kar.install("file:/D:/talend/bundles/db_timer_task_0.1.kar");
        } catch (Exception e) {
            assertTrue(e instanceof JMXInstallOperationException);
        }
        // InstanceNotFoundException
        try {
            kar.install("file:/D:/talend/bundles/db_timer_task_0.1.kar");
        } catch (Exception e) {
            assertTrue(e instanceof JMXInstallOperationException);
        }
        // MBeanException
        try {
            kar.install("file:/D:/talend/bundles/db_timer_task_0.1.kar");
        } catch (Exception e) {
            assertTrue(e instanceof JMXInstallOperationException);
        }
        // IOException
        try {
            kar.install("file:/D:/talend/bundles/db_timer_task_0.1.kar");
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
            kar.install("file:/D:/talend/bundles/db_timer_task_0.1.kar");
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

        Long rsRS = kar.install("file:/D:/talend/bundles/db_timer_task_0.1.kar", true);
        assertNotNull(rsRS);

        // ReflectionException
        try {
            Long rsRS1 = kar.install("file:/D:/talend/bundles/db_timer_task_0.1.kar", true);
        } catch (Exception e) {
            assertTrue(e instanceof JMXInstallOperationException);
        }
        // InstanceNotFoundException
        try {
            Long rsRS1 = kar.install("file:/D:/talend/bundles/db_timer_task_0.1.kar", true);
        } catch (Exception e) {
            assertTrue(e instanceof JMXInstallOperationException);
        }
        // MBeanException
        try {
            Long rsRS1 = kar.install("file:/D:/talend/bundles/db_timer_task_0.1.kar", true);
        } catch (Exception e) {
            assertTrue(e instanceof JMXInstallOperationException);
        }
        // IOException
        try {
            Long rsRS1 = kar.install("file:/D:/talend/bundles/db_timer_task_0.1.kar", true);
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
            Long rsRS1 = kar.install("file:/D:/talend/bundles/db_timer_task_0.1.kar", true);
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

        kar.uninstall("mvn:org.talend.esb.test_project/db_timer_task/0.1.0");

        // ReflectionException
        try {
            kar.uninstall("mvn:org.talend.esb.test_project/db_timer_task/0.1.0");
        } catch (Exception e) {
            assertTrue(e instanceof JMXUninstallOperationException);
        }
        // InstanceNotFoundException
        try {
            kar.uninstall("mvn:org.talend.esb.test_project/db_timer_task/0.1.0");
        } catch (Exception e) {
            assertTrue(e instanceof JMXUninstallOperationException);
        }
        // MBeanException
        try {
            kar.uninstall("mvn:org.talend.esb.test_project/db_timer_task/0.1.0");
        } catch (Exception e) {
            assertTrue(e instanceof JMXUninstallOperationException);
        }
        // IOException
        try {
            kar.uninstall("mvn:org.talend.esb.test_project/db_timer_task/0.1.0");
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
            kar.uninstall("mvn:org.talend.esb.test_project/db_timer_task/0.1.0");
        } catch (Exception e) {
            assertTrue(e instanceof JMXUninstallOperationException);
        }

        Mockito.verify(mBeanServerConnection, Mockito.times(6)).invoke(Mockito.any(ObjectName.class),
                Mockito.anyString(),
                Mockito.any(Object[].class),
                Mockito.any(String[].class));
        mBeanServerUtil.close();
    }
}
