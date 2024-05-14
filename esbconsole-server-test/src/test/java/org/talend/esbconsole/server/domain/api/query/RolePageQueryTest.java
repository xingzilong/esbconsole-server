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
 * {@link RolePageQuery} 单元测试
 *
 * @author xingzilong
 * @date 2021/10/24
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class RolePageQueryTest {

    private void init(RolePageQuery pageQuery) {
        pageQuery.setName("test");
        pageQuery.setRoleName("admin");
        pageQuery.setStatus("0");
        pageQuery.setCreateTime(new TimeInterval());
        pageQuery.setCreateTimeSort("t");
    }

    @Test
    public void test() {
        RolePageQuery rolePageQuery = new RolePageQuery();
        RolePageQuery pageQuery = rolePageQuery;
        assertTrue(rolePageQuery.equals(pageQuery));

        pageQuery = new RolePageQuery();
        assertTrue(rolePageQuery.equals(pageQuery));
        assertFalse(rolePageQuery.equals(null));

        init(rolePageQuery);
        rolePageQuery.toString();
        rolePageQuery.hashCode();

        init(pageQuery);
        assertTrue(rolePageQuery.equals(pageQuery));

        pageQuery.setName("tt");
        assertFalse(rolePageQuery.equals(pageQuery));

        init(pageQuery);
        pageQuery.setRoleName("trend");
        assertFalse(rolePageQuery.equals(pageQuery));

        init(pageQuery);
        pageQuery.setCreateTimeSort("tt");
        assertFalse(rolePageQuery.equals(pageQuery));

        init(pageQuery);
        pageQuery.setStatus("1");
        assertFalse(rolePageQuery.equals(pageQuery));

        init(pageQuery);
        TimeInterval timeInterval = new TimeInterval();
        timeInterval.setEndTime("");
        timeInterval.setStartTime("");
        pageQuery.setCreateTime(timeInterval);
        assertFalse(rolePageQuery.equals(pageQuery));
    }
}
