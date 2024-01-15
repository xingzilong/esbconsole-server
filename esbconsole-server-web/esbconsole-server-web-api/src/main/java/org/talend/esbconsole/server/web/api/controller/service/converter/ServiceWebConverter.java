package org.talend.esbconsole.server.web.api.controller.service.converter;

import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.TypeReference;
import org.talend.esbconsole.server.domain.api.model.ServiceDTO;
import org.talend.esbconsole.server.domain.api.model.TaskTimeConsumptionDTO;
import org.talend.esbconsole.server.web.api.controller.service.request.*;
import org.talend.esbconsole.server.web.api.controller.service.request.APICallAnalysisRequest;
import org.talend.esbconsole.server.web.api.controller.service.request.APIRunAnalysisRequest;
import org.talend.esbconsole.server.web.api.controller.service.request.ServiceInstallRequest;
import org.talend.esbconsole.server.web.api.controller.service.request.ServiceModifyRequest;
import org.talend.esbconsole.server.web.api.controller.service.request.ServicePageQueryRequest;
import org.talend.esbconsole.server.web.api.controller.service.vo.ServiceVO;
import org.talend.esbconsole.server.web.api.controller.service.vo.TaskTimeConsumptionVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.talend.esbconsole.server.domain.api.param.APICallAnalysisParam;
import org.talend.esbconsole.server.domain.api.param.APIRunAnalysisParam;
import org.talend.esbconsole.server.domain.api.param.ServiceInstallParam;
import org.talend.esbconsole.server.domain.api.param.ServiceModifyParam;
import org.talend.esbconsole.server.domain.api.param.ServicePageQueryParam;
import org.talend.esbconsole.server.domain.api.param.ServiceStatusActionParam;
import org.talend.esbconsole.server.domain.api.param.ServiceTimedTaskParam;
import org.talend.esbconsole.server.web.api.controller.service.request.ServiceStatusActionRequest;
import org.talend.esbconsole.server.web.api.controller.service.request.ServiceTimedTaskRequest;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public abstract class ServiceWebConverter {

    /**
     * 参数转换
     *
     * @param request
     * @return
     */
    public abstract ServicePageQueryParam req2param(ServicePageQueryRequest request);

    /**
     * 参数转换
     *
     * @param request
     * @return
     */
    public abstract ServiceStatusActionParam req2param(ServiceStatusActionRequest request);

    /**
     * 参数转换
     *
     * @param request
     * @return
     */
    public abstract List<ServiceStatusActionParam> req2param(List<ServiceStatusActionRequest> request);

    /**
     * 参数转换
     *
     * @param request
     * @return
     */
    public abstract ServiceInstallParam req2param(ServiceInstallRequest request);

    /**
     * 参数转换
     *
     * @param request
     * @return
     */
    public abstract ServiceModifyParam req2param(ServiceModifyRequest request);

    /**
     * 参数转换
     *
     * @param request
     * @return
     */
    public abstract APICallAnalysisParam req2param(APICallAnalysisRequest request);

    /**
     * 参数转换
     *
     * @param request
     * @return
     */
    public abstract APIRunAnalysisParam req2param(APIRunAnalysisRequest request);

    /**
     * 参数转换
     *
     * @param request
     * @return
     */
    public abstract List<TaskTimeConsumptionVO> dto2vo(List<TaskTimeConsumptionDTO> request);

    /**
     * 参数转换
     *
     * @param request
     * @return
     */
    public abstract ServiceTimedTaskParam req2param(ServiceTimedTaskRequest request);

    /**
     * 参数转换
     *
     * @param request
     * @return
     */
    public abstract List<ServiceVO> dto2vo4service(List<ServiceDTO> request);

    /**
     * 参数转换
     *
     * @param serviceDTO
     * @return
     */
    @Mapping(source = "createTime", target = "createTime", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Mapping(source = "updateTime", target = "updateTime", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Mapping(target = "job", expression = "java(jsonString2List(serviceDTO.getJob()))")
    @Mapping(target = "bundleName", expression = "java(jsonString2List(serviceDTO.getBundleName()))")
    public abstract ServiceVO dto2vo(ServiceDTO serviceDTO);

    /**
     * 将json字符串转换为List对象
     *
     * @param jsonString
     * @return
     */
    public List<String> jsonString2List(String jsonString) {
        if (jsonString == null) {
            return null;
        }
        return JSONObject.parseObject(jsonString, new TypeReference<ArrayList<String>>() {
        });
    }

}
