package org.talend.esbconsole.server.domain.repository.entity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * {@link AccessControlFrequencyDO} 单元测试
 *
 * @author xingzilong
 * @date 2021/10/17
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class AccessControlFrequencyDOTest {

    public void init(AccessControlFrequencyDO accessControlFrequencyDO) {
        accessControlFrequencyDO.setId("test-id");
        accessControlFrequencyDO.setServiceKey("test-servicekey");
        accessControlFrequencyDO.setType("test-type");
        accessControlFrequencyDO.setTimeInterval(16L);
        accessControlFrequencyDO.setThreshold(16L);
        accessControlFrequencyDO.setStatus("1");
        accessControlFrequencyDO.setCreateTime(LocalDateTime.MIN);
        accessControlFrequencyDO.setUpdateTime(LocalDateTime.MIN);
    }

    @Test
    public void test() {

        AccessControlFrequencyDO accessControlFrequencyDO = new AccessControlFrequencyDO();
        AccessControlFrequencyDO accessControlFrequencyDO1 = new AccessControlFrequencyDO();

        assertTrue(accessControlFrequencyDO.equals(accessControlFrequencyDO1));

        accessControlFrequencyDO.hashCode();
        accessControlFrequencyDO.toString();
        init(accessControlFrequencyDO);
        accessControlFrequencyDO.hashCode();

        AccessControlFrequencyDO accessControlFrequencyDO2 = accessControlFrequencyDO;
        assertTrue(accessControlFrequencyDO2.equals(accessControlFrequencyDO));

        assertFalse(accessControlFrequencyDO1.equals(accessControlFrequencyDO));
        assertFalse(accessControlFrequencyDO1.equals(null));

        init(accessControlFrequencyDO1);
        assertTrue(accessControlFrequencyDO1.equals(accessControlFrequencyDO));

        accessControlFrequencyDO1.setId("test-ids");
        assertFalse(accessControlFrequencyDO1.equals(accessControlFrequencyDO));

        init(accessControlFrequencyDO1);
        accessControlFrequencyDO1.setServiceKey("test-servicekeys");
        assertFalse(accessControlFrequencyDO1.equals(accessControlFrequencyDO));

        init(accessControlFrequencyDO1);
        accessControlFrequencyDO1.setType("test-types");
        assertFalse(accessControlFrequencyDO1.equals(accessControlFrequencyDO));

        init(accessControlFrequencyDO1);
        accessControlFrequencyDO1.setTimeInterval(161L);
        assertFalse(accessControlFrequencyDO1.equals(accessControlFrequencyDO));

        init(accessControlFrequencyDO1);
        accessControlFrequencyDO1.setThreshold(161L);
        assertFalse(accessControlFrequencyDO1.equals(accessControlFrequencyDO));

        init(accessControlFrequencyDO1);
        accessControlFrequencyDO1.setStatus("11");
        assertFalse(accessControlFrequencyDO1.equals(accessControlFrequencyDO));

        init(accessControlFrequencyDO1);
        accessControlFrequencyDO1.setCreateTime(LocalDateTime.MIN.plusDays(1L));
        assertFalse(accessControlFrequencyDO1.equals(accessControlFrequencyDO));

        init(accessControlFrequencyDO1);
        accessControlFrequencyDO1.setUpdateTime(LocalDateTime.MIN.plusDays(1L));
        assertFalse(accessControlFrequencyDO1.equals(accessControlFrequencyDO));
    }
}
