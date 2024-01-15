package org.talend.esbconsole.server.domain.core.converter.query;

import org.talend.esbconsole.server.domain.api.param.ConsumerPageQueryParam;
import org.talend.esbconsole.server.domain.api.query.ConsumerPageQuery;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * {@link ConsumerQueryConverterImpl} 单元测试
 *
 * @author xingzilong
 * @date 2023/10/17
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class ConsumerQueryConverterImplTest {

    @InjectMocks
    private ConsumerQueryConverterImpl consumerQueryConverterImpl;

    @Test
    public void param2queryTest() {
        ConsumerPageQueryParam param = new ConsumerPageQueryParam();
        ConsumerPageQuery consumerPageQuery = consumerQueryConverterImpl.param2query(param);
        assertNotNull(consumerPageQuery);
        param = null;
        ConsumerPageQuery consumerPageQuery1 = consumerQueryConverterImpl.param2query(param);
        assertNull(consumerPageQuery1);
    }

}
