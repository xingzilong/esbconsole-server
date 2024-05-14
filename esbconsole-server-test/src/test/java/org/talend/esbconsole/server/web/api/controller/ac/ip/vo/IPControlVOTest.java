package org.talend.esbconsole.server.web.api.controller.ac.ip.vo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * {@link IPControlVO} 单元测试
 *
 * @author xingzilong
 * @date 2021/10/9
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class IPControlVOTest {

    private void init(IPControlVO ipControlVO) {
        ipControlVO.setBlackList("127.0.0.1");
        ipControlVO.setCreateTime("");
        ipControlVO.setId("1231");
        ipControlVO.setServiceKey("t");
        ipControlVO.setServiceName("test");
        ipControlVO.setStatus("1");
        ipControlVO.setType("black");
        ipControlVO.setUpdateTime("");
        ipControlVO.setWhiteList("ttt");
    }

    @Test
    public void test() {
        IPControlVO ipControlVO = new IPControlVO();
        IPControlVO ipControl = new IPControlVO();

        assertTrue(ipControlVO.equals(ipControl));
        assertFalse(ipControlVO.equals(null));

        init(ipControl);
        ipControl.toString();
        ipControl.hashCode();
        assertFalse(ipControlVO.equals(ipControl));

        ipControl.getBlackList();
        ipControl.getCreateTime();
        ipControl.getId();
        ipControl.getServiceKey();
        ipControl.getServiceName();
        ipControl.getStatus();
        ipControl.getType();
        ipControl.getUpdateTime();
        ipControl.getWhiteList();

        init(ipControlVO);
        assertTrue(ipControlVO.equals(ipControl));

        init(ipControlVO);
        ipControlVO.setBlackList("");
        assertFalse(ipControlVO.equals(ipControl));

        init(ipControlVO);
        ipControlVO.setCreateTime("2021/10/9");
        assertFalse(ipControlVO.equals(ipControl));

        init(ipControlVO);
        ipControlVO.setId("1");
        assertFalse(ipControlVO.equals(ipControl));

        init(ipControlVO);
        ipControlVO.setServiceKey("tt");
        assertFalse(ipControlVO.equals(ipControl));

        init(ipControlVO);
        ipControlVO.setServiceName("tt");
        assertFalse(ipControlVO.equals(ipControl));

        init(ipControlVO);
        ipControlVO.setStatus("0");
        assertFalse(ipControlVO.equals(ipControl));

        init(ipControlVO);
        ipControlVO.setType("white");
        assertFalse(ipControlVO.equals(ipControl));

        init(ipControlVO);
        ipControlVO.setUpdateTime("2021/10/9");
        assertFalse(ipControlVO.equals(ipControl));

        init(ipControlVO);
        ipControlVO.setWhiteList("tt");
        assertFalse(ipControlVO.equals(ipControl));

    }

}
