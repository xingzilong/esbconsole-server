package org.talend.esbconsole.server.domain.api.param;

import org.talend.esbconsole.server.tools.base.request.TimeInterval;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * {@link APICallAnalysisParam} 单元测试
 *
 * @author xingzilong
 * @date 2023/10/24
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class APICallAnalysisParamTest {

    public void init(APICallAnalysisParam param) {

        param.setConsumerSystem("esb");
        param.setId("1");
        param.setPageNum(1);
        param.setPageSize(10);
        param.setTimeInterval(new TimeInterval());
    }

    @Test
    public void test() {

        APICallAnalysisParam param = new APICallAnalysisParam();
        APICallAnalysisParam ar = param;
        param.hashCode();
        assertTrue(param.equals(ar));
        assertFalse(param.equals(null));
        ar = new APICallAnalysisParam();
        assertTrue(param.equals(ar));
        init(param);
        assertFalse(param.equals(ar));
        param.hashCode();
        param.toString();
        init(ar);
        assertTrue(param.equals(ar));

    }

}
