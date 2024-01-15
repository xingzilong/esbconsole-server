package org.talend.esbconsole.server.domain.api.param;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * {@link AC4FlowPageQueryParam} 单元测试
 *
 * @author xingzilong
 * @date 2023/10/24
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class AC4FlowStatusActionParamTest {

    private void init(AC4FlowStatusActionParam param) {
        param.setId("45451");
        param.setIntervalThreshold(1L);
        param.setServiceKey("t");
        param.setServiceName("test");
        param.setSingleThreshold(1L);
        param.setStatus("0");
        param.setTimeInterval(300L);
        param.setType("single");
    }

    @Test
    public void test() {
        AC4FlowStatusActionParam param = new AC4FlowStatusActionParam();
        AC4FlowStatusActionParam ac4FlowStatusActionParam = new AC4FlowStatusActionParam();

        assertTrue(param.equals(ac4FlowStatusActionParam));
        assertFalse(param.equals(null));
        param.toString();
        param.hashCode();

        init(ac4FlowStatusActionParam);

        param.getId();
        param.getIntervalThreshold();
        param.getServiceKey();
        param.getServiceName();
        param.getSingleThreshold();
        param.getStatus();
        param.getTimeInterval();
        param.getType();

        assertFalse(param.equals(ac4FlowStatusActionParam));

        init(param);
        param.setId("12");
        assertFalse(param.equals(ac4FlowStatusActionParam));

        init(param);
        param.setIntervalThreshold(5L);
        assertFalse(param.equals(ac4FlowStatusActionParam));

        init(param);
        param.setServiceKey("tt");
        assertFalse(param.equals(ac4FlowStatusActionParam));

        init(param);
        param.setServiceName("tt");
        assertFalse(param.equals(ac4FlowStatusActionParam));

        init(param);
        param.setSingleThreshold(5L);
        assertFalse(param.equals(ac4FlowStatusActionParam));

        init(param);
        param.setStatus("1");
        assertFalse(param.equals(ac4FlowStatusActionParam));

        init(param);
        param.setTimeInterval(600L);
        assertFalse(param.equals(ac4FlowStatusActionParam));

        init(param);
        param.setType("global");
        assertFalse(param.equals(ac4FlowStatusActionParam));


        param.canEqual(ac4FlowStatusActionParam);
    }
}
