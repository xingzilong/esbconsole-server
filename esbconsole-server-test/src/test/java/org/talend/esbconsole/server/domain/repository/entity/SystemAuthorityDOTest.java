package org.talend.esbconsole.server.domain.repository.entity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * {@link SystemAuthorityDO} 单元测试
 *
 * @author xingzilong
 * @date 2023/10/18
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class SystemAuthorityDOTest {

    public void init(SystemAuthorityDO systemAuthorityDO) {
        systemAuthorityDO.setId(1L);
        systemAuthorityDO.setName("test-name");
        systemAuthorityDO.setParentId(2L);
        systemAuthorityDO.setRouteName("test-password");
        systemAuthorityDO.setRoutePath("test-email");
        systemAuthorityDO.setRouteComponent("test-number");
        systemAuthorityDO.setOrderNum(1);
        systemAuthorityDO.setRouteLevel(1);
        systemAuthorityDO.setIcon("test-number");
        systemAuthorityDO.setAuthorityCode("test-password");
        systemAuthorityDO.setAuthorityKey("test-email");
        systemAuthorityDO.setDescription("test-number");
        systemAuthorityDO.setDeleteFlag("1");
        systemAuthorityDO.setType("1");
        systemAuthorityDO.setStatus("1");
        systemAuthorityDO.setCreateTime(LocalDateTime.MIN);
        systemAuthorityDO.setUpdateTime(LocalDateTime.MIN);
    }

    @Test
    public void test() {

        SystemAuthorityDO systemAuthorityDO = new SystemAuthorityDO();
        SystemAuthorityDO systemAuthorityDO1 = new SystemAuthorityDO();

        assertTrue(systemAuthorityDO.equals(systemAuthorityDO1));

        systemAuthorityDO.hashCode();
        systemAuthorityDO.toString();
        init(systemAuthorityDO);
        systemAuthorityDO.hashCode();

        SystemAuthorityDO systemAuthorityDO2 = systemAuthorityDO;
        assertTrue(systemAuthorityDO2.equals(systemAuthorityDO));

        assertFalse(systemAuthorityDO1.equals(systemAuthorityDO));
        assertFalse(systemAuthorityDO1.equals(null));

        init(systemAuthorityDO1);
        assertTrue(systemAuthorityDO1.equals(systemAuthorityDO));

        systemAuthorityDO1.setId(11L);
        assertFalse(systemAuthorityDO1.equals(systemAuthorityDO));

        init(systemAuthorityDO1);
        systemAuthorityDO1.setName("test-systemnames");
        assertFalse(systemAuthorityDO1.equals(systemAuthorityDO));

        init(systemAuthorityDO1);
        systemAuthorityDO1.setParentId(112L);
        assertFalse(systemAuthorityDO1.equals(systemAuthorityDO));

        init(systemAuthorityDO1);
        systemAuthorityDO1.setRouteName("test-systemnames");
        assertFalse(systemAuthorityDO1.equals(systemAuthorityDO));

        init(systemAuthorityDO1);
        systemAuthorityDO1.setRoutePath("test-systemnames");
        assertFalse(systemAuthorityDO1.equals(systemAuthorityDO));

        init(systemAuthorityDO1);
        systemAuthorityDO1.setRouteComponent("test-systemnames");
        assertFalse(systemAuthorityDO1.equals(systemAuthorityDO));

        init(systemAuthorityDO1);
        systemAuthorityDO1.setOrderNum(11);
        assertFalse(systemAuthorityDO1.equals(systemAuthorityDO));

        init(systemAuthorityDO1);
        systemAuthorityDO1.setRouteLevel(22);
        assertFalse(systemAuthorityDO1.equals(systemAuthorityDO));

        init(systemAuthorityDO1);
        systemAuthorityDO1.setIcon("test-systemnames");
        assertFalse(systemAuthorityDO1.equals(systemAuthorityDO));

        init(systemAuthorityDO1);
        systemAuthorityDO1.setAuthorityCode("test-systemnames");
        assertFalse(systemAuthorityDO1.equals(systemAuthorityDO));

        init(systemAuthorityDO1);
        systemAuthorityDO1.setAuthorityKey("test-systemnames");
        assertFalse(systemAuthorityDO1.equals(systemAuthorityDO));

        init(systemAuthorityDO1);
        systemAuthorityDO1.setDescription("test-systemnames");
        assertFalse(systemAuthorityDO1.equals(systemAuthorityDO));

        init(systemAuthorityDO1);
        systemAuthorityDO1.setDeleteFlag("test-systemnames");
        assertFalse(systemAuthorityDO1.equals(systemAuthorityDO));

        init(systemAuthorityDO1);
        systemAuthorityDO1.setType("test-systemnames");
        assertFalse(systemAuthorityDO1.equals(systemAuthorityDO));

        init(systemAuthorityDO1);
        systemAuthorityDO1.setStatus("11");
        assertFalse(systemAuthorityDO1.equals(systemAuthorityDO));

        init(systemAuthorityDO1);
        systemAuthorityDO1.setCreateTime(LocalDateTime.MIN.plusDays(1L));
        assertFalse(systemAuthorityDO1.equals(systemAuthorityDO));

        init(systemAuthorityDO1);
        systemAuthorityDO1.setUpdateTime(LocalDateTime.MIN.plusDays(1L));
        assertFalse(systemAuthorityDO1.equals(systemAuthorityDO));
    }
}
