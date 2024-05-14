package org.talend.esbconsole.server.domain.core.converter.query;

import org.talend.esbconsole.server.domain.api.param.ServiceLogPageQueryParam;
import org.talend.esbconsole.server.domain.api.query.ServiceLogPageQuery;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * {@link ServiceLogQueryConverterImpl} 单元测试
 *
 * @author xingzilong
 * @date 2021/10/17
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class ServiceLogQueryConverterImplTest {

    @InjectMocks
    private ServiceLogQueryConverterImpl serviceLogQueryConverterImpl;

    @Test
    public void param2queryTest() {
        ServiceLogPageQueryParam param = new ServiceLogPageQueryParam();
        ServiceLogPageQuery serviceLogPageQuery = serviceLogQueryConverterImpl.param2query(param);
        assertNotNull(serviceLogPageQuery);
        param = null;
        ServiceLogPageQuery serviceLogPageQuery1 = serviceLogQueryConverterImpl.param2query(param);
        assertNull(serviceLogPageQuery1);
    }

}
