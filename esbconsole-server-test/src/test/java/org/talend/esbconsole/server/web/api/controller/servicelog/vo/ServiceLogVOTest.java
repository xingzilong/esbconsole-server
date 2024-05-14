package org.talend.esbconsole.server.web.api.controller.servicelog.vo;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * {@link ServiceLogVO} 单元测试
 *
 * @author xingzilong
 * @date 2021/10/9
 */
public class ServiceLogVOTest {

    public void init(ServiceLogVO vo) {
        vo.setType("saop");
        vo.setStartTime("2021-01-23");
        vo.setServiceName("test");
        vo.setServiceKey("key");
        vo.setResponseTime(578946L);
        vo.setMiMessageId("4567");
        vo.setMiFlowId("4564");
        vo.setId(1L);
        vo.setHttpStatus(1);
        vo.setFailureCause("test");
        vo.setConsumerIp("4786");
        vo.setBusinessSystem("esb");
    }

    @Test
    public void test() {
        ServiceLogVO vo = new ServiceLogVO();
        ServiceLogVO logVO = new ServiceLogVO();
        vo.hashCode();
        assertTrue(vo.equals(logVO));
        assertFalse(vo.equals(null));
        init(vo);
        vo.hashCode();
        vo.toString();
        assertFalse(vo.equals(logVO));
        ServiceLogVO temp = vo;
        assertTrue(vo.equals(temp));
        init(logVO);
        assertTrue(vo.equals(logVO));
        logVO.setType("restful");
        assertFalse(vo.equals(logVO));
    }
}
