package org.talend.esbconsole.server.domain.api.service;

import org.talend.esbconsole.server.domain.api.model.FlowControlModel;
import org.talend.esbconsole.server.domain.api.model.ServiceDTO;
import org.talend.esbconsole.server.domain.api.param.AC4FlowPageQueryParam;
import org.talend.esbconsole.server.domain.api.param.AC4FlowStatusActionParam;
import org.talend.esbconsole.server.tools.base.result.PageResult;

import java.util.List;

/**
 * 流量访问控制相关的功能服务接口
 *
 * @author xingzilong
 * @date 2021/05/04
 */
public interface FlowControlService {

    /**
     * 分页获取用户
     *
     * @param ac4FlowPageQueryParam
     * @return
     */
    PageResult<FlowControlModel> getFlowControls(AC4FlowPageQueryParam ac4FlowPageQueryParam);

    /**
     * 添加一条新的访问控制信息
     *
     * @param ac4FlowStatusActionParam
     */
    void addFlowControl(AC4FlowStatusActionParam ac4FlowStatusActionParam);

    /**
     * 启用规则
     *
     * @param ac4FlowStatusActionParam
     */
    void enableFlowControl(AC4FlowStatusActionParam ac4FlowStatusActionParam);

    /**
     * 停用规则
     *
     * @param ac4FlowStatusActionParam
     */
    void disableFlowControl(AC4FlowStatusActionParam ac4FlowStatusActionParam);

    /**
     * 删除一条访问控制信息
     *
     * @param ac4FlowStatusActionParam
     */
    void removeFlowControl(AC4FlowStatusActionParam ac4FlowStatusActionParam);

    /**
     * 修改一条访问控制信息
     *
     * @param ac4FlowStatusActionParam
     */
    void modifyFlowControls(AC4FlowStatusActionParam ac4FlowStatusActionParam);

    /**
     * 获取未绑定规则的服务
     *
     * @return
     */
    List<ServiceDTO> getServiceNoBindFlowControl();

    /**
     * 根据id获取一条规则信息
     *
     * @param id
     * @return
     */
    FlowControlModel getFlowControlById(String id);
}
