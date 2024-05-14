package org.talend.esbconsole.server.domain.api.query;

import org.talend.esbconsole.server.tools.base.request.TimeInterval;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * {@link AC4FrequencyPageQuery} 单元测试
 *
 * @author xingzilong
 * @date 2021/10/23
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class AC4FrequencyPageQueryTest {

    private void init(AC4FrequencyPageQuery pageQuery) {
        pageQuery.setServiceName("test");
        pageQuery.setCreateTimeSort("t");
        pageQuery.setStatus("0");
        pageQuery.setCreateTime(new TimeInterval());
        pageQuery.setType("single");
    }

    @Test
    public void test() {
        AC4FrequencyPageQuery ac4FrequencyPageQuery = new AC4FrequencyPageQuery();
        AC4FrequencyPageQuery pageQuery = ac4FrequencyPageQuery;
        assertTrue(ac4FrequencyPageQuery.equals(pageQuery));

        pageQuery = new AC4FrequencyPageQuery();
        assertTrue(ac4FrequencyPageQuery.equals(pageQuery));
        assertFalse(ac4FrequencyPageQuery.equals(null));

        init(ac4FrequencyPageQuery);
        ac4FrequencyPageQuery.toString();
        ac4FrequencyPageQuery.hashCode();

        init(pageQuery);
        assertTrue(ac4FrequencyPageQuery.equals(pageQuery));

        pageQuery.setServiceName("tt");
        assertFalse(ac4FrequencyPageQuery.equals(pageQuery));

        init(pageQuery);
        pageQuery.setCreateTimeSort("tt");
        assertFalse(ac4FrequencyPageQuery.equals(pageQuery));

        init(pageQuery);
        pageQuery.setStatus("1");
        assertFalse(ac4FrequencyPageQuery.equals(pageQuery));

        init(pageQuery);
        TimeInterval timeInterval = new TimeInterval();
        timeInterval.setEndTime("");
        timeInterval.setStartTime("");
        pageQuery.setCreateTime(timeInterval);
        assertFalse(ac4FrequencyPageQuery.equals(pageQuery));

        init(pageQuery);
        pageQuery.setType("global");
        assertFalse(ac4FrequencyPageQuery.equals(pageQuery));

    }

}
