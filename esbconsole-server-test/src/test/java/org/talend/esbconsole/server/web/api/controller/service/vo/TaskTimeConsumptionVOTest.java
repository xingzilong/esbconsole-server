package org.talend.esbconsole.server.web.api.controller.service.vo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * {@link TaskTimeConsumptionVO} 单元测试
 *
 * @author xingzilong
 * @date 2023/10/10
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class TaskTimeConsumptionVOTest {

    public void init(TaskTimeConsumptionVO vo) {
        vo.setExecutionTime(Arrays.asList("sss"));
        vo.setServiceName("test");
        vo.setTimeConsumed(Arrays.asList(100L));
    }

    @Test
    public void test() {
        TaskTimeConsumptionVO vo = new TaskTimeConsumptionVO();
        TaskTimeConsumptionVO tc = vo;
        vo.hashCode();
        assertTrue(vo.equals(tc));
        assertFalse(vo.equals(null));
        tc = new TaskTimeConsumptionVO();
        init(vo);
        vo.toString();
        vo.hashCode();
        assertFalse(vo.equals(tc));
        init(tc);
        assertTrue(vo.equals(tc));
    }
}
