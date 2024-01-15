package org.talend.esbconsole.server.domain.api.param;

import org.talend.esbconsole.server.tools.base.request.TimeInterval;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * {@link ServiceTimedTaskParam} 单元测试
 *
 * @author xingzilong
 * @date 2023/10/24
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class ServiceTimedTaskParamTest {

    public void init(ServiceTimedTaskParam param) {

        param.setPageNum(1);
        param.setPageSize(10);
        param.setTaskName("test");
        param.setId("456");
        param.setTimeInterval(new TimeInterval());
    }

    @Test
    public void test() {
        ServiceTimedTaskParam param = new ServiceTimedTaskParam();
        ServiceTimedTaskParam sr = param;
        param.hashCode();
        assertTrue(param.equals(sr));
        assertFalse(param.equals(null));
        sr = new ServiceTimedTaskParam();
        assertTrue(param.equals(sr));
        init(param);
        assertFalse(param.equals(sr));
        param.hashCode();
        param.toString();
        init(sr);
        assertTrue(param.equals(sr));
    }
}
