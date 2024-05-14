package org.talend.esbconsole.server.domain.api.param;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * {@link UserModifyParam} 单元测试
 *
 * @author xingzilong
 * @date 2021/10/24
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class UserModifyParamTest {

    public void init(UserModifyParam param) {
        param.setEmail("test@163.com");
        param.setId("1");
        param.setName("test");
        param.setPhoneNumber("19846321756");
        param.setUserName("byTest");
    }

    @Test
    public void test() {
        UserModifyParam param = new UserModifyParam();
        UserModifyParam mr = new UserModifyParam();
        assertTrue(mr.equals(param));
        param.hashCode();
        init(param);
        param.toString();
        param.hashCode();
        assertFalse(mr.equals(param));
        init(mr);
        assertTrue(mr.equals(param));

        param.setEmail("sdsa");
        assertFalse(mr.equals(param));
        init(param);

        param.setId("2");
        assertFalse(param.equals(mr));
        init(param);

        param.setName("lll");
        assertFalse(param.equals(mr));
        init(param);
        param.setPhoneNumber("198456464");
        assertFalse(param.equals(mr));
        init(param);
        param.setUserName("ppp");
        assertFalse(param.equals(mr));
        init(param);
        assertFalse(param.equals(null));

        mr = param;
        assertTrue(mr.equals(param));

    }
}
