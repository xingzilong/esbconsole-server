package org.talend.esbconsole.server.web.api.controller.ac.frequency.converter;

import org.talend.esbconsole.server.domain.api.model.FrequencyControlModel;
import org.talend.esbconsole.server.domain.api.param.AC4FrequencyPageQueryParam;
import org.talend.esbconsole.server.domain.api.param.AC4FrequencyStatusActionParam;
import org.talend.esbconsole.server.web.api.controller.ac.frequency.request.AC4FrequencyPageQueryRequest;
import org.talend.esbconsole.server.web.api.controller.ac.frequency.request.AC4FrequencyStatusActionRequest;
import org.talend.esbconsole.server.web.api.controller.ac.frequency.vo.FrequencyControlVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class AC4FrequencyWebConverter {

    /**
     * 参数转换
     *
     * @param request
     * @return
     */
    public abstract AC4FrequencyPageQueryParam req2param(AC4FrequencyPageQueryRequest request);

    /**
     * 参数转换
     *
     * @param request
     * @return
     */
    public abstract AC4FrequencyStatusActionParam req2param(AC4FrequencyStatusActionRequest request);

    /**
     * 参数转换
     *
     * @param frequencyControlModel
     * @return
     */
    @Mapping(source = "createTime", target = "createTime", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Mapping(source = "updateTime", target = "updateTime", dateFormat = "yyyy-MM-dd HH:mm:ss")
    public abstract FrequencyControlVO dto2vo(FrequencyControlModel frequencyControlModel);


    /**
     * 参数转换
     *
     * @param frequencyControlModels
     * @return
     */
    public abstract List<FrequencyControlVO> dto2vo(List<FrequencyControlModel> frequencyControlModels);

}
