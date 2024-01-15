package org.talend.esbconsole.server.domain.core.converter.query;

import org.talend.esbconsole.server.domain.api.param.UserPageQueryParam;
import org.talend.esbconsole.server.domain.api.query.UserPageQuery;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class UserQueryConverter {

    /**
     * 参数转换
     *
     * @param userPageQueryParam
     * @return
     */
    public abstract UserPageQuery param2query(UserPageQueryParam userPageQueryParam);
}
