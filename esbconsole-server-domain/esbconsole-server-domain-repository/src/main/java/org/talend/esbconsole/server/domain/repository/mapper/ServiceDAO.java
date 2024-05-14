package org.talend.esbconsole.server.domain.repository.mapper;

import org.talend.esbconsole.server.domain.api.model.ServiceDTO;
import org.talend.esbconsole.server.domain.api.query.ServicePageQuery;
import org.talend.esbconsole.server.domain.repository.entity.ServiceDO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * service表相应的DAO
 *
 * @author xingzilong
 * @date 2021/05/04
 */
@Repository
public interface ServiceDAO {

    /**
     * 存入一条数据
     *
     * @param serviceDO 服务的描述对象
     */
    void saveService(ServiceDO serviceDO);

    /**
     * 更新一条服务信息
     *
     * @param serviceDO
     */
    void updateService(ServiceDO serviceDO);

    /**
     * 获取所有的服务信息
     *
     * @return
     */
    List<ServiceDO> listAllServices();

    /**
     * 根据ID获取服务信息
     *
     * @param id
     * @return
     */
    ServiceDO getServiceById(String id);

    /**
     * 分页获取所有的服务信息
     *
     * @return
     */
    List<ServiceDO> listServices();

    /**
     * 根据查询条件分页获取所有的服务信息
     *
     * @param servicePageQuery 查询参数对象
     * @return
     */
    List<ServiceDTO> listServicesByConditions(ServicePageQuery servicePageQuery);

    /**
     * 删除一条数据
     */
    void removeService(String id);

    /**
     * 获取服务总数
     *
     * @return
     */
    Long getServiceTotal();

    /**
     * 获取webserviceAPI总数
     *
     * @return
     */
    Long getServiceTotal4API();

    /**
     * 获取定时路由总数
     *
     * @return
     */
    Long getServiceTotal4TimedRoute();

    /**
     * 获取常规路由总数
     *
     * @return
     */
    Long getServiceTotal4ConventionalRoute();

    /**
     * 获取 SOAP webserviceAPI总数
     *
     * @return
     */
    Long getServiceTotal4SOAPAPI();

    /**
     * 获取 RestFul webserviceAPI总数
     *
     * @return
     */
    Long getServiceTotal4RestFulAPI();

    /**
     * 获取webserviceAPI总数
     *
     * @return
     */
    Long getServiceTotal4ProxyRoute();

    /**
     * 根据serviceid获取定时任务
     *
     * @param id
     * @return
     */
    String getTaskConsumptionTotal(String id);
}
