package org.talend.esbconsole.server.web.api.controller.joblog.vo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * {@link JobLogAnalysisVO} 单元测试
 *
 * @author xingzilong
 * @date 2023/10/9
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class JobLogAnalysisVOTest {
    public void init(JobLogAnalysisVO jobLogAnalysisVO) {
        jobLogAnalysisVO.setComponentTimeDataList(new ArrayList<>());
        jobLogAnalysisVO.setJob("test-job");
        jobLogAnalysisVO.setStatus("test-status");
        jobLogAnalysisVO.setDataVolume(1L);
        jobLogAnalysisVO.setLogCatcherRecord(new ArrayList<>());
        jobLogAnalysisVO.setStatCatcherRecord(new ArrayList<>());
        jobLogAnalysisVO.setFlowMeterCatcherRecord(new ArrayList<>());
    }

    @Test
    public void test() {

        JobLogAnalysisVO jobLogAnalysisVO = new JobLogAnalysisVO();
        JobLogAnalysisVO jobLogAnalysisVO1 = new JobLogAnalysisVO();

        assertTrue(jobLogAnalysisVO.equals(jobLogAnalysisVO1));

        jobLogAnalysisVO.hashCode();
        jobLogAnalysisVO.toString();
        init(jobLogAnalysisVO);
        jobLogAnalysisVO.hashCode();

        JobLogAnalysisVO jobLogAnalysisVO2 = jobLogAnalysisVO;
        assertTrue(jobLogAnalysisVO2.equals(jobLogAnalysisVO));

        assertFalse(jobLogAnalysisVO1.equals(jobLogAnalysisVO));
        assertFalse(jobLogAnalysisVO1.equals(null));

        init(jobLogAnalysisVO1);
        assertTrue(jobLogAnalysisVO1.equals(jobLogAnalysisVO));
        jobLogAnalysisVO1.setComponentTimeDataList(null);
        assertFalse(jobLogAnalysisVO1.equals(jobLogAnalysisVO));

        init(jobLogAnalysisVO1);
        jobLogAnalysisVO1.setJob("test-jobs");
        assertFalse(jobLogAnalysisVO1.equals(jobLogAnalysisVO));

        init(jobLogAnalysisVO1);
        jobLogAnalysisVO1.setStatus("test-statuss");
        assertFalse(jobLogAnalysisVO1.equals(jobLogAnalysisVO));

        init(jobLogAnalysisVO1);
        jobLogAnalysisVO1.setDataVolume(11L);
        assertFalse(jobLogAnalysisVO1.equals(jobLogAnalysisVO));

        init(jobLogAnalysisVO1);
        jobLogAnalysisVO1.setLogCatcherRecord(null);
        assertFalse(jobLogAnalysisVO1.equals(jobLogAnalysisVO));

        init(jobLogAnalysisVO1);
        jobLogAnalysisVO1.setStatCatcherRecord(null);
        assertFalse(jobLogAnalysisVO1.equals(jobLogAnalysisVO));

        init(jobLogAnalysisVO1);
        jobLogAnalysisVO1.setFlowMeterCatcherRecord(null);
        assertFalse(jobLogAnalysisVO1.equals(jobLogAnalysisVO));

    }
}
