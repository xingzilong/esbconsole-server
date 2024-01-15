package org.talend.esbconsole.server.domain.core.jmx.karaf;

import org.talend.esbconsole.server.domain.core.jmx.MBeanServerUtil;
import org.talend.esbconsole.server.tools.base.exception.jmx.JMXInstallOperationException;
import org.talend.esbconsole.server.tools.base.exception.jmx.JMXOperationException;
import org.talend.esbconsole.server.tools.base.exception.jmx.JMXUninstallOperationException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import javax.management.*;
import javax.management.openmbean.TabularData;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * {@link Feature} 单元测试
 *
 * @author xingzilong
 * @date 2023/10/16
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class FeatureTest {

    @InjectMocks
    private Feature feature;

    @Test
    public void featuresAttributeTest() throws IOException, ReflectionException, InstanceNotFoundException {
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

        TabularData tabularDataRS = feature.featuresAttribute();
        assertNotNull(tabularDataRS);

        // ReflectionException
        try {
            TabularData tabularDataRS1 = feature.featuresAttribute();
        } catch (Exception e) {
            assertTrue(e instanceof JMXOperationException);
        }
        // InstanceNotFoundException
        try {
            TabularData tabularDataRS1 = feature.featuresAttribute();
        } catch (Exception e) {
            assertTrue(e instanceof JMXOperationException);
        }
        // IOException
        try {
            TabularData tabularDataRS1 = feature.featuresAttribute();
        } catch (Exception e) {
            assertTrue(e instanceof JMXOperationException);
        }

        // MalformedObjectNameException
        try {
            Mockito.doAnswer(invocation -> {
                throw new MalformedObjectNameException();
            }).when(mBeanServerConnection).getAttributes(Mockito.any(ObjectName.class), Mockito.any(String[].class));
            TabularData tabularDataRS1 = feature.featuresAttribute();
        } catch (Exception e) {
            assertTrue(e instanceof JMXOperationException);
        }

        Mockito.verify(mBeanServerConnection, Mockito.times(5)).getAttributes(Mockito.any(ObjectName.class), Mockito.any(String[].class));
        Mockito.verify(attributeList).get(0);
        Mockito.verify(attribute).getValue();

        mBeanServerUtil.close();
    }

    @Test
    public void installFeatureTest() throws IOException, ReflectionException, InstanceNotFoundException, MBeanException {
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

        feature.installFeature("featurename");

        // ReflectionException
        try {
            feature.installFeature("featurename");
        } catch (Exception e) {
            assertTrue(e instanceof JMXInstallOperationException);
        }
        // InstanceNotFoundException
        try {
            feature.installFeature("featurename");
        } catch (Exception e) {
            assertTrue(e instanceof JMXInstallOperationException);
        }
        // MBeanException
        try {
            feature.installFeature("featurename");
        } catch (Exception e) {
            assertTrue(e instanceof JMXInstallOperationException);
        }
        // IOException
        try {
            feature.installFeature("featurename");
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
            feature.installFeature("featurename");
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
    public void uninstallFeatureTest() throws IOException, ReflectionException, InstanceNotFoundException, MBeanException {
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

        feature.uninstallFeature("featurename");

        // ReflectionException
        try {
            feature.uninstallFeature("featurename");
        } catch (Exception e) {
            assertTrue(e instanceof JMXUninstallOperationException);
        }
        // InstanceNotFoundException
        try {
            feature.uninstallFeature("featurename");
        } catch (Exception e) {
            assertTrue(e instanceof JMXUninstallOperationException);
        }
        // MBeanException
        try {
            feature.uninstallFeature("featurename");
        } catch (Exception e) {
            assertTrue(e instanceof JMXUninstallOperationException);
        }
        // IOException
        try {
            feature.uninstallFeature("featurename");
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
            feature.uninstallFeature("featurename");
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
    public void infoFeatureTest() throws IOException, ReflectionException, InstanceNotFoundException, MBeanException {
        MBeanServerConnection mBeanServerConnection = Mockito.mock(MBeanServerConnection.class);
        TabularData tabularData = Mockito.mock(TabularData.class);
        MockedStatic<MBeanServerUtil> mBeanServerUtil = Mockito.mockStatic(MBeanServerUtil.class);
        mBeanServerUtil.when(() -> MBeanServerUtil.getMbeanServerConnection()).thenReturn(mBeanServerConnection);
        Mockito.doReturn(tabularData)
                .doThrow(new ReflectionException(new Exception()))
                .doThrow(new InstanceNotFoundException("test"))
                .doThrow(new MBeanException(new Exception()))
                .doThrow(new IOException("test"))
                .when(mBeanServerConnection).invoke(Mockito.any(ObjectName.class),
                        Mockito.anyString(),
                        Mockito.any(Object[].class),
                        Mockito.any(String[].class));

        TabularData tabularDataRS = feature.infoFeature("test");
        assertNotNull(tabularDataRS);

        // ReflectionException
        try {
            TabularData tabularDataRS1 = feature.infoFeature("test");
        } catch (Exception e) {
            assertTrue(e instanceof JMXOperationException);
        }
        // InstanceNotFoundException
        try {
            TabularData tabularDataRS1 = feature.infoFeature("test");
        } catch (Exception e) {
            assertTrue(e instanceof JMXOperationException);
        }
        // MBeanException
        try {
            TabularData tabularDataRS1 = feature.infoFeature("test");
        } catch (Exception e) {
            assertTrue(e instanceof JMXOperationException);
        }
        // IOException
        try {
            TabularData tabularDataRS1 = feature.infoFeature("test");
        } catch (Exception e) {
            assertTrue(e instanceof JMXOperationException);
        }

        // MalformedObjectNameException
        try {
            Mockito.doAnswer(invocation -> {
                throw new MalformedObjectNameException();
            }).when(mBeanServerConnection).invoke(Mockito.any(ObjectName.class),
                    Mockito.anyString(),
                    Mockito.any(Object[].class),
                    Mockito.any(String[].class));
            TabularData tabularDataRS1 = feature.infoFeature("test");
        } catch (Exception e) {
            assertTrue(e instanceof JMXOperationException);
        }

        Mockito.verify(mBeanServerConnection, Mockito.times(6)).invoke(Mockito.any(ObjectName.class),
                Mockito.anyString(),
                Mockito.any(Object[].class),
                Mockito.any(String[].class));
        mBeanServerUtil.close();
    }
}
