package org.talend.esbconsole.server.domain.core.impl;

import org.talend.esbconsole.server.domain.repository.entity.SystemUserDO;
import org.talend.esbconsole.server.domain.repository.mapper.SystemAuthorityDAO;
import org.talend.esbconsole.server.domain.repository.mapper.SystemUserDAO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * {@link UserDetailsServiceImpl} 单元测试
 *
 * @author xingzilong
 * @date 2023/10/10
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class UserDetailsServiceImplTest {

    @Mock
    private SystemUserDAO systemUserDAO;
    @Mock
    private SystemAuthorityDAO systemAuthorityDAO;
    @InjectMocks
    private UserDetailsServiceImpl service;

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void loadUserByUsernameTest() {

        SystemUserDO user = Mockito.mock(SystemUserDO.class);
        Mockito.doReturn(user).doReturn(null).when(systemUserDAO).getSystemUserByUserName(Mockito.anyString());
        Mockito.doReturn("1").when(user).getId();
        Mockito.doReturn(new HashSet<String>(Arrays.asList("ssss"))).when(systemAuthorityDAO).listAuthorityKeyByUserId(Mockito.anyString());

        try {
            UserDetails details = service.loadUserByUsername("test");

            assertEquals(1, details.getAuthorities().size());
            Mockito.verify(systemUserDAO).getSystemUserByUserName(Mockito.anyString());
            Mockito.verify(systemAuthorityDAO).listAuthorityKeyByUserId(Mockito.anyString());

            service.loadUserByUsername("test");
        } catch (Exception e) {
            assertEquals("未找到用户：test", e.getMessage());
        }
    }

}
