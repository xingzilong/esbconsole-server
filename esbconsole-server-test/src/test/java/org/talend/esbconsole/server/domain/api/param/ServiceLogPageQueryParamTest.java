package org.talend.esbconsole.server.domain.api.param;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * {@link ServiceLogPageQueryParam} 单元测试
 *
 * @author xingzilong
 * @date 2021/10/24
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class ServiceLogPageQueryParamTest {

    public void init(ServiceLogPageQueryParam param) {

        param.setType("soap");
        param.setBusinessSystemId("esb");
        param.setConsumerIp("127.0.0.1");
        param.setHttpStatus("success");
        param.setPageNum(1);
        param.setPageSize(10);
        param.setServiceName("test");
        param.setTimeSort("6789");
        param.setRequestTime(null);

    }

    @Test
    public void test() {
        ServiceLogPageQueryParam param = new ServiceLogPageQueryParam();
        ServiceLogPageQueryParam qr = new ServiceLogPageQueryParam();
        param.hashCode();
        assertTrue(param.equals(qr));
        init(param);
        assertFalse(param.equals(qr));
        assertFalse(param.equals(null));
        ServiceLogPageQueryParam pqr = param;
        assertTrue(param.equals(pqr));
        param.toString();
        param.hashCode();
        init(qr);
        assertTrue(param.equals(qr));
        qr.setType("restful");
        assertFalse(param.equals(qr));
    }
}
