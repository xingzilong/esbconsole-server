package org.talend.esbconsole.server.web.api.controller.role.request;

import org.talend.esbconsole.server.tools.base.request.TimeInterval;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * {@link RolePageQueryRequest} 单元测试
 *
 * @author xingzilong
 * @date 2021/10/10
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class RolePageQueryRequestTest {

    public void init(RolePageQueryRequest rolePageQueryRequest) {
        TimeInterval timeInterval = new TimeInterval();
        timeInterval.setStartTime("2021-10-09");
        timeInterval.setEndTime("2021-10-10");
        rolePageQueryRequest.setName("test-name");
        rolePageQueryRequest.setRoleName("test-rolename");
        rolePageQueryRequest.setStatus("0");
        rolePageQueryRequest.setCreateTime(timeInterval);
        rolePageQueryRequest.setCreateTimeSort("asc");
        rolePageQueryRequest.setPageNum(5);
        rolePageQueryRequest.setPageSize(10);
    }

    @Test
    public void test() {

        RolePageQueryRequest rolePageQueryRequest = new RolePageQueryRequest();
        RolePageQueryRequest rolePageQueryRequest1 = new RolePageQueryRequest();

        assertTrue(rolePageQueryRequest.equals(rolePageQueryRequest1));

        rolePageQueryRequest.hashCode();
        rolePageQueryRequest.toString();
        init(rolePageQueryRequest);
        rolePageQueryRequest.hashCode();

        RolePageQueryRequest rolePageQueryRequest2 = rolePageQueryRequest;
        assertTrue(rolePageQueryRequest2.equals(rolePageQueryRequest));

        assertFalse(rolePageQueryRequest1.equals(rolePageQueryRequest));
        assertFalse(rolePageQueryRequest1.equals(null));

        init(rolePageQueryRequest1);
        assertTrue(rolePageQueryRequest1.equals(rolePageQueryRequest));

        rolePageQueryRequest1.setName("test-names");
        assertFalse(rolePageQueryRequest1.equals(rolePageQueryRequest));

        init(rolePageQueryRequest1);
        rolePageQueryRequest1.setRoleName("test-rolenames");
        assertFalse(rolePageQueryRequest1.equals(rolePageQueryRequest));

        init(rolePageQueryRequest1);
        rolePageQueryRequest1.setStatus("1");
        assertFalse(rolePageQueryRequest1.equals(rolePageQueryRequest));

        init(rolePageQueryRequest1);
        rolePageQueryRequest1.setCreateTimeSort("ascs");
        assertFalse(rolePageQueryRequest1.equals(rolePageQueryRequest));

        init(rolePageQueryRequest1);
        rolePageQueryRequest1.setCreateTime(null);
        assertFalse(rolePageQueryRequest1.equals(rolePageQueryRequest));
    }
}
