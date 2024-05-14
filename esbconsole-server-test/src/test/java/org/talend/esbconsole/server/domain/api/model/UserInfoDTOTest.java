package org.talend.esbconsole.server.domain.api.model;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * {@link UserInfoDTO} 单元测试
 *
 * @author xingzilong
 * @date 2021/10/24
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class UserInfoDTOTest {

    private void init(UserInfoDTO dto) {
        dto.setUserName("admin");
        dto.setAuthorities(new HashSet<String>());
    }

    @Test
    public void test() {
        UserInfoDTO userRolesDTO = new UserInfoDTO();
        UserInfoDTO dto = userRolesDTO;
        assertTrue(userRolesDTO.equals(dto));

        dto = new UserInfoDTO();
        assertTrue(dto.equals(userRolesDTO));
        assertFalse(dto.equals(null));

        init(userRolesDTO);
        userRolesDTO.toString();
        userRolesDTO.hashCode();

        init(dto);
        assertTrue(dto.equals(userRolesDTO));

        dto.setUserName("test");
        assertFalse(dto.equals(userRolesDTO));

        init(dto);
        dto.setAuthorities(null);
        assertFalse(dto.equals(userRolesDTO));

        new UserInfoDTO(null, null);
    }
}
