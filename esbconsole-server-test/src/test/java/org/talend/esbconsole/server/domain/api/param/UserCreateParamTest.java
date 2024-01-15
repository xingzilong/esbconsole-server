package org.talend.esbconsole.server.domain.api.param;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * {@link UserCreateParam} 单元测试
 *
 * @author xingzilong
 * @date 2023/10/24
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class UserCreateParamTest {

    public void init(UserCreateParam param) {
        param.setEmail("test@163.com");
        param.setName("test");
        param.setPassword("Wps456");
        param.setPhoneNumber("19845673456");
        param.setUserName("sss");
    }

    @Test
    public void test() {

        UserCreateParam param = new UserCreateParam();
        UserCreateParam cr = new UserCreateParam();
        param.hashCode();
        assertTrue(param.equals(cr));
        init(param);
        param.toString();
        param.hashCode();
        UserCreateParam tar = param;
        assertTrue(tar.equals(param));
        assertFalse(cr.equals(param));
        assertFalse(cr.equals(null));
        init(cr);
        assertTrue(cr.equals(param));
        cr.setEmail("test");
        assertFalse(cr.equals(param));
        init(cr);
        cr.setName("ssss");
        assertFalse(cr.equals(param));
        init(cr);
        cr.setPassword("sdasda");
        assertFalse(cr.equals(param));
        init(cr);
        cr.setPhoneNumber("19864781325");
        assertFalse(cr.equals(param));
        cr.setUserName("kkkk");
        assertFalse(cr.equals(param));

    }

}
