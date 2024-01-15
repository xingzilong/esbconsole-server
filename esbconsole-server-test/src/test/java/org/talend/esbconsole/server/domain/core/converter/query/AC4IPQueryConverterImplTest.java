package org.talend.esbconsole.server.domain.core.converter.query;

import org.talend.esbconsole.server.domain.api.param.AC4IPPageQueryParam;
import org.talend.esbconsole.server.domain.api.query.AC4IPPageQuery;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * {@link AC4IPQueryConverterImpl} 单元测试
 *
 * @author xingzilong
 * @date 2023/10/17
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class AC4IPQueryConverterImplTest {

    @InjectMocks
    private AC4IPQueryConverterImpl ac4IPQueryConverterImpl;

    @Test
    public void param2queryTest() {
        AC4IPPageQueryParam param = new AC4IPPageQueryParam();
        AC4IPPageQuery ac4IPPageQuery = ac4IPQueryConverterImpl.param2query(param);
        assertNotNull(ac4IPPageQuery);
        param = null;
        AC4IPPageQuery ac4IPPageQuery1 = ac4IPQueryConverterImpl.param2query(param);
        assertNull(ac4IPPageQuery1);
    }

}
