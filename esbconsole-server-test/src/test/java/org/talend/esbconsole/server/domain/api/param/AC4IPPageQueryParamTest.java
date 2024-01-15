package org.talend.esbconsole.server.domain.api.param;

import org.talend.esbconsole.server.tools.base.request.TimeInterval;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * {@link AC4IPPageQueryParam} 单元测试
 *
 * @author xingzilong
 * @date 2023/10/24
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class AC4IPPageQueryParamTest {

    private void init(AC4IPPageQueryParam param) {
        param.setCreateTime(new TimeInterval());
        param.setCreateTimeSort("t");
        param.setPageNum(1);
        param.setPageSize(10);
        param.setStatus("0");
        param.setType("black");

    }

    @Test
    public void test() {
        AC4IPPageQueryParam param = new AC4IPPageQueryParam();
        AC4IPPageQueryParam ac4ipPageQueryParam = new AC4IPPageQueryParam();

        assertTrue(param.equals(ac4ipPageQueryParam));
        assertFalse(param.equals(null));

        init(ac4ipPageQueryParam);

        ac4ipPageQueryParam.toString();
        ac4ipPageQueryParam.hashCode();
        assertFalse(param.equals(ac4ipPageQueryParam));

        ac4ipPageQueryParam.getCreateTime();
        ac4ipPageQueryParam.getCreateTimeSort();
        ac4ipPageQueryParam.getPageNum();
        ac4ipPageQueryParam.getServiceName();
        ac4ipPageQueryParam.getPageSize();
        ac4ipPageQueryParam.getStatus();
        ac4ipPageQueryParam.getType();

        init(param);
        assertTrue(param.equals(ac4ipPageQueryParam));

        TimeInterval timeInterval = new TimeInterval();
        timeInterval.setEndTime("");
        timeInterval.setStartTime("");
        param.setCreateTime(timeInterval);
        assertFalse(param.equals(ac4ipPageQueryParam));

        init(param);
        param.setCreateTimeSort("tt");
        assertFalse(param.equals(ac4ipPageQueryParam));

        init(param);
        param.setStatus("1");
        assertFalse(param.equals(ac4ipPageQueryParam));

        init(param);
        param.setType("white");
        assertFalse(param.equals(ac4ipPageQueryParam));

        param.canEqual(ac4ipPageQueryParam);
    }


}
