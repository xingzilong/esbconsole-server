package org.talend.esbconsole.server.domain.core.impl;

import org.talend.esbconsole.server.domain.api.model.BusinessSystemDTO;
import org.talend.esbconsole.server.domain.api.model.ServiceDTO;
import org.talend.esbconsole.server.domain.api.param.BusinessCreateParam;
import org.talend.esbconsole.server.domain.api.param.BusinessModifyParam;
import org.talend.esbconsole.server.domain.api.param.BusinessPageQueryParam;
import org.talend.esbconsole.server.domain.api.query.BusinessPageQuery;
import org.talend.esbconsole.server.domain.core.converter.BusinessSystemConverter;
import org.talend.esbconsole.server.domain.core.converter.query.BusinessQueryConverter;
import org.talend.esbconsole.server.domain.repository.entity.BusinessSystemDO;
import org.talend.esbconsole.server.domain.repository.mapper.BusinessSystemDAO;
import org.talend.esbconsole.server.domain.repository.mapper.ServiceDAO;
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
 * {@link BusinessSystemServiceImpl} 单元测试
 *
 * @author xingzilong
 * @date 2023/10/10
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class BusinessSystemServiceImplTest {

    @Mock
    BusinessSystemDAO businessSystemDAO;

    @Mock
    ServiceDAO serviceDAO;

    @Mock
    BusinessSystemConverter businessSystemConverter;

    @Mock
    BusinessQueryConverter businessQueryConverter;

    @InjectMocks
    BusinessSystemServiceImpl businessSystemService;

    @Before
    public void setUp() {
        //初始化
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void addBusinessTest() {
        BusinessCreateParam businessCreateParam = new BusinessCreateParam();

        BusinessSystemDO businessSystemDO = new BusinessSystemDO();
        businessCreateParam.setResponsiblePerson("admin");
        businessCreateParam.setSystemName("test");
        when(businessSystemConverter.param2do(businessCreateParam)).thenReturn(businessSystemDO);

        doNothing().when(businessSystemDAO).saveBusinessSystem(businessSystemDO);

        businessSystemService.addBusiness(businessCreateParam);

        verify(businessSystemConverter).param2do(businessCreateParam);
        verify(businessSystemDAO).saveBusinessSystem(businessSystemDO);
    }

    @Test
    public void removeBusinessTest() {
        List<ServiceDTO> serviceDTOS = new ArrayList<>();
        doReturn(serviceDTOS).when(serviceDAO).listServicesByConditions(Mockito.any());
        doNothing().when(businessSystemDAO).removeBusinessSystem(Mockito.any());
        businessSystemService.removeBusiness("213213");
        verify(serviceDAO).listServicesByConditions(Mockito.any());
        verify(businessSystemDAO).removeBusinessSystem(Mockito.any());
    }

    @Test
    public void modifyBusinessTest() {
        BusinessModifyParam businessModifyParam = new BusinessModifyParam();

        BusinessSystemDO businessSystemDO = new BusinessSystemDO();
        businessSystemDO.setId("1232");
        businessSystemDO.setResponsiblePerson("admin");
        when(businessSystemConverter.param2do(businessModifyParam)).thenReturn(businessSystemDO);
        doNothing().when(businessSystemDAO).updateBusinessSystem(businessSystemDO);

        businessSystemService.modifyBusiness(businessModifyParam);
        verify(businessSystemDAO).updateBusinessSystem(businessSystemDO);
    }

    @Test
    public void disableBusinessTest() {
        doNothing().when(businessSystemDAO).updateBusinessSystem(Mockito.any());
        businessSystemService.disableBusiness("1561");
        verify(businessSystemDAO).updateBusinessSystem(Mockito.any());

    }

    @Test
    public void enableBusinessTest() {
        doNothing().when(businessSystemDAO).updateBusinessSystem(Mockito.any());
        businessSystemService.enableBusiness("156");
        verify(businessSystemDAO).updateBusinessSystem(Mockito.any());

    }

    @Test
    public void getBusinesssTest() {
        BusinessPageQueryParam businessPageQueryParam = new BusinessPageQueryParam();
        businessPageQueryParam.setPageNum(1);
        businessPageQueryParam.setPageSize(10);

        BusinessPageQuery businessPageQuery = new BusinessPageQuery();
        businessPageQuery.setStatus("1");
        businessPageQuery.setSystemName("test");
        when(businessQueryConverter.param2query(businessPageQueryParam)).thenReturn(businessPageQuery);

        List<BusinessSystemDO> businessSystemDOS = new ArrayList<BusinessSystemDO>();
        BusinessSystemDO businessSystemDO = new BusinessSystemDO();
        businessSystemDO.setId("61651");
        businessPageQuery.setStatus("1");
        businessSystemDOS.add(businessSystemDO);
        when(businessSystemDAO.listAllBusinessSystemsByConditions(businessPageQuery)).thenReturn(businessSystemDOS);


        List<BusinessSystemDTO> businessSystems = new ArrayList<BusinessSystemDTO>();
        BusinessSystemDTO businessSystemDTO = new BusinessSystemDTO();
        businessSystemDTO.setId("61651");
        businessSystemDTO.setStatus("1");
        businessSystemDTO.setResponsiblePerson("admin");
        businessSystems.add(businessSystemDTO);
        when(businessSystemConverter.do2dto(businessSystemDOS)).thenReturn(businessSystems);

        businessSystemService.getBusinesss(businessPageQueryParam);

        verify(businessQueryConverter).param2query(businessPageQueryParam);
        verify(businessSystemDAO).listAllBusinessSystemsByConditions(businessPageQuery);
        verify(businessSystemConverter).do2dto(businessSystemDOS);
    }

    @Test
    public void getBusinessTest() {
        BusinessSystemDO businessSystemDO = new BusinessSystemDO();
        when(businessSystemDAO.getBusinessSystemById(Mockito.any())).thenReturn(businessSystemDO);

        BusinessSystemDTO businessSystemDTO = new BusinessSystemDTO();
        when(businessSystemConverter.do2dto(businessSystemDO)).thenReturn(businessSystemDTO);

        businessSystemService.getBusiness("313");

        verify(businessSystemDAO).getBusinessSystemById(Mockito.any());
        verify(businessSystemConverter).do2dto(businessSystemDO);
    }

    @Test
    public void getAllBusinessSystemNameTest() {
        List<BusinessSystemDO> namList = new ArrayList<BusinessSystemDO>();

        when(businessSystemDAO.getAllBusinessSystemName()).thenReturn(namList);
        businessSystemService.getAllBusinessSystemName();
        verify(businessSystemDAO).getAllBusinessSystemName();

    }

    @Test
    public void getBusinessByBusinessNameTest() {
        BusinessSystemDO businessSystemDO = new BusinessSystemDO();
        when(businessSystemDAO.getBusinessSystemBySystemName(Mockito.any())).thenReturn(businessSystemDO);

        BusinessSystemDTO businessSystemDTO = new BusinessSystemDTO();
        when(businessSystemConverter.do2dto(Mockito.eq(businessSystemDO))).thenReturn(businessSystemDTO);

        businessSystemService.getBusinessByBusinessName("test");

        verify(businessSystemDAO).getBusinessSystemBySystemName(Mockito.any());
        verify(businessSystemConverter).do2dto(Mockito.eq(businessSystemDO));
    }

}
