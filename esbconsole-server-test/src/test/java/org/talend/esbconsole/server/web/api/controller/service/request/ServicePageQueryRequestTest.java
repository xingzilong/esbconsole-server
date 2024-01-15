package org.talend.esbconsole.server.web.api.controller.service.request;

import org.talend.esbconsole.server.tools.base.request.TimeInterval;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * {@link ServicePageQueryRequest} 单元测试
 *
 * @author xingzilong
 * @date 2023/10/10
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class ServicePageQueryRequestTest {

    public void init(ServicePageQueryRequest request) {
        request.setBusinessSystemId("test");
        request.setDescription("test");
        request.setCreateTime(new TimeInterval());
        request.setCreateTimeSort("45678");
        request.setEnabledSAM("SAM");
        request.setFileName("test");
        request.setFileType(".xml");
        request.setName("esb");
        request.setPageNum(1);
        request.setPageSize(10);
        request.setResponsiblePerson("person");
        request.setStatus("success");
    }

    @Test
    public void test() {
        ServicePageQueryRequest request = new ServicePageQueryRequest();
        ServicePageQueryRequest sr = request;
        request.hashCode();
        assertTrue(request.equals(sr));
        assertFalse(request.equals(null));
        sr = new ServicePageQueryRequest();
        assertTrue(request.equals(sr));
        init(request);
        assertFalse(request.equals(sr));
        request.hashCode();
        request.toString();
        init(sr);
        assertTrue(request.equals(sr));
    }

}
