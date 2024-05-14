package org.talend.esbconsole.server.domain.repository.entity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * {@link ConsumerSystemDO} 单元测试
 *
 * @author xingzilong
 * @date 2021/10/17
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class ConsumerSystemDOTest {

    public void init(ConsumerSystemDO consumerSystemDO) {
        consumerSystemDO.setId("test-id");
        consumerSystemDO.setSystemName("test-systemname");
        consumerSystemDO.setStatus("1");
        consumerSystemDO.setResponsiblePerson("zhangsan");
        consumerSystemDO.setRemark("test");
        consumerSystemDO.setCreateTime(LocalDateTime.MIN);
        consumerSystemDO.setUpdateTime(LocalDateTime.MIN);
    }

    @Test
    public void test() {

        ConsumerSystemDO consumerSystemDO = new ConsumerSystemDO();
        ConsumerSystemDO consumerSystemDO1 = new ConsumerSystemDO();

        assertTrue(consumerSystemDO.equals(consumerSystemDO1));

        consumerSystemDO.hashCode();
        consumerSystemDO.toString();
        init(consumerSystemDO);
        consumerSystemDO.hashCode();

        ConsumerSystemDO consumerSystemDO2 = consumerSystemDO;
        assertTrue(consumerSystemDO2.equals(consumerSystemDO));

        assertFalse(consumerSystemDO1.equals(consumerSystemDO));
        assertFalse(consumerSystemDO1.equals(null));

        init(consumerSystemDO1);
        assertTrue(consumerSystemDO1.equals(consumerSystemDO));

        consumerSystemDO1.setId("test-ids");
        assertFalse(consumerSystemDO1.equals(consumerSystemDO));

        init(consumerSystemDO1);
        consumerSystemDO1.setSystemName("test-systemnames");
        assertFalse(consumerSystemDO1.equals(consumerSystemDO));

        init(consumerSystemDO1);
        consumerSystemDO1.setStatus("11");
        assertFalse(consumerSystemDO1.equals(consumerSystemDO));

        init(consumerSystemDO1);
        consumerSystemDO1.setResponsiblePerson("zhangsans");
        assertFalse(consumerSystemDO1.equals(consumerSystemDO));

        init(consumerSystemDO1);
        consumerSystemDO1.setRemark("tests");
        assertFalse(consumerSystemDO1.equals(consumerSystemDO));

        init(consumerSystemDO1);
        consumerSystemDO1.setCreateTime(LocalDateTime.MIN.plusDays(1L));
        assertFalse(consumerSystemDO1.equals(consumerSystemDO));

        init(consumerSystemDO1);
        consumerSystemDO1.setUpdateTime(LocalDateTime.MIN.plusDays(1L));
        assertFalse(consumerSystemDO1.equals(consumerSystemDO));

        ConsumerSystemDO.builder()
                .id("test-id")
                .systemName("test")
                .status("1")
                .responsiblePerson("zhangsan")
                .remark("test")
                .createTime(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .build();

        ConsumerSystemDO.builder()
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
