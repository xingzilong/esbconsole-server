package org.talend.esbconsole.server.domain.api.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * {@link JVMDetails}单元测试
 *
 * @author xingzilong
 * @date 2021/10/24
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class JVMDetailsTest {
    public void init(JVMDetails dto) {
        dto.setHome("127.0.0.1");
        dto.setInputArgs("sk");
        dto.setName("test");
        dto.setRunTime("runtime");
        dto.setStartTime("starttime");
        dto.setUserDir("dir");
        dto.setVersion("v1");
    }

    @Test
    public void test() {
        JVMDetails dto = new JVMDetails();
        dto.hashCode();
        assertFalse(dto.equals(null));
        assertTrue(dto.equals(dto));
        JVMDetails dtos = new JVMDetails();
        assertTrue(dto.equals(dtos));
        init(dto);
        assertFalse(dto.equals(dtos));
        dto.hashCode();
        dto.toString();
        init(dtos);
        //equals方法未完全覆盖
        assertTrue(dto.equals(dtos));
        dtos.setHome("no");
        assertFalse(dto.equals(dtos));

        dtos.setHome("127.0.0.1");
        dtos.setInputArgs("po");
        assertFalse(dto.equals(dtos));

        dtos.setInputArgs("sk");
        dtos.setName("qwer");
        assertFalse(dto.equals(dtos));

        dtos.setName("test");
        dtos.setRunTime("p");
        assertFalse(dto.equals(dtos));

        dtos.setRunTime("runtime");
        dtos.setStartTime("st");
        assertFalse(dto.equals(dtos));

        dtos.setStartTime("starttime");
        dtos.setUserDir("p");
        assertFalse(dto.equals(dtos));

        dtos.setUserDir("dir");
        dtos.setVersion("v2");
        assertFalse(dto.equals(dtos));
    }
}
