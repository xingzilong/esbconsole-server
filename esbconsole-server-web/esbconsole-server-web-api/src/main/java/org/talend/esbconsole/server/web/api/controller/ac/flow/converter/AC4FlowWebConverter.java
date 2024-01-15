package org.talend.esbconsole.server.web.api.controller.ac.flow.converter;

import org.talend.esbconsole.server.domain.api.model.FlowControlModel;
import org.talend.esbconsole.server.domain.api.param.AC4FlowPageQueryParam;
import org.talend.esbconsole.server.domain.api.param.AC4FlowStatusActionParam;
import org.talend.esbconsole.server.web.api.controller.ac.flow.request.AC4FlowPageQueryRequest;
import org.talend.esbconsole.server.web.api.controller.ac.flow.request.AC4FlowStatusActionRequest;
import org.talend.esbconsole.server.web.api.controller.ac.flow.vo.FlowControlVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class AC4FlowWebConverter {

    /**
     * 参数转换
     *
     * @param request
     * @return
     */
    public abstract AC4FlowPageQueryParam req2param(AC4FlowPageQueryRequest request);

    /**
     * 参数转换
     *
     * @param request
     * @return
     */
    public abstract AC4FlowStatusActionParam req2param(AC4FlowStatusActionRequest request);

    /**
     * 参数转换
     *
     * @param flowControlModel
     * @return
     */
    @Mapping(source = "createTime", target = "createTime", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Mapping(source = "updateTime", target = "updateTime", dateFormat = "yyyy-MM-dd HH:mm:ss")
    public abstract FlowControlVO dto2vo(FlowControlModel flowControlModel);


    /**
     * 参数转换
     *
     * @param flowControlModels
     * @return
     */
    public abstract List<FlowControlVO> dto2vo(List<FlowControlModel> flowControlModels);

}
