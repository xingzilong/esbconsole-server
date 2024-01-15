package org.talend.esbconsole.server.domain.core.converter.query;

import org.talend.esbconsole.server.domain.api.param.RolePageQueryParam;
import org.talend.esbconsole.server.domain.api.query.RolePageQuery;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class RoleQueryConverter {

    /**
     * 参数转换
     *
     * @param rolePageQueryParam
     * @return
     */
    public abstract RolePageQuery param2query(RolePageQueryParam rolePageQueryParam);
}
