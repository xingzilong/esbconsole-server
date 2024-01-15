package org.talend.esbconsole.server.domain.core.converter.query;

import org.talend.esbconsole.server.domain.api.param.AC4FlowPageQueryParam;
import org.talend.esbconsole.server.domain.api.query.AC4FlowPageQuery;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * {@link AC4FlowQueryConverterImpl} 单元测试
 *
 * @author xingzilong
 * @date 2023/10/17
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class AC4FlowQueryConverterImplTest {

    @InjectMocks
    private AC4FlowQueryConverterImpl ac4FlowQueryConverterImpl;

    @Test
    public void param2queryTest() {
        AC4FlowPageQueryParam param = new AC4FlowPageQueryParam();
        AC4FlowPageQuery ac4FlowPageQuery = ac4FlowQueryConverterImpl.param2query(param);
        assertNotNull(ac4FlowPageQuery);
        param = null;
        AC4FlowPageQuery ac4FlowPageQuery1 = ac4FlowQueryConverterImpl.param2query(param);
        assertNull(ac4FlowPageQuery1);
    }

}
