package org.talend.esbconsole.server.domain.api.param;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * {@link RoleCreateParam} 单元测试
 *
 * @author xingzilong
 * @date 2023/10/24
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class RoleCreateParamTest {

    public void init(RoleCreateParam roleCreateParam) {
        roleCreateParam.setName("test-name");
        roleCreateParam.setRoleName("test-rolename");
        roleCreateParam.setDescription("test-description");
    }

    @Test
    public void test() {

        RoleCreateParam roleCreateParam = new RoleCreateParam();
        RoleCreateParam roleCreateParam1 = new RoleCreateParam();

        assertTrue(roleCreateParam.equals(roleCreateParam1));

        roleCreateParam.hashCode();
        roleCreateParam.toString();
        init(roleCreateParam);
        roleCreateParam.hashCode();

        RoleCreateParam roleCreateParam2 = roleCreateParam;
        assertTrue(roleCreateParam2.equals(roleCreateParam));

        assertFalse(roleCreateParam1.equals(roleCreateParam));
        assertFalse(roleCreateParam1.equals(null));

        init(roleCreateParam1);
        assertTrue(roleCreateParam1.equals(roleCreateParam));

        roleCreateParam1.setName("test-names");
        assertFalse(roleCreateParam1.equals(roleCreateParam));

        init(roleCreateParam1);
        roleCreateParam1.setRoleName("test-rolenames");
        assertFalse(roleCreateParam1.equals(roleCreateParam));

        init(roleCreateParam1);
        roleCreateParam1.setDescription("test-descriptions");
        assertFalse(roleCreateParam1.equals(roleCreateParam));
    }
}
