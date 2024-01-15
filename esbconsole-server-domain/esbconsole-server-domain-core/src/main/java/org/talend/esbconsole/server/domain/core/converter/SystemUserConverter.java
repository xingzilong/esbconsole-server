package org.talend.esbconsole.server.domain.core.converter;

import org.talend.esbconsole.server.domain.api.model.SystemUserDTO;
import org.talend.esbconsole.server.domain.api.param.ResetPasswordParam;
import org.talend.esbconsole.server.domain.api.param.UserCreateParam;
import org.talend.esbconsole.server.domain.api.param.UserModifyParam;
import org.talend.esbconsole.server.domain.repository.entity.SystemUserDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class SystemUserConverter {

    /**
     * 模型转换
     *
     * @param systemUserDO
     * @return
     */
    @Mapping(source = "createTime", target = "createTime", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Mapping(source = "updateTime", target = "updateTime", dateFormat = "yyyy-MM-dd HH:mm:ss")
    public abstract SystemUserDTO do2dto(SystemUserDO systemUserDO);

    /**
     * 模型转换
     *
     * @param systemUserDOs
     * @return
     */
    public abstract List<SystemUserDTO> do2dto(List<SystemUserDO> systemUserDOs);

    /**
     * 模型转换
     *
     * @param userCreateParam
     * @return
     */
    public abstract SystemUserDO param2do(UserCreateParam userCreateParam);

    /**
     * 模型转换
     *
     * @param userModifyParam
     * @return
     */
    public abstract SystemUserDO param2do(UserModifyParam userModifyParam);

    /**
     * 模型转换
     *
     * @param resetPasswordParam
     * @return
     */
    public abstract SystemUserDO param2do(ResetPasswordParam resetPasswordParam);


}
