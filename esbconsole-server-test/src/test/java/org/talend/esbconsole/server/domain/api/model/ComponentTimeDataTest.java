package org.talend.esbconsole.server.domain.api.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * {@link ComponentTimeData}单元测试
 *
 * @author xingzilong
 * @date 2023/10/24
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class ComponentTimeDataTest {

    @Test
    public void test() {
        ComponentTimeData td = new ComponentTimeData();
        td.hashCode();
        assertFalse(td.equals(null));
        assertTrue(td.equals(td));
        ComponentTimeData ct = new ComponentTimeData();
        assertTrue(td.equals(ct));
        td.setName("test");
        td.setValue(1L);
        assertFalse(td.equals(ct));
        td.hashCode();
        td.toString();
        ct.setName("test");
        assertFalse(td.equals(ct));
        ct.setValue(1L);
        assertTrue(td.equals(ct));
    }


}
