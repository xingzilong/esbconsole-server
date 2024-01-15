package org.talend.esbconsole.server.domain.api.param;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * {@link AssignRoleParam} 单元测试
 *
 * @author xingzilong
 * @date 2023/10/24
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class AssignRoleParamTest {


    public AssignRoleParam getParam() {

        AssignRoleParam param = new AssignRoleParam();
        param.setUserId("1");
        return param;
    }

    @Test
    public void test() {

        Set<String> set = Mockito.mock(Set.class);
        AssignRoleParam param = getParam();
        param.setRoleIdList(set);
        param.toString();
        param.hashCode();
        assertFalse(param.equals(null));
        assertTrue(param.equals(param));
        AssignRoleParam target = getParam();
        target.setRoleIdList(set);
        assertTrue(param.equals(target));

        target.setRoleIdList(null);
        assertFalse(param.equals(target));

        target.setRoleIdList(set);
        target.setUserId("2");
        assertFalse(param.equals(target));

        AssignRoleParam rr = new AssignRoleParam();
        rr.hashCode();
        assertFalse(rr.equals(param));

        rr.setUserId("1");
        assertFalse(rr.equals(param));

    }

}
