package org.talend.esbconsole.server.web.api.controller.authority.converter;

import org.talend.esbconsole.server.domain.api.param.UserPageQueryParam;
import org.talend.esbconsole.server.web.api.controller.user.request.UserPageQueryRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class AuthorityWebConverter {

    /**
     * 参数转换
     *
     * @param request
     * @return
     */
    public abstract UserPageQueryParam dto2vo(UserPageQueryRequest request);


}
