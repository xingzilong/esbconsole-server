package org.talend.esbconsole.server.domain.core.converter;

import org.talend.esbconsole.server.domain.api.model.FlowMeterCatcherDTO;
import org.talend.esbconsole.server.domain.repository.entity.FlowMeterCatcherDO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * {@link FlowMeterCatcherConverterImpl} 单元测试
 *
 * @author xingzilong
 * @date 2023/10/17
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class FlowMeterCatcherConverterImplTest {

    @InjectMocks
    private FlowMeterCatcherConverterImpl flowMeterCatcherConverterImpl;

    @Test
    public void do2dto4FlowMeterCatcherDOTest() {
        FlowMeterCatcherDO flowMeterCatcherDO = new FlowMeterCatcherDO();
        FlowMeterCatcherDTO flowMeterCatcherDTO = flowMeterCatcherConverterImpl.do2dto(flowMeterCatcherDO);
        assertNotNull(flowMeterCatcherDTO);
        flowMeterCatcherDO = null;
        FlowMeterCatcherDTO flowMeterCatcherDTO1 = flowMeterCatcherConverterImpl.do2dto(flowMeterCatcherDO);
        assertNull(flowMeterCatcherDTO1);
    }

    @Test
    public void do2dto4FlowMeterCatcherDOListTest() {
        ArrayList<FlowMeterCatcherDO> flowMeterCatcherDOS = new ArrayList<>();
        flowMeterCatcherDOS.add(new FlowMeterCatcherDO());
        List<FlowMeterCatcherDTO> flowMeterCatcherDTOS = flowMeterCatcherConverterImpl.do2dto(flowMeterCatcherDOS);
        assertNotNull(flowMeterCatcherDTOS);
        flowMeterCatcherDOS = null;
        List<FlowMeterCatcherDTO> flowMeterCatcherDTOS1 = flowMeterCatcherConverterImpl.do2dto(flowMeterCatcherDOS);
        assertNull(flowMeterCatcherDTOS1);
    }

}
