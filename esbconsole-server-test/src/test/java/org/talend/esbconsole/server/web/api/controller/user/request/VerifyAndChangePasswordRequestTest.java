package org.talend.esbconsole.server.web.api.controller.user.request;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * {@link VerifyAndChangePasswordRequest} 单元测试
 *
 * @author xingzilong
 * @date 2021/10/9
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class VerifyAndChangePasswordRequestTest {

    public void init(VerifyAndChangePasswordRequest request) {
        request.setPassword("Wps123456");
    }

    @Test
    public void test() {

        VerifyAndChangePasswordRequest request = new VerifyAndChangePasswordRequest();
        VerifyAndChangePasswordRequest cp = new VerifyAndChangePasswordRequest();
        request.hashCode();
        assertTrue(request.equals(cp));
        init(request);
        request.hashCode();
        request.toString();
        assertEquals("Wps123456", request.getPassword());
        assertFalse(request.equals(cp));
        request.hashCode();
        init(cp);
        assertTrue(request.equals(cp));
        cp.setPassword("wps");
        assertFalse(request.equals(cp));
        assertFalse(request.equals(null));
    }

}
