package org.talend.esbconsole.server.web.api.controller.server.converter;

import org.talend.esbconsole.server.domain.api.model.JVMInfo;
import org.talend.esbconsole.server.domain.api.model.JVMMemoryInfo;
import org.talend.esbconsole.server.web.api.controller.server.vo.JVMInfoVO;
import org.talend.esbconsole.server.web.api.controller.server.vo.JVMMemoryInfoVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * {@link ServerWebConverterImpl} 单元测试
 *
 * @author xingzilong
 * @date 2021/10/10
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class ServerWebConverterImplTest {

    @InjectMocks
    private ServerWebConverterImpl serverWebConverterImpl;

    @Test
    public void dto2vo4JVMInfoTest() {
        JVMInfo jvmInfo = new JVMInfo();
        jvmInfo.setMemoryPool(Arrays.asList(new JVMMemoryInfo()));
        JVMInfoVO jVMInfoVO = serverWebConverterImpl.dto2vo(jvmInfo);
        assertNotNull(jVMInfoVO);
        jvmInfo = null;
        JVMInfoVO jVMInfoVO1 = serverWebConverterImpl.dto2vo(jvmInfo);
        assertNull(jVMInfoVO1);
    }

    @Test
    public void dto2vo4JVMMemoryInfoTest() {
        JVMMemoryInfo jvmMemoryInfo = new JVMMemoryInfo();
        JVMMemoryInfoVO jVMMemoryInfoVO = serverWebConverterImpl.dto2vo(jvmMemoryInfo);
        assertNotNull(jVMMemoryInfoVO);
        jvmMemoryInfo = null;
        JVMMemoryInfoVO jVMMemoryInfoVO1 = serverWebConverterImpl.dto2vo(jvmMemoryInfo);
        assertNull(jVMMemoryInfoVO1);
    }
}
