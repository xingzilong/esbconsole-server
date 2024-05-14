package org.talend.esbconsole.server.web.api.controller.user.request;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * {@link UserCreateRequest} 单元测试
 *
 * @author xingzilong
 * @date 2021/10/9
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class UserCreateRequestTest {

    public void init(UserCreateRequest request) {
        request.setEmail("test@163.com");
        request.setName("test");
        request.setPassword("Wps456");
        request.setPhoneNumber("19845673456");
        request.setUserName("sss");
    }

    @Test
    public void test() {

        UserCreateRequest request = new UserCreateRequest();
        UserCreateRequest cr = new UserCreateRequest();
        request.hashCode();
        assertTrue(request.equals(cr));
        init(request);
        request.toString();
        request.hashCode();
        UserCreateRequest tar = request;
        assertTrue(tar.equals(request));
        assertFalse(cr.equals(request));
        assertFalse(cr.equals(null));
        init(cr);
        assertTrue(cr.equals(request));
        cr.setEmail("test");
        assertFalse(cr.equals(request));
        init(cr);
        cr.setName("ssss");
        assertFalse(cr.equals(request));
        init(cr);
        cr.setPassword("sdasda");
        assertFalse(cr.equals(request));
        init(cr);
        cr.setPhoneNumber("19864781325");
        assertFalse(cr.equals(request));
        cr.setUserName("kkkk");
        assertFalse(cr.equals(request));

    }

}
