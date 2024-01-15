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
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * {@link MemoryPool} 单元测试
 *
 * @author xingzilong
 * @date 2023/10/13
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class MemoryPoolTest {

    @InjectMocks
    private MemoryPool memoryPool;

    @Test
    public void getMemoryNamesTest() throws IOException {
        MBeanServerConnection mBeanServerConnection = Mockito.mock(MBeanServerConnection.class);

        Set<ObjectName> objectNames = Mockito.mock(HashSet.class);
        Iterator<ObjectName> iterator = Mockito.mock(Iterator.class);
        ObjectName objectName = Mockito.mock(ObjectName.class);
        MockedStatic<MBeanServerUtil> mBeanServerUtil = Mockito.mockStatic(MBeanServerUtil.class);
        mBeanServerUtil.when(() -> MBeanServerUtil.getMbeanServerConnection()).thenReturn(mBeanServerConnection);
        Mockito.doReturn(objectNames)
                .doThrow(new IOException("test"))
                .when(mBeanServerConnection).queryNames(Mockito.any(ObjectName.class), Mockito.any());

        Mockito.doReturn(iterator).when(objectNames).iterator();
        Mockito.doReturn(true).doReturn(false).when(iterator).hasNext();
        Mockito.doReturn(objectName).when(iterator).next();

        memoryPool.getMemoryNames();

        // IOException
        try {
            memoryPool.getMemoryNames();
        } catch (Exception e) {
//            assertEquals("JMX操作发生异常，原因：ReflectionException", e.getMessage());
        }

        // MalformedObjectNameException
        try {
            Mockito.doAnswer(invocation -> {
                throw new MalformedObjectNameException();
            }).when(mBeanServerConnection).queryNames(Mockito.any(ObjectName.class), Mockito.any());
            memoryPool.getMemoryNames();
        } catch (Exception e) {
            assertTrue(e instanceof JMXOperationException);
        }

        Mockito.verify(mBeanServerConnection, Mockito.times(3)).queryNames(Mockito.any(ObjectName.class), Mockito.any());
        Mockito.verify(objectNames).iterator();
        Mockito.verify(iterator, Mockito.times(2)).hasNext();
        Mockito.verify(iterator).next();

        mBeanServerUtil.close();
    }

    @Test
    public void getMemoryInfoTest() throws ReflectionException, AttributeNotFoundException, InstanceNotFoundException, MBeanException, IOException, MalformedObjectNameException {

        MBeanServerConnection mBeanServerConnection = Mockito.mock(MBeanServerConnection.class);
        CompositeData usage = Mockito.mock(CompositeData.class);

        Set<ObjectName> objectNames = Mockito.mock(HashSet.class);
        Iterator<ObjectName> iterator = Mockito.mock(Iterator.class);
        ObjectName objectName = Mockito.mock(ObjectName.class);
        MockedStatic<MBeanServerUtil> mBeanServerUtil = Mockito.mockStatic(MBeanServerUtil.class);
        mBeanServerUtil.when(() -> MBeanServerUtil.getMbeanServerConnection()).thenReturn(mBeanServerConnection);
        Mockito.doReturn(objectNames).when(mBeanServerConnection).queryNames(Mockito.any(ObjectName.class), Mockito.any());

        Mockito.doReturn(iterator).when(objectNames).iterator();
        Mockito.doReturn(true).doReturn(false).when(iterator).hasNext();
        Mockito.doReturn(objectName).when(iterator).next();

        Mockito.doReturn("name")
                .doReturn("type")
                .doReturn(usage)
                .doThrow(new ReflectionException(new Exception()))
                .doThrow(new InstanceNotFoundException("test"))
                .doThrow(new IOException("test"))
                .doThrow(new AttributeNotFoundException("test"))
                .doThrow(new MBeanException(new Exception()))
                .when(mBeanServerConnection).getAttribute(Mockito.any(ObjectName.class), Mockito.anyString());

        List<JVMMemoryInfo> memoryInfo = memoryPool.getMemoryInfo();
        assertNotNull(memoryInfo);

        // ReflectionException
        try {
            List<JVMMemoryInfo> memoryInfo1 = memoryPool.getMemoryInfo();
        } catch (Exception e) {
            assertTrue(e instanceof JMXOperationException);
        }
        // InstanceNotFoundException
        try {
            List<JVMMemoryInfo> memoryInfo1 = memoryPool.getMemoryInfo();
        } catch (Exception e) {
            assertTrue(e instanceof JMXOperationException);
        }
        // IOException
        try {
            List<JVMMemoryInfo> memoryInfo1 = memoryPool.getMemoryInfo();
        } catch (Exception e) {
            assertTrue(e instanceof JMXOperationException);
        }
        // AttributeNotFoundException
        try {
            List<JVMMemoryInfo> memoryInfo1 = memoryPool.getMemoryInfo();
        } catch (Exception e) {
            assertTrue(e instanceof JMXOperationException);
        }
        // MBeanException
        try {
            List<JVMMemoryInfo> memoryInfo1 = memoryPool.getMemoryInfo();
        } catch (Exception e) {
            assertTrue(e instanceof JMXOperationException);
        }
        Mockito.verify(mBeanServerConnection).queryNames(Mockito.any(ObjectName.class), Mockito.any());
        Mockito.verify(objectNames).iterator();
        Mockito.verify(iterator, Mockito.times(2)).hasNext();
        Mockito.verify(iterator).next();

        Mockito.verify(mBeanServerConnection, Mockito.times(8)).getAttribute(Mockito.any(ObjectName.class), Mockito.anyString());

        mBeanServerUtil.close();
    }


}
