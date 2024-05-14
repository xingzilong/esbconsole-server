package org.talend.esbconsole.server.domain.core.converter.query;

import org.talend.esbconsole.server.domain.api.param.ServicePageQueryParam;
import org.talend.esbconsole.server.domain.api.query.ServicePageQuery;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * {@link ServiceQueryConverterImpl} 单元测试
 *
 * @author xingzilong
 * @date 2021/10/17
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class ServiceQueryConverterImplTest {

    @InjectMocks
    private ServiceQueryConverterImpl serviceQueryConverterImpl;

    @Test
    public void param2queryTest() {
        ServicePageQueryParam param = new ServicePageQueryParam();
        ServicePageQuery servicePageQuery = serviceQueryConverterImpl.param2query(param);
        assertNotNull(servicePageQuery);
        param = null;
        ServicePageQuery servicePageQuery1 = serviceQueryConverterImpl.param2query(param);
        assertNull(servicePageQuery1);
    }

}
