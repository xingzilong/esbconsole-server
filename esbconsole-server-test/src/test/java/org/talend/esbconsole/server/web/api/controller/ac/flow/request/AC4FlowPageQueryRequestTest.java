package org.talend.esbconsole.server.web.api.controller.ac.flow.request;

import org.talend.esbconsole.server.tools.base.request.TimeInterval;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * {@link AC4FlowPageQueryRequest} 单元测试
 *
 * @author xingzilong
 * @date 2023/10/9
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class AC4FlowPageQueryRequestTest {


    private void init(AC4FlowPageQueryRequest request) {
        request.setCreateTime(new TimeInterval());
        request.setCreateTimeSort("test");
        request.setPageNum(1);
        request.setPageSize(10);
        request.setServiceName("test");
        request.setStatus("0");
        request.setType("single");
    }

    @Test
    public void test() {
        AC4FlowPageQueryRequest asAc4FlowPageQueryRequest = new AC4FlowPageQueryRequest();
        AC4FlowPageQueryRequest aQueryRequest = new AC4FlowPageQueryRequest();
        assertTrue(asAc4FlowPageQueryRequest.equals(aQueryRequest));

        init(asAc4FlowPageQueryRequest);
        asAc4FlowPageQueryRequest.toString();

        aQueryRequest.equals(asAc4FlowPageQueryRequest);
        aQueryRequest.equals(null);

        asAc4FlowPageQueryRequest.getCreateTimeSort();
        asAc4FlowPageQueryRequest.getType();
        asAc4FlowPageQueryRequest.getPageNum();
        asAc4FlowPageQueryRequest.getServiceName();
        asAc4FlowPageQueryRequest.getPageSize();
        asAc4FlowPageQueryRequest.getStatus();
        asAc4FlowPageQueryRequest.hashCode();
        asAc4FlowPageQueryRequest.canEqual(aQueryRequest);

        init(aQueryRequest);
        assertTrue(aQueryRequest.equals(asAc4FlowPageQueryRequest));

        init(aQueryRequest);
        aQueryRequest.setType("consumer");
        assertFalse(aQueryRequest.equals(asAc4FlowPageQueryRequest));

        init(aQueryRequest);
        aQueryRequest.setStatus("1");
        assertFalse(aQueryRequest.equals(asAc4FlowPageQueryRequest));

        init(aQueryRequest);
        aQueryRequest.setCreateTimeSort("t");
        assertFalse(aQueryRequest.equals(asAc4FlowPageQueryRequest));

        init(aQueryRequest);
        TimeInterval timeInterval = new TimeInterval();
        timeInterval.setEndTime("");
        timeInterval.setStartTime("");
        aQueryRequest.setCreateTime(timeInterval);
        assertFalse(aQueryRequest.equals(asAc4FlowPageQueryRequest));

    }

}
