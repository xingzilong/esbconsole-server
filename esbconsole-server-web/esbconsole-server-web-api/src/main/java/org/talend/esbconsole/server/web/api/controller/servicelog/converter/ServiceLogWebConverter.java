package org.talend.esbconsole.server.web.api.controller.servicelog.converter;

import org.talend.esbconsole.server.domain.api.model.ServiceLogDTO;
import org.talend.esbconsole.server.domain.api.param.ServiceLogPageQueryParam;
import org.talend.esbconsole.server.web.api.controller.servicelog.request.ServiceLogPageQueryRequest;
import org.talend.esbconsole.server.web.api.controller.servicelog.vo.ServiceLogVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class ServiceLogWebConverter {

    /**
     * 参数转换
     *
     * @param request
     * @return
     */
    public abstract ServiceLogPageQueryParam req2param(ServiceLogPageQueryRequest request);

    /**
     * 参数转换
     *
     * @param serviceLogDTO
     * @return
     */
    @Mapping(source = "startTime", target = "startTime", dateFormat = "yyyy-MM-dd HH:mm:ss")
    public abstract ServiceLogVO dto2vo(ServiceLogDTO serviceLogDTO);


    /**
     * 参数转换
     *
     * @param serviceLogDTOS
     * @return
     */
    public abstract List<ServiceLogVO> dto2vo(List<ServiceLogDTO> serviceLogDTOS);


}
