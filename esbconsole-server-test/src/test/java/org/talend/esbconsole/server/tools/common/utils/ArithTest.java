package org.talend.esbconsole.server.tools.common.utils;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * {@link Arith} 单元测试
 *
 * @author xingzilong
 * @date 2021/10/16
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class ArithTest {

    @InjectMocks
    Arith arith;

    @Test
    public void addTest() {
        double v1 = 12.5;
        double v2 = 12.3;
        double v3 = arith.add(v1, v2);
        assertEquals(v3, 24.8, 0);
    }

    @Test
    public void subTest() {
        double v1 = 12.5;
        double v2 = 12.3;
        assertEquals(0.2, arith.sub(v1, v2), 0);
    }

    @Test
    public void mulTest() {
        double v1 = 2.5;
        double v2 = 1.0;
        assertEquals(2.5, arith.mul(v1, v2), 0);
    }

    @Test
    public void divTest() {
        double v1 = 99.0;
        double v2 = 2.2;
        assertEquals(45, arith.div(v1, v2), 0);

        v1 = 0;
        assertEquals(0, arith.div(v1, v2), 0);
    }

    @Test
    public void roundTest() {
        try {
            arith.round(1.564, -1);
        } catch (Exception e) {
            assertTrue(e instanceof IllegalArgumentException);
        }

        assertEquals(2, arith.round(1.564, 0), 0);
        assertEquals(1.56, arith.round(1.564, 2), 0);
    }

}
