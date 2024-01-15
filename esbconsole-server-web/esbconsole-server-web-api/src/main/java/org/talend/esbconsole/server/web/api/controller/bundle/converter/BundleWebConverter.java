package org.talend.esbconsole.server.web.api.controller.bundle.converter;

import org.talend.esbconsole.server.domain.api.param.BundlePageQueryParam;
import org.talend.esbconsole.server.web.api.controller.bundle.request.BundlePageQueryRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class BundleWebConverter {

    /**
     * 参数转换
     *
     * @param request
     * @return
     */
    public abstract BundlePageQueryParam req2param(BundlePageQueryRequest request);

}
