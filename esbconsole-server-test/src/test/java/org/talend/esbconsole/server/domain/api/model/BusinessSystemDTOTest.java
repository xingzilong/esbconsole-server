package org.talend.esbconsole.server.domain.api.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * {@link BusinessSystemDTO} 单元测试
 *
 * @author xingzilong
 * @date 2021/10/23
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class BusinessSystemDTOTest {

    public void init(BusinessSystemDTO businessSystemDTO) {
        businessSystemDTO.setId("test-id");
        businessSystemDTO.setSystemName("test-systemname");
        businessSystemDTO.setStatus("1");
        businessSystemDTO.setResponsiblePerson("zhangsan");
        businessSystemDTO.setRemark("test");
        businessSystemDTO.setCreateTime("2021-10-17");
        businessSystemDTO.setUpdateTime("2021-10-17");
    }

    @Test
    public void test() {

        BusinessSystemDTO businessSystemDTO = new BusinessSystemDTO();
        BusinessSystemDTO businessSystemDTO1 = new BusinessSystemDTO();

        assertTrue(businessSystemDTO.equals(businessSystemDTO1));

        businessSystemDTO.hashCode();
        businessSystemDTO.toString();
        init(businessSystemDTO);
        businessSystemDTO.hashCode();

        BusinessSystemDTO businessSystemDTO2 = businessSystemDTO;
        assertTrue(businessSystemDTO2.equals(businessSystemDTO));

        assertFalse(businessSystemDTO1.equals(businessSystemDTO));
        assertFalse(businessSystemDTO1.equals(null));

        init(businessSystemDTO1);
        assertTrue(businessSystemDTO1.equals(businessSystemDTO));

        businessSystemDTO1.setId("test-ids");
        assertFalse(businessSystemDTO1.equals(businessSystemDTO));

        init(businessSystemDTO1);
        businessSystemDTO1.setSystemName("test-systemnames");
        assertFalse(businessSystemDTO1.equals(businessSystemDTO));

        init(businessSystemDTO1);
        businessSystemDTO1.setStatus("11");
        assertFalse(businessSystemDTO1.equals(businessSystemDTO));

        init(businessSystemDTO1);
        businessSystemDTO1.setResponsiblePerson("zhangsans");
        assertFalse(businessSystemDTO1.equals(businessSystemDTO));

        init(businessSystemDTO1);
        businessSystemDTO1.setRemark("tests");
        assertFalse(businessSystemDTO1.equals(businessSystemDTO));

        init(businessSystemDTO1);
        businessSystemDTO1.setCreateTime("2021-10-16");
        assertFalse(businessSystemDTO1.equals(businessSystemDTO));

        init(businessSystemDTO1);
        businessSystemDTO1.setUpdateTime("2021-10-16");
        assertFalse(businessSystemDTO1.equals(businessSystemDTO));
    }
}
