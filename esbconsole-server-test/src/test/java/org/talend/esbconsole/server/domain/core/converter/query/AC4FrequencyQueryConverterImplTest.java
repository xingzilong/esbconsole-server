package org.talend.esbconsole.server.domain.core.converter.query;

import org.talend.esbconsole.server.domain.api.param.AC4FrequencyPageQueryParam;
import org.talend.esbconsole.server.domain.api.query.AC4FrequencyPageQuery;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * {@link AC4FrequencyQueryConverterImpl} 单元测试
 *
 * @author xingzilong
 * @date 2021/10/17
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class AC4FrequencyQueryConverterImplTest {

    @InjectMocks
    private AC4FrequencyQueryConverterImpl ac4FrequencyQueryConverterImpl;

    @Test
    public void param2queryTest() {
        AC4FrequencyPageQueryParam param = new AC4FrequencyPageQueryParam();
        AC4FrequencyPageQuery ac4FrequencyPageQuery = ac4FrequencyQueryConverterImpl.param2query(param);
        assertNotNull(ac4FrequencyPageQuery);
        param = null;
        AC4FrequencyPageQuery ac4FrequencyPageQuery1 = ac4FrequencyQueryConverterImpl.param2query(param);
        assertNull(ac4FrequencyPageQuery1);
    }

}
