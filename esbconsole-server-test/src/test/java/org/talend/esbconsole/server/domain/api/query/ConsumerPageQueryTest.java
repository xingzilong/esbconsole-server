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
 * {@link ConsumerPageQuery} 单元测试
 *
 * @author xingzilong
 * @date 2021/10/24
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class ConsumerPageQueryTest {

    private void init(ConsumerPageQuery pageQuery) {
        pageQuery.setSystemName("test");
        pageQuery.setCreateTimeSort("t");
        pageQuery.setStatus("0");
        pageQuery.setCreateTime(new TimeInterval());
        pageQuery.setIp("127.0.0.1");
    }

    @Test
    public void test() {
        ConsumerPageQuery consumerPageQuery = new ConsumerPageQuery();
        ConsumerPageQuery pageQuery = consumerPageQuery;
        assertTrue(consumerPageQuery.equals(pageQuery));

        pageQuery = new ConsumerPageQuery();
        assertTrue(consumerPageQuery.equals(pageQuery));
        assertFalse(consumerPageQuery.equals(null));

        init(consumerPageQuery);
        consumerPageQuery.toString();
        consumerPageQuery.hashCode();

        init(pageQuery);
        assertTrue(consumerPageQuery.equals(pageQuery));

        pageQuery.setSystemName("tt");
        assertFalse(consumerPageQuery.equals(pageQuery));

        init(pageQuery);
        pageQuery.setCreateTimeSort("tt");
        assertFalse(consumerPageQuery.equals(pageQuery));

        init(pageQuery);
        pageQuery.setStatus("1");
        assertFalse(consumerPageQuery.equals(pageQuery));

        init(pageQuery);
        pageQuery.setIp("");
        assertFalse(consumerPageQuery.equals(pageQuery));

        init(pageQuery);
        TimeInterval timeInterval = new TimeInterval();
        timeInterval.setEndTime("");
        timeInterval.setStartTime("");
        pageQuery.setCreateTime(timeInterval);
        assertFalse(consumerPageQuery.equals(pageQuery));


    }
}
