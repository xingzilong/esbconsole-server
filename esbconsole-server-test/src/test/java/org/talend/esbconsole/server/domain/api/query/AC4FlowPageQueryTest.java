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
 * {@link AC4FlowPageQuery} 单元测试
 *
 * @author xingzilong
 * @date 2023/10/24
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class AC4FlowPageQueryTest {


    private void init(AC4FlowPageQuery pageQuery) {
        pageQuery.setServiceName("test");
        pageQuery.setCreateTimeSort("t");
        pageQuery.setStatus("0");
        pageQuery.setCreateTime(new TimeInterval());
        pageQuery.setType("single");
    }

    @Test
    public void test() {
        AC4FlowPageQuery ac4FlowPageQuery = new AC4FlowPageQuery();
        AC4FlowPageQuery pageQuery = ac4FlowPageQuery;
        assertTrue(ac4FlowPageQuery.equals(pageQuery));

        pageQuery = new AC4FlowPageQuery();
        assertTrue(ac4FlowPageQuery.equals(pageQuery));
        assertFalse(ac4FlowPageQuery.equals(null));

        init(ac4FlowPageQuery);
        ac4FlowPageQuery.toString();
        ac4FlowPageQuery.hashCode();

        init(pageQuery);
        assertTrue(ac4FlowPageQuery.equals(pageQuery));

        pageQuery.setServiceName("tt");
        assertFalse(ac4FlowPageQuery.equals(pageQuery));

        init(pageQuery);
        pageQuery.setCreateTimeSort("tt");
        assertFalse(ac4FlowPageQuery.equals(pageQuery));

        init(pageQuery);
        pageQuery.setStatus("1");
        assertFalse(ac4FlowPageQuery.equals(pageQuery));

        init(pageQuery);
        TimeInterval timeInterval = new TimeInterval();
        timeInterval.setEndTime("");
        timeInterval.setStartTime("");
        pageQuery.setCreateTime(timeInterval);
        assertFalse(ac4FlowPageQuery.equals(pageQuery));

        init(pageQuery);
        pageQuery.setType("global");
        assertFalse(ac4FlowPageQuery.equals(pageQuery));

    }
}
