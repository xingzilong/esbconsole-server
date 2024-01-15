package org.talend.esbconsole.server.domain.repository.entity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * {@link AccessControlFlowDO} 单元测试
 *
 * @author xingzilong
 * @date 2023/10/17
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class AccessControlFlowDOTest {

    public void init(AccessControlFlowDO accessControlFlowDO) {
        accessControlFlowDO.setId("test-id");
        accessControlFlowDO.setServiceKey("test-servicekey");
        accessControlFlowDO.setType("test-type");
        accessControlFlowDO.setTimeInterval(16L);
        accessControlFlowDO.setIntervalThreshold(16L);
        accessControlFlowDO.setSingleThreshold(16L);
        accessControlFlowDO.setStatus("1");
        accessControlFlowDO.setCreateTime(LocalDateTime.MIN);
        accessControlFlowDO.setUpdateTime(LocalDateTime.MIN);
    }

    @Test
    public void test() {

        AccessControlFlowDO accessControlFlowDO = new AccessControlFlowDO();
        AccessControlFlowDO accessControlFlowDO1 = new AccessControlFlowDO();

        assertTrue(accessControlFlowDO.equals(accessControlFlowDO1));

        accessControlFlowDO.hashCode();
        accessControlFlowDO.toString();
        init(accessControlFlowDO);
        accessControlFlowDO.hashCode();

        AccessControlFlowDO accessControlFlowDO2 = accessControlFlowDO;
        assertTrue(accessControlFlowDO2.equals(accessControlFlowDO));

        assertFalse(accessControlFlowDO1.equals(accessControlFlowDO));
        assertFalse(accessControlFlowDO1.equals(null));

        init(accessControlFlowDO1);
        assertTrue(accessControlFlowDO1.equals(accessControlFlowDO));

        accessControlFlowDO1.setId("test-ids");
        assertFalse(accessControlFlowDO1.equals(accessControlFlowDO));

        init(accessControlFlowDO1);
        accessControlFlowDO1.setServiceKey("test-servicekeys");
        assertFalse(accessControlFlowDO1.equals(accessControlFlowDO));

        init(accessControlFlowDO1);
        accessControlFlowDO1.setType("test-types");
        assertFalse(accessControlFlowDO1.equals(accessControlFlowDO));

        init(accessControlFlowDO1);
        accessControlFlowDO1.setTimeInterval(161L);
        assertFalse(accessControlFlowDO1.equals(accessControlFlowDO));

        init(accessControlFlowDO1);
        accessControlFlowDO1.setIntervalThreshold(161L);
        assertFalse(accessControlFlowDO1.equals(accessControlFlowDO));

        init(accessControlFlowDO1);
        accessControlFlowDO1.setSingleThreshold(161L);
        assertFalse(accessControlFlowDO1.equals(accessControlFlowDO));

        init(accessControlFlowDO1);
        accessControlFlowDO1.setStatus("11");
        assertFalse(accessControlFlowDO1.equals(accessControlFlowDO));

        init(accessControlFlowDO1);
        accessControlFlowDO1.setCreateTime(LocalDateTime.MIN.plusDays(1L));
        assertFalse(accessControlFlowDO1.equals(accessControlFlowDO));

        init(accessControlFlowDO1);
        accessControlFlowDO1.setUpdateTime(LocalDateTime.MIN.plusDays(1L));
        assertFalse(accessControlFlowDO1.equals(accessControlFlowDO));
    }
}
