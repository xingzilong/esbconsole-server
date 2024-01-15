package org.talend.esbconsole.server.domain.api.param;

import org.talend.esbconsole.server.tools.base.request.TimeInterval;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * {@link ServicePageQueryParam} 单元测试
 *
 * @author xingzilong
 * @date 2023/10/24
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class ServicePageQueryParamTest {

    public void init(ServicePageQueryParam param) {
        param.setBusinessSystemId("test");
        param.setDescription("test");
        param.setCreateTime(new TimeInterval());
        param.setCreateTimeSort("45678");
        param.setEnabledSAM("SAM");
        param.setFileName("test");
        param.setFileType(".xml");
        param.setName("esb");
        param.setPageNum(1);
        param.setPageSize(10);
        param.setResponsiblePerson("person");
        param.setStatus("success");
    }

    @Test
    public void test() {
        ServicePageQueryParam param = new ServicePageQueryParam();
        ServicePageQueryParam sr = param;
        param.hashCode();
        assertTrue(param.equals(sr));
        assertFalse(param.equals(null));
        sr = new ServicePageQueryParam();
        assertTrue(param.equals(sr));
        init(param);
        assertFalse(param.equals(sr));
        param.hashCode();
        param.toString();
        init(sr);
        assertTrue(param.equals(sr));
    }

}
