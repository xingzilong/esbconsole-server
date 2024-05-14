package org.talend.esbconsole.server.domain.api.param;

import org.talend.esbconsole.server.tools.base.request.TimeInterval;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * {@link ConsumerPageQueryParam} 单元测试
 *
 * @author xingzilong
 * @date 2021/10/24
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class ConsumerPageQueryParamTest {

    private void init(ConsumerPageQueryParam param) {
        param.setCreateTime(new TimeInterval());
        param.setCreateTimeSort("t");
        param.setIp("127.0.0.1");
        param.setStatus("0");
        param.setSystemName("test");
    }

    @Test
    public void test() {
        ConsumerPageQueryParam param = new ConsumerPageQueryParam();
        ConsumerPageQueryParam consumerPageQueryParam = param;
        assertTrue(param.equals(consumerPageQueryParam));

        consumerPageQueryParam = new ConsumerPageQueryParam();
        assertTrue(param.equals(consumerPageQueryParam));
        assertFalse(param.equals(null));

        init(consumerPageQueryParam);
        consumerPageQueryParam.toString();
        consumerPageQueryParam.hashCode();
        assertFalse(param.equals(consumerPageQueryParam));

        init(param);
        TimeInterval timeInterval = new TimeInterval();
        timeInterval.setEndTime("");
        timeInterval.setStartTime("");
        param.setCreateTime(timeInterval);
        assertFalse(param.equals(consumerPageQueryParam));

        init(param);
        param.setCreateTimeSort("tt");
        assertFalse(param.equals(consumerPageQueryParam));

        init(param);
        param.setIp("");
        assertFalse(param.equals(consumerPageQueryParam));

        init(param);
        param.setStatus("1");
        assertFalse(param.equals(consumerPageQueryParam));

        init(param);
        param.setSystemName("tt");
        assertFalse(param.equals(consumerPageQueryParam));


    }
}
