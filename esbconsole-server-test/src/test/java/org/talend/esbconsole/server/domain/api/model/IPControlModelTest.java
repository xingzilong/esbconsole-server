package org.talend.esbconsole.server.domain.api.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * {@link IPControlModel} 单元测试
 *
 * @author xingzilong
 * @date 2021/10/23
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class IPControlModelTest {

    public void init(IPControlModel ipControlModel) {
        ipControlModel.setId("test-id");
        ipControlModel.setServiceKey("test-servicekey");
        ipControlModel.setType("test-type");
        ipControlModel.setBlackList("127.0.0.1");
        ipControlModel.setWhiteList("127.0.0.1");
        ipControlModel.setStatus("1");
        ipControlModel.setCreateTime(LocalDateTime.MIN);
        ipControlModel.setUpdateTime(LocalDateTime.MIN);
    }

    @Test
    public void test() {

        IPControlModel ipControlModel = new IPControlModel();
        IPControlModel ipControlModel1 = new IPControlModel();

        assertTrue(ipControlModel.equals(ipControlModel1));

        ipControlModel.hashCode();
        ipControlModel.toString();
        init(ipControlModel);
        ipControlModel.hashCode();

        IPControlModel ipControlModel2 = ipControlModel;
        assertTrue(ipControlModel2.equals(ipControlModel));

        assertFalse(ipControlModel1.equals(ipControlModel));
        assertFalse(ipControlModel1.equals(null));

        init(ipControlModel1);
        assertTrue(ipControlModel1.equals(ipControlModel));

        ipControlModel1.setId("test-ids");
        assertFalse(ipControlModel1.equals(ipControlModel));

        init(ipControlModel1);
        ipControlModel1.setServiceKey("test-servicekeys");
        assertFalse(ipControlModel1.equals(ipControlModel));

        init(ipControlModel1);
        ipControlModel1.setType("test-types");
        assertFalse(ipControlModel1.equals(ipControlModel));

        init(ipControlModel1);
        ipControlModel1.setBlackList("127.0.0.2");
        assertFalse(ipControlModel1.equals(ipControlModel));

        init(ipControlModel1);
        ipControlModel1.setWhiteList("127.0.0.2");
        assertFalse(ipControlModel1.equals(ipControlModel));

        init(ipControlModel1);
        ipControlModel1.setStatus("11");
        assertFalse(ipControlModel1.equals(ipControlModel));

        init(ipControlModel1);
        ipControlModel1.setCreateTime(LocalDateTime.MIN.plusDays(1L));
        assertFalse(ipControlModel1.equals(ipControlModel));

        init(ipControlModel1);
        ipControlModel1.setUpdateTime(LocalDateTime.MIN.plusDays(1L));
        assertFalse(ipControlModel1.equals(ipControlModel));
    }
}
