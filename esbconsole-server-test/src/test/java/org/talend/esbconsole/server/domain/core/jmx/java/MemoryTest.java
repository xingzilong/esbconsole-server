package org.talend.esbconsole.server.domain.core.jmx.java;

import org.talend.esbconsole.server.domain.api.model.JVMMemoryInfo;
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
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * {@link Memory} 单元测试
 *
 * @author xingzilong
 * @date 2021/10/13
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class MemoryTest {

    @InjectMocks
    private Memory memory;

    @Test
    public void getHeapMemoryInfoTest() throws ReflectionException, AttributeNotFoundException, InstanceNotFoundException, MBeanException, IOException {
        MBeanServerConnection mBeanServerConnection = Mockito.mock(MBeanServerConnection.class);
        CompositeData usage = Mockito.mock(CompositeData.class);

        MockedStatic<MBeanServerUtil> mBeanServerUtil = Mockito.mockStatic(MBeanServerUtil.class);
        mBeanServerUtil.when(() -> MBeanServerUtil.getMbeanServerConnection()).thenReturn(mBeanServerConnection);
        Mockito.doReturn(usage)
                .doThrow(new ReflectionException(new Exception()))
                .doThrow(new InstanceNotFoundException("test"))
                .doThrow(new IOException("test"))
                .doThrow(new AttributeNotFoundException("test"))
                .doThrow(new MBeanException(new Exception()))
                .when(mBeanServerConnection).getAttribute(Mockito.any(ObjectName.class), Mockito.anyString());

        JVMMemoryInfo jvmMemoryInfo = memory.getHeapMemoryInfo();

        assertNotNull(jvmMemoryInfo);

        // ReflectionException
        try {
            JVMMemoryInfo jvmMemoryInfo1 = memory.getHeapMemoryInfo();
        } catch (Exception e) {
            assertTrue(e instanceof JMXOperationException);
        }
        // InstanceNotFoundException
        try {
            JVMMemoryInfo jvmMemoryInfo1 = memory.getHeapMemoryInfo();
        } catch (Exception e) {
            assertTrue(e instanceof JMXOperationException);
        }
        // IOException
        try {
            JVMMemoryInfo jvmMemoryInfo1 = memory.getHeapMemoryInfo();
        } catch (Exception e) {
            assertTrue(e instanceof JMXOperationException);
        }
        // AttributeNotFoundException
        try {
            JVMMemoryInfo jvmMemoryInfo1 = memory.getHeapMemoryInfo();
        } catch (Exception e) {
            assertTrue(e instanceof JMXOperationException);
        }
        // MBeanException
        try {
            JVMMemoryInfo jvmMemoryInfo1 = memory.getHeapMemoryInfo();
        } catch (Exception e) {
            assertTrue(e instanceof JMXOperationException);
        }

        // MalformedObjectNameException
        try {
            Mockito.doAnswer(invocation -> {
                throw new MalformedObjectNameException();
            }).when(mBeanServerConnection).getAttribute(Mockito.any(ObjectName.class), Mockito.anyString());
            JVMMemoryInfo jvmMemoryInfo1 = memory.getHeapMemoryInfo();
        } catch (Exception e) {
            assertTrue(e instanceof JMXOperationException);
        }

        Mockito.verify(mBeanServerConnection, Mockito.times(7)).getAttribute(Mockito.any(ObjectName.class), Mockito.anyString());

        mBeanServerUtil.close();
    }

    @Test
    public void getNoHeapMemoryInfoTest() throws ReflectionException, AttributeNotFoundException, InstanceNotFoundException, MBeanException, IOException {
        MBeanServerConnection mBeanServerConnection = Mockito.mock(MBeanServerConnection.class);
        CompositeData usage = Mockito.mock(CompositeData.class);

        MockedStatic<MBeanServerUtil> mBeanServerUtil = Mockito.mockStatic(MBeanServerUtil.class);
        mBeanServerUtil.when(() -> MBeanServerUtil.getMbeanServerConnection()).thenReturn(mBeanServerConnection);
        Mockito.doReturn(usage)
                .doThrow(new ReflectionException(new Exception()))
                .doThrow(new InstanceNotFoundException("test"))
                .doThrow(new IOException("test"))
                .doThrow(new AttributeNotFoundException("test"))
                .doThrow(new MBeanException(new Exception()))
                .when(mBeanServerConnection).getAttribute(Mockito.any(ObjectName.class), Mockito.anyString());

        JVMMemoryInfo jvmMemoryInfo = memory.getNoHeapMemoryInfo();

        assertNotNull(jvmMemoryInfo);

        // ReflectionException
        try {
            JVMMemoryInfo jvmMemoryInfo1 = memory.getNoHeapMemoryInfo();
        } catch (Exception e) {
            assertTrue(e instanceof JMXOperationException);
        }
        // InstanceNotFoundException
        try {
            JVMMemoryInfo jvmMemoryInfo1 = memory.getNoHeapMemoryInfo();
        } catch (Exception e) {
            assertTrue(e instanceof JMXOperationException);
        }
        // IOException
        try {
            JVMMemoryInfo jvmMemoryInfo1 = memory.getNoHeapMemoryInfo();
        } catch (Exception e) {
            assertTrue(e instanceof JMXOperationException);
        }
        // AttributeNotFoundException
        try {
            JVMMemoryInfo jvmMemoryInfo1 = memory.getNoHeapMemoryInfo();
        } catch (Exception e) {
            assertTrue(e instanceof JMXOperationException);
        }
        // MBeanException
        try {
            JVMMemoryInfo jvmMemoryInfo1 = memory.getNoHeapMemoryInfo();
        } catch (Exception e) {
            assertTrue(e instanceof JMXOperationException);
        }

        // MalformedObjectNameException
        try {
            Mockito.doAnswer(invocation -> {
                throw new MalformedObjectNameException();
            }).when(mBeanServerConnection).getAttribute(Mockito.any(ObjectName.class), Mockito.anyString());
            JVMMemoryInfo jvmMemoryInfo1 = memory.getNoHeapMemoryInfo();
        } catch (Exception e) {
            assertTrue(e instanceof JMXOperationException);
        }

        Mockito.verify(mBeanServerConnection, Mockito.times(7)).getAttribute(Mockito.any(ObjectName.class), Mockito.anyString());

        mBeanServerUtil.close();
    }


}
