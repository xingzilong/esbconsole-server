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
 * {@link BusinessPageQuery} 单元测试
 *
 * @author xingzilong
 * @date 2023/10/24
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class BusinessPageQueryTest {

    private void init(BusinessPageQuery pageQuery) {
        pageQuery.setSystemName("test");
        pageQuery.setCreateTimeSort("t");
        pageQuery.setStatus("0");
        pageQuery.setCreateTime(new TimeInterval());
    }

    @Test
    public void test() {
        BusinessPageQuery businessPageQuery = new BusinessPageQuery();
        BusinessPageQuery pageQuery = businessPageQuery;
        assertTrue(businessPageQuery.equals(pageQuery));

        pageQuery = new BusinessPageQuery();
        assertTrue(businessPageQuery.equals(pageQuery));
        assertFalse(businessPageQuery.equals(null));

        init(businessPageQuery);
        businessPageQuery.toString();
        businessPageQuery.hashCode();

        init(pageQuery);
        assertTrue(businessPageQuery.equals(pageQuery));

        pageQuery.setSystemName("tt");
        assertFalse(businessPageQuery.equals(pageQuery));

        init(pageQuery);
        pageQuery.setCreateTimeSort("tt");
        assertFalse(businessPageQuery.equals(pageQuery));

        init(pageQuery);
        pageQuery.setStatus("1");
        assertFalse(businessPageQuery.equals(pageQuery));

        init(pageQuery);
        TimeInterval timeInterval = new TimeInterval();
        timeInterval.setEndTime("");
        timeInterval.setStartTime("");
        pageQuery.setCreateTime(timeInterval);
        assertFalse(businessPageQuery.equals(pageQuery));


    }
}
