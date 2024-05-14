package org.talend.esbconsole.server.domain.api.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * {@link AuthorityOtherInfoDTO}单元测试
 *
 * @author xingzilong
 * @date 2021/10/24
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class AuthorityOtherInfoDTOTest {

    public void init(AuthorityOtherInfoDTO dto) {

        dto.setAllAuthoritiesTree(Arrays.asList(new RouteAndAuthorityModel()));
        dto.setAllRoteIdList(new HashSet<Long>(Arrays.asList(1L)));
        dto.setAuthorityIdListByRoleId(new HashSet<Long>(Arrays.asList(3L)));
    }

    @Test
    public void test() {
        AuthorityOtherInfoDTO dto = new AuthorityOtherInfoDTO();
        dto.hashCode();
        assertFalse(dto.equals(null));
        assertTrue(dto.equals(dto));
        AuthorityOtherInfoDTO dtos = new AuthorityOtherInfoDTO();
        assertTrue(dto.equals(dtos));
        init(dto);
        assertFalse(dto.equals(dtos));
        dto.hashCode();
        dto.toString();
        init(dtos);
        AuthorityOtherInfoDTO tas = new AuthorityOtherInfoDTO(Arrays.asList(new RouteAndAuthorityModel()), new HashSet<Long>(Arrays.asList(1L)), new HashSet<Long>(Arrays.asList(3L)));
        //equals方法未完全覆盖
        assertTrue(dto.equals(dtos));
        dtos.setAllAuthoritiesTree(Arrays.asList());
        assertFalse(dto.equals(dtos));
        tas.setAuthorityIdListByRoleId(new HashSet<Long>(Arrays.asList(15L)));
        assertFalse(dto.equals(tas));
    }
}
