package org.talend.esbconsole.server.web.api.controller.user.request;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * {@link UserPageQueryRequest} 单元测试
 *
 * @author xingzilong
 * @date 2023/10/9
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class UserPageQueryRequestTest {

    public void init(UserPageQueryRequest request) {

        request.setEmail("test@163.com");
        request.setName("test");
        request.setPageNum(5);
        request.setPageSize(10);
        request.setPhoneNumber("19812345678");
        request.setStatus("ok");
        request.setUserName("by");
        request.setCreateTimeSort("456789");
        request.setCreateTime(null);
    }

    @Test
    public void test() {

        UserPageQueryRequest request = new UserPageQueryRequest();
        UserPageQueryRequest qr = new UserPageQueryRequest();
        assertTrue(request.equals(qr));
        request.hashCode();
        init(request);
        assertFalse(request.equals(qr));
        request.toString();
        request.hashCode();

        init(qr);
        assertTrue(request.equals(qr));
        qr.setCreateTimeSort("113334");
        assertFalse(request.equals(qr));
        init(qr);
        qr.setEmail("pp");
        assertFalse(request.equals(qr));
        init(qr);
        qr.setName("lll");
        assertFalse(request.equals(qr));
        init(qr);
        qr.setPhoneNumber("999");
        assertFalse(request.equals(qr));
        init(qr);
        qr.setCreateTimeSort("45678");
        assertFalse(request.equals(qr));
    }
}
