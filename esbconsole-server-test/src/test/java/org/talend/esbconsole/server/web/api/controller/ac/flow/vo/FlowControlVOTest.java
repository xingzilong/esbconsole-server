package org.talend.esbconsole.server.web.api.controller.ac.flow.vo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * {@link FlowControlVO} 单元测试
 *
 * @author xingzilong
 * @date 2021/10/9
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class FlowControlVOTest {

    private void init(FlowControlVO flowControlVO) {
        flowControlVO.setCreateTime("");
        flowControlVO.setId("651");
        flowControlVO.setIntervalThreshold(10L);
        flowControlVO.setServiceKey("t");
        flowControlVO.setServiceName("test");
        flowControlVO.setSingleThreshold(15L);
        flowControlVO.setStatus("0");
        flowControlVO.setTimeInterval(500L);
        flowControlVO.setType("single");
        flowControlVO.setUpdateTime("");
    }

    @Test
    public void test() {
        FlowControlVO flowControlVO = new FlowControlVO();
        FlowControlVO flowControl = new FlowControlVO();

        assertTrue(flowControlVO.equals(flowControl));

        init(flowControl);

        flowControl.toString();
        flowControl.hashCode();

        assertFalse(flowControl.equals(flowControlVO));
        assertFalse(flowControl.equals(null));

        flowControl.getCreateTime();
        flowControl.getId();
        flowControl.getIntervalThreshold();
        flowControl.getServiceKey();
        flowControl.getServiceName();
        flowControl.getSingleThreshold();
        flowControl.getStatus();
        flowControl.getTimeInterval();
        flowControl.getType();
        flowControl.getUpdateTime();

        init(flowControlVO);
        flowControlVO.setCreateTime("2021/10/9");
        assertFalse(flowControl.equals(flowControlVO));

        init(flowControlVO);
        flowControlVO.setId("111");
        assertFalse(flowControl.equals(flowControlVO));

        init(flowControlVO);
        flowControlVO.setIntervalThreshold(20L);
        assertFalse(flowControl.equals(flowControlVO));

        init(flowControlVO);
        flowControlVO.setServiceKey("tt");
        assertFalse(flowControl.equals(flowControlVO));

        init(flowControlVO);
        flowControlVO.setServiceName("tt");
        assertFalse(flowControl.equals(flowControlVO));

        init(flowControlVO);
        flowControlVO.setSingleThreshold(20L);
        assertFalse(flowControl.equals(flowControlVO));

        init(flowControlVO);
        flowControlVO.setStatus("1");
        assertFalse(flowControl.equals(flowControlVO));

        init(flowControlVO);
        flowControlVO.setTimeInterval(200L);
        assertFalse(flowControl.equals(flowControlVO));

        init(flowControlVO);
        flowControlVO.setType("global");
        assertFalse(flowControl.equals(flowControlVO));

        init(flowControlVO);
        flowControlVO.setUpdateTime("2021/10/9");
        assertFalse(flowControl.equals(flowControlVO));

    }

}
