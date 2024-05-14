package org.talend.esbconsole.server.domain.core.impl;

import org.talend.esbconsole.server.domain.api.model.ConsumerSystemDTO;
import org.talend.esbconsole.server.domain.api.param.ConsumerCreateParam;
import org.talend.esbconsole.server.domain.api.param.ConsumerModifyParam;
import org.talend.esbconsole.server.domain.api.param.ConsumerPageQueryParam;
import org.talend.esbconsole.server.domain.api.query.ConsumerPageQuery;
import org.talend.esbconsole.server.domain.core.converter.ConsumerSystemConverter;
import org.talend.esbconsole.server.domain.core.converter.query.ConsumerQueryConverter;
import org.talend.esbconsole.server.domain.repository.entity.ConsumerSystemDO;
import org.talend.esbconsole.server.domain.repository.mapper.ConsumerSystemDAO;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

/**
 * {@link ConsumerSystemServiceImpl} 单元测试
 *
 * @author xingzilong
 * @date 2021/10/11
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class ConsumerSystemServiceImplTest {

    @InjectMocks
    ConsumerSystemServiceImpl consumerSystemService;

    @Mock
    ConsumerSystemDAO consumerSystemDAO;

    @Mock
    ConsumerSystemConverter consumerSystemConverter;

    @Mock
    ConsumerQueryConverter consumerQueryConverter;

    @Before
    public void setUp() {
        //初始化
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void addConsumerTest() {
        ConsumerCreateParam consumerCreateParam = new ConsumerCreateParam();

        ConsumerSystemDO consumerSystemDO = new ConsumerSystemDO();
        when(consumerSystemConverter.param2do(consumerCreateParam)).thenReturn(consumerSystemDO);

        doNothing().when(consumerSystemDAO).saveConsumerSystem(consumerSystemDO);

        consumerSystemService.addConsumer(consumerCreateParam);

        verify(consumerSystemConverter).param2do(consumerCreateParam);
        verify(consumerSystemDAO).saveConsumerSystem(consumerSystemDO);
    }

    @Test
    public void removeConsumerTest() {
        doNothing().when(consumerSystemDAO).removeConsumerSystem("");
        consumerSystemService.removeConsumer("");
        verify(consumerSystemDAO).removeConsumerSystem("");
    }


    @Test
    public void modifyConsumerTest() {
        ConsumerModifyParam consumerModifyParam = new ConsumerModifyParam();

        ConsumerSystemDO consumerSystemDO = new ConsumerSystemDO();
        when(consumerSystemConverter.param2do(consumerModifyParam)).thenReturn(consumerSystemDO);

        doNothing().when(consumerSystemDAO).updateConsumerSystem(consumerSystemDO);

        consumerSystemService.modifyConsumer(consumerModifyParam);

        verify(consumerSystemConverter).param2do(consumerModifyParam);
        verify(consumerSystemDAO).updateConsumerSystem(consumerSystemDO);
    }

    @Test
    public void disableConsumerTest() {
        doNothing().when(consumerSystemDAO).updateConsumerSystem(Mockito.any());
        consumerSystemService.disableConsumer("");
        verify(consumerSystemDAO).updateConsumerSystem(Mockito.any());

    }

    @Test
    public void enableConsumerTest() {
        doNothing().when(consumerSystemDAO).updateConsumerSystem(Mockito.any());
        consumerSystemService.enableConsumer("");
        verify(consumerSystemDAO).updateConsumerSystem(Mockito.any());
    }

    @Test
    public void getConsumersTest() {
        ConsumerPageQueryParam consumerPageQueryParam = new ConsumerPageQueryParam();
        consumerPageQueryParam.setPageNum(1);
        consumerPageQueryParam.setPageSize(10);

        ConsumerPageQuery consumerPageQuery = new ConsumerPageQuery();
        when(consumerQueryConverter.param2query(consumerPageQueryParam)).thenReturn(consumerPageQuery);

        List<ConsumerSystemDO> consumerSystemDOS = new ArrayList<ConsumerSystemDO>();
        ConsumerSystemDO consumerSystemDO = new ConsumerSystemDO();
        consumerSystemDO.setId("");
        consumerSystemDO.setIp("127.0.0.1");
        consumerSystemDOS.add(consumerSystemDO);
        when(consumerSystemDAO.listAllConsumerSystemsByConditions(consumerPageQuery)).thenReturn(consumerSystemDOS);

        List<ConsumerSystemDTO> consumerSystems = new ArrayList<ConsumerSystemDTO>();
        ConsumerSystemDTO consumerSystemDTO = new ConsumerSystemDTO();
        consumerSystems.add(consumerSystemDTO);
        when(consumerSystemConverter.do2dto(consumerSystemDOS)).thenReturn(consumerSystems);

        consumerSystemService.getConsumers(consumerPageQueryParam);

        verify(consumerQueryConverter).param2query(consumerPageQueryParam);
        verify(consumerSystemDAO).listAllConsumerSystemsByConditions(consumerPageQuery);
        verify(consumerSystemConverter).do2dto(consumerSystemDOS);

    }

    @Test
    public void getConsumerTest() {
        ConsumerSystemDO consumerSystemDO = new ConsumerSystemDO();
        when(consumerSystemDAO.getConsumerSystemById("")).thenReturn(consumerSystemDO);

        ConsumerSystemDTO consumerSystemDTO = new ConsumerSystemDTO();
        when(consumerSystemConverter.do2dto(consumerSystemDO)).thenReturn(consumerSystemDTO);

        consumerSystemService.getConsumer("");
        verify(consumerSystemDAO).getConsumerSystemById("");
        verify(consumerSystemConverter).do2dto(consumerSystemDO);
    }

    @Test
    public void getConsumerByIpTest() {
        ConsumerSystemDO consumerSystemDO = new ConsumerSystemDO();
        when(consumerSystemDAO.getConsumerSystemByIp("")).thenReturn(consumerSystemDO);

        ConsumerSystemDTO consumerSystemDTO = new ConsumerSystemDTO();
        when(consumerSystemConverter.do2dto(consumerSystemDO)).thenReturn(consumerSystemDTO);

        consumerSystemService.getConsumerByIp("");

        verify(consumerSystemDAO).getConsumerSystemByIp("");
        verify(consumerSystemConverter).do2dto(consumerSystemDO);

    }

}
