package org.talend.esbconsole.server.web.api.controller.ac.ip.request;

import org.talend.esbconsole.server.tools.base.request.TimeInterval;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * {@link AC4IPPageQueryRequest} 单元测试
 *
 * @author xingzilong
 * @date 2021/10/9
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class AC4IPPageQueryRequestTest {

    private void init(AC4IPPageQueryRequest request) {
        request.setCreateTime(new TimeInterval());
        request.setCreateTimeSort("t");
        request.setPageNum(1);
        request.setPageSize(10);
        request.setStatus("0");
        request.setType("black");

    }

    @Test
    public void test() {
        AC4IPPageQueryRequest request = new AC4IPPageQueryRequest();
        AC4IPPageQueryRequest ac4ipPageQueryRequest = new AC4IPPageQueryRequest();

        assertTrue(request.equals(ac4ipPageQueryRequest));
        assertFalse(request.equals(null));

        init(ac4ipPageQueryRequest);

        ac4ipPageQueryRequest.toString();
        ac4ipPageQueryRequest.hashCode();
        assertFalse(request.equals(ac4ipPageQueryRequest));

        ac4ipPageQueryRequest.getCreateTime();
        ac4ipPageQueryRequest.getCreateTimeSort();
        ac4ipPageQueryRequest.getPageNum();
        ac4ipPageQueryRequest.getServiceName();
        ac4ipPageQueryRequest.getPageSize();
        ac4ipPageQueryRequest.getStatus();
        ac4ipPageQueryRequest.getType();

        init(request);
        assertTrue(request.equals(ac4ipPageQueryRequest));

        TimeInterval timeInterval = new TimeInterval();
        timeInterval.setEndTime("");
        timeInterval.setStartTime("");
        request.setCreateTime(timeInterval);
        assertFalse(request.equals(ac4ipPageQueryRequest));

        init(request);
        request.setCreateTimeSort("tt");
        assertFalse(request.equals(ac4ipPageQueryRequest));

        init(request);
        request.setStatus("1");
        assertFalse(request.equals(ac4ipPageQueryRequest));

        init(request);
        request.setType("white");
        assertFalse(request.equals(ac4ipPageQueryRequest));

        request.canEqual(ac4ipPageQueryRequest);
    }


}
