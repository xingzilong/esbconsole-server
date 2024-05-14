package org.talend.esbconsole.server.web.api.controller.ac.frequency.vo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * {@link FrequencyControlVO} 单元测试
 *
 * @author xingzilong
 * @date 2021/10/9
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class FrequencyControlVOTest {

    private void init(FrequencyControlVO frequencyControlVO) {
        frequencyControlVO.setCreateTime("");
        frequencyControlVO.setId("123");
        frequencyControlVO.setServiceKey("test");
        frequencyControlVO.setType("global");
        frequencyControlVO.setTimeInterval(10L);
        frequencyControlVO.setThreshold(20L);
        frequencyControlVO.setStatus("0");
        frequencyControlVO.setUpdateTime("");
        frequencyControlVO.setServiceName("test");
    }

    @Test
    public void test() {
        FrequencyControlVO frequencyControlVO = new FrequencyControlVO();
        FrequencyControlVO frequencyControl = new FrequencyControlVO();

        assertTrue(frequencyControlVO.equals(frequencyControl));
        assertFalse(frequencyControlVO.equals(null));

        init(frequencyControl);
        frequencyControl.toString();
        frequencyControl.hashCode();
        assertFalse(frequencyControlVO.equals(frequencyControl));

        frequencyControl.getCreateTime();
        frequencyControl.getId();
        frequencyControl.getServiceKey();
        frequencyControl.getServiceName();
        frequencyControl.getStatus();
        frequencyControl.getThreshold();
        frequencyControl.getTimeInterval();
        frequencyControl.getType();
        frequencyControl.getUpdateTime();

        init(frequencyControlVO);
        assertTrue(frequencyControlVO.equals(frequencyControl));

        init(frequencyControlVO);
        frequencyControlVO.setCreateTime("2021/10/9");
        assertFalse(frequencyControlVO.equals(frequencyControl));

        init(frequencyControlVO);
        frequencyControlVO.setId("45613");
        assertFalse(frequencyControlVO.equals(frequencyControl));

        init(frequencyControlVO);
        frequencyControlVO.setServiceKey("tt");
        assertFalse(frequencyControlVO.equals(frequencyControl));

        init(frequencyControlVO);
        frequencyControlVO.setType("consumer");

        init(frequencyControlVO);
        frequencyControlVO.setTimeInterval(15L);
        assertFalse(frequencyControlVO.equals(frequencyControl));

        init(frequencyControlVO);
        frequencyControlVO.setThreshold(25L);
        assertFalse(frequencyControlVO.equals(frequencyControl));

        init(frequencyControlVO);
        frequencyControlVO.setStatus("1");
        assertFalse(frequencyControlVO.equals(frequencyControl));

        init(frequencyControlVO);
        frequencyControlVO.setUpdateTime("2021/10/9");
        assertFalse(frequencyControlVO.equals(frequencyControl));

        init(frequencyControlVO);
        frequencyControlVO.setServiceName("tt");
        assertFalse(frequencyControlVO.equals(frequencyControl));


    }

}
