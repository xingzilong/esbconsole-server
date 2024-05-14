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
 * {@link UserPageQuery} 单元测试
 *
 * @author xingzilong
 * @date 2021/10/24
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class UserPageQueryTest {

    private void init(UserPageQuery pageQuery) {
        pageQuery.setCreateTime(new TimeInterval());
        pageQuery.setCreateTimeSort("t");
        pageQuery.setDeleteFlag("0");
        pageQuery.setEmail("xxx@163.com");
        pageQuery.setName("test");
        pageQuery.setPhoneNumber("131********");
        pageQuery.setStatus("0");
        pageQuery.setUserName("admin");
    }

    @Test
    public void test() {
        UserPageQuery userPageQuery = new UserPageQuery();
        UserPageQuery pageQuery = userPageQuery;
        assertTrue(userPageQuery.equals(pageQuery));

        pageQuery = new UserPageQuery();
        assertTrue(userPageQuery.equals(pageQuery));
        assertFalse(userPageQuery.equals(null));

        init(userPageQuery);
        userPageQuery.toString();
        userPageQuery.hashCode();

        init(pageQuery);
        assertTrue(userPageQuery.equals(pageQuery));

        pageQuery.setCreateTimeSort("asc");
        assertFalse(userPageQuery.equals(pageQuery));

        init(pageQuery);
        pageQuery.setDeleteFlag("1");
        assertFalse(userPageQuery.equals(pageQuery));

        init(pageQuery);
        pageQuery.setEmail("");
        assertFalse(userPageQuery.equals(pageQuery));

        init(pageQuery);
        pageQuery.setName("ttt");
        assertFalse(userPageQuery.equals(pageQuery));

        init(pageQuery);
        TimeInterval timeInterval = new TimeInterval();
        timeInterval.setEndTime("");
        timeInterval.setStartTime("");
        pageQuery.setCreateTime(timeInterval);
        assertFalse(userPageQuery.equals(pageQuery));

        init(pageQuery);
        pageQuery.setPhoneNumber("186*******");
        assertFalse(userPageQuery.equals(pageQuery));

        init(pageQuery);
        pageQuery.setStatus("1");
        assertFalse(userPageQuery.equals(pageQuery));

        init(pageQuery);
        pageQuery.setUserName("tt");
        assertFalse(userPageQuery.equals(pageQuery));

    }


}
