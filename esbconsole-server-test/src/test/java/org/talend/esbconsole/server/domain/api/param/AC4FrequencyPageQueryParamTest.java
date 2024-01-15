package org.talend.esbconsole.server.domain.api.param;

import org.talend.esbconsole.server.tools.base.request.TimeInterval;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * {@link AC4FrequencyPageQueryParam} 单元测试
 *
 * @author xingzilong
 * @date 2023/10/24
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class AC4FrequencyPageQueryParamTest {

    private void init(AC4FrequencyPageQueryParam param) {
        param.setServiceName("test");
        param.setCreateTime(new TimeInterval());
        param.setCreateTimeSort("t");
        param.setType("single");
        param.setStatus("0");
    }

    @Test
    public void test() {
        AC4FrequencyPageQueryParam param = new AC4FrequencyPageQueryParam();
        AC4FrequencyPageQueryParam ac4FrequencyPageQueryParam = new AC4FrequencyPageQueryParam();

        assertTrue(param.equals(ac4FrequencyPageQueryParam));
        assertFalse(param.equals(null));

        init(ac4FrequencyPageQueryParam);
        assertFalse(param.equals(ac4FrequencyPageQueryParam));

        ac4FrequencyPageQueryParam.toString();
        ac4FrequencyPageQueryParam.hashCode();

        ac4FrequencyPageQueryParam.getCreateTime();
        ac4FrequencyPageQueryParam.getCreateTimeSort();
        ac4FrequencyPageQueryParam.getServiceName();
        ac4FrequencyPageQueryParam.getStatus();
        ac4FrequencyPageQueryParam.getType();

        init(param);
        assertTrue(param.equals(ac4FrequencyPageQueryParam));

        init(param);
        param.setServiceName("tt");
        assertFalse(param.equals(ac4FrequencyPageQueryParam));

        init(param);
        param.setCreateTime(null);
        assertFalse(param.equals(ac4FrequencyPageQueryParam));

        init(param);
        param.setCreateTimeSort("tt");
        assertFalse(param.equals(ac4FrequencyPageQueryParam));

        init(param);
        param.setType("global");
        assertFalse(param.equals(ac4FrequencyPageQueryParam));

        init(param);
        param.setStatus("1");
        assertFalse(param.equals(ac4FrequencyPageQueryParam));

        param.canEqual(ac4FrequencyPageQueryParam);
    }

}
