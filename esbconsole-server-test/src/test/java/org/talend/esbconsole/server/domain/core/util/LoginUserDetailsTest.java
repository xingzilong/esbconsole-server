package org.talend.esbconsole.server.domain.core.util;

import org.talend.esbconsole.server.domain.repository.entity.SystemUserDO;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Set;

/**
 * {@link LoginUserDetails} 单元测试
 *
 * @author xingzilong
 * @date 2021/10/12
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class LoginUserDetailsTest {

    @InjectMocks
    LoginUserDetails loginUserDetails;

    @Test
    public void test() {
        SystemUserDO systemUser = new SystemUserDO();
        Set<String> authoritiesRaw = new HashSet<String>();
        LoginUserDetails loginUserDetails = new LoginUserDetails(systemUser, authoritiesRaw);
    }

    @Test
    public void getSystemUserTest() {
        loginUserDetails.getSystemUser();
    }

    @Test
    public void setSystemUserTest() {
        SystemUserDO systemUser = new SystemUserDO();
        loginUserDetails.setSystemUser(systemUser);
    }

    @Test
    public void getAuthoritiesRawTest() {
        loginUserDetails.getAuthoritiesRaw();
    }

    @Test
    public void setAuthoritiesRawTest() {
        Set<String> authoritiesRaw = new HashSet<String>();
        loginUserDetails.setAuthoritiesRaw(authoritiesRaw);
    }

    @Test
    public void isAccountNonExpiredTest() {
        loginUserDetails.isAccountNonExpired();
    }

    @Test
    public void isAccountNonLockedTest() {
        loginUserDetails.isAccountNonLocked();
    }

    @Test
    public void isCredentialsNonExpiredTest() {
        loginUserDetails.isCredentialsNonExpired();
    }

    @Test
    public void isEnabledTest() {
        loginUserDetails.isEnabled();
    }
}
