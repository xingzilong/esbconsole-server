package org.talend.esbconsole.server.web.api.controller.user.request;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * {@link UserModifyRequest} 单元测试
 *
 * @author xingzilong
 * @date 2021/10/9
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class UserModifyRequestTest {

    public void init(UserModifyRequest request) {
        request.setEmail("test@163.com");
        request.setId("1");
        request.setName("test");
        request.setPhoneNumber("19846321756");
        request.setUserName("byTest");
    }

    @Test
    public void test() {
        UserModifyRequest request = new UserModifyRequest();
        UserModifyRequest mr = new UserModifyRequest();
        assertTrue(mr.equals(request));
        request.hashCode();
        init(request);
        request.toString();
        request.hashCode();
        assertFalse(mr.equals(request));
        init(mr);
        assertTrue(mr.equals(request));

        request.setEmail("sdsa");
        assertFalse(mr.equals(request));
        init(request);

        request.setId("2");
        assertFalse(request.equals(mr));
        init(request);

        request.setName("lll");
        assertFalse(request.equals(mr));
        init(request);
        request.setPhoneNumber("198456464");
        assertFalse(request.equals(mr));
        init(request);
        request.setUserName("ppp");
        assertFalse(request.equals(mr));
        init(request);
        assertFalse(request.equals(null));

        mr = request;
        assertTrue(mr.equals(request));

    }
}
