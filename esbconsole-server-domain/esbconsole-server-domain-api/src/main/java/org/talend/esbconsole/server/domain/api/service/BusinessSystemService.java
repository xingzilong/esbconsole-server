package org.talend.esbconsole.server.domain.api.service;

import org.talend.esbconsole.server.domain.api.model.BusinessSystemDTO;
import org.talend.esbconsole.server.domain.api.param.BusinessCreateParam;
import org.talend.esbconsole.server.domain.api.param.BusinessModifyParam;
import org.talend.esbconsole.server.domain.api.param.BusinessPageQueryParam;
import org.talend.esbconsole.server.tools.base.result.PageResult;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 业务系统相关的功能服务接口
 *
 * @author xingzilong
 * @date 2021/08/22
 */
@Service
public interface BusinessSystemService {
    /**
     * 新增一条业务系统信息
     *
     * @param businessCreateParam
     */
    void addBusiness(BusinessCreateParam businessCreateParam);

    /**
     * 删除一条业务系统信息（逻辑删除）
     *
     * @param id
     */
    void removeBusiness(String id);

    /**
     * 修改一条业务系统信息
     *
     * @param businessModifyParam
     */
    void modifyBusiness(BusinessModifyParam businessModifyParam);

    /**
     * 停用业务系统
     *
     * @param id
     */
    void disableBusiness(String id);

    /**
     * 启用业务系统
     *
     * @param id
     */
    void enableBusiness(String id);

    /**
     * 分页获取业务系统
     *
     * @param businessPageQueryParam
     * @return
     */
    PageResult<BusinessSystemDTO> getBusinesss(BusinessPageQueryParam businessPageQueryParam);

    /**
     * 根据业务系统ID获取业务系统信息
     *
     * @param id 业务系统ID
     * @return
     */
    BusinessSystemDTO getBusiness(String id);

    /**
     * 获取所有业务系统的系统名称
     *
     * @return
     */
    List<BusinessSystemDTO> getAllBusinessSystemName();

    /**
     * 根据业务系统名称获取业务系统信息
     *
     * @param systemName 业务系统名称
     * @return
     */
    BusinessSystemDTO getBusinessByBusinessName(String systemName);
}
