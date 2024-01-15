package org.talend.esbconsole.server.tools.base.request;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * {@link TimeInterval4LDT} 单元测试
 *
 * @author xingzilong
 * @date 2023/10/16
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class TimeInterval4LDTTest {

    @Test
    public void test() {

        TimeInterval4LDT time = new TimeInterval4LDT();
        TimeInterval4LDT interval = time;
        time.hashCode();
        LocalDateTime dateTime = LocalDateTime.MIN;
        assertTrue(time.equals(interval));
        assertFalse(time.equals(null));
        time.setEndTime(dateTime);
        time.setStartTime(dateTime);
        time.toString();
        time.hashCode();
        interval = new TimeInterval4LDT();
        assertFalse(time.equals(interval));
        TimeInterval4LDT ti = new TimeInterval4LDT();
        ti.setEndTime(dateTime);
        ti.setStartTime(dateTime);
        assertTrue(time.equals(ti));
        ti.setEndTime(LocalDateTime.now());
        assertFalse(time.equals(ti));
    }


}
