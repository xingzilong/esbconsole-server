package org.talend.esbconsole.server.tools.base.result;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * {@link PageResult} 单元测试
 *
 * @author xingzilong
 * @date 2023/10/16
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class PageResultTest {

    @Test
    public void test() {

        PageResult result = new PageResult(Arrays.asList("test"), 100L, 1L, 20L);
        result.hashCode();
        result.toString();
        assertFalse(result.equals(null));
        PageResult page = result;
        assertTrue(result.equals(page));
        page = new PageResult();
        page.hashCode();
        assertFalse(result.equals(page));
        page.setList(Arrays.asList("test"));
        page.setPageNum(1L);
        page.setTotal(100L);
        page.setPageSize(20L);
        assertTrue(result.equals(page));
        page.setTotal(99L);
        assertFalse(result.equals(page));
    }

    @Test
    public void init() {
        PageResult.of();
        PageResult.of(null, 100L);
        PageResult.of(null, 100L, 1L, 20L);
    }
}
