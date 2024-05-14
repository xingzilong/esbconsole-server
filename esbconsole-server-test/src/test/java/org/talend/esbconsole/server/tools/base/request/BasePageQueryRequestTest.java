package org.talend.esbconsole.server.tools.base.request;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * {@link BasePageQueryRequest} 单元测试
 *
 * @author xingzilong
 * @date 2021/10/16
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class BasePageQueryRequestTest {


    public void init(BasePageQueryRequest request) {

        request.setPageNum(1);
        request.setPageSize(10);
    }

    @Test
    public void test() {
        BasePageQueryRequest request = new BasePageQueryRequest();
        request.hashCode();
        BasePageQueryRequest qr = request;
        assertTrue(request.equals(qr));
        assertFalse(request.equals(null));
        qr = new BasePageQueryRequest();
        assertTrue(request.equals(qr));
        init(request);
        request.hashCode();
        request.toString();
        assertFalse(request.equals(qr));
        BasePageQueryRequest par = new BasePageQueryRequest();
        par.setPageNum(1);
        par.setPageSize(10);
        assertTrue(request.equals(par));
        par.setPageSize(100);
        assertFalse(request.equals(par));
    }
}
