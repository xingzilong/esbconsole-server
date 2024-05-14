package org.talend.esbconsole.server.domain.api.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * {@link SystemRoleDTO} 单元测试
 *
 * @author xingzilong
 * @date 2021/10/24
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class SystemRoleDTOTest {

    public void init(SystemRoleDTO systemRoleDTO) {
        systemRoleDTO.setId("test-id");
        systemRoleDTO.setName("test-name");
        systemRoleDTO.setRoleName("test-username");
        systemRoleDTO.setDescription("test-password");
        systemRoleDTO.setStatus("1");
        systemRoleDTO.setCreateTime("test");
        systemRoleDTO.setUpdateTime("test");
    }

    @Test
    public void test() {

        SystemRoleDTO systemRoleDTO = new SystemRoleDTO();
        SystemRoleDTO systemRoleDTO1 = new SystemRoleDTO();

        assertTrue(systemRoleDTO.equals(systemRoleDTO1));

        systemRoleDTO.hashCode();
        systemRoleDTO.toString();
        init(systemRoleDTO);
        systemRoleDTO.hashCode();

        SystemRoleDTO systemRoleDTO2 = systemRoleDTO;
        assertTrue(systemRoleDTO2.equals(systemRoleDTO));

        assertFalse(systemRoleDTO1.equals(systemRoleDTO));
        assertFalse(systemRoleDTO1.equals(null));

        init(systemRoleDTO1);
        assertTrue(systemRoleDTO1.equals(systemRoleDTO));

        systemRoleDTO1.setId("test-ids");
        assertFalse(systemRoleDTO1.equals(systemRoleDTO));

        init(systemRoleDTO1);
        systemRoleDTO1.setRoleName("test-systemnames");
        assertFalse(systemRoleDTO1.equals(systemRoleDTO));

        init(systemRoleDTO1);
        systemRoleDTO1.setName("test-systemnames");
        assertFalse(systemRoleDTO1.equals(systemRoleDTO));

        init(systemRoleDTO1);
        systemRoleDTO1.setDescription("test-systemnames");
        assertFalse(systemRoleDTO1.equals(systemRoleDTO));

        init(systemRoleDTO1);
        systemRoleDTO1.setStatus("11");
        assertFalse(systemRoleDTO1.equals(systemRoleDTO));

        init(systemRoleDTO1);
        systemRoleDTO1.setCreateTime("tests");
        assertFalse(systemRoleDTO1.equals(systemRoleDTO));

        init(systemRoleDTO1);
        systemRoleDTO1.setUpdateTime("tests");
        assertFalse(systemRoleDTO1.equals(systemRoleDTO));

    }
}
