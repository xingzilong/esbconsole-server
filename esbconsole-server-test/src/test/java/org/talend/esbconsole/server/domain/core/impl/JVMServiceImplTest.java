package org.talend.esbconsole.server.domain.core.impl;

import org.talend.esbconsole.server.domain.api.model.JVMDetails;
import org.talend.esbconsole.server.domain.api.model.JVMInfo;
import org.talend.esbconsole.server.domain.api.model.JVMMemoryInfo;
import org.talend.esbconsole.server.domain.core.jmx.java.Memory;
import org.talend.esbconsole.server.domain.core.jmx.java.MemoryPool;
import org.talend.esbconsole.server.domain.core.jmx.java.Runtime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * {@link JVMServiceImpl} 单元测试
 *
 * @author xingzilong
 * @date 2023/10/11
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class JVMServiceImplTest {

    @Mock
    private Memory memory;

    @Mock
    private MemoryPool memoryPool;

    @Mock
    private Runtime runtime;

    @InjectMocks
    private JVMServiceImpl jvmService;

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getESBServerJVMMemoryTest() {
        JVMMemoryInfo memoryInfo = Mockito.mock(JVMMemoryInfo.class);
        JVMMemoryInfo heapMemoryInfo = Mockito.mock(JVMMemoryInfo.class);
        JVMMemoryInfo noHeapMemoryInfo = Mockito.mock(JVMMemoryInfo.class);

        Mockito.doReturn(Arrays.asList(memoryInfo)).when(memoryPool).getMemoryInfo();
        Mockito.doReturn(heapMemoryInfo).when(memory).getHeapMemoryInfo();
        Mockito.doReturn(noHeapMemoryInfo).when(memory).getNoHeapMemoryInfo();

        JVMInfo esbServerJVMMemory = jvmService.getESBServerJVMMemory();

        Mockito.verify(memoryPool).getMemoryInfo();
        Mockito.verify(memory).getHeapMemoryInfo();
        Mockito.verify(memory).getNoHeapMemoryInfo();

        assertEquals(1, esbServerJVMMemory.getMemoryPool().size());
    }

    @Test
    public void getESBServerJVMInfoTest() {
        JVMDetails jvmDetails = Mockito.mock(JVMDetails.class);

        Mockito.doReturn(jvmDetails).when(runtime).getJVMDetails();

        JVMDetails jvmDetailsRS = jvmService.getESBServerJVMInfo();

        Mockito.verify(runtime).getJVMDetails();

        assertNotNull(jvmDetailsRS);
    }
}
