package org.talend.esbconsole.server.web.api.controller.server.vo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * {@link JVMMemoryInfoVO} 单元测试
 *
 * @author xingzilong
 * @date 2021/10/9
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class JVMMemoryInfoVOTest {

    public void init(JVMMemoryInfoVO jvmMemoryInfoVO) {
        jvmMemoryInfoVO.setName("test-name");
        jvmMemoryInfoVO.setType("test-name");
        jvmMemoryInfoVO.setCommitted(16.6);
        jvmMemoryInfoVO.setInit(16.6);
        jvmMemoryInfoVO.setMax(16.6);
        jvmMemoryInfoVO.setUsed(16.6);
    }

    @Test
    public void test() {

        JVMMemoryInfoVO jvmMemoryInfoVO = new JVMMemoryInfoVO();
        JVMMemoryInfoVO jvmMemoryInfoVO1 = new JVMMemoryInfoVO();

        assertTrue(jvmMemoryInfoVO.equals(jvmMemoryInfoVO1));

        jvmMemoryInfoVO.hashCode();
        jvmMemoryInfoVO.toString();
        init(jvmMemoryInfoVO);
        jvmMemoryInfoVO.hashCode();

        JVMMemoryInfoVO jvmMemoryInfoVO2 = jvmMemoryInfoVO;
        assertTrue(jvmMemoryInfoVO2.equals(jvmMemoryInfoVO));

        assertFalse(jvmMemoryInfoVO1.equals(jvmMemoryInfoVO));
        assertFalse(jvmMemoryInfoVO1.equals(null));

        init(jvmMemoryInfoVO1);
        assertTrue(jvmMemoryInfoVO1.equals(jvmMemoryInfoVO));

        init(jvmMemoryInfoVO1);
        jvmMemoryInfoVO1.setName("test-systemnames");
        assertFalse(jvmMemoryInfoVO1.equals(jvmMemoryInfoVO));

        init(jvmMemoryInfoVO1);
        jvmMemoryInfoVO1.setType("test-nameasdas");
        assertFalse(jvmMemoryInfoVO1.equals(jvmMemoryInfoVO));

        init(jvmMemoryInfoVO1);
        jvmMemoryInfoVO1.setCommitted(17.6);
        assertFalse(jvmMemoryInfoVO1.equals(jvmMemoryInfoVO));

        init(jvmMemoryInfoVO1);
        jvmMemoryInfoVO1.setInit(17.6);
        assertFalse(jvmMemoryInfoVO1.equals(jvmMemoryInfoVO));

        init(jvmMemoryInfoVO1);
        jvmMemoryInfoVO1.setMax(17.6);
        assertFalse(jvmMemoryInfoVO1.equals(jvmMemoryInfoVO));

        init(jvmMemoryInfoVO1);
        jvmMemoryInfoVO1.setUsed(17.6);
        assertFalse(jvmMemoryInfoVO1.equals(jvmMemoryInfoVO));

    }
}