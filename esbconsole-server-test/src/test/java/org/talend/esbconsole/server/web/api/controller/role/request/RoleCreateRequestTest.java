package org.talend.esbconsole.server.web.api.controller.role.request;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * {@link RoleCreateRequest} 单元测试
 *
 * @author xingzilong
 * @date 2021/10/10
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class RoleCreateRequestTest {

    public void init(RoleCreateRequest roleCreateRequest) {
        roleCreateRequest.setName("test-name");
        roleCreateRequest.setRoleName("test-rolename");
        roleCreateRequest.setDescription("test-description");
    }

    @Test
    public void test() {

        RoleCreateRequest roleCreateRequest = new RoleCreateRequest();
        RoleCreateRequest roleCreateRequest1 = new RoleCreateRequest();

        assertTrue(roleCreateRequest.equals(roleCreateRequest1));

        roleCreateRequest.hashCode();
        roleCreateRequest.toString();
        init(roleCreateRequest);
        roleCreateRequest.hashCode();

        RoleCreateRequest roleCreateRequest2 = roleCreateRequest;
        assertTrue(roleCreateRequest2.equals(roleCreateRequest));

        assertFalse(roleCreateRequest1.equals(roleCreateRequest));
        assertFalse(roleCreateRequest1.equals(null));

        init(roleCreateRequest1);
        assertTrue(roleCreateRequest1.equals(roleCreateRequest));

        roleCreateRequest1.setName("test-names");
        assertFalse(roleCreateRequest1.equals(roleCreateRequest));

        init(roleCreateRequest1);
        roleCreateRequest1.setRoleName("test-rolenames");
        assertFalse(roleCreateRequest1.equals(roleCreateRequest));

        init(roleCreateRequest1);
        roleCreateRequest1.setDescription("test-descriptions");
        assertFalse(roleCreateRequest1.equals(roleCreateRequest));
    }
}
