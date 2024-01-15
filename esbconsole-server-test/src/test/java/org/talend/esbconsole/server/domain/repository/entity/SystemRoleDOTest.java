package org.talend.esbconsole.server.domain.repository.entity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * {@link SystemRoleDO} 单元测试
 *
 * @author xingzilong
 * @date 2023/10/18
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class SystemRoleDOTest {

    public void init(SystemRoleDO systemRoleDO) {
        systemRoleDO.setId("test-id");
        systemRoleDO.setName("test-name");
        systemRoleDO.setRoleName("test-username");
        systemRoleDO.setDescription("test-password");
        systemRoleDO.setStatus("1");
        systemRoleDO.setDeleteFlag("1");
        systemRoleDO.setCreateTime(LocalDateTime.MIN);
        systemRoleDO.setUpdateTime(LocalDateTime.MIN);
    }

    @Test
    public void test() {

        SystemRoleDO systemRoleDO = new SystemRoleDO();
        SystemRoleDO systemRoleDO1 = new SystemRoleDO();

        assertTrue(systemRoleDO.equals(systemRoleDO1));

        systemRoleDO.hashCode();
        systemRoleDO.toString();
        init(systemRoleDO);
        systemRoleDO.hashCode();

        SystemRoleDO systemRoleDO2 = systemRoleDO;
        assertTrue(systemRoleDO2.equals(systemRoleDO));

        assertFalse(systemRoleDO1.equals(systemRoleDO));
        assertFalse(systemRoleDO1.equals(null));

        init(systemRoleDO1);
        assertTrue(systemRoleDO1.equals(systemRoleDO));

        systemRoleDO1.setId("test-ids");
        assertFalse(systemRoleDO1.equals(systemRoleDO));

        init(systemRoleDO1);
        systemRoleDO1.setRoleName("test-systemnames");
        assertFalse(systemRoleDO1.equals(systemRoleDO));

        init(systemRoleDO1);
        systemRoleDO1.setName("test-systemnames");
        assertFalse(systemRoleDO1.equals(systemRoleDO));

        init(systemRoleDO1);
        systemRoleDO1.setDescription("test-systemnames");
        assertFalse(systemRoleDO1.equals(systemRoleDO));

        init(systemRoleDO1);
        systemRoleDO1.setStatus("11");
        assertFalse(systemRoleDO1.equals(systemRoleDO));

        init(systemRoleDO1);
        systemRoleDO1.setDeleteFlag("zhangsans");
        assertFalse(systemRoleDO1.equals(systemRoleDO));

        init(systemRoleDO1);
        systemRoleDO1.setCreateTime(LocalDateTime.MIN.plusDays(1L));
        assertFalse(systemRoleDO1.equals(systemRoleDO));

        init(systemRoleDO1);
        systemRoleDO1.setUpdateTime(LocalDateTime.MIN.plusDays(1L));
        assertFalse(systemRoleDO1.equals(systemRoleDO));

        SystemRoleDO
                .builder()
                .id("test-id")
                .name("test-name")
                .roleName("test-password")
                .description("test-email")
                .status("1")
                .deleteFlag("1")
                .createTime(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .build();

        SystemRoleDO
                .builder()
                .id("test-id")
                .name("test-name")
                .roleName("test-password")
                .description("test-email")
                .status("1")
                .deleteFlag("1")
                .createTime(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .toString();
    }
}
