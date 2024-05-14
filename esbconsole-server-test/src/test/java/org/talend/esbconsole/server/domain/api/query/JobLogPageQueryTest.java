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
 * {@link JobLogPageQuery} 单元测试
 *
 * @author xingzilong
 * @date 2021/10/24
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class JobLogPageQueryTest {

    private void init(JobLogPageQuery pageQuery) {
        pageQuery.setServiceName("test");
        pageQuery.setJob("t");
        pageQuery.setStatus("0");
        pageQuery.setExecutTime(new TimeInterval());
        pageQuery.setExecutTimeSort("asc");
    }

    @Test
    public void test() {
        JobLogPageQuery jobLogPageQuery = new JobLogPageQuery();
        JobLogPageQuery pageQuery = jobLogPageQuery;
        assertTrue(jobLogPageQuery.equals(pageQuery));

        pageQuery = new JobLogPageQuery();
        assertTrue(jobLogPageQuery.equals(pageQuery));
        assertFalse(jobLogPageQuery.equals(null));

        init(jobLogPageQuery);
        jobLogPageQuery.toString();
        jobLogPageQuery.hashCode();

        init(pageQuery);
        assertTrue(jobLogPageQuery.equals(pageQuery));

        pageQuery.setServiceName("tt");
        assertFalse(jobLogPageQuery.equals(pageQuery));

        init(pageQuery);
        pageQuery.setExecutTimeSort("desc");
        assertFalse(jobLogPageQuery.equals(pageQuery));

        init(pageQuery);
        pageQuery.setJob("tt");
        assertFalse(jobLogPageQuery.equals(pageQuery));

        init(pageQuery);
        pageQuery.setStatus("1");
        assertFalse(jobLogPageQuery.equals(pageQuery));

        init(pageQuery);
        TimeInterval timeInterval = new TimeInterval();
        timeInterval.setEndTime("");
        timeInterval.setStartTime("");
        pageQuery.setExecutTime(timeInterval);
        assertFalse(jobLogPageQuery.equals(pageQuery));
    }
}
