package org.talend.esbconsole.server.domain.repository.entity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * {@link SystemRoleAuthorityDO} 单元测试
 *
 * @author xingzilong
 * @date 2021/10/18
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class SystemRoleAuthorityDOTest {

    public void init(SystemRoleAuthorityDO systemRoleAuthorityDO) {
        systemRoleAuthorityDO.setId("test-id");
        systemRoleAuthorityDO.setRoleId("test-name");
        systemRoleAuthorityDO.setAuthorityId(2L);
        systemRoleAuthorityDO.setCreateTime(LocalDateTime.MIN);
        systemRoleAuthorityDO.setUpdateTime(LocalDateTime.MIN);
    }

    @Test
    public void test() {

        SystemRoleAuthorityDO systemRoleAuthorityDO = new SystemRoleAuthorityDO();
        SystemRoleAuthorityDO systemRoleAuthorityDO1 = new SystemRoleAuthorityDO();

        assertTrue(systemRoleAuthorityDO.equals(systemRoleAuthorityDO1));

        systemRoleAuthorityDO.hashCode();
        systemRoleAuthorityDO.toString();
        init(systemRoleAuthorityDO);
        systemRoleAuthorityDO.hashCode();

        SystemRoleAuthorityDO systemRoleAuthorityDO2 = systemRoleAuthorityDO;
        assertTrue(systemRoleAuthorityDO2.equals(systemRoleAuthorityDO));

        assertFalse(systemRoleAuthorityDO1.equals(systemRoleAuthorityDO));
        assertFalse(systemRoleAuthorityDO1.equals(null));

        init(systemRoleAuthorityDO1);
        assertTrue(systemRoleAuthorityDO1.equals(systemRoleAuthorityDO));

        systemRoleAuthorityDO1.setId("11L");
        assertFalse(systemRoleAuthorityDO1.equals(systemRoleAuthorityDO));

        init(systemRoleAuthorityDO1);
        systemRoleAuthorityDO1.setRoleId("test-systemnames");
        assertFalse(systemRoleAuthorityDO1.equals(systemRoleAuthorityDO));

        init(systemRoleAuthorityDO1);
        systemRoleAuthorityDO1.setAuthorityId(112L);
        assertFalse(systemRoleAuthorityDO1.equals(systemRoleAuthorityDO));

        init(systemRoleAuthorityDO1);
        systemRoleAuthorityDO1.setCreateTime(LocalDateTime.MIN.plusDays(1L));
        assertFalse(systemRoleAuthorityDO1.equals(systemRoleAuthorityDO));

        init(systemRoleAuthorityDO1);
        systemRoleAuthorityDO1.setUpdateTime(LocalDateTime.MIN.plusDays(1L));
        assertFalse(systemRoleAuthorityDO1.equals(systemRoleAuthorityDO));
    }
}
