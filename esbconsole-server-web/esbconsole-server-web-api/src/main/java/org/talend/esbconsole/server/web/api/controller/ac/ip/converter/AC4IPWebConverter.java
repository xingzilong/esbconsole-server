package org.talend.esbconsole.server.web.api.controller.ac.ip.converter;

import org.talend.esbconsole.server.domain.api.model.IPControlModel;
import org.talend.esbconsole.server.domain.api.param.AC4IPPageQueryParam;
import org.talend.esbconsole.server.domain.api.param.AC4IPStatusActionParam;
import org.talend.esbconsole.server.web.api.controller.ac.ip.request.AC4IPPageQueryRequest;
import org.talend.esbconsole.server.web.api.controller.ac.ip.request.AC4IPStatusActionRequest;
import org.talend.esbconsole.server.web.api.controller.ac.ip.vo.IPControlVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class AC4IPWebConverter {

    /**
     * 参数转换
     *
     * @param request
     * @return
     */
    public abstract AC4IPPageQueryParam req2param(AC4IPPageQueryRequest request);

    /**
     * 参数转换
     *
     * @param request
     * @return
     */
    public abstract AC4IPStatusActionParam req2param(AC4IPStatusActionRequest request);

    /**
     * 参数转换
     *
     * @param ipControlModel
     * @return
     */
    @Mapping(source = "createTime", target = "createTime", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Mapping(source = "updateTime", target = "updateTime", dateFormat = "yyyy-MM-dd HH:mm:ss")
    public abstract IPControlVO dto2vo(IPControlModel ipControlModel);


    /**
     * 参数转换
     *
     * @param ipControlModels
     * @return
     */
    public abstract List<IPControlVO> dto2vo(List<IPControlModel> ipControlModels);

}
