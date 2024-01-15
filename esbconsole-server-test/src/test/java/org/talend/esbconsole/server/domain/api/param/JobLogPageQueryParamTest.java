package org.talend.esbconsole.server.domain.api.param;

import org.talend.esbconsole.server.tools.base.request.TimeInterval;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * {@link JobLogPageQueryParam} 单元测试
 *
 * @author xingzilong
 * @date 2023/10/24
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class JobLogPageQueryParamTest {

    public void init(JobLogPageQueryParam jobLogPageQueryParam) {
        TimeInterval timeInterval = new TimeInterval();
        timeInterval.setStartTime("2023-10-09");
        timeInterval.setEndTime("2023-10-10");
        jobLogPageQueryParam.setServiceName("test-servicename");
        jobLogPageQueryParam.setJob("test-job");
        jobLogPageQueryParam.setStatus("success");
        jobLogPageQueryParam.setExecutTime(timeInterval);
        jobLogPageQueryParam.setExecutTimeSort("asc");
        jobLogPageQueryParam.setPageNum(5);
        jobLogPageQueryParam.setPageSize(10);
    }

    @Test
    public void test() {

        JobLogPageQueryParam jobLogPageQueryParam = new JobLogPageQueryParam();
        JobLogPageQueryParam jobLogPageQueryParam1 = new JobLogPageQueryParam();

        assertTrue(jobLogPageQueryParam.equals(jobLogPageQueryParam1));

        jobLogPageQueryParam.hashCode();
        jobLogPageQueryParam.toString();
        init(jobLogPageQueryParam);
        jobLogPageQueryParam.hashCode();

        JobLogPageQueryParam jobLogPageQueryParam2 = jobLogPageQueryParam;
        assertTrue(jobLogPageQueryParam2.equals(jobLogPageQueryParam));

        assertFalse(jobLogPageQueryParam1.equals(jobLogPageQueryParam));
        assertFalse(jobLogPageQueryParam1.equals(null));

        init(jobLogPageQueryParam1);
        assertTrue(jobLogPageQueryParam1.equals(jobLogPageQueryParam));

        jobLogPageQueryParam1.setServiceName("test-servicename1");
        assertFalse(jobLogPageQueryParam1.equals(jobLogPageQueryParam));

        init(jobLogPageQueryParam1);
        jobLogPageQueryParam1.setJob("test-job1");
        assertFalse(jobLogPageQueryParam1.equals(jobLogPageQueryParam));

        init(jobLogPageQueryParam1);
        jobLogPageQueryParam1.setStatus("success1");
        assertFalse(jobLogPageQueryParam1.equals(jobLogPageQueryParam));

        init(jobLogPageQueryParam1);
        jobLogPageQueryParam1.setExecutTimeSort("asc1");
        assertFalse(jobLogPageQueryParam1.equals(jobLogPageQueryParam));

        init(jobLogPageQueryParam1);
        jobLogPageQueryParam1.setExecutTime(null);
        assertFalse(jobLogPageQueryParam1.equals(jobLogPageQueryParam));
    }

}
