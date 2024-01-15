package org.talend.esbconsole.server.web.api.controller.service.vo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * {@link ServiceVO} 单元测试
 *
 * @author xingzilong
 * @date 2023/10/9
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class ServiceVOTest {

    public void init(ServiceVO serviceVO) {
        serviceVO.setId("test-id");
        serviceVO.setName("test-name");
        serviceVO.setBusinessSystem("test-name");
        serviceVO.setServiceType("test-password");
        serviceVO.setJob(new ArrayList<>());
        serviceVO.setFileName("test-number");
        serviceVO.setFileType("test");
        serviceVO.setBundleId(1L);
        serviceVO.setFeatureName("test");
        serviceVO.setBundleName(new ArrayList<>());
        serviceVO.setServiceKey("test");
        serviceVO.setEnabledSAM("test");
        serviceVO.setDescription("test");
        serviceVO.setResponsiblePerson("test");
        serviceVO.setCreateTime("2023-12-12");
        serviceVO.setUpdateTime("2023-12-12");
    }

    @Test
    public void test() {

        ServiceVO serviceVO = new ServiceVO();
        ServiceVO serviceVO1 = new ServiceVO();

        assertTrue(serviceVO.equals(serviceVO1));

        serviceVO.hashCode();
        serviceVO.toString();
        init(serviceVO);
        serviceVO.hashCode();

        ServiceVO serviceVO2 = serviceVO;
        assertTrue(serviceVO2.equals(serviceVO));

        assertFalse(serviceVO1.equals(serviceVO));
        assertFalse(serviceVO1.equals(null));

        init(serviceVO1);
        assertTrue(serviceVO1.equals(serviceVO));

        serviceVO1.setId("test-ids");
        assertFalse(serviceVO1.equals(serviceVO));

        init(serviceVO1);
        serviceVO1.setName("test-systemnames");
        assertFalse(serviceVO1.equals(serviceVO));

        init(serviceVO1);
        serviceVO1.setBusinessSystem("test-systemnames");
        assertFalse(serviceVO1.equals(serviceVO));

        init(serviceVO1);
        serviceVO1.setServiceType("test-systemnames");
        assertFalse(serviceVO1.equals(serviceVO));

        init(serviceVO1);
        List<String> list = new ArrayList<>();
        list.add("a");
        serviceVO1.setJob(list);
        assertFalse(serviceVO1.equals(serviceVO));

        init(serviceVO1);
        serviceVO1.setFileName("test-systemnames");
        assertFalse(serviceVO1.equals(serviceVO));

        init(serviceVO1);
        serviceVO1.setFileType("test-systemnames");
        assertFalse(serviceVO1.equals(serviceVO));

        init(serviceVO1);
        serviceVO1.setBundleId(11L);
        assertFalse(serviceVO1.equals(serviceVO));

        init(serviceVO1);
        serviceVO1.setFeatureName("test-systemnames");
        assertFalse(serviceVO1.equals(serviceVO));

        init(serviceVO1);
        serviceVO1.setBundleName(list);
        assertFalse(serviceVO1.equals(serviceVO));

        init(serviceVO1);
        serviceVO1.setServiceKey("test-systemnames");
        assertFalse(serviceVO1.equals(serviceVO));

        init(serviceVO1);
        serviceVO1.setEnabledSAM("test-systemnames");
        assertFalse(serviceVO1.equals(serviceVO));

        init(serviceVO1);
        serviceVO1.setDescription("test-systemnames");
        assertFalse(serviceVO1.equals(serviceVO));

        init(serviceVO1);
        serviceVO1.setResponsiblePerson("test-systemnames");
        assertFalse(serviceVO1.equals(serviceVO));

        init(serviceVO1);
        serviceVO1.setCreateTime("2023-12-13");
        assertFalse(serviceVO1.equals(serviceVO));

        init(serviceVO1);
        serviceVO1.setUpdateTime("2023-12-13");
        assertFalse(serviceVO1.equals(serviceVO));
    }
}
