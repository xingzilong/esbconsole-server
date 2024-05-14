package org.talend.esbconsole.server.web.api.controller.server.vo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * {@link JVMInfoVO} 单元测试
 *
 * @author xingzilong
 * @date 2021/10/9
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class JVMInfoVOTest {

    public void init(JVMInfoVO jvmInfoVO) {
        jvmInfoVO.setMemoryPool(new ArrayList<>());
        jvmInfoVO.setHeapMemory(new JVMMemoryInfoVO());
        jvmInfoVO.setNoHeapMemory(new JVMMemoryInfoVO());
    }

    @Test
    public void test() {

        JVMInfoVO jvmInfoVO = new JVMInfoVO();
        JVMInfoVO jvmInfoVO1 = new JVMInfoVO();

        assertTrue(jvmInfoVO.equals(jvmInfoVO1));

        jvmInfoVO.hashCode();
        jvmInfoVO.toString();
        init(jvmInfoVO);
        jvmInfoVO.hashCode();

        JVMInfoVO jvmInfoVO2 = jvmInfoVO;
        assertTrue(jvmInfoVO2.equals(jvmInfoVO));

        assertFalse(jvmInfoVO1.equals(jvmInfoVO));
        assertFalse(jvmInfoVO1.equals(null));

        init(jvmInfoVO1);
        assertTrue(jvmInfoVO1.equals(jvmInfoVO));

        init(jvmInfoVO1);
        List<JVMMemoryInfoVO> list = new ArrayList<>();
        list.add(new JVMMemoryInfoVO());
        jvmInfoVO1.setMemoryPool(list);
        assertFalse(jvmInfoVO1.equals(jvmInfoVO));

        init(jvmInfoVO1);
        JVMMemoryInfoVO jvmMemoryInfoVO = new JVMMemoryInfoVO();
        jvmMemoryInfoVO.setUsed(16.6);
        jvmInfoVO1.setHeapMemory(jvmMemoryInfoVO);
        assertFalse(jvmInfoVO1.equals(jvmInfoVO));

        init(jvmInfoVO1);
        jvmInfoVO1.setNoHeapMemory(jvmMemoryInfoVO);
        assertFalse(jvmInfoVO1.equals(jvmInfoVO));

    }
}