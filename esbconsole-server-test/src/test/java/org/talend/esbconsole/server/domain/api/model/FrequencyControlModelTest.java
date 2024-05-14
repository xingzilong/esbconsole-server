package org.talend.esbconsole.server.domain.api.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * {@link FrequencyControlModel} 单元测试
 *
 * @author xingzilong
 * @date 2021/10/23
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class FrequencyControlModelTest {

    public void init(FrequencyControlModel frequencyControlModel) {
        frequencyControlModel.setId("test-id");
        frequencyControlModel.setServiceKey("test-servicekey");
        frequencyControlModel.setType("test-type");
        frequencyControlModel.setTimeInterval(16L);
        frequencyControlModel.setThreshold(16L);
        frequencyControlModel.setStatus("1");
        frequencyControlModel.setCreateTime(LocalDateTime.MIN);
        frequencyControlModel.setUpdateTime(LocalDateTime.MIN);
    }

    @Test
    public void test() {

        FrequencyControlModel frequencyControlModel = new FrequencyControlModel();
        FrequencyControlModel frequencyControlModel1 = new FrequencyControlModel();

        assertTrue(frequencyControlModel.equals(frequencyControlModel1));

        frequencyControlModel.hashCode();
        frequencyControlModel.toString();
        init(frequencyControlModel);
        frequencyControlModel.hashCode();

        FrequencyControlModel frequencyControlModel2 = frequencyControlModel;
        assertTrue(frequencyControlModel2.equals(frequencyControlModel));

        assertFalse(frequencyControlModel1.equals(frequencyControlModel));
        assertFalse(frequencyControlModel1.equals(null));

        init(frequencyControlModel1);
        assertTrue(frequencyControlModel1.equals(frequencyControlModel));

        frequencyControlModel1.setId("test-ids");
        assertFalse(frequencyControlModel1.equals(frequencyControlModel));

        init(frequencyControlModel1);
        frequencyControlModel1.setServiceKey("test-servicekeys");
        assertFalse(frequencyControlModel1.equals(frequencyControlModel));

        init(frequencyControlModel1);
        frequencyControlModel1.setType("test-types");
        assertFalse(frequencyControlModel1.equals(frequencyControlModel));

        init(frequencyControlModel1);
        frequencyControlModel1.setTimeInterval(161L);
        assertFalse(frequencyControlModel1.equals(frequencyControlModel));

        init(frequencyControlModel1);
        frequencyControlModel1.setThreshold(161L);
        assertFalse(frequencyControlModel1.equals(frequencyControlModel));

        init(frequencyControlModel1);
        frequencyControlModel1.setStatus("11");
        assertFalse(frequencyControlModel1.equals(frequencyControlModel));

        init(frequencyControlModel1);
        frequencyControlModel1.setCreateTime(LocalDateTime.MIN.plusDays(1L));
        assertFalse(frequencyControlModel1.equals(frequencyControlModel));

        init(frequencyControlModel1);
        frequencyControlModel1.setUpdateTime(LocalDateTime.MIN.plusDays(1L));
        assertFalse(frequencyControlModel1.equals(frequencyControlModel));
    }
}
