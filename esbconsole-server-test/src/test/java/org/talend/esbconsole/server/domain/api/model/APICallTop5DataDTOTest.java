package org.talend.esbconsole.server.domain.api.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * {@link APICallTop5DataDTO}单元测试
 *
 * @author xingzilong
 * @date 2021/10/24
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class APICallTop5DataDTOTest {

    public void init(APICallTop5DataDTO dto) {
        dto.setServiceKey("test");
        dto.setCallFailureTotal(10L);
        dto.setCallTotal(200L);
        dto.setConsumerIP("127.0.0.1");
        dto.setConsumerSystem("esb");
    }

    @Test
    public void test() {
        APICallTop5DataDTO dto = new APICallTop5DataDTO();
        dto.hashCode();
        assertFalse(dto.equals(null));
        assertTrue(dto.equals(dto));
        APICallTop5DataDTO dtos = new APICallTop5DataDTO();
        assertTrue(dto.equals(dtos));
        init(dto);
        assertFalse(dto.equals(dtos));
        dto.hashCode();
        dto.toString();
        init(dtos);

        //equals方法未完全覆盖
        assertTrue(dto.equals(dtos));
        dtos.setServiceKey("ksp");
        assertFalse(dto.equals(dtos));
        dtos.setCallFailureTotal(1L);
        assertFalse(dto.equals(dtos));
    }

}
