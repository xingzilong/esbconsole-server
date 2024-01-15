package org.talend.esbconsole.server.domain.api.param;

import org.talend.esbconsole.server.tools.base.request.TimeInterval;
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
public class AC4FlowPageQueryParamTest {


    private void init(AC4FlowPageQueryParam param) {
        param.setCreateTime(new TimeInterval());
        param.setCreateTimeSort("test");
        param.setPageNum(1);
        param.setPageSize(10);
        param.setServiceName("test");
        param.setStatus("0");
        param.setType("single");
    }

    @Test
    public void test() {
        AC4FlowPageQueryParam asAc4FlowPageQueryParam = new AC4FlowPageQueryParam();
        AC4FlowPageQueryParam aQueryParam = new AC4FlowPageQueryParam();
        assertTrue(asAc4FlowPageQueryParam.equals(aQueryParam));

        init(asAc4FlowPageQueryParam);
        asAc4FlowPageQueryParam.toString();

        aQueryParam.equals(asAc4FlowPageQueryParam);
        aQueryParam.equals(null);

        asAc4FlowPageQueryParam.getCreateTimeSort();
        asAc4FlowPageQueryParam.getType();
        asAc4FlowPageQueryParam.getPageNum();
        asAc4FlowPageQueryParam.getServiceName();
        asAc4FlowPageQueryParam.getPageSize();
        asAc4FlowPageQueryParam.getStatus();
        asAc4FlowPageQueryParam.hashCode();
        asAc4FlowPageQueryParam.canEqual(aQueryParam);

        init(aQueryParam);
        assertTrue(aQueryParam.equals(asAc4FlowPageQueryParam));

        init(aQueryParam);
        aQueryParam.setType("consumer");
        assertFalse(aQueryParam.equals(asAc4FlowPageQueryParam));

        init(aQueryParam);
        aQueryParam.setStatus("1");
        assertFalse(aQueryParam.equals(asAc4FlowPageQueryParam));

        init(aQueryParam);
        aQueryParam.setCreateTimeSort("t");
        assertFalse(aQueryParam.equals(asAc4FlowPageQueryParam));

        init(aQueryParam);
        TimeInterval timeInterval = new TimeInterval();
        timeInterval.setEndTime("");
        timeInterval.setStartTime("");
        aQueryParam.setCreateTime(timeInterval);
        assertFalse(aQueryParam.equals(asAc4FlowPageQueryParam));

    }

}
