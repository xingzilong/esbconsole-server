package org.talend.esbconsole.server.domain.core.converter;

import org.talend.esbconsole.server.domain.api.model.ConsumerSystemDTO;
import org.talend.esbconsole.server.domain.api.param.ConsumerCreateParam;
import org.talend.esbconsole.server.domain.api.param.ConsumerModifyParam;
import org.talend.esbconsole.server.domain.repository.entity.ConsumerSystemDO;
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
 * {@link ConsumerSystemConverterImpl} 单元测试
 *
 * @author xingzilong
 * @date 2023/10/17
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class ConsumerSystemConverterImplTest {

    @InjectMocks
    private ConsumerSystemConverterImpl consumerSystemConverterImpl;

    @Test
    public void do2dto4ConsumerSystemDOTest() {
        ConsumerSystemDO consumerSystemDO = new ConsumerSystemDO();
        consumerSystemDO.setCreateTime(LocalDateTime.now());
        consumerSystemDO.setUpdateTime(LocalDateTime.now());
        ConsumerSystemDTO consumerSystemDTO = consumerSystemConverterImpl.do2dto(consumerSystemDO);
        assertNotNull(consumerSystemDTO);
        consumerSystemDO = null;
        ConsumerSystemDTO consumerSystemDTO1 = consumerSystemConverterImpl.do2dto(consumerSystemDO);
        assertNull(consumerSystemDTO1);
    }

    @Test
    public void do2dto4ConsumerSystemDOListTest() {
        ArrayList<ConsumerSystemDO> consumerSystemDOS = new ArrayList<>();
        consumerSystemDOS.add(new ConsumerSystemDO());
        List<ConsumerSystemDTO> consumerSystemDTOS = consumerSystemConverterImpl.do2dto(consumerSystemDOS);
        assertNotNull(consumerSystemDTOS);
        consumerSystemDOS = null;
        List<ConsumerSystemDTO> consumerSystemDTOS1 = consumerSystemConverterImpl.do2dto(consumerSystemDOS);
        assertNull(consumerSystemDTOS1);
    }

    @Test
    public void param2do4ConsumerCreateParamTest() {
        ConsumerCreateParam param = new ConsumerCreateParam();
        ConsumerSystemDO consumerSystemDO = consumerSystemConverterImpl.param2do(param);
        assertNotNull(consumerSystemDO);
        param = null;
        ConsumerSystemDO consumerSystemDO1 = consumerSystemConverterImpl.param2do(param);
        assertNull(consumerSystemDO1);
    }

    @Test
    public void param2do4ConsumerModifyParamTest() {
        ConsumerModifyParam param = new ConsumerModifyParam();
        ConsumerSystemDO consumerSystemDO = consumerSystemConverterImpl.param2do(param);
        assertNotNull(consumerSystemDO);
        param = null;
        ConsumerSystemDO consumerSystemDO1 = consumerSystemConverterImpl.param2do(param);
        assertNull(consumerSystemDO1);
    }
}
