package org.talend.esbconsole.server.domain.api.param;

import org.talend.esbconsole.server.tools.base.request.TimeInterval;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * {@link RolePageQueryParam} 单元测试
 *
 * @author xingzilong
 * @date 2023/10/24
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class RolePageQueryParamTest {

    public void init(RolePageQueryParam rolePageQueryParam) {
        TimeInterval timeInterval = new TimeInterval();
        timeInterval.setStartTime("2023-10-09");
        timeInterval.setEndTime("2023-10-10");
        rolePageQueryParam.setName("test-name");
        rolePageQueryParam.setRoleName("test-rolename");
        rolePageQueryParam.setStatus("0");
        rolePageQueryParam.setCreateTime(timeInterval);
        rolePageQueryParam.setCreateTimeSort("asc");
        rolePageQueryParam.setPageNum(5);
        rolePageQueryParam.setPageSize(10);
    }

    @Test
    public void test() {

        RolePageQueryParam rolePageQueryParam = new RolePageQueryParam();
        RolePageQueryParam rolePageQueryParam1 = new RolePageQueryParam();

        assertTrue(rolePageQueryParam.equals(rolePageQueryParam1));

        rolePageQueryParam.hashCode();
        rolePageQueryParam.toString();
        init(rolePageQueryParam);
        rolePageQueryParam.hashCode();

        RolePageQueryParam rolePageQueryParam2 = rolePageQueryParam;
        assertTrue(rolePageQueryParam2.equals(rolePageQueryParam));

        assertFalse(rolePageQueryParam1.equals(rolePageQueryParam));
        assertFalse(rolePageQueryParam1.equals(null));

        init(rolePageQueryParam1);
        assertTrue(rolePageQueryParam1.equals(rolePageQueryParam));

        rolePageQueryParam1.setName("test-names");
        assertFalse(rolePageQueryParam1.equals(rolePageQueryParam));

        init(rolePageQueryParam1);
        rolePageQueryParam1.setRoleName("test-rolenames");
        assertFalse(rolePageQueryParam1.equals(rolePageQueryParam));

        init(rolePageQueryParam1);
        rolePageQueryParam1.setStatus("1");
        assertFalse(rolePageQueryParam1.equals(rolePageQueryParam));

        init(rolePageQueryParam1);
        rolePageQueryParam1.setCreateTimeSort("ascs");
        assertFalse(rolePageQueryParam1.equals(rolePageQueryParam));

        init(rolePageQueryParam1);
        rolePageQueryParam1.setCreateTime(null);
        assertFalse(rolePageQueryParam1.equals(rolePageQueryParam));
    }
}
