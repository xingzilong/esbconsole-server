package org.talend.esbconsole.server.web.api.controller.ac.ip.converter;

import org.talend.esbconsole.server.domain.api.model.IPControlModel;
import org.talend.esbconsole.server.domain.api.param.AC4IPPageQueryParam;
import org.talend.esbconsole.server.domain.api.param.AC4IPStatusActionParam;
import org.talend.esbconsole.server.web.api.controller.ac.ip.request.AC4IPPageQueryRequest;
import org.talend.esbconsole.server.web.api.controller.ac.ip.request.AC4IPStatusActionRequest;
import org.talend.esbconsole.server.web.api.controller.ac.ip.vo.IPControlVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * {@link AC4IPWebConverter} 单元测试
 *
 * @author xingzilong
 * @date 2021/10/17
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class AC4IPWebConverterImplTest {

    @InjectMocks
    private AC4IPWebConverterImpl ac4IPWebConverterImpl;

    @Test
    public void req2param4AC4IPPageQueryRequestTest() {
        AC4IPPageQueryRequest request = new AC4IPPageQueryRequest();
        AC4IPPageQueryParam ac4IPPageQueryParam = ac4IPWebConverterImpl.req2param(request);
        assertNotNull(ac4IPPageQueryParam);
        request = null;
        AC4IPPageQueryParam ac4IPPageQueryParam1 = ac4IPWebConverterImpl.req2param(request);
        assertNull(ac4IPPageQueryParam1);
    }

    @Test
    public void req2param4AC4IPStatusActionRequestTest() {
        AC4IPStatusActionRequest request = new AC4IPStatusActionRequest();
        AC4IPStatusActionParam ac4IPStatusActionParam = ac4IPWebConverterImpl.req2param(request);
        assertNotNull(ac4IPStatusActionParam);
        request = null;
        AC4IPStatusActionParam ac4IPStatusActionParam1 = ac4IPWebConverterImpl.req2param(request);
        assertNull(ac4IPStatusActionParam1);
    }

    @Test
    public void dto2vo4IPControlModelTest() {
        IPControlModel dto = new IPControlModel();
        dto.setCreateTime(LocalDateTime.now());
        dto.setUpdateTime(LocalDateTime.now());
        IPControlVO ipControlVO = ac4IPWebConverterImpl.dto2vo(dto);
        assertNotNull(ipControlVO);
        dto = null;
        IPControlVO ipControlVO1 = ac4IPWebConverterImpl.dto2vo(dto);
        assertNull(ipControlVO1);
    }

    @Test
    public void dto2vo4IPControlModelListTest() {
        ArrayList<IPControlModel> ipControlModels = new ArrayList<IPControlModel>();
        ipControlModels.add(new IPControlModel());
        List<IPControlVO> ipControlVOS = ac4IPWebConverterImpl.dto2vo(ipControlModels);
        assertNotNull(ipControlVOS);
        ipControlModels = null;
        List<IPControlVO> ipControlVOS1 = ac4IPWebConverterImpl.dto2vo(ipControlModels);
        assertNull(ipControlVOS1);
    }

}
