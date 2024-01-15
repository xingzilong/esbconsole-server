package org.talend.esbconsole.server.web.api.controller.role.converter;

import org.talend.esbconsole.server.domain.api.param.AssignAuthorityParam;
import org.talend.esbconsole.server.domain.api.param.RoleCreateParam;
import org.talend.esbconsole.server.domain.api.param.RoleModifyParam;
import org.talend.esbconsole.server.domain.api.param.RolePageQueryParam;
import org.talend.esbconsole.server.web.api.controller.role.request.AssignAuthorityRequest;
import org.talend.esbconsole.server.web.api.controller.role.request.RoleCreateRequest;
import org.talend.esbconsole.server.web.api.controller.role.request.RoleModifyRequest;
import org.talend.esbconsole.server.web.api.controller.role.request.RolePageQueryRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * {@link RoleWebConverterImpl} 单元测试
 *
 * @author xingzilong
 * @date 2023/10/10
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class RoleWebConverterImplTest {

    @InjectMocks
    private RoleWebConverterImpl roleWebConverterImpl;

    @Test
    public void req2param4RolePageQueryRequestTest() {
        RolePageQueryRequest request = new RolePageQueryRequest();
        RolePageQueryParam rolePageQueryParam = roleWebConverterImpl.req2param(request);
        assertNotNull(rolePageQueryParam);
        request = null;
        RolePageQueryParam rolePageQueryParam1 = roleWebConverterImpl.req2param(request);
        assertNull(rolePageQueryParam1);
    }

    @Test
    public void req2param4RoleCreateRequestTest() {
        RoleCreateRequest request = new RoleCreateRequest();
        RoleCreateParam roleCreateParam = roleWebConverterImpl.req2param(request);
        assertNotNull(roleCreateParam);
        request = null;
        RoleCreateParam roleCreateParam1 = roleWebConverterImpl.req2param(request);
        assertNull(roleCreateParam1);
    }

    @Test
    public void req2param4RoleModifyRequestTest() {
        RoleModifyRequest request = new RoleModifyRequest();
        RoleModifyParam roleModifyParam = roleWebConverterImpl.req2param(request);
        assertNotNull(roleModifyParam);
        request = null;
        RoleModifyParam roleModifyParam1 = roleWebConverterImpl.req2param(request);
        assertNull(roleModifyParam1);
    }

    @Test
    public void req2param4AssignAuthorityRequestTest() {
        AssignAuthorityRequest request = new AssignAuthorityRequest();
        request.setAuthorityIdList(new HashSet<>());
        AssignAuthorityParam assignAuthorityParam = roleWebConverterImpl.req2param(request);
        assertNotNull(assignAuthorityParam);
        request = null;
        AssignAuthorityParam assignAuthorityParam1 = roleWebConverterImpl.req2param(request);
        assertNull(assignAuthorityParam1);
    }

}
