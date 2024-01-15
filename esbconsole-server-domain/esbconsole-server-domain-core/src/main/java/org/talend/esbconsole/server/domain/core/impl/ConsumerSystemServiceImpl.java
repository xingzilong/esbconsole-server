package org.talend.esbconsole.server.domain.core.impl;

import org.talend.esbconsole.server.domain.api.model.ConsumerSystemDTO;
import org.talend.esbconsole.server.domain.api.param.ConsumerCreateParam;
import org.talend.esbconsole.server.domain.api.param.ConsumerModifyParam;
import org.talend.esbconsole.server.domain.api.param.ConsumerPageQueryParam;
import org.talend.esbconsole.server.domain.api.query.ConsumerPageQuery;
import org.talend.esbconsole.server.domain.api.service.ConsumerSystemService;
import org.talend.esbconsole.server.domain.core.converter.ConsumerSystemConverter;
import org.talend.esbconsole.server.domain.core.converter.query.ConsumerQueryConverter;
import org.talend.esbconsole.server.domain.repository.entity.ConsumerSystemDO;
import org.talend.esbconsole.server.domain.repository.mapper.ConsumerSystemDAO;
import org.talend.esbconsole.server.tools.base.result.PageResult;
import org.talend.esbconsole.server.tools.common.utils.UUIDUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 消费方系统相关的功能服务接口 {@link ConsumerSystemService} 的实现类
 *
 * @author xingzilong
 * @date 2023/08/22
 */
@Service
public class ConsumerSystemServiceImpl implements ConsumerSystemService {
    @Autowired
    private ConsumerSystemDAO consumerSystemDAO;
    @Autowired
    private ConsumerSystemConverter consumerSystemConverter;
    @Autowired
    private ConsumerQueryConverter consumerQueryConverter;

    @Override
    public void addConsumer(ConsumerCreateParam consumerCreateParam) {
        ConsumerSystemDO consumerSystemDO = consumerSystemConverter.param2do(consumerCreateParam);
        consumerSystemDO.setId(UUIDUtil.getUUID());
        consumerSystemDO.setCreateTime(LocalDateTime.now());
        consumerSystemDO.setUpdateTime(LocalDateTime.now());
        consumerSystemDO.setStatus("1");
        consumerSystemDAO.saveConsumerSystem(consumerSystemDO);
    }

    @Override
    public void removeConsumer(String id) {
        consumerSystemDAO.removeConsumerSystem(id);
    }

    @Override
    public void modifyConsumer(ConsumerModifyParam consumerModifyParam) {
        ConsumerSystemDO consumerSystemDO = consumerSystemConverter.param2do(consumerModifyParam);
        consumerSystemDO.setUpdateTime(LocalDateTime.now());
        consumerSystemDAO.updateConsumerSystem(consumerSystemDO);
    }

    @Override
    public void disableConsumer(String id) {
        ConsumerSystemDO consumerSystemDO = ConsumerSystemDO
                .builder()
                .id(id)
                .status("0")
                .updateTime(LocalDateTime.now())
                .build();
        consumerSystemDAO.updateConsumerSystem(consumerSystemDO);
    }

    @Override
    public void enableConsumer(String id) {
        ConsumerSystemDO consumerSystemDO = ConsumerSystemDO
                .builder()
                .id(id)
                .status("1")
                .updateTime(LocalDateTime.now())
                .build();
        consumerSystemDAO.updateConsumerSystem(consumerSystemDO);
    }

    @Override
    public PageResult<ConsumerSystemDTO> getConsumers(ConsumerPageQueryParam consumerPageQueryParam) {
        ConsumerPageQuery consumerPageQuery = consumerQueryConverter.param2query(consumerPageQueryParam);
        PageHelper.startPage(consumerPageQueryParam.getPageNum(), consumerPageQueryParam.getPageSize());
        List<ConsumerSystemDO> consumerSystemDOS = consumerSystemDAO.listAllConsumerSystemsByConditions(consumerPageQuery);
        PageInfo<ConsumerSystemDO> pageInfo = new PageInfo<ConsumerSystemDO>(consumerSystemDOS);
        List<ConsumerSystemDTO> consumerSystems = consumerSystemConverter.do2dto(consumerSystemDOS);
        return PageResult.of(consumerSystems, pageInfo.getTotal());
    }

    @Override
    public ConsumerSystemDTO getConsumer(String id) {
        ConsumerSystemDO consumerSystemDO = consumerSystemDAO.getConsumerSystemById(id);
        ConsumerSystemDTO consumerSystemDTO = consumerSystemConverter.do2dto(consumerSystemDO);
        return consumerSystemDTO;
    }

    @Override
    public ConsumerSystemDTO getConsumerByIp(String ip) {
        ConsumerSystemDO consumerSystemDO = consumerSystemDAO.getConsumerSystemByIp(ip);
        ConsumerSystemDTO consumerSystemDTO = consumerSystemConverter.do2dto(consumerSystemDO);
        return consumerSystemDTO;
    }
}
