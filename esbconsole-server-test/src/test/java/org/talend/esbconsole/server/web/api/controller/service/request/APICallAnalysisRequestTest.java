package org.talend.esbconsole.server.web.api.controller.service.request;

import org.talend.esbconsole.server.tools.base.request.TimeInterval;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * {@link APICallAnalysisRequest} 单元测试
 *
 * @author xingzilong
 * @date 2021/10/10
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class APICallAnalysisRequestTest {

    public void init(APICallAnalysisRequest request) {

        request.setConsumerSystem("esb");
        request.setId("1");
        request.setPageNum(1);
        request.setPageSize(10);
        request.setTimeInterval(new TimeInterval());
    }

    @Test
    public void test() {

        APICallAnalysisRequest request = new APICallAnalysisRequest();
        APICallAnalysisRequest ar = request;
        request.hashCode();
        assertTrue(request.equals(ar));
        assertFalse(request.equals(null));
        ar = new APICallAnalysisRequest();
        assertTrue(request.equals(ar));
        init(request);
        assertFalse(request.equals(ar));
        request.hashCode();
        request.toString();
        init(ar);
        assertTrue(request.equals(ar));

    }

}
