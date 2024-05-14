package org.talend.esbconsole.server.web.api.controller.joblog.request;

import org.talend.esbconsole.server.tools.base.request.TimeInterval;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * {@link JobLogPageQueryRequest} 单元测试
 *
 * @author xingzilong
 * @date 2021/10/9
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class JobLogPageQueryRequestTest {

    public void init(JobLogPageQueryRequest jobLogPageQueryRequest) {
        TimeInterval timeInterval = new TimeInterval();
        timeInterval.setStartTime("2021-10-09");
        timeInterval.setEndTime("2021-10-10");
        jobLogPageQueryRequest.setServiceName("test-servicename");
        jobLogPageQueryRequest.setJob("test-job");
        jobLogPageQueryRequest.setStatus("success");
        jobLogPageQueryRequest.setExecutTime(timeInterval);
        jobLogPageQueryRequest.setExecutTimeSort("asc");
        jobLogPageQueryRequest.setPageNum(5);
        jobLogPageQueryRequest.setPageSize(10);
    }

    @Test
    public void test() {

        JobLogPageQueryRequest jobLogPageQueryRequest = new JobLogPageQueryRequest();
        JobLogPageQueryRequest jobLogPageQueryRequest1 = new JobLogPageQueryRequest();

        assertTrue(jobLogPageQueryRequest.equals(jobLogPageQueryRequest1));

        jobLogPageQueryRequest.hashCode();
        jobLogPageQueryRequest.toString();
        init(jobLogPageQueryRequest);
        jobLogPageQueryRequest.hashCode();

        JobLogPageQueryRequest jobLogPageQueryRequest2 = jobLogPageQueryRequest;
        assertTrue(jobLogPageQueryRequest2.equals(jobLogPageQueryRequest));

        assertFalse(jobLogPageQueryRequest1.equals(jobLogPageQueryRequest));
        assertFalse(jobLogPageQueryRequest1.equals(null));

        init(jobLogPageQueryRequest1);
        assertTrue(jobLogPageQueryRequest1.equals(jobLogPageQueryRequest));

        jobLogPageQueryRequest1.setServiceName("test-servicename1");
        assertFalse(jobLogPageQueryRequest1.equals(jobLogPageQueryRequest));

        init(jobLogPageQueryRequest1);
        jobLogPageQueryRequest1.setJob("test-job1");
        assertFalse(jobLogPageQueryRequest1.equals(jobLogPageQueryRequest));

        init(jobLogPageQueryRequest1);
        jobLogPageQueryRequest1.setStatus("success1");
        assertFalse(jobLogPageQueryRequest1.equals(jobLogPageQueryRequest));

        init(jobLogPageQueryRequest1);
        jobLogPageQueryRequest1.setExecutTimeSort("asc1");
        assertFalse(jobLogPageQueryRequest1.equals(jobLogPageQueryRequest));

        init(jobLogPageQueryRequest1);
        jobLogPageQueryRequest1.setExecutTime(null);
        assertFalse(jobLogPageQueryRequest1.equals(jobLogPageQueryRequest));
    }

}
