package org.talend.esbconsole.server.domain.api.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * {@link SystemUserDTO} 单元测试
 *
 * @author xingzilong
 * @date 2023/10/24
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class SystemUserDTOTest {

    public void init(SystemUserDTO systemUserDTO) {
        systemUserDTO.setId("test-id");
        systemUserDTO.setName("test-name");
        systemUserDTO.setUserName("test-username");
        systemUserDTO.setEmail("test-email");
        systemUserDTO.setPhoneNumber("test-number");
        systemUserDTO.setStatus("1");
        systemUserDTO.setCreateTime("test");
        systemUserDTO.setUpdateTime("test");
    }

    @Test
    public void test() {

        SystemUserDTO systemUserDTO = new SystemUserDTO();
        SystemUserDTO systemUserDTO1 = new SystemUserDTO();

        assertTrue(systemUserDTO.equals(systemUserDTO1));

        systemUserDTO.hashCode();
        systemUserDTO.toString();
        init(systemUserDTO);
        systemUserDTO.hashCode();

        SystemUserDTO systemUserDTO2 = systemUserDTO;
        assertTrue(systemUserDTO2.equals(systemUserDTO));

        assertFalse(systemUserDTO1.equals(systemUserDTO));
        assertFalse(systemUserDTO1.equals(null));

        init(systemUserDTO1);
        assertTrue(systemUserDTO1.equals(systemUserDTO));

        systemUserDTO1.setId("test-ids");
        assertFalse(systemUserDTO1.equals(systemUserDTO));

        init(systemUserDTO1);
        systemUserDTO1.setUserName("test-systemnames");
        assertFalse(systemUserDTO1.equals(systemUserDTO));

        init(systemUserDTO1);
        systemUserDTO1.setName("test-systemnames");
        assertFalse(systemUserDTO1.equals(systemUserDTO));

        init(systemUserDTO1);
        systemUserDTO1.setEmail("test-systemnames");
        assertFalse(systemUserDTO1.equals(systemUserDTO));

        init(systemUserDTO1);
        systemUserDTO1.setPhoneNumber("test-systemnames");
        assertFalse(systemUserDTO1.equals(systemUserDTO));

        init(systemUserDTO1);
        systemUserDTO1.setStatus("11");
        assertFalse(systemUserDTO1.equals(systemUserDTO));

        init(systemUserDTO1);
        systemUserDTO1.setCreateTime("tests");
        assertFalse(systemUserDTO1.equals(systemUserDTO));

        init(systemUserDTO1);
        systemUserDTO1.setUpdateTime("tests");
        assertFalse(systemUserDTO1.equals(systemUserDTO));
    }
}
