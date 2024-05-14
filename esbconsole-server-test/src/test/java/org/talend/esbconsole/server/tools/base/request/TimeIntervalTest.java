package org.talend.esbconsole.server.tools.base.request;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * {@link TimeInterval} 单元测试
 *
 * @author xingzilong
 * @date 2021/10/16
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class TimeIntervalTest {

    @Test
    public void test() {

        TimeInterval time = new TimeInterval();
        TimeInterval interval = time;
        time.hashCode();
        assertTrue(time.equals(interval));
        assertFalse(time.equals(null));
        time.setEndTime("2012");
        time.setStartTime("2023");
        time.toString();
        time.hashCode();
        interval = new TimeInterval();
        assertFalse(time.equals(interval));
        TimeInterval ti = new TimeInterval();
        ti.setStartTime("2023");
        ti.setEndTime("2012");
        assertTrue(time.equals(ti));
        ti.setEndTime("2014");
        assertFalse(time.equals(ti));
    }

}
