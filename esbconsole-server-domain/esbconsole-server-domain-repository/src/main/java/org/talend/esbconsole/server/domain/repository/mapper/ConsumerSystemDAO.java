package org.talend.esbconsole.server.domain.repository.mapper;

import org.talend.esbconsole.server.domain.api.query.ConsumerPageQuery;
import org.talend.esbconsole.server.domain.repository.entity.ConsumerSystemDO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * consumer_system表相应的DAO
 *
 * @author xingzilong
 * @date 2023/08/22
 */
@Repository
public interface ConsumerSystemDAO {
    /**
     * 新增一条消费方系统信息
     *
     * @param consumer
     */
    void saveConsumerSystem(ConsumerSystemDO consumer);

    /**
     * 删除一条消费方系统信息
     *
     * @param id
     */
    void removeConsumerSystem(String id);

    /**
     * 更新一条消费方系统信息
     *
     * @param consumer
     */
    void updateConsumerSystem(ConsumerSystemDO consumer);

    /**
     * 根据请求的 id 查询该消费方系统的 consumerName
     *
     * @param id 消费方系统ID
     * @return 消费方系统名
     */
    String getConsumerSystemNameByConsumerId(String id);

    /**
     * 获取所有的消费方系统信息
     *
     * @return 返回所有的消费方系统
     */
    List<ConsumerSystemDO> listAllConsumerSystems();

    /**
     * 根据查询条件获取所有的用户信息
     *
     * @return 返回所有的用户
     */
    List<ConsumerSystemDO> listAllConsumerSystemsByConditions(ConsumerPageQuery consumerPageQuery);


    /**
     * 根据请求的 id 查询该消费方系统的所有信息
     *
     * @param id 消费方系统ID
     * @return 消费方系统信息
     */
    ConsumerSystemDO getConsumerSystemById(String id);

    /**
     * 根据ip地址 查询该消费方系统的所有信息
     *
     * @param ip 消费方系统IP地址
     * @return 消费方系统信息
     */
    ConsumerSystemDO getConsumerSystemByIp(String ip);

    /**
     * 根据系统名称获取对应的IP
     *
     * @param systemName
     * @return
     */
    String getIPBySystemName(String systemName);
}
