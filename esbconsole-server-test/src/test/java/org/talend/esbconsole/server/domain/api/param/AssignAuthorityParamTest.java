package org.talend.esbconsole.server.domain.api.param;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * {@link AssignAuthorityParam} 单元测试
 *
 * @author xingzilong
 * @date 2021/10/24
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class AssignAuthorityParamTest {

    public void init(AssignAuthorityParam assignAuthorityParam) {
        assignAuthorityParam.setRoleId("test-id");
        assignAuthorityParam.setAuthorityIdList(new HashSet<>());
    }

    @Test
    public void test() {

        AssignAuthorityParam assignAuthorityParam = new AssignAuthorityParam();
        AssignAuthorityParam assignAuthorityParam1 = new AssignAuthorityParam();

        assertTrue(assignAuthorityParam.equals(assignAuthorityParam1));

        assignAuthorityParam.hashCode();
        assignAuthorityParam.toString();
        init(assignAuthorityParam);
        assignAuthorityParam.hashCode();

        AssignAuthorityParam assignAuthorityParam2 = assignAuthorityParam;
        assertTrue(assignAuthorityParam2.equals(assignAuthorityParam));

        assertFalse(assignAuthorityParam1.equals(assignAuthorityParam));
        assertFalse(assignAuthorityParam1.equals(null));

        init(assignAuthorityParam1);
        assertTrue(assignAuthorityParam1.equals(assignAuthorityParam));

        assignAuthorityParam1.setRoleId("test-ids");
        assertFalse(assignAuthorityParam1.equals(assignAuthorityParam));

        init(assignAuthorityParam1);
        assignAuthorityParam1.setAuthorityIdList(null);
        assertFalse(assignAuthorityParam1.equals(assignAuthorityParam));
    }
}
