package org.talend.esbconsole.server.domain.core.impl;

import org.talend.esbconsole.server.domain.api.model.ServiceLogDTO;
import org.talend.esbconsole.server.domain.api.model.ServiceLogModel;
import org.talend.esbconsole.server.domain.api.model.message.HttpMessage;
import org.talend.esbconsole.server.domain.api.param.ServiceLogPageQueryParam;
import org.talend.esbconsole.server.domain.api.query.ServiceLogPageQuery;
import org.talend.esbconsole.server.domain.api.service.ServiceLogService;
import org.talend.esbconsole.server.domain.core.converter.EventsConverter;
import org.talend.esbconsole.server.domain.core.converter.query.ServiceLogQueryConverter;
import org.talend.esbconsole.server.domain.repository.entity.EventsDO;
import org.talend.esbconsole.server.domain.repository.mapper.EventsDAO;
import org.talend.esbconsole.server.tools.base.result.PageResult;
import org.talend.esbconsole.server.tools.common.constant.Constants;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 服务日志功能服务接口 {@link ServiceLogService} 的实现类
 *
 * @author xingzilong
 * @date 2021/05/04
 */
@Service
public class ServiceLogServiceImpl implements ServiceLogService {

    @Autowired
    private EventsDAO eventsDAO;

    @Autowired
    private ServiceLogQueryConverter serviceLogQueryConverter;

    @Autowired
    private EventsConverter eventsConverter;

    @Override
    public PageResult<ServiceLogDTO> getServiceLogs(ServiceLogPageQueryParam serviceLogPageQueryParam) {
        ServiceLogPageQuery serviceLogPageQuery = serviceLogQueryConverter.param2query(serviceLogPageQueryParam);
        PageHelper.startPage(serviceLogPageQueryParam.getPageNum(), serviceLogPageQueryParam.getPageSize());
//        List<ServiceLog> serviceLogList = eventsDAO.listEventsByConditions(serviceLogPageQuery);
//        PageInfo<ServiceLog> serviceLogListPageInfo = new PageInfo<ServiceLog>(serviceLogList);
        // 查询 符合条件的MiFlowId集合
        List<String> miFlowIdList = eventsDAO.listREQMiFlowIdByConditions(serviceLogPageQuery);
        // 如果查询不到符合条件的 miFlowIdList ， 直接返回
        if (miFlowIdList.size() == 0) {
            return PageResult.of(new ArrayList<ServiceLogDTO>(), 0L);
        }
        // 获取数据总数
        PageInfo<String> miFlowIdListPageInfo = new PageInfo<String>(miFlowIdList);
        // 根据miFlowIdList查询相应的请求和响应的日志结果集
        List<ServiceLogModel> serviceLogModelList = eventsDAO.listEventsByMiFlowIdList(miFlowIdList);
        // 将查询的结果集进行聚合转换，即将同属于一次的请求和响应日志聚合为一条记录
        List<ServiceLogDTO> serviceLogDTOList = convertToServiceLogDTOList(serviceLogModelList, serviceLogPageQuery);
        return PageResult.of(serviceLogDTOList, miFlowIdListPageInfo.getTotal());
    }

    @Override
    public HttpMessage getRequestMessage(String flowId) {
        EventsDO eventsDO = eventsDAO.getHttpMessage("REQ", flowId);
        HttpMessage httpMessage = eventsConverter.do2dto(eventsDO);
        return httpMessage;
    }

    @Override
    public HttpMessage getResponseMessage(String flowId) {
        EventsDO eventsDO = eventsDAO.getHttpMessage("RESP", flowId);
        HttpMessage httpMessage = eventsConverter.do2dto(eventsDO);
        return httpMessage;
    }

    /**
     * 将serviceLogList 转换为 serviceLogDTOList
     *
     * @param serviceLogModelList 原始数据
     * @param serviceLogPageQuery 查询条件
     * @return
     */
    public List<ServiceLogDTO> convertToServiceLogDTOList(List<ServiceLogModel> serviceLogModelList, ServiceLogPageQuery serviceLogPageQuery) {
        // 按miFlowId对ServiceLog对象进行分组，会打乱顺序
        Map<String, List<ServiceLogModel>> groupedLogs = serviceLogModelList.stream()
                .collect(Collectors.groupingBy(ServiceLogModel::getMiFlowId));

        List<ServiceLogDTO> serviceLogDTOList = new ArrayList<>();

        // 将每组ServiceLog对象转换为ServiceLogDTO
        for (List<ServiceLogModel> serviceLogModel : groupedLogs.values()) {
            ServiceLogDTO serviceLogDTO = createServiceLogDTO(serviceLogModel);
            serviceLogDTOList.add(serviceLogDTO);
        }
        // 重新排序，在分组时会将此页内的顺序打乱，所以要重新按时间进行排序
        // 页内升序
        if (Constants.ASC.equals(serviceLogPageQuery.getEiTimeStampSort())) {
            Collections.sort(serviceLogDTOList, Comparator.comparing(ServiceLogDTO::getStartTime, (s1, s2) -> s1.compareTo(s2)));
        }
        // 页内降序
        if (Constants.DESC.equals(serviceLogPageQuery.getEiTimeStampSort())) {
            Collections.sort(serviceLogDTOList, Comparator.comparing(ServiceLogDTO::getStartTime, (s1, s2) -> s2.compareTo(s1)));
        }
        return serviceLogDTOList;
    }

    /**
     * 根据serviceLogList创建ServiceLogDTO实例对象。
     *
     * @param serviceLogModelList 传入的ServiceLog数组，正常情况下此数组的长度只能是 2
     * @return
     */
    private ServiceLogDTO createServiceLogDTO(List<ServiceLogModel> serviceLogModelList) {
        ServiceLogDTO serviceLogDTO = new ServiceLogDTO();
        for (ServiceLogModel serviceLogModel : serviceLogModelList) {

            if (isRequest(serviceLogModel)) {
                serviceLogDTO.setId(serviceLogModel.getId());
                serviceLogDTO.setServiceName(serviceLogModel.getServiceName());
                serviceLogDTO.setBusinessSystem(serviceLogModel.getBusinessSystem());
                serviceLogDTO.setServiceKey(serviceLogModel.getServiceKey());
                serviceLogDTO.setType(getBindingType(serviceLogModel));
                serviceLogDTO.setConsumerIp(serviceLogModel.getConsumerIp());
                serviceLogDTO.setStartTime(serviceLogModel.getEiTimestamp());
                serviceLogDTO.setMiFlowId(serviceLogModel.getMiFlowId());
            } else {
                serviceLogDTO.setHttpStatus(serviceLogModel.getHttpStatus());
                serviceLogDTO.setFailureCause(serviceLogModel.getFailureCause());
                serviceLogDTO.setResponseTime(serviceLogModel.getResponseTime());
            }
        }
        return serviceLogDTO;
    }

    /**
     * 获取调用日志所记录的接口的webservice风格类型
     *
     * @param serviceLogModel
     * @return
     */
    private String getBindingType(ServiceLogModel serviceLogModel) {
        if (serviceLogModel.getMiTransportType().contains(Constants.SOAP)) {
            return Constants.SOAP;
        }
        return Constants.RESTFUL;
    }

    /**
     * 判断调用日志是请求日志还是响应日志
     *
     * @param serviceLogModel
     * @return
     */
    private boolean isRequest(ServiceLogModel serviceLogModel) {
        boolean isRequest = true;
        if (Constants.MESSAGE_TYPE_RESP.equals(serviceLogModel.getMessageType())) {
            isRequest = false;
        }
        if (serviceLogModel.getHttpStatus() != 0) {
            isRequest = false;
        }
        return isRequest;
    }
}
