package org.talend.esbconsole.server.domain.core.impl;

import org.talend.esbconsole.server.domain.api.model.APICallSuccessFailureDTO;
import org.talend.esbconsole.server.domain.api.model.APICallTotalAnalysisDTO;
import org.talend.esbconsole.server.domain.api.model.ApplicationTotalDTO;
import org.talend.esbconsole.server.domain.api.model.TaskProgressReportDTO;
import org.talend.esbconsole.server.domain.api.model.TotalAnalysisDTO;
import org.talend.esbconsole.server.domain.api.service.HomeAnalysisService;
import org.talend.esbconsole.server.domain.core.util.datafilling.DataFillingUtil;
import org.talend.esbconsole.server.domain.repository.mapper.EventsDAO;
import org.talend.esbconsole.server.domain.repository.mapper.FlowMeterCatcherDAO;
import org.talend.esbconsole.server.domain.repository.mapper.ServiceDAO;
import org.talend.esbconsole.server.domain.repository.mapper.StatCatcherDAO;
import org.talend.esbconsole.server.tools.common.constant.DateTimeFormatterConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * homeService的实现类
 *
 * @author xingzilong
 * @date 2023/08/14
 **/
@Slf4j
@Service
public class HomeAnalysisServiceImpl implements HomeAnalysisService {
    @Autowired
    private ServiceDAO serviceDAO;
    @Autowired
    private FlowMeterCatcherDAO flowMeterCatcherDAO;
    @Autowired
    private EventsDAO eventsDAO;
    @Autowired
    private StatCatcherDAO statCatcherDAO;

    @Override
    public TotalAnalysisDTO getContentTopDataList() {
        TotalAnalysisDTO totalAnalysisDTO = new TotalAnalysisDTO();
        // 获取服务总数、发布api总数、定时路由、路由总数
        totalAnalysisDTO.setServiceTotal(serviceDAO.getServiceTotal());
        totalAnalysisDTO.setApiTotal(serviceDAO.getServiceTotal4API());
        totalAnalysisDTO.setTimedRouteTotal(serviceDAO.getServiceTotal4TimedRoute());
        totalAnalysisDTO.setConventionalRouteTotal(serviceDAO.getServiceTotal4ConventionalRoute());
        //数据传输总数  flowMeterCatcherDAO
        totalAnalysisDTO.setDataTransmissionTotal(flowMeterCatcherDAO.getFlowMeterTotal());
        //调用API次数  eventsDAO  MI_FLOW_ID区分
        totalAnalysisDTO.setApiCallTotal(eventsDAO.getCallTotal());
        return totalAnalysisDTO;
    }

    @Override
    public List<ApplicationTotalDTO> getApplitionAnalysisDataList() {
        ArrayList<ApplicationTotalDTO> applicationTotalDTOS = new ArrayList<>();
        //获取RestFul接口数量，SOAPL接口数量，代理接口数量，定时路由数量，常规路由数量
        applicationTotalDTOS.add(new ApplicationTotalDTO("RestFul接口", serviceDAO.getServiceTotal4RestFulAPI()));
        applicationTotalDTOS.add(new ApplicationTotalDTO("SOAP接口", serviceDAO.getServiceTotal4SOAPAPI()));
        applicationTotalDTOS.add(new ApplicationTotalDTO("代理接口", serviceDAO.getServiceTotal4ProxyRoute()));
        applicationTotalDTOS.add(new ApplicationTotalDTO("定时路由", serviceDAO.getServiceTotal4TimedRoute()));
        applicationTotalDTOS.add(new ApplicationTotalDTO("常规路由", serviceDAO.getServiceTotal4ConventionalRoute()));
        return applicationTotalDTOS;
    }

    @Override
    public APICallSuccessFailureDTO getApiCallStatisticsData() {
        //获取成功、失败次数
        APICallSuccessFailureDTO apiCallSuccessFailureDTO = new APICallSuccessFailureDTO(eventsDAO.getCallTotal4Success(), eventsDAO.getCallTotal4Failure());
        return apiCallSuccessFailureDTO;
    }

    @Override
    public List<TaskProgressReportDTO> getTaskProgressReportData(LocalDate startDate, LocalDate endDate) {
        long days = ChronoUnit.DAYS.between(startDate, endDate);
        List<TaskProgressReportDTO> taskProgressReportDTOS = statCatcherDAO.listTaskProgressReportTotal(startDate.format(DateTimeFormatterConstants.formatter5yyyyMMdd), endDate.format(DateTimeFormatterConstants.formatter5yyyyMMdd));
        // 补全空白日期数据
        for (long i = 0; i < days; i++) {
            LocalDate currentDate = startDate.plus(i, ChronoUnit.DAYS);
            boolean found = false;
            for (TaskProgressReportDTO dto : taskProgressReportDTOS) {
                if (dto.getDate().equals(currentDate)) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                taskProgressReportDTOS.add(new TaskProgressReportDTO(currentDate, 0L, 0L));
            }
        }
        // 2023-10-11 简化了此段代码。还未进行任何测试！！！
//        // 定义一个比较器来按照日期升序排列
//        Comparator<TaskProgressReportDTO> dateComparator = new Comparator<TaskProgressReportDTO>() {
//            @Override
//            public int compare(TaskProgressReportDTO dto1, TaskProgressReportDTO dto2) {
//                return dto1.getDate().compareTo(dto2.getDate());
//            }
//        };
//
//        // 对 taskProgressReportDTOS 列表进行排序
//        Collections.sort(taskProgressReportDTOS, dateComparator);

        Collections.sort(taskProgressReportDTOS, Comparator.comparing(TaskProgressReportDTO::getDate, (s1, s2) -> s1.compareTo(s2)));
        return taskProgressReportDTOS;
    }

    @Override
    public List<APICallTotalAnalysisDTO> getDataCallTotal4Date(LocalDate startDate, LocalDate endDate) {
        long days = ChronoUnit.DAYS.between(startDate, endDate);
        List<APICallTotalAnalysisDTO> apiCallTotalAnalysisDTOS = eventsDAO.listDataCallTotalByEiTimestamp(startDate.format(DateTimeFormatterConstants.formatter5yyyyMMdd), endDate.format(DateTimeFormatterConstants.formatter5yyyyMMdd));

        DataFillingUtil.filling41024(apiCallTotalAnalysisDTOS, startDate, endDate);


        return apiCallTotalAnalysisDTOS;
    }

    @Override
    public List<APICallTotalAnalysisDTO> getCallTotal4Date(LocalDate startDate, LocalDate endDate) {
        long days = ChronoUnit.DAYS.between(startDate, endDate);
        List<APICallTotalAnalysisDTO> apiCallTotalAnalysisDTOS = eventsDAO.listCallTotalByEiTimestamp(startDate.format(DateTimeFormatterConstants.formatter5yyyyMMdd), endDate.format(DateTimeFormatterConstants.formatter5yyyyMMdd));

        DataFillingUtil.filling410000(apiCallTotalAnalysisDTOS, startDate, endDate);

        return apiCallTotalAnalysisDTOS;
    }
}
