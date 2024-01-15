package org.talend.esbconsole.server.domain.api.query;

import org.talend.esbconsole.server.tools.base.request.TimeInterval;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * {@link ServiceLogPageQuery} 单元测试
 *
 * @author xingzilong
 * @date 2023/10/24
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class ServiceLogPageQueryTest {

    private void init(ServiceLogPageQuery pageQuery) {
        pageQuery.setBusinessSystemId("test");
        pageQuery.setConsumerIp("127.0.0.1");
        pageQuery.setEiTimeStampSort("asc");
        pageQuery.setRequestTime(new TimeInterval());
        pageQuery.setHttpStatus("success");
        pageQuery.setServiceName("test");
        pageQuery.setType("sopa");
    }

    @Test
    public void test() {
        ServiceLogPageQuery serviceLogPageQuery = new ServiceLogPageQuery();
        ServiceLogPageQuery pageQuery = serviceLogPageQuery;
        assertTrue(serviceLogPageQuery.equals(pageQuery));

        pageQuery = new ServiceLogPageQuery();
        assertTrue(serviceLogPageQuery.equals(pageQuery));
        assertFalse(serviceLogPageQuery.equals(null));

        init(serviceLogPageQuery);
        serviceLogPageQuery.toString();
        serviceLogPageQuery.hashCode();

        init(pageQuery);
        assertTrue(serviceLogPageQuery.equals(pageQuery));

        pageQuery.setServiceName("tt");
        assertFalse(serviceLogPageQuery.equals(pageQuery));

        init(pageQuery);
        pageQuery.setEiTimeStampSort("desc");
        assertFalse(serviceLogPageQuery.equals(pageQuery));

        init(pageQuery);
        pageQuery.setConsumerIp("");
        assertFalse(serviceLogPageQuery.equals(pageQuery));

        init(pageQuery);
        pageQuery.setHttpStatus("fail");
        assertFalse(serviceLogPageQuery.equals(pageQuery));

        init(pageQuery);
        TimeInterval timeInterval = new TimeInterval();
        timeInterval.setEndTime("");
        timeInterval.setStartTime("");
        pageQuery.setRequestTime(timeInterval);
        assertFalse(serviceLogPageQuery.equals(pageQuery));

        init(pageQuery);
        pageQuery.setBusinessSystemId("tt");
        assertFalse(serviceLogPageQuery.equals(pageQuery));

        init(pageQuery);
        pageQuery.setType("restful");
        assertFalse(serviceLogPageQuery.equals(pageQuery));

    }

}
