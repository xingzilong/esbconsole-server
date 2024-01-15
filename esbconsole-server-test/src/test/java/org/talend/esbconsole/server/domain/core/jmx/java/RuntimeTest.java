package org.talend.esbconsole.server.domain.core.jmx.java;

import org.talend.esbconsole.server.domain.api.model.JVMDetails;
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
import javax.management.openmbean.CompositeData;
import javax.management.openmbean.TabularData;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * {@link Runtime} 单元测试
 *
 * @author xingzilong
 * @date 2023/10/13
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class RuntimeTest {

    @InjectMocks
    private Runtime runtime;

    @Test
    public void getJVMDetailsTest() throws IOException, ReflectionException, AttributeNotFoundException, InstanceNotFoundException, MBeanException {
        MBeanServerConnection mBeanServerConnection = Mockito.mock(MBeanServerConnection.class);
        TabularData systemProperties = Mockito.mock(TabularData.class);
        Set<?> ketSet = Mockito.mock(HashSet.class);
        Iterator<?> iterator = Mockito.mock(Iterator.class);
        CompositeData systemPropertie = Mockito.mock(CompositeData.class);
        List listObject = Mockito.mock(List.class);
        MockedStatic<MBeanServerUtil> mBeanServerUtil = Mockito.mockStatic(MBeanServerUtil.class);
        mBeanServerUtil.when(() -> MBeanServerUtil.getMbeanServerConnection()).thenReturn(mBeanServerConnection);
        Mockito.doReturn(1L)
                .doReturn(new String[]{"aaa"})
                .doReturn("name")
                .doReturn(systemProperties)
                .doThrow(new ReflectionException(new Exception()))
                .doThrow(new InstanceNotFoundException("test"))
                .doThrow(new IOException("test"))
                .doThrow(new AttributeNotFoundException("test"))
                .doThrow(new MBeanException(new Exception()))
                .when(mBeanServerConnection).getAttribute(Mockito.any(ObjectName.class), Mockito.anyString());
        Mockito.doReturn(ketSet).when(systemProperties).keySet();
        Mockito.doReturn(iterator).when(ketSet).iterator();
        Mockito.doReturn(true).doReturn(false).when(iterator).hasNext();
        Mockito.doReturn(listObject).when(iterator).next();
        Mockito.doReturn(systemPropertie).when(systemProperties).get(Mockito.any());
        Mockito.doReturn("java.version")
                .doReturn("java.version")
                .when(systemPropertie).get(Mockito.anyString());

        JVMDetails jvmDetails = runtime.getJVMDetails();
        assertNotNull(jvmDetails);

        // ReflectionException
        try {
            JVMDetails jvmDetails1 = runtime.getJVMDetails();
        } catch (Exception e) {
            assertTrue(e instanceof JMXOperationException);
        }
        // InstanceNotFoundException
        try {
            JVMDetails jvmDetails1 = runtime.getJVMDetails();
        } catch (Exception e) {
            assertTrue(e instanceof JMXOperationException);
        }
        // IOException
        try {
            JVMDetails jvmDetails1 = runtime.getJVMDetails();
        } catch (Exception e) {
            assertTrue(e instanceof JMXOperationException);
        }
        // AttributeNotFoundException
        try {
            JVMDetails jvmDetails1 = runtime.getJVMDetails();
        } catch (Exception e) {
            assertTrue(e instanceof JMXOperationException);
        }
        // MBeanException
        try {
            JVMDetails jvmDetails1 = runtime.getJVMDetails();
        } catch (Exception e) {
            assertTrue(e instanceof JMXOperationException);
        }

        // MalformedObjectNameException
        try {
            Mockito.doAnswer(invocation -> {
                throw new MalformedObjectNameException();
            }).when(mBeanServerConnection).getAttribute(Mockito.any(ObjectName.class), Mockito.anyString());
            JVMDetails jvmDetails1 = runtime.getJVMDetails();
        } catch (Exception e) {
            assertTrue(e instanceof JMXOperationException);
        }

        Mockito.verify(mBeanServerConnection, Mockito.times(10)).getAttribute(Mockito.any(ObjectName.class), Mockito.anyString());
        Mockito.verify(systemPropertie, Mockito.times(2)).get(Mockito.anyString());

        mBeanServerUtil.close();
    }
}
