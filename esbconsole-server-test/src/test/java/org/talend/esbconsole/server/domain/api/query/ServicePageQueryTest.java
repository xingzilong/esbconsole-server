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
 * {@link ServicePageQuery} 单元测试
 *
 * @author xingzilong
 * @date 2021/10/24
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class ServicePageQueryTest {

    private void init(ServicePageQuery pageQuery) {
        pageQuery.setBusinessSystemId("test");
        pageQuery.setCreateTime(new TimeInterval());
        pageQuery.setCreateTimeSort("t");
        pageQuery.setEnabledSAM("0");
        pageQuery.setName("test");
        pageQuery.setResponsiblePerson("admin");
        pageQuery.setFileType("kar");

    }

    @Test
    public void test() {
        ServicePageQuery ServicePageQuery = new ServicePageQuery();
        ServicePageQuery pageQuery = ServicePageQuery;
        assertTrue(ServicePageQuery.equals(pageQuery));

        pageQuery = new ServicePageQuery();
        assertTrue(ServicePageQuery.equals(pageQuery));
        assertFalse(ServicePageQuery.equals(null));

        init(ServicePageQuery);
        ServicePageQuery.toString();
        ServicePageQuery.hashCode();

        init(pageQuery);
        assertTrue(ServicePageQuery.equals(pageQuery));

        pageQuery.setBusinessSystemId("tt");
        assertFalse(ServicePageQuery.equals(pageQuery));

        init(pageQuery);
        pageQuery.setCreateTimeSort("desc");
        assertFalse(ServicePageQuery.equals(pageQuery));

        init(pageQuery);
        pageQuery.setEnabledSAM("1");
        assertFalse(ServicePageQuery.equals(pageQuery));

        init(pageQuery);
        pageQuery.setName("tt");
        assertFalse(ServicePageQuery.equals(pageQuery));

        init(pageQuery);
        TimeInterval timeInterval = new TimeInterval();
        timeInterval.setEndTime("");
        timeInterval.setStartTime("");
        pageQuery.setCreateTime(timeInterval);
        assertFalse(ServicePageQuery.equals(pageQuery));

        init(pageQuery);
        pageQuery.setResponsiblePerson("trend");
        assertFalse(ServicePageQuery.equals(pageQuery));

        init(pageQuery);
        pageQuery.setFileType("jar");
        assertFalse(ServicePageQuery.equals(pageQuery));

    }
}
