package org.talend.esbconsole.server.domain.api.param;

import org.talend.esbconsole.server.tools.base.request.TimeInterval;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * {@link BusinessPageQueryParam} 单元测试
 *
 * @author xingzilong
 * @date 2023/10/24
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class BusinessPageQueryParamTest {


    private void init(BusinessPageQueryParam param) {
        param.setCreateTime(new TimeInterval());
        param.setCreateTimeSort("t");
        param.setStatus("1");
        param.setSystemName("test");
    }

    @Test
    public void test() {
        BusinessPageQueryParam param = new BusinessPageQueryParam();
        BusinessPageQueryParam businessPageQueryParam = param;
        assertTrue(param.equals(businessPageQueryParam));

        businessPageQueryParam = new BusinessPageQueryParam();
        assertTrue(param.equals(businessPageQueryParam));
        assertFalse(param.equals(null));

        init(businessPageQueryParam);
        assertFalse(businessPageQueryParam.equals(param));
        businessPageQueryParam.toString();
        businessPageQueryParam.hashCode();

        init(param);
        TimeInterval timeInterval = new TimeInterval();
        timeInterval.setEndTime("");
        timeInterval.setStartTime("");
        param.setCreateTime(timeInterval);
        assertFalse(businessPageQueryParam.equals(param));

        init(param);
        param.setCreateTimeSort("tt");
        assertFalse(businessPageQueryParam.equals(param));

        init(param);
        param.setStatus("0");
        assertFalse(businessPageQueryParam.equals(param));

        init(param);
        param.setSystemName("tt");
        assertFalse(businessPageQueryParam.equals(param));

    }
}
