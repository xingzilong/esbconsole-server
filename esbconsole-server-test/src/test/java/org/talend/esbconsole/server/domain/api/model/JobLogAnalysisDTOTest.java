package org.talend.esbconsole.server.domain.api.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * {@link JobLogAnalysisDTO}单元测试
 *
 * @author xingzilong
 * @date 2023/10/24
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class JobLogAnalysisDTOTest {

    public void init(JobLogAnalysisDTO dto) {
        dto.setComponentTimeDataList(null);
        dto.setDataVolume(1L);
        dto.setFlowMeterCatcherRecord(null);
        dto.setJob("job");
        dto.setLogCatcherRecord(null);
        dto.setStatCatcherRecord(null);
        dto.setStatus("test");
    }

    @Test
    public void test() {
        JobLogAnalysisDTO dto = new JobLogAnalysisDTO();
        dto.hashCode();
        assertFalse(dto.equals(null));
        assertTrue(dto.equals(dto));
        JobLogAnalysisDTO dtos = new JobLogAnalysisDTO();
        assertTrue(dto.equals(dtos));
        init(dto);
        assertFalse(dto.equals(dtos));
        dto.hashCode();
        dto.toString();
        init(dtos);
        //equals方法未完全覆盖
        assertTrue(dto.equals(dtos));
        dtos.setStatus("ok");
        assertFalse(dto.equals(dtos));

        dtos.setStatus("test");
        dtos.setJob("test");
        assertFalse(dto.equals(dtos));

        dtos.setJob("job");
        dtos.setDataVolume(2L);
        assertFalse(dto.equals(dtos));

        dtos.setDataVolume(1L);
        dtos.setComponentTimeDataList(Arrays.asList(new ComponentTimeData()));
        dtos.hashCode();
        assertFalse(dto.equals(dtos));

        dtos.setComponentTimeDataList(null);
        dtos.setFlowMeterCatcherRecord(Arrays.asList(new FlowMeterCatcherDTO()));
        dtos.hashCode();
        assertFalse(dto.equals(dtos));

        dtos.setFlowMeterCatcherRecord(null);
        dtos.setLogCatcherRecord(Arrays.asList(new LogCatcherDTO()));
        dtos.hashCode();
        assertFalse(dto.equals(dtos));

        dtos.setLogCatcherRecord(null);
        dtos.setStatCatcherRecord(Arrays.asList(new StatCatcherDTO()));
        dtos.hashCode();
        assertFalse(dto.equals(dtos));

    }
}
