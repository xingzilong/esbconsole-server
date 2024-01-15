package org.talend.esbconsole.server.start.authentication.converter;

import org.talend.esbconsole.server.domain.api.param.LoginUserParam;
import org.talend.esbconsole.server.start.authentication.request.LoginUserRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class AuthenticationWebConverter {

    /**
     * 参数转换
     *
     * @param request
     * @return
     */
    public abstract LoginUserParam req2param(LoginUserRequest request);

}
