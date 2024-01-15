package org.talend.esbconsole.server.domain.core.converter.query;

import org.talend.esbconsole.server.domain.api.param.BusinessPageQueryParam;
import org.talend.esbconsole.server.domain.api.query.BusinessPageQuery;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class BusinessQueryConverter {
    /**
     * 参数转换
     *
     * @param businessPageQueryParam
     * @return
     */
    public abstract BusinessPageQuery param2query(BusinessPageQueryParam businessPageQueryParam);
}
