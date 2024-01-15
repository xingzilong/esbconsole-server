package org.talend.esbconsole.server.domain.api.param;

import org.talend.esbconsole.server.tools.base.request.TimeInterval;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * {@link LoginUserParam} 单元测试
 *
 * @author xingzilong
 * @date 2023/10/24
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class LoginUserParamTest {

    public void init(LoginUserParam loginUserParam) {
        TimeInterval timeInterval = new TimeInterval();
        timeInterval.setStartTime("2023-10-09");
        timeInterval.setEndTime("2023-10-10");
        loginUserParam.setUserName("test-name");
        loginUserParam.setPassword("test-password");
    }

    @Test
    public void test() {

        LoginUserParam loginUserParam = new LoginUserParam();
        LoginUserParam loginUserParam1 = new LoginUserParam();

        assertTrue(loginUserParam.equals(loginUserParam1));

        loginUserParam.hashCode();
        loginUserParam.toString();
        init(loginUserParam);
        loginUserParam.hashCode();

        LoginUserParam loginUserParam2 = loginUserParam;
        assertTrue(loginUserParam2.equals(loginUserParam));

        assertFalse(loginUserParam1.equals(loginUserParam));
        assertFalse(loginUserParam1.equals(null));

        init(loginUserParam1);
        assertTrue(loginUserParam1.equals(loginUserParam));

        loginUserParam1.setUserName("test-name1");
        assertFalse(loginUserParam1.equals(loginUserParam));

        init(loginUserParam1);
        loginUserParam1.setPassword("test-password1");
        assertFalse(loginUserParam1.equals(loginUserParam));
    }
}
