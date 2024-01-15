package org.talend.esbconsole.server.web.api.controller.feature.converter;

import org.talend.esbconsole.server.domain.api.param.FeaturePageQueryParam;
import org.talend.esbconsole.server.web.api.controller.feature.request.FeaturePageQueryRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class FeatureWebConverter {

    /**
     * 参数转换
     *
     * @param request
     * @return
     */
    public abstract FeaturePageQueryParam req2param(FeaturePageQueryRequest request);

}
