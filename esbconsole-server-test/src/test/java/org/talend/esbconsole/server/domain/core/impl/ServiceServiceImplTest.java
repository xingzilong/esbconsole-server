package org.talend.esbconsole.server.domain.core.impl;

import com.alibaba.fastjson2.JSONObject;
import org.talend.esbconsole.server.domain.api.model.APICallAnalysisTableDTO;
import org.talend.esbconsole.server.domain.api.model.APICallTop5DataDTO;
import org.talend.esbconsole.server.domain.api.model.APICallTotalAnalysisDTO;
import org.talend.esbconsole.server.domain.api.model.BundleInfo;
import org.talend.esbconsole.server.domain.api.model.ServiceDTO;
import org.talend.esbconsole.server.domain.api.model.StatCatcherDTO;
import org.talend.esbconsole.server.domain.api.param.AC4FlowStatusActionParam;
import org.talend.esbconsole.server.domain.api.param.AC4FrequencyStatusActionParam;
import org.talend.esbconsole.server.domain.api.param.AC4IPStatusActionParam;
import org.talend.esbconsole.server.domain.api.param.APICallAnalysisParam;
import org.talend.esbconsole.server.domain.api.param.APIRunAnalysisParam;
import org.talend.esbconsole.server.domain.api.param.ServiceInstallParam;
import org.talend.esbconsole.server.domain.api.param.ServiceModifyParam;
import org.talend.esbconsole.server.domain.api.param.ServicePageQueryParam;
import org.talend.esbconsole.server.domain.api.param.ServiceStatusActionParam;
import org.talend.esbconsole.server.domain.api.param.ServiceTimedTaskParam;
import org.talend.esbconsole.server.domain.api.query.ServicePageQuery;
import org.talend.esbconsole.server.domain.core.converter.ServiceConverter;
import org.talend.esbconsole.server.domain.core.converter.StatCatcherConverter;
import org.talend.esbconsole.server.domain.core.converter.query.ServiceQueryConverter;
import org.talend.esbconsole.server.domain.core.util.datafilling.DataFillingUtil;
import org.talend.esbconsole.server.domain.repository.entity.ServiceDO;
import org.talend.esbconsole.server.domain.repository.entity.StatCatcherDO;
import org.talend.esbconsole.server.domain.repository.mapper.ConsumerSystemDAO;
import org.talend.esbconsole.server.domain.repository.mapper.EventsDAO;
import org.talend.esbconsole.server.domain.repository.mapper.ServiceDAO;
import org.talend.esbconsole.server.domain.repository.mapper.StatCatcherDAO;
import org.talend.esbconsole.server.tools.base.request.TimeInterval;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.multipart.MultipartFile;
import org.talend.esbconsole.server.domain.api.service.BundleService;
import org.talend.esbconsole.server.domain.api.service.FlowControlService;
import org.talend.esbconsole.server.domain.api.service.FrequencyControlService;
import org.talend.esbconsole.server.domain.api.service.IPControlService;
import org.talend.esbconsole.server.domain.api.service.KarService;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertSame;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * {@link ServiceServiceImpl} 单元测试
 *
 * @author xingzilong
 * @date 2023/10/12
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class ServiceServiceImplTest {
    @Mock
    private ServiceDAO serviceDAO;

    @Mock
    private EventsDAO eventsDAO;

    @Mock
    private StatCatcherDAO statCatcherDAO;

    @Mock
    private ConsumerSystemDAO consumerSystemDAO;

    @Mock
    private BundleService bundleService;

    @Mock
    private KarService karService;

    @Mock
    private ServiceConverter serviceConverter;

    @Mock
    private ServiceQueryConverter serviceQueryConverter;

    @Mock
    private IPControlService ipControlService;

    @Mock
    private FrequencyControlService frequencyControlService;

    @Mock
    private FlowControlService flowControlService;

    @Mock
    private StatCatcherConverter setStatCatcherRecord;

    @InjectMocks
    private ServiceServiceImpl serviceImpl;

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getAllServicesTest() {
        List<ServiceDTO> result = Mockito.mock(List.class);
        Mockito.doReturn(Arrays.asList(new ServiceDO())).when(serviceDAO).listAllServices();
        Mockito.doReturn(result).when(serviceConverter).do2dto(Mockito.anyList());
        assertSame(result, serviceImpl.getAllServices());
        Mockito.verify(serviceDAO).listAllServices();
        Mockito.verify(serviceConverter).do2dto(Mockito.anyList());
    }

    @Test
    public void getServiceByIdTest() {
        ServiceDTO result = Mockito.mock(ServiceDTO.class);
        Mockito.doReturn(new ServiceDO()).when(serviceDAO).getServiceById(Mockito.anyString());
        Mockito.doReturn(result).when(serviceConverter).do2dto(Mockito.any(ServiceDO.class));
        assertSame(result, serviceImpl.getServiceById("test"));
        Mockito.verify(serviceDAO).getServiceById(Mockito.anyString());
        Mockito.verify(serviceConverter).do2dto(Mockito.any(ServiceDO.class));
    }

    @Test
    public void getServicesTest() {
        ServicePageQueryParam param = new ServicePageQueryParam();
        param.setPageNum(1);
        param.setPageSize(10);
        Mockito.doReturn(new ServicePageQuery()).when(serviceQueryConverter).param2query(Mockito.any(ServicePageQueryParam.class));
        Mockito.doReturn(Arrays.asList(new ServiceDTO())).when(serviceDAO).listServicesByConditions(Mockito.any(ServicePageQuery.class));
        serviceImpl.getServices(param);
        Mockito.verify(serviceQueryConverter).param2query(Mockito.any(ServicePageQueryParam.class));
        Mockito.verify(serviceDAO).listServicesByConditions(Mockito.any(ServicePageQuery.class));
    }

    @Test
    public void installServiceTest() throws IllegalStateException, IOException {
        MultipartFile file = Mockito.mock(MultipartFile.class);
        ServiceDO serviceDO = Mockito.mock(ServiceDO.class);
        ServiceInstallParam param = Mockito.mock(ServiceInstallParam.class);
        URI uri = Mockito.mock(URI.class);
        URL url = Mockito.mock(URL.class);
        Mockito.doReturn(serviceDO).when(serviceConverter).param2do(Mockito.any(ServiceInstallParam.class));
        Mockito.doReturn("test.jar").when(file).getOriginalFilename();
//    	MockedConstructionImpl<File> appFile = (MockedConstructionImpl<File>) Mockito.mockConstruction(File.class,(mock,context)->{
//    		Mockito.doReturn(false).when(mock).exists();
//    		Mockito.doReturn(uri).when(mock).toURI();
//    		Mockito.doReturn(true).when(mock).delete();
//    	}); 
        Mockito.doNothing().when(file).transferTo(Mockito.any(File.class));
//    	Mockito.doReturn(url).when(uri).toURL();
//    	Mockito.doReturn("/service").when(url).toString();
        Mockito.doReturn("restful_ws").doReturn("sts").when(serviceDO).getServiceType();
        Mockito.doReturn("test").when(serviceDO).getServiceKey();
        Mockito.doReturn(1L).when(bundleService).installBundle(Mockito.anyString());
        Mockito.doNothing().when(serviceDAO).saveService(Mockito.any(ServiceDO.class));
        Mockito.doNothing().when(karService).installKar(Mockito.anyString());
        try {
            serviceImpl.installService(file, param);
            serviceImpl.installService(file, param);
        } catch (Exception e) {
            assertEquals("未找到相关类型信息，File：sts", e.getMessage());
        }
        try {
            Mockito.doReturn("test.kar").doReturn("test.txt").when(file).getOriginalFilename();
            Mockito.doReturn("restful_ws").when(serviceDO).getServiceType();
            serviceImpl.installService(file, param);
            Mockito.doReturn(null).when(serviceDO).getServiceKey();
            Mockito.doReturn("test").when(param).getProxyAddress();
            serviceImpl.installService(file, param);
            Mockito.doThrow(new IOException()).when(file).transferTo(Mockito.any(File.class));
            serviceImpl.installService(file, param);
        } catch (Exception e) {
            assertEquals("文件已存在，File：test.txt", e.getMessage());
        }
//    	appFile.close();

    }

    @Test
    public void getBundlesForServiceTest() {
        BundleInfo info = new BundleInfo();
        info.setIdentifier(1L);
        info.setUpdateLocation("es");
        BundleInfo infos = new BundleInfo();
        infos.setIdentifier(0L);
        infos.setUpdateLocation("skt");
        Mockito.doReturn(Arrays.asList(info, infos)).when(bundleService).getAllBundlesForKaraf();
        ServiceStatusActionParam param = Mockito.mock(ServiceStatusActionParam.class);
        Mockito.doReturn("jar").doReturn("kar").when(param).getFileType();
        Mockito.doReturn(1L).when(param).getBundleId();
        Mockito.doReturn(Arrays.asList("test")).when(param).getBundleName();
        serviceImpl.getBundlesForService(param);
        serviceImpl.getBundlesForService(param);
        Mockito.verify(bundleService, Mockito.times(2)).getAllBundlesForKaraf();
        Mockito.doReturn("test").when(param).getFileType();
        serviceImpl.getBundlesForService(param);
    }

    @Test
    public void modifyServiceTest() {
        ServiceModifyParam param = new ServiceModifyParam();
        Mockito.doReturn(new ServiceDO()).when(serviceConverter).param2do(Mockito.any(ServiceModifyParam.class));
        Mockito.doNothing().when(serviceDAO).updateService(Mockito.any(ServiceDO.class));
        serviceImpl.modifyService(param);
        Mockito.verify(serviceConverter).param2do(Mockito.any(ServiceModifyParam.class));
        Mockito.verify(serviceDAO).updateService(Mockito.any(ServiceDO.class));
    }

    @Test
    public void getCallTotalTop5ChartTest() {
        APICallAnalysisParam param = new APICallAnalysisParam();
        param.setId("1456");
        param.setTimeInterval(new TimeInterval());
        ServiceDO serviceDO = new ServiceDO();
        serviceDO.setServiceKey("test");
        Mockito.doReturn(serviceDO).when(serviceDAO).getServiceById(Mockito.anyString());
        List<APICallTop5DataDTO> list = new ArrayList<APICallTop5DataDTO>();
        list.add(new APICallTop5DataDTO());
        Mockito.doReturn(list).when(eventsDAO).getCallTotalTop5(Mockito.anyString(), Mockito.any(TimeInterval.class));
        serviceImpl.getCallTotalTop5Chart(param);
        Mockito.verify(eventsDAO).getCallTotalTop5(Mockito.anyString(), Mockito.any(TimeInterval.class));
        Mockito.verify(serviceDAO).getServiceById(Mockito.anyString());
        list.add(new APICallTop5DataDTO());
        list.add(new APICallTop5DataDTO());
        list.add(new APICallTop5DataDTO());
        list.add(new APICallTop5DataDTO());
        Mockito.doReturn(list).when(eventsDAO).getCallTotalTop5(Mockito.anyString(), Mockito.any(TimeInterval.class));
        serviceImpl.getCallTotalTop5Chart(param);

    }

    @Test
    public void getFailureTotalTop5ChartTest() {
        APICallAnalysisParam param = new APICallAnalysisParam();
        param.setId("1456");
        param.setTimeInterval(new TimeInterval());
        ServiceDO serviceDO = new ServiceDO();
        serviceDO.setServiceKey("test");
        Mockito.doReturn(serviceDO).when(serviceDAO).getServiceById(Mockito.anyString());
        List<APICallTop5DataDTO> list = new ArrayList<APICallTop5DataDTO>();
        list.add(new APICallTop5DataDTO());
        Mockito.doReturn(list).when(eventsDAO).getFailureTotalTop5(Mockito.anyString(), Mockito.any(TimeInterval.class));
        serviceImpl.getFailureTotalTop5Chart(param);
        Mockito.verify(eventsDAO).getFailureTotalTop5(Mockito.anyString(), Mockito.any(TimeInterval.class));
        Mockito.verify(serviceDAO).getServiceById(Mockito.anyString());
        list.add(new APICallTop5DataDTO());
        list.add(new APICallTop5DataDTO());
        list.add(new APICallTop5DataDTO());
        list.add(new APICallTop5DataDTO());
        Mockito.doReturn(list).when(eventsDAO).getFailureTotalTop5(Mockito.anyString(), Mockito.any(TimeInterval.class));
        serviceImpl.getFailureTotalTop5Chart(param);
    }

    @Test
    public void getAPICallAnalysisTest() {
        APICallAnalysisParam param = new APICallAnalysisParam();
        param.setId("1456");
        param.setConsumerSystem("esb");
        param.setPageNum(1);
        param.setPageSize(10);
        param.setTimeInterval(new TimeInterval());
        ServiceDO serviceDO = new ServiceDO();
        serviceDO.setServiceKey("test");
        Mockito.doReturn(serviceDO).when(serviceDAO).getServiceById(Mockito.anyString());
        Mockito.doReturn("test").doReturn(null).when(consumerSystemDAO).getIPBySystemName(Mockito.anyString());
        APICallAnalysisTableDTO dto = new APICallAnalysisTableDTO();
        Mockito.doReturn(Arrays.asList(dto)).when(eventsDAO).getCallTotal4CallAnalysis(Mockito.anyString(), Mockito.anyString(), Mockito.any(TimeInterval.class));
        Mockito.doReturn(Arrays.asList(new APICallAnalysisTableDTO())).when(eventsDAO).getCallFailureTotal4CallAnalysis(Mockito.anyList(), Mockito.anyString(), Mockito.any(TimeInterval.class));
        Mockito.doReturn(Arrays.asList(new APICallAnalysisTableDTO())).when(eventsDAO).getCallFailureTotalFrequency4CallAnalysis(Mockito.anyList(), Mockito.anyString(), Mockito.any(TimeInterval.class));
        Mockito.doReturn(Arrays.asList(new APICallAnalysisTableDTO())).when(eventsDAO).getCallFailureTotalFlow4CallAnalysis(Mockito.anyList(), Mockito.anyString(), Mockito.any(TimeInterval.class));
        Mockito.doReturn(Arrays.asList(new APICallAnalysisTableDTO())).when(eventsDAO).getCallFailureTotalIP4CallAnalysis(Mockito.anyList(), Mockito.anyString(), Mockito.any(TimeInterval.class));
        Mockito.doReturn(Arrays.asList(new APICallAnalysisTableDTO())).when(eventsDAO).getAVGResponseTime4CallAnalysis(Mockito.anyList(), Mockito.anyString(), Mockito.any(TimeInterval.class));
        serviceImpl.getAPICallAnalysis(param);
        Mockito.verify(serviceDAO).getServiceById(Mockito.anyString());
        Mockito.verify(consumerSystemDAO).getIPBySystemName(Mockito.anyString());
        Mockito.verify(eventsDAO).getCallTotal4CallAnalysis(Mockito.anyString(), Mockito.anyString(), Mockito.any(TimeInterval.class));
        Mockito.verify(eventsDAO).getCallFailureTotal4CallAnalysis(Mockito.anyList(), Mockito.anyString(), Mockito.any(TimeInterval.class));
        Mockito.verify(eventsDAO).getCallFailureTotalFrequency4CallAnalysis(Mockito.anyList(), Mockito.anyString(), Mockito.any(TimeInterval.class));
        Mockito.verify(eventsDAO).getAVGResponseTime4CallAnalysis(Mockito.anyList(), Mockito.anyString(), Mockito.any(TimeInterval.class));
        serviceImpl.getAPICallAnalysis(param);
        param.setConsumerSystem(null);
        serviceImpl.getAPICallAnalysis(param);

    }

    @Test
    public void getAPICallTotaleAnalysisTest() {
        APIRunAnalysisParam param = new APIRunAnalysisParam();
        param.setId("123456");
        TimeInterval time = new TimeInterval();
        time.setStartTime("2021-12-14");
        time.setEndTime("2021-12-16");
        param.setTimeInterval(time);
        ServiceDO serviceDO = new ServiceDO();
        serviceDO.setServiceKey("test");
        Mockito.doReturn(serviceDO).when(serviceDAO).getServiceById(Mockito.anyString());
        Mockito.doReturn(Arrays.asList(new APICallTotalAnalysisDTO())).when(eventsDAO).getServiceSuccessTotalByDate(Mockito.anyString(), Mockito.any(TimeInterval.class));
        Mockito.doReturn(Arrays.asList(new APICallTotalAnalysisDTO())).when(eventsDAO).getServiceFailureTotalByDate(Mockito.anyString(), Mockito.any(TimeInterval.class));
        MockedStatic<DataFillingUtil> util = Mockito.mockStatic(DataFillingUtil.class);
        util.when(() -> DataFillingUtil.filling410000(Mockito.anyList(), Mockito.any(LocalDate.class), Mockito.any(LocalDate.class))).thenAnswer((Answer<Void>) invocation -> null);
        try {
            serviceImpl.getAPICallTotaleAnalysis(param);
            Mockito.verify(serviceDAO).getServiceById(Mockito.anyString());
            Mockito.verify(eventsDAO).getServiceSuccessTotalByDate(Mockito.anyString(), Mockito.any(TimeInterval.class));
            Mockito.verify(eventsDAO).getServiceFailureTotalByDate(Mockito.anyString(), Mockito.any(TimeInterval.class));
        } catch (Exception e) {

        } finally {
            util.close();
        }
    }

    @Test
    public void getAPIResponseTimeAnalysisTest() {
        APIRunAnalysisParam param = new APIRunAnalysisParam();
        param.setId("123456");
        TimeInterval time = new TimeInterval();
        time.setStartTime("2021-12-14");
        time.setEndTime("2021-12-16");
        param.setTimeInterval(time);
        ServiceDO serviceDO = new ServiceDO();
        serviceDO.setServiceKey("test");
        Mockito.doReturn(serviceDO).when(serviceDAO).getServiceById(Mockito.anyString());
        Mockito.doReturn(Arrays.asList(new APICallTotalAnalysisDTO())).when(eventsDAO).getServiceResponseTimeByDate(Mockito.anyString(), Mockito.any(TimeInterval.class));
        MockedStatic<DataFillingUtil> util = Mockito.mockStatic(DataFillingUtil.class);
        util.when(() -> DataFillingUtil.filling410000(Mockito.anyList(), Mockito.any(LocalDate.class), Mockito.any(LocalDate.class))).thenAnswer((Answer<Void>) invocation -> null);
        try {
            serviceImpl.getAPIResponseTimeAnalysis(param);
            Mockito.verify(serviceDAO).getServiceById(Mockito.anyString());
            Mockito.verify(eventsDAO).getServiceResponseTimeByDate(Mockito.anyString(), Mockito.any(TimeInterval.class));
        } catch (Exception e) {

        } finally {
            util.close();
        }
    }

    @Test
    public void getAPIMessageSizeAnalysisTest() {
        APIRunAnalysisParam param = new APIRunAnalysisParam();
        param.setId("123456");
        TimeInterval time = new TimeInterval();
        time.setStartTime("2021-12-14");
        time.setEndTime("2021-12-16");
        param.setTimeInterval(time);
        ServiceDO serviceDO = new ServiceDO();
        serviceDO.setServiceKey("test");
        Mockito.doReturn(serviceDO).when(serviceDAO).getServiceById(Mockito.anyString());
        Mockito.doReturn(Arrays.asList(new APICallTotalAnalysisDTO())).when(eventsDAO).getServiceMessageSizeByDate(Mockito.anyString(), Mockito.any(TimeInterval.class));
        MockedStatic<DataFillingUtil> util = Mockito.mockStatic(DataFillingUtil.class);
        util.when(() -> DataFillingUtil.filling41024(Mockito.anyList(), Mockito.any(LocalDate.class), Mockito.any(LocalDate.class))).thenAnswer((Answer<Void>) invocation -> null);
        try {
            serviceImpl.getAPIMessageSizeAnalysis(param);
            Mockito.verify(serviceDAO).getServiceById(Mockito.anyString());
            Mockito.verify(eventsDAO).getServiceMessageSizeByDate(Mockito.anyString(), Mockito.any(TimeInterval.class));
        } catch (Exception e) {

        } finally {
            util.close();
        }
    }

    @Test
    public void getTaskExecutionTest() {
        ServiceTimedTaskParam param = new ServiceTimedTaskParam();
        param.setId("123456");
        TimeInterval time = new TimeInterval();
        time.setStartTime("2021-12-14");
        time.setEndTime("2021-12-16");
        param.setTimeInterval(time);
        param.setTaskName("es");
        Mockito.doReturn(JSONObject.toJSONString("test")).when(serviceDAO).getTaskConsumptionTotal(Mockito.anyString());
        Mockito.doReturn(1).when(statCatcherDAO).getTaskExecuteSuccess(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString());
        Mockito.doReturn(1).when(statCatcherDAO).getTaskExecuteFailure(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString());
        serviceImpl.getTaskExecution(param);
        Mockito.verify(serviceDAO).getTaskConsumptionTotal(Mockito.anyString());
        Mockito.verify(statCatcherDAO).getTaskExecuteSuccess(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString());
        Mockito.verify(statCatcherDAO).getTaskExecuteFailure(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString());

    }

    @Test
    public void getTaskConsumptionTest() {
        ServiceTimedTaskParam param = new ServiceTimedTaskParam();
        param.setId("12306");
        Mockito.doReturn(JSONObject.toJSONString("test")).when(serviceDAO).getTaskConsumptionTotal(Mockito.anyString());
        TimeInterval time = new TimeInterval();
        time.setStartTime("2021-12-14");
        time.setEndTime("2021-12-16");
        param.setTimeInterval(time);
        param.setTaskName("test");
        StatCatcherDO cat = Mockito.mock(StatCatcherDO.class);
        Mockito.doReturn(Arrays.asList(cat)).when(statCatcherDAO).getTimedTask(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString());
        Mockito.doReturn(LocalDateTime.now()).when(cat).getMoment();
        Mockito.doReturn(1L).when(cat).getDuration();
        serviceImpl.getTaskConsumption(param);
        Mockito.verify(serviceDAO).getTaskConsumptionTotal(Mockito.anyString());
        Mockito.verify(statCatcherDAO).getTimedTask(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString());
    }

    @Test
    public void getTimedTaskTableTest() {
        ServiceTimedTaskParam param = new ServiceTimedTaskParam();
        param.setId("12306");
        param.setPageNum(1);
        param.setPageSize(10);
        TimeInterval time = new TimeInterval();
        time.setStartTime("2021-12-14");
        time.setEndTime("2021-12-16");
        param.setTimeInterval(time);
        Mockito.doReturn(JSONObject.toJSONString("test")).doReturn(JSONObject.toJSONString(new ArrayList<String>())).when(serviceDAO).getTaskConsumptionTotal(Mockito.anyString());
        Mockito.doReturn(Arrays.asList("message")).when(statCatcherDAO).listUUIdByConditions4Analysis(Mockito.anyList(), Mockito.any(TimeInterval.class));
        StatCatcherDO catcher = new StatCatcherDO();
        catcher.setUuid("445678");
        Mockito.doReturn(Arrays.asList(catcher)).when(statCatcherDAO).listStatCatchersByUUIdList4Analysis(Mockito.anyList());
        StatCatcherDTO dto = new StatCatcherDTO();
        dto.setPid("4567");
        Mockito.doReturn(dto).when(setStatCatcherRecord).do2dto(Mockito.any(StatCatcherDO.class));
        serviceImpl.getTimedTaskTable(param);
        Mockito.verify(serviceDAO).getTaskConsumptionTotal(Mockito.anyString());
        Mockito.verify(setStatCatcherRecord).do2dto(Mockito.any(StatCatcherDO.class));
        Mockito.verify(statCatcherDAO).listUUIdByConditions4Analysis(Mockito.anyList(), Mockito.any(TimeInterval.class));
        Mockito.verify(statCatcherDAO).listStatCatchersByUUIdList4Analysis(Mockito.anyList());
        param.setTaskName("test");
        Mockito.doReturn(Arrays.asList()).doReturn(null).when(statCatcherDAO).listUUIdByConditions4Analysis(Mockito.anyList(), Mockito.any(TimeInterval.class));
        serviceImpl.getTimedTaskTable(param);
        serviceImpl.getTimedTaskTable(param);
        Mockito.doReturn(null).doReturn(JSONObject.toJSONString(new ArrayList<String>())).when(serviceDAO).getTaskConsumptionTotal(Mockito.anyString());
        param.setTaskName(null);
        serviceImpl.getTimedTaskTable(param);
        serviceImpl.getTimedTaskTable(param);

    }

    @Test
    public void unInstallServiceTest() {
        ServiceStatusActionParam param = new ServiceStatusActionParam();
        param.setFileType("jar");
        param.setId("12306");
        param.setServiceType("conventional_route");
        param.setFileName("test");
        param.setBundleId(1L);
        Mockito.doNothing().when(bundleService).uninstallBundleByID(Mockito.anyLong());
        Mockito.doNothing().when(karService).uninstallKar(Mockito.anyString());
        Mockito.doNothing().when(serviceDAO).removeService(Mockito.anyString());
        Mockito.doNothing().when(ipControlService).removeIPControl(Mockito.any(AC4IPStatusActionParam.class));
        Mockito.doNothing().when(frequencyControlService).removeFrequencyControl(Mockito.any(AC4FrequencyStatusActionParam.class));
        Mockito.doNothing().when(flowControlService).removeFlowControl(Mockito.any(AC4FlowStatusActionParam.class));
        serviceImpl.unInstallService(param);
        Mockito.verify(bundleService).uninstallBundleByID(Mockito.anyLong());
        Mockito.verify(serviceDAO).removeService(Mockito.anyString());
        Mockito.verify(ipControlService).removeIPControl(Mockito.any(AC4IPStatusActionParam.class));
        Mockito.verify(frequencyControlService).removeFrequencyControl(Mockito.any(AC4FrequencyStatusActionParam.class));
        Mockito.verify(flowControlService).removeFlowControl(Mockito.any(AC4FlowStatusActionParam.class));

        param.setFileType("kar");
        param.setServiceType("soap_ws");
        serviceImpl.unInstallService(param);
        Mockito.verify(karService).uninstallKar(Mockito.anyString());

        param.setServiceType("restful_ws");
        serviceImpl.unInstallService(param);

        param.setServiceType("test");
        serviceImpl.unInstallService(param);


    }

    @Test
    public void startServiceTest() {
        ServiceStatusActionParam param = new ServiceStatusActionParam();
        param.setFileType("jar");
        param.setId("12306");
        param.setServiceType("conventional_route");
        param.setFileName("test");
        param.setBundleId(1L);
        Mockito.doNothing().when(bundleService).startBundleByID(Mockito.anyLong());

        param.setBundleName(Arrays.asList("test"));
        Mockito.doNothing().when(bundleService).startBundleByMVNName(Mockito.anyString());

        serviceImpl.startService(param);
        Mockito.verify(bundleService).startBundleByID(Mockito.anyLong());

        param.setFileType("kar");
        serviceImpl.startService(param);
        Mockito.verify(bundleService).startBundleByMVNName(Mockito.anyString());
    }

    @Test
    public void stopServiceTest() {
        ServiceStatusActionParam param = new ServiceStatusActionParam();
        param.setFileType("jar");
        param.setId("12306");
        param.setServiceType("conventional_route");
        param.setFileName("test");
        param.setBundleId(1L);
        Mockito.doNothing().when(bundleService).stopBundleByID(Mockito.anyLong());

        param.setBundleName(Arrays.asList("test"));
        Mockito.doNothing().when(bundleService).stopBundleByMVNName(Mockito.anyString());

        serviceImpl.stopService(param);
        Mockito.verify(bundleService).stopBundleByID(Mockito.anyLong());

        param.setFileType("kar");
        serviceImpl.stopService(param);
        Mockito.verify(bundleService).stopBundleByMVNName(Mockito.anyString());
    }

}
