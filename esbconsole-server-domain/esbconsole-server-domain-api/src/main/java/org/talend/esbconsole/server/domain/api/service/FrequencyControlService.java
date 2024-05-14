package org.talend.esbconsole.server.domain.api.service;

import org.talend.esbconsole.server.domain.api.model.FrequencyControlModel;
import org.talend.esbconsole.server.domain.api.model.ServiceDTO;
import org.talend.esbconsole.server.domain.api.param.AC4FrequencyPageQueryParam;
import org.talend.esbconsole.server.domain.api.param.AC4FrequencyStatusActionParam;
import org.talend.esbconsole.server.tools.base.result.PageResult;

import java.util.List;

/**
 * 频次访问控制相关的功能服务接口
 *
 * @author xingzilong
 * @date 2021/05/04
 */
public interface FrequencyControlService {

    /**
     * 分页获取用户
     *
     * @param ac4FrequencyPageQueryParam
     * @return
     */
    PageResult<FrequencyControlModel> getFrequencyControls(AC4FrequencyPageQueryParam ac4FrequencyPageQueryParam);

    /**
     * 添加一条新的访问控制信息
     *
     * @param ac4FrequencyStatusActionParam
     */
    void addFrequencyControl(AC4FrequencyStatusActionParam ac4FrequencyStatusActionParam);

    /**
     * 启用规则
     *
     * @param ac4FrequencyStatusActionParam
     */
    void enableFrequencyControl(AC4FrequencyStatusActionParam ac4FrequencyStatusActionParam);

    /**
     * 停用规则
     *
     * @param ac4FrequencyStatusActionParam
     */
    void disableFrequencyControl(AC4FrequencyStatusActionParam ac4FrequencyStatusActionParam);

    /**
     * 删除一条访问控制信息
     *
     * @param ac4FrequencyStatusActionParam
     */
    void removeFrequencyControl(AC4FrequencyStatusActionParam ac4FrequencyStatusActionParam);

    /**
     * 修改一条访问控制信息
     *
     * @param ac4FrequencyStatusActionParam
     */
    void modifyFrequencyControls(AC4FrequencyStatusActionParam ac4FrequencyStatusActionParam);

    /**
     * 获取未绑定规则的服务
     *
     * @return
     */
    List<ServiceDTO> getServiceNoBindFrequencyControl();

    /**
     * 根据id获取一条规则信息
     *
     * @param id
     * @return
     */
    FrequencyControlModel getFrequencyControlById(String id);
}
