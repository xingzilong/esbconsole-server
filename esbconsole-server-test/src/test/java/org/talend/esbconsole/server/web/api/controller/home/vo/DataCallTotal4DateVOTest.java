package org.talend.esbconsole.server.web.api.controller.home.vo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * {@link DataCallTotal4DateVO} 单元测试
 *
 * @author xingzilong
 * @date 2021/10/9
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class DataCallTotal4DateVOTest {

    public void init(DataCallTotal4DateVO dataCallTotal4DateVO) {
        dataCallTotal4DateVO.setDate("2021-10-09");
        dataCallTotal4DateVO.setTotal("1024");
        dataCallTotal4DateVO.setUnit("万");
    }

    @Test
    public void test() {

        DataCallTotal4DateVO dataCallTotal4DateVO = new DataCallTotal4DateVO();
        DataCallTotal4DateVO dataCallTotal4DateVO1 = new DataCallTotal4DateVO();

        assertTrue(dataCallTotal4DateVO.equals(dataCallTotal4DateVO1));

        dataCallTotal4DateVO.hashCode();
        dataCallTotal4DateVO.toString();
        init(dataCallTotal4DateVO);
        dataCallTotal4DateVO.hashCode();

        DataCallTotal4DateVO dataCallTotal4DateVO2 = dataCallTotal4DateVO;
        assertTrue(dataCallTotal4DateVO2.equals(dataCallTotal4DateVO));

        assertFalse(dataCallTotal4DateVO1.equals(dataCallTotal4DateVO));
        assertFalse(dataCallTotal4DateVO1.equals(null));

        init(dataCallTotal4DateVO1);
        assertTrue(dataCallTotal4DateVO1.equals(dataCallTotal4DateVO));

        dataCallTotal4DateVO1.setDate("2021-10-10");
        assertFalse(dataCallTotal4DateVO1.equals(dataCallTotal4DateVO));
        init(dataCallTotal4DateVO1);
        dataCallTotal4DateVO1.setTotal("10240");
        assertFalse(dataCallTotal4DateVO1.equals(dataCallTotal4DateVO));
        init(dataCallTotal4DateVO1);
        dataCallTotal4DateVO1.setUnit("亿");
        assertFalse(dataCallTotal4DateVO1.equals(dataCallTotal4DateVO));


    }
}
