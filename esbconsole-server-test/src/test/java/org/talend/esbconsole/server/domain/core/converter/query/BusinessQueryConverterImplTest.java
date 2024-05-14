package org.talend.esbconsole.server.domain.core.converter.query;

import org.talend.esbconsole.server.domain.api.param.BusinessPageQueryParam;
import org.talend.esbconsole.server.domain.api.query.BusinessPageQuery;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * {@link BusinessQueryConverterImpl} 单元测试
 *
 * @author xingzilong
 * @date 2021/10/17
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class BusinessQueryConverterImplTest {

    @InjectMocks
    private BusinessQueryConverterImpl businessQueryConverterImpl;

    @Test
    public void param2queryTest() {
        BusinessPageQueryParam param = new BusinessPageQueryParam();
        BusinessPageQuery businessPageQuery = businessQueryConverterImpl.param2query(param);
        assertNotNull(businessPageQuery);
        param = null;
        BusinessPageQuery businessPageQuery1 = businessQueryConverterImpl.param2query(param);
        assertNull(businessPageQuery1);
    }

}
