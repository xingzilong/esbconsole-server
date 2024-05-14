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
 * {@link AC4IPPageQuery} 单元测试
 *
 * @author xingzilong
 * @date 2021/10/24
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class AC4IPPageQueryTest {

    private void init(AC4IPPageQuery pageQuery) {
        pageQuery.setServiceName("test");
        pageQuery.setCreateTimeSort("t");
        pageQuery.setStatus("0");
        pageQuery.setCreateTime(new TimeInterval());
        pageQuery.setType("single");
    }

    @Test
    public void test() {
        AC4IPPageQuery aC4IPPageQuery = new AC4IPPageQuery();
        AC4IPPageQuery pageQuery = aC4IPPageQuery;
        assertTrue(aC4IPPageQuery.equals(pageQuery));

        pageQuery = new AC4IPPageQuery();
        assertTrue(aC4IPPageQuery.equals(pageQuery));
        assertFalse(aC4IPPageQuery.equals(null));

        init(aC4IPPageQuery);
        aC4IPPageQuery.toString();
        aC4IPPageQuery.hashCode();

        init(pageQuery);
        assertTrue(aC4IPPageQuery.equals(pageQuery));

        pageQuery.setServiceName("tt");
        assertFalse(aC4IPPageQuery.equals(pageQuery));

        init(pageQuery);
        pageQuery.setCreateTimeSort("tt");
        assertFalse(aC4IPPageQuery.equals(pageQuery));

        init(pageQuery);
        pageQuery.setStatus("1");
        assertFalse(aC4IPPageQuery.equals(pageQuery));

        init(pageQuery);
        TimeInterval timeInterval = new TimeInterval();
        timeInterval.setEndTime("");
        timeInterval.setStartTime("");
        pageQuery.setCreateTime(timeInterval);
        assertFalse(aC4IPPageQuery.equals(pageQuery));

        init(pageQuery);
        pageQuery.setType("global");
        assertFalse(aC4IPPageQuery.equals(pageQuery));

    }
}
