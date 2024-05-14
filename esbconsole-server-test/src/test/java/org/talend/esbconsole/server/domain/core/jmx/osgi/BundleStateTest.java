package org.talend.esbconsole.server.domain.core.jmx.osgi;

import org.talend.esbconsole.server.domain.core.jmx.MBeanServerUtil;
import org.talend.esbconsole.server.tools.base.exception.jmx.JMXOperationException;
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
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * {@link BundleState} 单元测试
 *
 * @author xingzilong
 * @date 2021/10/16
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class BundleStateTest {

    @InjectMocks
    private BundleState bundleState;

    @Test
    public void listBundlesTest() throws IOException, ReflectionException, InstanceNotFoundException, MBeanException {
        MBeanServerConnection mBeanServerConnection = Mockito.mock(MBeanServerConnection.class);
        Set<ObjectName> objectNames = Mockito.mock(HashSet.class);
        Iterator<ObjectName> iterator = Mockito.mock(Iterator.class);
        ObjectName realBundleStateObjectName = Mockito.mock(ObjectName.class);
        TabularData tabularData = Mockito.mock(TabularData.class);
        MockedStatic<MBeanServerUtil> mBeanServerUtil = Mockito.mockStatic(MBeanServerUtil.class);
        mBeanServerUtil.when(() -> MBeanServerUtil.getMbeanServerConnection()).thenReturn(mBeanServerConnection);
        Mockito.doReturn(objectNames).when(mBeanServerConnection).queryNames(Mockito.any(ObjectName.class), Mockito.any());
        Mockito.doReturn(iterator).when(objectNames).iterator();
        Mockito.doReturn(realBundleStateObjectName).when(iterator).next();
        Mockito.doReturn(tabularData)
                .doThrow(new ReflectionException(new Exception()))
                .doThrow(new InstanceNotFoundException("test"))
                .doThrow(new MBeanException(new Exception()))
                .doThrow(new IOException("test"))
                .when(mBeanServerConnection).invoke(Mockito.any(ObjectName.class),
                        Mockito.anyString(),
                        Mockito.any(),
                        Mockito.any());

        TabularData tabularDataRS = bundleState.listBundles();
        assertNotNull(tabularDataRS);

        // ReflectionException
        try {
            TabularData tabularDataRS1 = bundleState.listBundles();
        } catch (Exception e) {
            assertTrue(e instanceof JMXOperationException);
        }
        // InstanceNotFoundException
        try {
            TabularData tabularDataRS1 = bundleState.listBundles();
        } catch (Exception e) {
            assertTrue(e instanceof JMXOperationException);
        }
        // MBeanException
        try {
            TabularData tabularDataRS1 = bundleState.listBundles();
        } catch (Exception e) {
            assertTrue(e instanceof JMXOperationException);
        }
        // IOException
        try {
            TabularData tabularDataRS1 = bundleState.listBundles();
        } catch (Exception e) {
            assertTrue(e instanceof JMXOperationException);
        }

        // MalformedObjectNameException
        try {
            Mockito.doAnswer(invocation -> {
                throw new MalformedObjectNameException();
            }).when(mBeanServerConnection).invoke(Mockito.any(ObjectName.class),
                    Mockito.anyString(),
                    Mockito.any(),
                    Mockito.any());
            TabularData tabularDataRS1 = bundleState.listBundles();
        } catch (Exception e) {
            assertTrue(e instanceof JMXOperationException);
        }

        Mockito.verify(mBeanServerConnection, Mockito.times(6)).invoke(Mockito.any(ObjectName.class),
                Mockito.anyString(),
                Mockito.any(),
                Mockito.any());

        mBeanServerUtil.close();
    }
}
