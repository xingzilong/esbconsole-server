package org.talend.esbconsole.server.domain.api.param;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * {@link AC4FrequencyStatusActionParam} 单元测试
 *
 * @author Xingzilong
 * @date 2023/10/24
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class AC4FrequencyStatusActionParamTest {

    private void init(AC4FrequencyStatusActionParam param) {
        param.setId("41231");
        param.setServiceName("test");
        param.setServiceKey("t");
        param.setType("global");
        param.setTimeInterval(5L);
        param.setThreshold(10L);
        param.setStatus("0");
    }

    @Test
    public void test() {
        AC4FrequencyStatusActionParam param = new AC4FrequencyStatusActionParam();
        AC4FrequencyStatusActionParam ac4FrequencyStatusActionParam = new AC4FrequencyStatusActionParam();
        assertTrue(param.equals(ac4FrequencyStatusActionParam));
        assertFalse(param.equals(null));

        init(ac4FrequencyStatusActionParam);
        ac4FrequencyStatusActionParam.toString();
        ac4FrequencyStatusActionParam.hashCode();
        assertFalse(param.equals(ac4FrequencyStatusActionParam));

        ac4FrequencyStatusActionParam.getId();
        ac4FrequencyStatusActionParam.getServiceKey();
        ac4FrequencyStatusActionParam.getServiceName();
        ac4FrequencyStatusActionParam.getStatus();
        ac4FrequencyStatusActionParam.getThreshold();
        ac4FrequencyStatusActionParam.getTimeInterval();
        ac4FrequencyStatusActionParam.getType();

        init(param);
        assertTrue(param.equals(ac4FrequencyStatusActionParam));

        init(param);
        param.setId("123");
        assertFalse(param.equals(ac4FrequencyStatusActionParam));

        init(param);
        param.setServiceName("tt");
        assertFalse(param.equals(ac4FrequencyStatusActionParam));

        init(param);
        param.setServiceKey("tt");
        assertFalse(param.equals(ac4FrequencyStatusActionParam));

        init(param);
        param.setType("consumer");
        assertFalse(param.equals(ac4FrequencyStatusActionParam));

        init(param);
        param.setTimeInterval(10L);
        assertFalse(param.equals(ac4FrequencyStatusActionParam));

        init(param);
        param.setThreshold(20L);
        assertFalse(param.equals(ac4FrequencyStatusActionParam));

        init(param);
        param.setStatus("1");
        assertFalse(param.equals(ac4FrequencyStatusActionParam));
    }
}
