package org.talend.esbconsole.server.domain.repository.entity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * {@link SystemUserDO} 单元测试
 *
 * @author xingzilong
 * @date 2023/10/18
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class SystemUserDOTest {

    public void init(SystemUserDO systemUserDO) {
        systemUserDO.setId("test-id");
        systemUserDO.setName("test-name");
        systemUserDO.setUserName("test-username");
        systemUserDO.setPassword("test-password");
        systemUserDO.setEmail("test-email");
        systemUserDO.setPhoneNumber("test-number");
        systemUserDO.setStatus("1");
        systemUserDO.setDeleteFlag("1");
        systemUserDO.setCreateTime(LocalDateTime.MIN);
        systemUserDO.setUpdateTime(LocalDateTime.MIN);
    }

    @Test
    public void test() {

        SystemUserDO systemUserDO = new SystemUserDO();
        SystemUserDO systemUserDO1 = new SystemUserDO();

        assertTrue(systemUserDO.equals(systemUserDO1));

        systemUserDO.hashCode();
        systemUserDO.toString();
        init(systemUserDO);
        systemUserDO.hashCode();

        SystemUserDO systemUserDO2 = systemUserDO;
        assertTrue(systemUserDO2.equals(systemUserDO));

        assertFalse(systemUserDO1.equals(systemUserDO));
        assertFalse(systemUserDO1.equals(null));

        init(systemUserDO1);
        assertTrue(systemUserDO1.equals(systemUserDO));

        systemUserDO1.setId("test-ids");
        assertFalse(systemUserDO1.equals(systemUserDO));

        init(systemUserDO1);
        systemUserDO1.setUserName("test-systemnames");
        assertFalse(systemUserDO1.equals(systemUserDO));

        init(systemUserDO1);
        systemUserDO1.setName("test-systemnames");
        assertFalse(systemUserDO1.equals(systemUserDO));

        init(systemUserDO1);
        systemUserDO1.setPassword("test-systemnames");
        assertFalse(systemUserDO1.equals(systemUserDO));

        init(systemUserDO1);
        systemUserDO1.setEmail("test-systemnames");
        assertFalse(systemUserDO1.equals(systemUserDO));

        init(systemUserDO1);
        systemUserDO1.setPhoneNumber("test-systemnames");
        assertFalse(systemUserDO1.equals(systemUserDO));

        init(systemUserDO1);
        systemUserDO1.setStatus("11");
        assertFalse(systemUserDO1.equals(systemUserDO));

        init(systemUserDO1);
        systemUserDO1.setDeleteFlag("zhangsans");
        assertFalse(systemUserDO1.equals(systemUserDO));

        init(systemUserDO1);
        systemUserDO1.setCreateTime(LocalDateTime.MIN.plusDays(1L));
        assertFalse(systemUserDO1.equals(systemUserDO));

        init(systemUserDO1);
        systemUserDO1.setUpdateTime(LocalDateTime.MIN.plusDays(1L));
        assertFalse(systemUserDO1.equals(systemUserDO));

        SystemUserDO
                .builder()
                .id("test-id")
                .name("test-name")
                .userName("test-name")
                .password("test-password")
                .email("test-email")
                .phoneNumber("test-number")
                .status("1")
                .deleteFlag("1")
                .createTime(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .build();

        SystemUserDO
                .builder()
                .id("test-id")
                .name("test-name")
                .userName("test-name")
                .password("test-password")
                .email("test-email")
                .phoneNumber("test-number")
                .status("1")
                .deleteFlag("1")
                .createTime(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .toString();
    }
}
