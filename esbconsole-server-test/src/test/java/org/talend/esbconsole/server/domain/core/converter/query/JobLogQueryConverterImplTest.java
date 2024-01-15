package org.talend.esbconsole.server.domain.core.converter.query;

import org.talend.esbconsole.server.domain.api.param.JobLogPageQueryParam;
import org.talend.esbconsole.server.domain.api.query.JobLogPageQuery;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * {@link JobLogQueryConverterImpl} 单元测试
 *
 * @author xingzilong
 * @date 2023/10/17
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class JobLogQueryConverterImplTest {

    @InjectMocks
    private JobLogQueryConverterImpl jobLogQueryConverterImpl;

    @Test
    public void param2queryTest() {
        JobLogPageQueryParam param = new JobLogPageQueryParam();
        JobLogPageQuery jobLogPageQuery = jobLogQueryConverterImpl.param2query(param);
        assertNotNull(jobLogPageQuery);
        param = null;
        JobLogPageQuery jobLogPageQuery1 = jobLogQueryConverterImpl.param2query(param);
        assertNull(jobLogPageQuery1);
    }

}
