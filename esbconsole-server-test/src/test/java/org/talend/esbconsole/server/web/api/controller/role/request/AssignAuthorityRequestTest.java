package org.talend.esbconsole.server.web.api.controller.role.request;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * {@link AssignAuthorityRequest} 单元测试
 *
 * @author xingzilong
 * @date 2023/10/10
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class AssignAuthorityRequestTest {

    public void init(AssignAuthorityRequest assignAuthorityRequest) {
        assignAuthorityRequest.setRoleId("test-id");
        assignAuthorityRequest.setAuthorityIdList(new HashSet<>());
    }

    @Test
    public void test() {

        AssignAuthorityRequest assignAuthorityRequest = new AssignAuthorityRequest();
        AssignAuthorityRequest assignAuthorityRequest1 = new AssignAuthorityRequest();

        assertTrue(assignAuthorityRequest.equals(assignAuthorityRequest1));

        assignAuthorityRequest.hashCode();
        assignAuthorityRequest.toString();
        init(assignAuthorityRequest);
        assignAuthorityRequest.hashCode();

        AssignAuthorityRequest assignAuthorityRequest2 = assignAuthorityRequest;
        assertTrue(assignAuthorityRequest2.equals(assignAuthorityRequest));

        assertFalse(assignAuthorityRequest1.equals(assignAuthorityRequest));
        assertFalse(assignAuthorityRequest1.equals(null));

        init(assignAuthorityRequest1);
        assertTrue(assignAuthorityRequest1.equals(assignAuthorityRequest));

        assignAuthorityRequest1.setRoleId("test-ids");
        assertFalse(assignAuthorityRequest1.equals(assignAuthorityRequest));

        init(assignAuthorityRequest1);
        assignAuthorityRequest1.setAuthorityIdList(null);
        assertFalse(assignAuthorityRequest1.equals(assignAuthorityRequest));
    }
}
