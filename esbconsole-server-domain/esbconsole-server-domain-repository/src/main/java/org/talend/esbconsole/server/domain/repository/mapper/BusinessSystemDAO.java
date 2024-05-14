package org.talend.esbconsole.server.domain.repository.mapper;

import org.talend.esbconsole.server.domain.api.query.BusinessPageQuery;
import org.talend.esbconsole.server.domain.repository.entity.BusinessSystemDO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * business_system表相应的DAO
 *
 * @author xingzilong
 * @date 2021/08/22
 */
@Repository
public interface BusinessSystemDAO {
    /**
     * 新增一条消费方系统信息
     *
     * @param businessSystemDO
     */
    void saveBusinessSystem(BusinessSystemDO businessSystemDO);

    /**
     * 删除一条消费方系统信息
     *
     * @param id
     */
    void removeBusinessSystem(String id);

    /**
     * 更新一条消费方系统信息
     *
     * @param businessSystemDO
     */
    void updateBusinessSystem(BusinessSystemDO businessSystemDO);

    /**
     * 根据请求的 id 查询该消费方系统的 businessName
     *
     * @param id 消费方系统ID
     * @return 消费方系统名
     */
    String getBusinessSystemNameByBusinessId(String id);

    /**
     * 获取所有的消费方系统信息
     *
     * @return 返回所有的消费方系统
     */
    List<BusinessSystemDO> listAllBusinessSystems();

    /**
     * 根据查询条件获取所有的用户信息
     *
     * @return 返回所有的用户
     */
    List<BusinessSystemDO> listAllBusinessSystemsByConditions(BusinessPageQuery businessPageQuery);

    /**
     * 根据请求的 id 查询该消费方系统的所有信息
     *
     * @param id 消费方系统ID
     * @return 消费方系统信息
     */
    BusinessSystemDO getBusinessSystemById(String id);

    /**
     * 获取所有的消费方系统信息
     *
     * @return 返回所有的消费方系统名称
     */
    List<BusinessSystemDO> getAllBusinessSystemName();

    /**
     * 根据请求的名称去获取消费方系统信息
     *
     * @param systemName 消费方系统名称
     * @return 返回所有的消费方系统名称
     */
    BusinessSystemDO getBusinessSystemBySystemName(String systemName);

}
