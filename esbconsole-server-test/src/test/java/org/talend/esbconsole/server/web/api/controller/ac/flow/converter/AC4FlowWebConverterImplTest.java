package org.talend.esbconsole.server.web.api.controller.ac.flow.converter;

import org.talend.esbconsole.server.domain.api.model.FlowControlModel;
import org.talend.esbconsole.server.domain.api.param.AC4FlowPageQueryParam;
import org.talend.esbconsole.server.domain.api.param.AC4FlowStatusActionParam;
import org.talend.esbconsole.server.web.api.controller.ac.flow.request.AC4FlowPageQueryRequest;
import org.talend.esbconsole.server.web.api.controller.ac.flow.request.AC4FlowStatusActionRequest;
import org.talend.esbconsole.server.web.api.controller.ac.flow.vo.FlowControlVO;
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
 * {@link AC4FlowWebConverterImpl} 单元测试
 *
 * @author xingzilong
 * @date 2023/10/17
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class AC4FlowWebConverterImplTest {

    @InjectMocks
    private AC4FlowWebConverterImpl ac4FlowWebConverterImpl;

    @Test
    public void req2param4AC4FlowPageQueryRequestTest() {
        AC4FlowPageQueryRequest request = new AC4FlowPageQueryRequest();
        AC4FlowPageQueryParam ac4FlowPageQueryParam = ac4FlowWebConverterImpl.req2param(request);
        assertNotNull(ac4FlowPageQueryParam);
        request = null;
        AC4FlowPageQueryParam ac4FlowPageQueryParam1 = ac4FlowWebConverterImpl.req2param(request);
        assertNull(ac4FlowPageQueryParam1);
    }

    @Test
    public void req2param4AC4FlowStatusActionRequestTest() {
        AC4FlowStatusActionRequest request = new AC4FlowStatusActionRequest();
        AC4FlowStatusActionParam ac4FlowStatusActionParam = ac4FlowWebConverterImpl.req2param(request);
        assertNotNull(ac4FlowStatusActionParam);
        request = null;
        AC4FlowStatusActionParam ac4FlowStatusActionParam1 = ac4FlowWebConverterImpl.req2param(request);
        assertNull(ac4FlowStatusActionParam1);
    }

    @Test
    public void dto2vo4FlowControlModelTest() {
        FlowControlModel dto = new FlowControlModel();
        dto.setCreateTime(LocalDateTime.now());
        dto.setUpdateTime(LocalDateTime.now());
        FlowControlVO flowControlVO = ac4FlowWebConverterImpl.dto2vo(dto);
        assertNotNull(flowControlVO);
        dto = null;
        FlowControlVO flowControlVO1 = ac4FlowWebConverterImpl.dto2vo(dto);
        assertNull(flowControlVO1);
    }

    @Test
    public void dto2vo4FlowControlModelListTest() {
        ArrayList<FlowControlModel> flowControlModels = new ArrayList<FlowControlModel>();
        flowControlModels.add(new FlowControlModel());
        List<FlowControlVO> flowControlVOS = ac4FlowWebConverterImpl.dto2vo(flowControlModels);
        assertNotNull(flowControlVOS);
        flowControlModels = null;
        List<FlowControlVO> flowControlVOS1 = ac4FlowWebConverterImpl.dto2vo(flowControlModels);
        assertNull(flowControlVOS1);
    }

}
