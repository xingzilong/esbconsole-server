package org.talend.esbconsole.server.domain.api.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * {@link SystemAuthorityDTO} 单元测试
 *
 * @author xingzilong
 * @date 2023/10/24
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class SystemAuthorityDTOTest {

    public void init(SystemAuthorityDTO systemAuthorityDTO) {
        systemAuthorityDTO.setName("test-pid");
        systemAuthorityDTO.setParentId(10L);
        systemAuthorityDTO.setRouteName("test-fatherid");
        systemAuthorityDTO.setRoutePath("test-rootid");
        systemAuthorityDTO.setRouteComponent("test-rootid");
        systemAuthorityDTO.setOrderNum(1);
        systemAuthorityDTO.setRouteLevel(1);
        systemAuthorityDTO.setIcon("test");
        systemAuthorityDTO.setAuthorityCode("test");
        systemAuthorityDTO.setAuthorityKey("test");
        systemAuthorityDTO.setDescription("test");
        systemAuthorityDTO.setDeleteFlag("test");
        systemAuthorityDTO.setType("test");
        systemAuthorityDTO.setCreateTime("2023-12-12");
        systemAuthorityDTO.setUpdateTime("2023-12-12");
    }

    @Test
    public void test() {

        SystemAuthorityDTO systemAuthorityDTO = new SystemAuthorityDTO();
        SystemAuthorityDTO systemAuthorityDTO1 = new SystemAuthorityDTO();

        assertTrue(systemAuthorityDTO.equals(systemAuthorityDTO1));

        systemAuthorityDTO.hashCode();
        systemAuthorityDTO.toString();
        init(systemAuthorityDTO);
        systemAuthorityDTO.hashCode();

        SystemAuthorityDTO systemAuthorityDTO2 = systemAuthorityDTO;
        assertTrue(systemAuthorityDTO2.equals(systemAuthorityDTO));

        assertFalse(systemAuthorityDTO1.equals(systemAuthorityDTO));
        assertFalse(systemAuthorityDTO1.equals(null));

        init(systemAuthorityDTO1);
        assertTrue(systemAuthorityDTO1.equals(systemAuthorityDTO));

        init(systemAuthorityDTO1);
        systemAuthorityDTO1.setName("test-pids");
        assertFalse(systemAuthorityDTO1.equals(systemAuthorityDTO));

        init(systemAuthorityDTO1);
        systemAuthorityDTO1.setParentId(100L);
        assertFalse(systemAuthorityDTO1.equals(systemAuthorityDTO));

        init(systemAuthorityDTO1);
        systemAuthorityDTO1.setRouteName("test-fatherids");
        assertFalse(systemAuthorityDTO1.equals(systemAuthorityDTO));

        init(systemAuthorityDTO1);
        systemAuthorityDTO1.setRoutePath("test-rootids");
        assertFalse(systemAuthorityDTO1.equals(systemAuthorityDTO));

        init(systemAuthorityDTO1);
        systemAuthorityDTO1.setRouteComponent("test-rootids");
        assertFalse(systemAuthorityDTO1.equals(systemAuthorityDTO));

        init(systemAuthorityDTO1);
        systemAuthorityDTO1.setOrderNum(2);
        assertFalse(systemAuthorityDTO1.equals(systemAuthorityDTO));

        init(systemAuthorityDTO1);
        systemAuthorityDTO1.setRouteLevel(2);
        assertFalse(systemAuthorityDTO1.equals(systemAuthorityDTO));

        init(systemAuthorityDTO1);
        systemAuthorityDTO1.setIcon("tests");
        assertFalse(systemAuthorityDTO1.equals(systemAuthorityDTO));

        init(systemAuthorityDTO1);
        systemAuthorityDTO1.setAuthorityCode("tests");
        assertFalse(systemAuthorityDTO1.equals(systemAuthorityDTO));

        init(systemAuthorityDTO1);
        systemAuthorityDTO1.setAuthorityKey("tests");
        assertFalse(systemAuthorityDTO1.equals(systemAuthorityDTO));

        init(systemAuthorityDTO1);
        systemAuthorityDTO1.setDescription("tests");
        assertFalse(systemAuthorityDTO1.equals(systemAuthorityDTO));

        init(systemAuthorityDTO1);
        systemAuthorityDTO1.setDeleteFlag("tests");
        assertFalse(systemAuthorityDTO1.equals(systemAuthorityDTO));

        init(systemAuthorityDTO1);
        systemAuthorityDTO1.setType("tests");
        assertFalse(systemAuthorityDTO1.equals(systemAuthorityDTO));

        init(systemAuthorityDTO1);
        systemAuthorityDTO1.setCreateTime("2023-12-13");
        assertFalse(systemAuthorityDTO1.equals(systemAuthorityDTO));

        init(systemAuthorityDTO1);
        systemAuthorityDTO1.setUpdateTime("2023-12-13");
        assertFalse(systemAuthorityDTO1.equals(systemAuthorityDTO));
    }
}
