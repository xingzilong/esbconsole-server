package org.talend.esbconsole.server.web.api.controller.joblog.vo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * {@link JobLogVO} 单元测试
 *
 * @author xingzilong
 * @date 2021/10/9
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class JobLogVOTest {
    public void init(JobLogVO jobLogVO) {
        jobLogVO.setServiceName("test-servicename");
        jobLogVO.setUuid("test-uuid");
        jobLogVO.setJob("test-job");
        jobLogVO.setStatus("test-status");
        jobLogVO.setTime(1L);
        jobLogVO.setExecutTime("2021-10-09");
    }

    @Test
    public void test() {

        JobLogVO jobLogVO = new JobLogVO();
        JobLogVO jobLogVO1 = new JobLogVO();

        assertTrue(jobLogVO.equals(jobLogVO1));

        jobLogVO.hashCode();
        jobLogVO.toString();
        init(jobLogVO);
        jobLogVO.hashCode();

        JobLogVO jobLogVO2 = jobLogVO;
        assertTrue(jobLogVO2.equals(jobLogVO));

        assertFalse(jobLogVO1.equals(jobLogVO));
        assertFalse(jobLogVO1.equals(null));

        init(jobLogVO1);
        assertTrue(jobLogVO1.equals(jobLogVO));
        jobLogVO1.setServiceName("test-servicenames");
        assertFalse(jobLogVO1.equals(jobLogVO));

        init(jobLogVO1);
        jobLogVO1.setUuid("test-uuids");
        assertFalse(jobLogVO1.equals(jobLogVO));

        init(jobLogVO1);
        jobLogVO1.setJob("test-jobs");
        assertFalse(jobLogVO1.equals(jobLogVO));

        init(jobLogVO1);
        jobLogVO1.setStatus("test-statuss");
        assertFalse(jobLogVO1.equals(jobLogVO));

        init(jobLogVO1);
        jobLogVO1.setTime(11L);
        assertFalse(jobLogVO1.equals(jobLogVO));

        init(jobLogVO1);
        jobLogVO1.setExecutTime("2021-10-10");
        assertFalse(jobLogVO1.equals(jobLogVO));

    }
}
