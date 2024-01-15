package org.talend.esbconsole.server.domain.api.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * {@link ConsumerSystemDTO} 单元测试
 *
 * @author xingzilong
 * @date 2023/10/23
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class ConsumerSystemDTOTest {

    public void init(ConsumerSystemDTO consumerSystemDTO) {
        consumerSystemDTO.setId("test-id");
        consumerSystemDTO.setSystemName("test-systemname");
        consumerSystemDTO.setStatus("1");
        consumerSystemDTO.setResponsiblePerson("zhangsan");
        consumerSystemDTO.setRemark("test");
        consumerSystemDTO.setCreateTime("2023-10-17");
        consumerSystemDTO.setUpdateTime("2023-10-17");
    }

    @Test
    public void test() {

        ConsumerSystemDTO consumerSystemDTO = new ConsumerSystemDTO();
        ConsumerSystemDTO consumerSystemDTO1 = new ConsumerSystemDTO();

        assertTrue(consumerSystemDTO.equals(consumerSystemDTO1));

        consumerSystemDTO.hashCode();
        consumerSystemDTO.toString();
        init(consumerSystemDTO);
        consumerSystemDTO.hashCode();

        ConsumerSystemDTO consumerSystemDTO2 = consumerSystemDTO;
        assertTrue(consumerSystemDTO2.equals(consumerSystemDTO));

        assertFalse(consumerSystemDTO1.equals(consumerSystemDTO));
        assertFalse(consumerSystemDTO1.equals(null));

        init(consumerSystemDTO1);
        assertTrue(consumerSystemDTO1.equals(consumerSystemDTO));

        consumerSystemDTO1.setId("test-ids");
        assertFalse(consumerSystemDTO1.equals(consumerSystemDTO));

        init(consumerSystemDTO1);
        consumerSystemDTO1.setSystemName("test-systemnames");
        assertFalse(consumerSystemDTO1.equals(consumerSystemDTO));

        init(consumerSystemDTO1);
        consumerSystemDTO1.setStatus("11");
        assertFalse(consumerSystemDTO1.equals(consumerSystemDTO));

        init(consumerSystemDTO1);
        consumerSystemDTO1.setResponsiblePerson("zhangsans");
        assertFalse(consumerSystemDTO1.equals(consumerSystemDTO));

        init(consumerSystemDTO1);
        consumerSystemDTO1.setRemark("tests");
        assertFalse(consumerSystemDTO1.equals(consumerSystemDTO));

        init(consumerSystemDTO1);
        consumerSystemDTO1.setCreateTime("2023-10-16");
        assertFalse(consumerSystemDTO1.equals(consumerSystemDTO));

        init(consumerSystemDTO1);
        consumerSystemDTO1.setUpdateTime("2023-10-16");
        assertFalse(consumerSystemDTO1.equals(consumerSystemDTO));
    }
}
