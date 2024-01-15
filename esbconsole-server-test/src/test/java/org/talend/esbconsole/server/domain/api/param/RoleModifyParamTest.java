package org.talend.esbconsole.server.domain.api.param;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * {@link RoleModifyParam} 单元测试
 *
 * @author xingzilong
 * @date 2023/10/24
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class RoleModifyParamTest {

    public void init(RoleModifyParam roleModifyParam) {
        roleModifyParam.setId("test-id");
        roleModifyParam.setName("test-name");
        roleModifyParam.setRoleName("test-rolename");
        roleModifyParam.setDescription("test-description");
    }

    @Test
    public void test() {

        RoleModifyParam roleModifyParam = new RoleModifyParam();
        RoleModifyParam roleModifyParam1 = new RoleModifyParam();

        assertTrue(roleModifyParam.equals(roleModifyParam1));

        roleModifyParam.hashCode();
        roleModifyParam.toString();
        init(roleModifyParam);
        roleModifyParam.hashCode();

        RoleModifyParam roleModifyParam2 = roleModifyParam;
        assertTrue(roleModifyParam2.equals(roleModifyParam));

        assertFalse(roleModifyParam1.equals(roleModifyParam));
        assertFalse(roleModifyParam1.equals(null));

        init(roleModifyParam1);
        assertTrue(roleModifyParam1.equals(roleModifyParam));

        roleModifyParam1.setId("test-ids");
        assertFalse(roleModifyParam1.equals(roleModifyParam));

        init(roleModifyParam1);
        roleModifyParam1.setName("test-names");
        assertFalse(roleModifyParam1.equals(roleModifyParam));

        init(roleModifyParam1);
        roleModifyParam1.setRoleName("test-rolenames");
        assertFalse(roleModifyParam1.equals(roleModifyParam));

        init(roleModifyParam1);
        roleModifyParam1.setDescription("test-descriptions");
        assertFalse(roleModifyParam1.equals(roleModifyParam));
    }
}
