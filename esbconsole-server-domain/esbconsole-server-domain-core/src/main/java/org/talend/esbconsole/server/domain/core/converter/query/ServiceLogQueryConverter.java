package org.talend.esbconsole.server.domain.core.converter.query;

import org.talend.esbconsole.server.domain.api.param.ServiceLogPageQueryParam;
import org.talend.esbconsole.server.domain.api.query.ServiceLogPageQuery;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class ServiceLogQueryConverter {

    /**
     * 参数转换
     *
     * @param serviceLogPageQueryParam
     * @return
     */
    @Mapping(source = "timeSort", target = "eiTimeStampSort")
    public abstract ServiceLogPageQuery param2query(ServiceLogPageQueryParam serviceLogPageQueryParam);
}
