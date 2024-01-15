package org.talend.esbconsole.server.domain.api.service;

import org.talend.esbconsole.server.domain.api.model.ConsumerSystemDTO;
import org.talend.esbconsole.server.domain.api.param.ConsumerCreateParam;
import org.talend.esbconsole.server.domain.api.param.ConsumerModifyParam;
import org.talend.esbconsole.server.domain.api.param.ConsumerPageQueryParam;
import org.talend.esbconsole.server.tools.base.result.PageResult;
import org.springframework.stereotype.Service;

/**
 * 消费方系统相关的功能服务接口
 *
 * @author xingzilong
 * @date 2023/08/22
 */
@Service
public interface ConsumerSystemService {
    /**
     * 新增一条消费方系统信息
     *
     * @param consumerCreateParam
     */
    void addConsumer(ConsumerCreateParam consumerCreateParam);

    /**
     * 删除一条消费方系统信息（逻辑删除）
     *
     * @param id
     */
    void removeConsumer(String id);

    /**
     * 修改一条消费方系统信息
     *
     * @param consumerModifyParam
     */
    void modifyConsumer(ConsumerModifyParam consumerModifyParam);

    /**
     * 停用消费方系统
     *
     * @param id
     */
    void disableConsumer(String id);

    /**
     * 启用消费方系统
     *
     * @param id
     */
    void enableConsumer(String id);

    /**
     * 分页获取消费方系统
     *
     * @param consumerPageQueryParam
     * @return
     */
    PageResult<ConsumerSystemDTO> getConsumers(ConsumerPageQueryParam consumerPageQueryParam);

    /**
     * 根据消费方系统ID获取消费方系统信息
     *
     * @param id 消费方系统ID
     * @return
     */
    ConsumerSystemDTO getConsumer(String id);

    /**
     * 根据消费方系统Ip获取消费方系统信息
     *
     * @param ip 消费方系统IP
     * @return
     */
    ConsumerSystemDTO getConsumerByIp(String ip);

}
