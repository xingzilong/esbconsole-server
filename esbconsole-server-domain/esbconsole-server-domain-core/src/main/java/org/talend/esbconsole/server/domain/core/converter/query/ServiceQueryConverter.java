package org.talend.esbconsole.server.domain.core.converter.query;

import org.talend.esbconsole.server.domain.api.param.ServicePageQueryParam;
import org.talend.esbconsole.server.domain.api.query.ServicePageQuery;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class ServiceQueryConverter {

    /**
     * 参数转换
     *
     * @param servicePageQueryParam
     * @return
     */
    public abstract ServicePageQuery param2query(ServicePageQueryParam servicePageQueryParam);
}
