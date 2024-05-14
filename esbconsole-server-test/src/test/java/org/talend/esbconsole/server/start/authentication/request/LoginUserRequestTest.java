package org.talend.esbconsole.server.start.authentication.request;

import org.talend.esbconsole.server.tools.base.request.TimeInterval;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * {@link LoginUserRequest} 单元测试
 *
 * @author xingzilong
 * @date 2021/10/17
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class LoginUserRequestTest {

    public void init(LoginUserRequest loginUserRequest) {
        TimeInterval timeInterval = new TimeInterval();
        timeInterval.setStartTime("2021-10-09");
        timeInterval.setEndTime("2021-10-10");
        loginUserRequest.setUserName("test-name");
        loginUserRequest.setPassword("test-password");
    }

    @Test
    public void test() {

        LoginUserRequest loginUserRequest = new LoginUserRequest();
        LoginUserRequest loginUserRequest1 = new LoginUserRequest();

        assertTrue(loginUserRequest.equals(loginUserRequest1));

        loginUserRequest.hashCode();
        loginUserRequest.toString();
        init(loginUserRequest);
        loginUserRequest.hashCode();

        LoginUserRequest loginUserRequest2 = loginUserRequest;
        assertTrue(loginUserRequest2.equals(loginUserRequest));

        assertFalse(loginUserRequest1.equals(loginUserRequest));
        assertFalse(loginUserRequest1.equals(null));

        init(loginUserRequest1);
        assertTrue(loginUserRequest1.equals(loginUserRequest));

        loginUserRequest1.setUserName("test-name1");
        assertFalse(loginUserRequest1.equals(loginUserRequest));

        init(loginUserRequest1);
        loginUserRequest1.setPassword("test-password1");
        assertFalse(loginUserRequest1.equals(loginUserRequest));
    }
}
