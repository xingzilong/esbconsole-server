package org.talend.esbconsole.server.domain.repository.entity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * {@link AccessControlIPDO} 单元测试
 *
 * @author xingzilong
 * @date 2021/10/17
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class AccessControlIPDOTest {

    public void init(AccessControlIPDO accessControlIPDO) {
        accessControlIPDO.setId("test-id");
        accessControlIPDO.setServiceKey("test-servicekey");
        accessControlIPDO.setType("test-type");
        accessControlIPDO.setBlackList("127.0.0.1");
        accessControlIPDO.setWhiteList("127.0.0.1");
        accessControlIPDO.setStatus("1");
        accessControlIPDO.setCreateTime(LocalDateTime.MIN);
        accessControlIPDO.setUpdateTime(LocalDateTime.MIN);
    }

    @Test
    public void test() {

        AccessControlIPDO accessControlIPDO = new AccessControlIPDO();
        AccessControlIPDO accessControlIPDO1 = new AccessControlIPDO();

        assertTrue(accessControlIPDO.equals(accessControlIPDO1));

        accessControlIPDO.hashCode();
        accessControlIPDO.toString();
        init(accessControlIPDO);
        accessControlIPDO.hashCode();

        AccessControlIPDO accessControlIPDO2 = accessControlIPDO;
        assertTrue(accessControlIPDO2.equals(accessControlIPDO));

        assertFalse(accessControlIPDO1.equals(accessControlIPDO));
        assertFalse(accessControlIPDO1.equals(null));

        init(accessControlIPDO1);
        assertTrue(accessControlIPDO1.equals(accessControlIPDO));

        accessControlIPDO1.setId("test-ids");
        assertFalse(accessControlIPDO1.equals(accessControlIPDO));

        init(accessControlIPDO1);
        accessControlIPDO1.setServiceKey("test-servicekeys");
        assertFalse(accessControlIPDO1.equals(accessControlIPDO));

        init(accessControlIPDO1);
        accessControlIPDO1.setType("test-types");
        assertFalse(accessControlIPDO1.equals(accessControlIPDO));

        init(accessControlIPDO1);
        accessControlIPDO1.setBlackList("127.0.0.2");
        assertFalse(accessControlIPDO1.equals(accessControlIPDO));

        init(accessControlIPDO1);
        accessControlIPDO1.setWhiteList("127.0.0.2");
        assertFalse(accessControlIPDO1.equals(accessControlIPDO));

        init(accessControlIPDO1);
        accessControlIPDO1.setStatus("11");
        assertFalse(accessControlIPDO1.equals(accessControlIPDO));

        init(accessControlIPDO1);
        accessControlIPDO1.setCreateTime(LocalDateTime.MIN.plusDays(1L));
        assertFalse(accessControlIPDO1.equals(accessControlIPDO));

        init(accessControlIPDO1);
        accessControlIPDO1.setUpdateTime(LocalDateTime.MIN.plusDays(1L));
        assertFalse(accessControlIPDO1.equals(accessControlIPDO));
    }
}
