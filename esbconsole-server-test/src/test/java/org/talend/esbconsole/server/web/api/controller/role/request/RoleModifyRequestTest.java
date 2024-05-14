package org.talend.esbconsole.server.web.api.controller.role.request;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * {@link RoleModifyRequest} 单元测试
 *
 * @author xingzilong
 * @date 2021/10/10
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class RoleModifyRequestTest {

    public void init(RoleModifyRequest roleModifyRequest) {
        roleModifyRequest.setId("test-id");
        roleModifyRequest.setName("test-name");
        roleModifyRequest.setRoleName("test-rolename");
        roleModifyRequest.setDescription("test-description");
    }

    @Test
    public void test() {

        RoleModifyRequest roleModifyRequest = new RoleModifyRequest();
        RoleModifyRequest roleModifyRequest1 = new RoleModifyRequest();

        assertTrue(roleModifyRequest.equals(roleModifyRequest1));

        roleModifyRequest.hashCode();
        roleModifyRequest.toString();
        init(roleModifyRequest);
        roleModifyRequest.hashCode();

        RoleModifyRequest roleModifyRequest2 = roleModifyRequest;
        assertTrue(roleModifyRequest2.equals(roleModifyRequest));

        assertFalse(roleModifyRequest1.equals(roleModifyRequest));
        assertFalse(roleModifyRequest1.equals(null));

        init(roleModifyRequest1);
        assertTrue(roleModifyRequest1.equals(roleModifyRequest));

        roleModifyRequest1.setId("test-ids");
        assertFalse(roleModifyRequest1.equals(roleModifyRequest));

        init(roleModifyRequest1);
        roleModifyRequest1.setName("test-names");
        assertFalse(roleModifyRequest1.equals(roleModifyRequest));

        init(roleModifyRequest1);
        roleModifyRequest1.setRoleName("test-rolenames");
        assertFalse(roleModifyRequest1.equals(roleModifyRequest));

        init(roleModifyRequest1);
        roleModifyRequest1.setDescription("test-descriptions");
        assertFalse(roleModifyRequest1.equals(roleModifyRequest));
    }
}
