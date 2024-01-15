package org.talend.esbconsole.server.domain.repository.entity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * {@link SystemUserRoleDO} 单元测试
 *
 * @author xingzilong
 * @date 2023/10/18
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class SystemUserRoleDOTest {

    public void init(SystemUserRoleDO systemUserRoleDO) {
        systemUserRoleDO.setId("test-id");
        systemUserRoleDO.setUserId("test-name");
        systemUserRoleDO.setRoleId("test-name");
        systemUserRoleDO.setCreateTime(LocalDateTime.MIN);
        systemUserRoleDO.setUpdateTime(LocalDateTime.MIN);
    }

    @Test
    public void test() {

        SystemUserRoleDO systemUserRoleDO = new SystemUserRoleDO();
        SystemUserRoleDO systemUserRoleDO1 = new SystemUserRoleDO();

        assertTrue(systemUserRoleDO.equals(systemUserRoleDO1));

        systemUserRoleDO.hashCode();
        systemUserRoleDO.toString();
        init(systemUserRoleDO);
        systemUserRoleDO.hashCode();

        SystemUserRoleDO systemUserRoleDO2 = systemUserRoleDO;
        assertTrue(systemUserRoleDO2.equals(systemUserRoleDO));

        assertFalse(systemUserRoleDO1.equals(systemUserRoleDO));
        assertFalse(systemUserRoleDO1.equals(null));

        init(systemUserRoleDO1);
        assertTrue(systemUserRoleDO1.equals(systemUserRoleDO));

        systemUserRoleDO1.setId("11L");
        assertFalse(systemUserRoleDO1.equals(systemUserRoleDO));

        init(systemUserRoleDO1);
        systemUserRoleDO1.setUserId("test-systemnames");
        assertFalse(systemUserRoleDO1.equals(systemUserRoleDO));

        init(systemUserRoleDO1);
        systemUserRoleDO1.setRoleId("test-systemnames");
        assertFalse(systemUserRoleDO1.equals(systemUserRoleDO));

        init(systemUserRoleDO1);
        systemUserRoleDO1.setCreateTime(LocalDateTime.MIN.plusDays(1L));
        assertFalse(systemUserRoleDO1.equals(systemUserRoleDO));

        init(systemUserRoleDO1);
        systemUserRoleDO1.setUpdateTime(LocalDateTime.MIN.plusDays(1L));
        assertFalse(systemUserRoleDO1.equals(systemUserRoleDO));
    }
}
