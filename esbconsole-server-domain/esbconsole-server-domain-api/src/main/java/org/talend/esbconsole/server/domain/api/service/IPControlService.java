package org.talend.esbconsole.server.domain.api.service;

import org.talend.esbconsole.server.domain.api.model.IPControlModel;
import org.talend.esbconsole.server.domain.api.model.ServiceDTO;
import org.talend.esbconsole.server.domain.api.param.AC4IPPageQueryParam;
import org.talend.esbconsole.server.domain.api.param.AC4IPStatusActionParam;
import org.talend.esbconsole.server.tools.base.result.PageResult;

import java.util.List;

/**
 * IP访问控制相关的功能服务接口
 *
 * @author xingzilong
 * @date 2021/05/04
 */
public interface IPControlService {

    /**
     * 分页获取用户
     *
     * @param ac4IPPageQueryParam
     * @return
     */
    PageResult<IPControlModel> getIPControls(AC4IPPageQueryParam ac4IPPageQueryParam);

    /**
     * 添加一条新的访问控制信息
     *
     * @param ac4IPStatusActionParam
     */
    void addIPControl(AC4IPStatusActionParam ac4IPStatusActionParam);

    /**
     * 启用规则
     *
     * @param ac4IPStatusActionParam
     */
    void enableIPControl(AC4IPStatusActionParam ac4IPStatusActionParam);

    /**
     * 停用规则
     *
     * @param ac4IPStatusActionParam
     */
    void disableIPControl(AC4IPStatusActionParam ac4IPStatusActionParam);

    /**
     * 删除一条访问控制信息
     *
     * @param ac4IPStatusActionParam
     */
    void removeIPControl(AC4IPStatusActionParam ac4IPStatusActionParam);

    /**
     * 修改一条访问控制信息
     *
     * @param ac4IPStatusActionParam
     */
    void modifyIPControls(AC4IPStatusActionParam ac4IPStatusActionParam);

    /**
     * 获取未绑定规则的服务
     *
     * @return
     */
    List<ServiceDTO> getServiceNoBindIPControl();

    /**
     * 根据id获取一条规则信息
     *
     * @param id
     * @return
     */
    IPControlModel getIPControlById(String id);
}
