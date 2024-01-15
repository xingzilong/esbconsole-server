package org.talend.esbconsole.server.web.api.controller.user.request;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * {@link AssignRoleRequest} 单元测试
 *
 * @author xingzilong
 * @date 2023/10/9
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class AssignRoleRequestTest {


    public AssignRoleRequest getRequest() {

        AssignRoleRequest request = new AssignRoleRequest();
        request.setUserId("1");
        return request;
    }

    @Test
    public void test() {

        Set<String> set = Mockito.mock(Set.class);
        AssignRoleRequest request = getRequest();
        request.setRoleIdList(set);
        request.toString();
        request.hashCode();
        assertFalse(request.equals(null));
        assertTrue(request.equals(request));
        AssignRoleRequest target = getRequest();
        target.setRoleIdList(set);
        assertTrue(request.equals(target));

        target.setRoleIdList(null);
        assertFalse(request.equals(target));

        target.setRoleIdList(set);
        target.setUserId("2");
        assertFalse(request.equals(target));

        AssignRoleRequest rr = new AssignRoleRequest();
        rr.hashCode();
        assertFalse(rr.equals(request));

        rr.setUserId("1");
        assertFalse(rr.equals(request));

    }

}
