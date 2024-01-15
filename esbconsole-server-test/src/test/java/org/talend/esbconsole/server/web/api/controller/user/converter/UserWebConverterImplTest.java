package org.talend.esbconsole.server.web.api.controller.user.converter;

import org.talend.esbconsole.server.web.api.controller.user.request.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.talend.esbconsole.server.domain.api.param.AssignRoleParam;
import org.talend.esbconsole.server.domain.api.param.ResetPasswordParam;
import org.talend.esbconsole.server.domain.api.param.UserCreateParam;
import org.talend.esbconsole.server.domain.api.param.UserModifyParam;
import org.talend.esbconsole.server.domain.api.param.UserPageQueryParam;
import org.talend.esbconsole.server.web.api.controller.user.request.AssignRoleRequest;
import org.talend.esbconsole.server.web.api.controller.user.request.ResetPasswordRequest;
import org.talend.esbconsole.server.web.api.controller.user.request.UserCreateRequest;
import org.talend.esbconsole.server.web.api.controller.user.request.UserModifyRequest;
import org.talend.esbconsole.server.web.api.controller.user.request.UserPageQueryRequest;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * {@link UserWebConverterImpl} 单元测试
 *
 * @author xingzilong
 * @date 2023/10/17
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class UserWebConverterImplTest {

    @InjectMocks
    private UserWebConverterImpl userWebConverterImpl;

    @Test
    public void req2param4UserPageQueryRequestTest() {
        UserPageQueryRequest request = new UserPageQueryRequest();
        UserPageQueryParam userPageQueryParam = userWebConverterImpl.req2param(request);
        assertNotNull(userPageQueryParam);
        request = null;
        UserPageQueryParam userPageQueryParam1 = userWebConverterImpl.req2param(request);
        assertNull(userPageQueryParam1);
    }

    @Test
    public void req2param4UserCreateRequestTest() {
        UserCreateRequest request = new UserCreateRequest();
        UserCreateParam userCreateParam = userWebConverterImpl.req2param(request);
        assertNotNull(userCreateParam);
        request = null;
        UserCreateParam userCreateParam1 = userWebConverterImpl.req2param(request);
        assertNull(userCreateParam1);
    }

    @Test
    public void req2param4UserModifyRequestTest() {
        UserModifyRequest request = new UserModifyRequest();
        UserModifyParam userModifyParam = userWebConverterImpl.req2param(request);
        assertNotNull(userModifyParam);
        request = null;
        UserModifyParam userModifyParam1 = userWebConverterImpl.req2param(request);
        assertNull(userModifyParam1);
    }

    @Test
    public void req2param4ResetPasswordRequestTest() {
        ResetPasswordRequest request = new ResetPasswordRequest();
        ResetPasswordParam resetPasswordParam = userWebConverterImpl.req2param(request);
        assertNotNull(resetPasswordParam);
        request = null;
        ResetPasswordParam resetPasswordParam1 = userWebConverterImpl.req2param(request);
        assertNull(resetPasswordParam1);
    }

    @Test
    public void req2param4AssignRoleRequestTest() {
        AssignRoleRequest request = new AssignRoleRequest();
        AssignRoleParam assignRoleParam = userWebConverterImpl.req2param(request);
        assertNotNull(assignRoleParam);
        request = null;
        AssignRoleParam assignRoleParam1 = userWebConverterImpl.req2param(request);
        assertNull(assignRoleParam1);
    }
}
