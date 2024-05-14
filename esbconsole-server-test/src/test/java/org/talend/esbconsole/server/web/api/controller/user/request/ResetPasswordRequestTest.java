package org.talend.esbconsole.server.web.api.controller.user.request;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * {@link ResetPasswordRequest} 单元测试
 *
 * @author xingzilong
 * @date 2021/10/9
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class ResetPasswordRequestTest {

    public ResetPasswordRequest getRequest() {

        ResetPasswordRequest request = new ResetPasswordRequest();
        request.setId("1");
        request.setPassword("Wps456");
        return request;
    }

    @Test
    public void test() {

        ResetPasswordRequest request = getRequest();
        ResetPasswordRequest target = getRequest();
        assertTrue(request.equals(target));
        assertFalse(request.equals(null));
        target.setId("2");
        assertFalse(request.equals(target));
        target.setId("1");
        target.setPassword("dsdf");
        assertFalse(request.equals(target));
        target.setId("2");
        assertFalse(request.equals(target));
        target = request;
        assertTrue(target.equals(request));

        request.toString();
        request.hashCode();

        ResetPasswordRequest pr = new ResetPasswordRequest();
        pr.hashCode();
        assertFalse(pr.equals(request));
        pr.setId("1");
        assertFalse(pr.equals(request));

    }
}
