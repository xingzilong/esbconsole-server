package org.talend.esbconsole.server.web.api.controller.ac.frequency.converter;

import org.talend.esbconsole.server.domain.api.model.FrequencyControlModel;
import org.talend.esbconsole.server.domain.api.param.AC4FrequencyPageQueryParam;
import org.talend.esbconsole.server.domain.api.param.AC4FrequencyStatusActionParam;
import org.talend.esbconsole.server.web.api.controller.ac.frequency.request.AC4FrequencyPageQueryRequest;
import org.talend.esbconsole.server.web.api.controller.ac.frequency.request.AC4FrequencyStatusActionRequest;
import org.talend.esbconsole.server.web.api.controller.ac.frequency.vo.FrequencyControlVO;
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
 * {@link AC4FrequencyWebConverterImpl} 单元测试
 *
 * @author xingzilong
 * @date 2021/10/17
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class AC4FrequencyWebConverterImplTest {

    @InjectMocks
    private AC4FrequencyWebConverterImpl ac4FrequencyWebConverterImpl;

    @Test
    public void req2param4AC4FrequencyPageQueryRequestTest() {
        AC4FrequencyPageQueryRequest request = new AC4FrequencyPageQueryRequest();
        AC4FrequencyPageQueryParam ac4FrequencyPageQueryParam = ac4FrequencyWebConverterImpl.req2param(request);
        assertNotNull(ac4FrequencyPageQueryParam);
        request = null;
        AC4FrequencyPageQueryParam ac4FrequencyPageQueryParam1 = ac4FrequencyWebConverterImpl.req2param(request);
        assertNull(ac4FrequencyPageQueryParam1);
    }

    @Test
    public void req2param4AC4FrequencyStatusActionRequestTest() {
        AC4FrequencyStatusActionRequest request = new AC4FrequencyStatusActionRequest();
        AC4FrequencyStatusActionParam ac4FrequencyStatusActionParam = ac4FrequencyWebConverterImpl.req2param(request);
        assertNotNull(ac4FrequencyStatusActionParam);
        request = null;
        AC4FrequencyStatusActionParam ac4FrequencyStatusActionParam1 = ac4FrequencyWebConverterImpl.req2param(request);
        assertNull(ac4FrequencyStatusActionParam1);
    }

    @Test
    public void dto2vo4FrequencyControlModelTest() {
        FrequencyControlModel dto = new FrequencyControlModel();
        dto.setCreateTime(LocalDateTime.now());
        dto.setUpdateTime(LocalDateTime.now());
        FrequencyControlVO frequencyControlVO = ac4FrequencyWebConverterImpl.dto2vo(dto);
        assertNotNull(frequencyControlVO);
        dto = null;
        FrequencyControlVO frequencyControlVO1 = ac4FrequencyWebConverterImpl.dto2vo(dto);
        assertNull(frequencyControlVO1);
    }

    @Test
    public void dto2vo4FrequencyControlModelListTest() {
        ArrayList<FrequencyControlModel> frequencyControlModels = new ArrayList<FrequencyControlModel>();
        frequencyControlModels.add(new FrequencyControlModel());
        List<FrequencyControlVO> frequencyControlVOS = ac4FrequencyWebConverterImpl.dto2vo(frequencyControlModels);
        assertNotNull(frequencyControlVOS);
        frequencyControlModels = null;
        List<FrequencyControlVO> frequencyControlVOS1 = ac4FrequencyWebConverterImpl.dto2vo(frequencyControlModels);
        assertNull(frequencyControlVOS1);
    }

}
