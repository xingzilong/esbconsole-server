package org.talend.esbconsole.server.domain.repository.entity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * {@link BusinessSystemDO} 单元测试
 *
 * @author xingzilong
 * @date 2023/10/17
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class BusinessSystemDOTest {

    public void init(BusinessSystemDO businessSystemDO) {
        businessSystemDO.setId("test-id");
        businessSystemDO.setSystemName("test-systemname");
        businessSystemDO.setStatus("1");
        businessSystemDO.setResponsiblePerson("zhangsan");
        businessSystemDO.setRemark("test");
        businessSystemDO.setCreateTime(LocalDateTime.MIN);
        businessSystemDO.setUpdateTime(LocalDateTime.MIN);
    }

    @Test
    public void test() {

        BusinessSystemDO businessSystemDO = new BusinessSystemDO();
        BusinessSystemDO businessSystemDO1 = new BusinessSystemDO();

        assertTrue(businessSystemDO.equals(businessSystemDO1));

        businessSystemDO.hashCode();
        businessSystemDO.toString();
        init(businessSystemDO);
        businessSystemDO.hashCode();

        BusinessSystemDO businessSystemDO2 = businessSystemDO;
        assertTrue(businessSystemDO2.equals(businessSystemDO));

        assertFalse(businessSystemDO1.equals(businessSystemDO));
        assertFalse(businessSystemDO1.equals(null));

        init(businessSystemDO1);
        assertTrue(businessSystemDO1.equals(businessSystemDO));

        businessSystemDO1.setId("test-ids");
        assertFalse(businessSystemDO1.equals(businessSystemDO));

        init(businessSystemDO1);
        businessSystemDO1.setSystemName("test-systemnames");
        assertFalse(businessSystemDO1.equals(businessSystemDO));

        init(businessSystemDO1);
        businessSystemDO1.setStatus("11");
        assertFalse(businessSystemDO1.equals(businessSystemDO));

        init(businessSystemDO1);
        businessSystemDO1.setResponsiblePerson("zhangsans");
        assertFalse(businessSystemDO1.equals(businessSystemDO));

        init(businessSystemDO1);
        businessSystemDO1.setRemark("tests");
        assertFalse(businessSystemDO1.equals(businessSystemDO));

        init(businessSystemDO1);
        businessSystemDO1.setCreateTime(LocalDateTime.MIN.plusDays(1L));
        assertFalse(businessSystemDO1.equals(businessSystemDO));

        init(businessSystemDO1);
        businessSystemDO1.setUpdateTime(LocalDateTime.MIN.plusDays(1L));
        assertFalse(businessSystemDO1.equals(businessSystemDO));

        BusinessSystemDO.builder()
                .id("test-id")
                .systemName("test")
                .status("1")
                .responsiblePerson("zhangsan")
                .remark("test")
                .createTime(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .build();

        BusinessSystemDO.builder()
                .id("test-id")
                .systemName("test")
                .status("1")
                .responsiblePerson("zhangsan")
                .remark("test")
                .createTime(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .toString();
    }
}
