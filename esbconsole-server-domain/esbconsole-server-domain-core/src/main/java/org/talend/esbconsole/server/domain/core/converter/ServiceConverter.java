package org.talend.esbconsole.server.domain.core.converter;

import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.TypeReference;
import org.talend.esbconsole.server.domain.api.model.ServiceDTO;
import org.talend.esbconsole.server.domain.api.param.ServiceInstallParam;
import org.talend.esbconsole.server.domain.api.param.ServiceModifyParam;
import org.talend.esbconsole.server.domain.repository.entity.ServiceDO;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public abstract class ServiceConverter {


    /**
     * 将json字符串转换为List对象
     *
     * @param jsonString
     * @return
     */
    public List<String> jsonString2List(String jsonString) {
        if (jsonString == null) {
            return null;
        }
        return JSONObject.parseObject(jsonString, new TypeReference<ArrayList<String>>() {
        });
    }

    /**
     * 模型转换
     *
     * @param serviceDO
     * @return
     */
    public abstract ServiceDTO do2dto(ServiceDO serviceDO);

    /**
     * 模型转换
     *
     * @param serviceDOS
     * @return
     */
    public abstract List<ServiceDTO> do2dto(List<ServiceDO> serviceDOS);

    /**
     * 模型转换
     *
     * @param serviceInstallParam
     * @return
     */
    public abstract ServiceDO param2do(ServiceInstallParam serviceInstallParam);

    /**
     * 模型转换
     *
     * @param serviceModifyParam
     * @return
     */
    public abstract ServiceDO param2do(ServiceModifyParam serviceModifyParam);


}
