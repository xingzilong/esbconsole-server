package org.talend.esbconsole.server.domain.api.param;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * {@link UserPageQueryParam} 单元测试
 *
 * @author xingzilong
 * @date 2021/10/24
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class UserPageQueryParamTest {

    public void init(UserPageQueryParam param) {

        param.setEmail("test@163.com");
        param.setName("test");
        param.setPageNum(5);
        param.setPageSize(10);
        param.setPhoneNumber("19812345678");
        param.setStatus("ok");
        param.setUserName("by");
        param.setCreateTimeSort("456789");
        param.setCreateTime(null);
    }

    @Test
    public void test() {

        UserPageQueryParam param = new UserPageQueryParam();
        UserPageQueryParam qr = new UserPageQueryParam();
        assertTrue(param.equals(qr));
        param.hashCode();
        init(param);
        assertFalse(param.equals(qr));
        param.toString();
        param.hashCode();

        init(qr);
        assertTrue(param.equals(qr));
        qr.setCreateTimeSort("113334");
        assertFalse(param.equals(qr));
        init(qr);
        qr.setEmail("pp");
        assertFalse(param.equals(qr));
        init(qr);
        qr.setName("lll");
        assertFalse(param.equals(qr));
        init(qr);
        qr.setPhoneNumber("999");
        assertFalse(param.equals(qr));
        init(qr);
        qr.setCreateTimeSort("45678");
        assertFalse(param.equals(qr));
    }
}
