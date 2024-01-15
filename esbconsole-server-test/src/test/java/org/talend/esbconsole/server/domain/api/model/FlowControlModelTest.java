package org.talend.esbconsole.server.domain.api.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * {@link FlowControlModel} 单元测试
 *
 * @author xingzilong
 * @date 2023/10/23
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class FlowControlModelTest {

    public void init(FlowControlModel flowControlModel) {
        flowControlModel.setId("test-id");
        flowControlModel.setServiceKey("test-servicekey");
        flowControlModel.setType("test-type");
        flowControlModel.setTimeInterval(16L);
        flowControlModel.setIntervalThreshold(16L);
        flowControlModel.setSingleThreshold(16L);
        flowControlModel.setStatus("1");
        flowControlModel.setCreateTime(LocalDateTime.MIN);
        flowControlModel.setUpdateTime(LocalDateTime.MIN);
    }

    @Test
    public void test() {

        FlowControlModel flowControlModel = new FlowControlModel();
        FlowControlModel flowControlModel1 = new FlowControlModel();

        assertTrue(flowControlModel.equals(flowControlModel1));

        flowControlModel.hashCode();
        flowControlModel.toString();
        init(flowControlModel);
        flowControlModel.hashCode();

        FlowControlModel flowControlModel2 = flowControlModel;
        assertTrue(flowControlModel2.equals(flowControlModel));

        assertFalse(flowControlModel1.equals(flowControlModel));
        assertFalse(flowControlModel1.equals(null));

        init(flowControlModel1);
        assertTrue(flowControlModel1.equals(flowControlModel));

        flowControlModel1.setId("test-ids");
        assertFalse(flowControlModel1.equals(flowControlModel));

        init(flowControlModel1);
        flowControlModel1.setServiceKey("test-servicekeys");
        assertFalse(flowControlModel1.equals(flowControlModel));

        init(flowControlModel1);
        flowControlModel1.setType("test-types");
        assertFalse(flowControlModel1.equals(flowControlModel));

        init(flowControlModel1);
        flowControlModel1.setTimeInterval(161L);
        assertFalse(flowControlModel1.equals(flowControlModel));

        init(flowControlModel1);
        flowControlModel1.setIntervalThreshold(161L);
        assertFalse(flowControlModel1.equals(flowControlModel));

        init(flowControlModel1);
        flowControlModel1.setSingleThreshold(161L);
        assertFalse(flowControlModel1.equals(flowControlModel));

        init(flowControlModel1);
        flowControlModel1.setStatus("11");
        assertFalse(flowControlModel1.equals(flowControlModel));

        init(flowControlModel1);
        flowControlModel1.setCreateTime(LocalDateTime.MIN.plusDays(1L));
        assertFalse(flowControlModel1.equals(flowControlModel));

        init(flowControlModel1);
        flowControlModel1.setUpdateTime(LocalDateTime.MIN.plusDays(1L));
        assertFalse(flowControlModel1.equals(flowControlModel));
    }
}
