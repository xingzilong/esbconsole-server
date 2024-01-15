package org.talend.esbconsole.server.web.api.controller.home.vo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * {@link CallTotal4DateVO} 单元测试
 *
 * @author xingzilong
 * @date 2023/10/9
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class CallTotal4DateVOTest {

    public void init(CallTotal4DateVO callTotal4DateVO) {
        callTotal4DateVO.setDate("2023-10-09");
        callTotal4DateVO.setTotal("1024");
        callTotal4DateVO.setUnit("GB");
    }

    @Test
    public void test() {

        CallTotal4DateVO callTotal4DateVO = new CallTotal4DateVO();
        CallTotal4DateVO callTotal4DateVO1 = new CallTotal4DateVO();

        assertTrue(callTotal4DateVO.equals(callTotal4DateVO1));

        callTotal4DateVO.hashCode();
        callTotal4DateVO.toString();
        init(callTotal4DateVO);
        callTotal4DateVO.hashCode();

        CallTotal4DateVO callTotal4DateVO2 = callTotal4DateVO;
        assertTrue(callTotal4DateVO2.equals(callTotal4DateVO));

        assertFalse(callTotal4DateVO1.equals(callTotal4DateVO));
        assertFalse(callTotal4DateVO1.equals(null));

        init(callTotal4DateVO1);
        assertTrue(callTotal4DateVO1.equals(callTotal4DateVO));

        callTotal4DateVO1.setDate("2023-10-10");
        assertFalse(callTotal4DateVO1.equals(callTotal4DateVO));

        init(callTotal4DateVO1);
        callTotal4DateVO1.setTotal("10240");
        assertFalse(callTotal4DateVO1.equals(callTotal4DateVO));

        init(callTotal4DateVO1);
        callTotal4DateVO1.setUnit("MB");
        assertFalse(callTotal4DateVO1.equals(callTotal4DateVO));

    }
}
