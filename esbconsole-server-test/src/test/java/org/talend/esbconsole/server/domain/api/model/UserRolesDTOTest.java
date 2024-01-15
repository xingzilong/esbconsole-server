package org.talend.esbconsole.server.domain.api.model;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * {@link UserRolesDTO} 单元测试
 *
 * @author xingzilong
 * @date 2023/10/24
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class UserRolesDTOTest {

    private void init(UserRolesDTO userRolesDTO) {
        userRolesDTO.setAllRoles(new ArrayList<SystemRoleDTO>());
        userRolesDTO.setRoleIdList(new ArrayList<String>());
    }

    @Test
    public void test() {
        UserRolesDTO userRolesDTO = new UserRolesDTO();
        UserRolesDTO dto = userRolesDTO;
        assertTrue(userRolesDTO.equals(dto));

        dto = new UserRolesDTO();
        assertTrue(dto.equals(userRolesDTO));
        assertFalse(dto.equals(null));

        init(dto);
        dto.toString();
        dto.hashCode();

        init(userRolesDTO);
        assertTrue(dto.equals(userRolesDTO));

        userRolesDTO.setAllRoles(null);
        ;
        assertFalse(dto.equals(userRolesDTO));

        init(userRolesDTO);
        userRolesDTO.setRoleIdList(null);
        assertFalse(dto.equals(userRolesDTO));

        UserRolesDTO userRoles = new UserRolesDTO(null, null);
    }

}
