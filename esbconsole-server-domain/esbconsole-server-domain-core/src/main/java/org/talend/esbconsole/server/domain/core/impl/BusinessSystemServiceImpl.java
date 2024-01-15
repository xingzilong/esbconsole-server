package org.talend.esbconsole.server.domain.core.impl;

import org.talend.esbconsole.server.domain.api.model.BusinessSystemDTO;
import org.talend.esbconsole.server.domain.api.model.ServiceDTO;
import org.talend.esbconsole.server.domain.api.param.BusinessCreateParam;
import org.talend.esbconsole.server.domain.api.param.BusinessModifyParam;
import org.talend.esbconsole.server.domain.api.param.BusinessPageQueryParam;
import org.talend.esbconsole.server.domain.api.query.BusinessPageQuery;
import org.talend.esbconsole.server.domain.api.query.ServicePageQuery;
import org.talend.esbconsole.server.domain.api.service.BusinessSystemService;
import org.talend.esbconsole.server.domain.core.converter.BusinessSystemConverter;
import org.talend.esbconsole.server.domain.core.converter.query.BusinessQueryConverter;
import org.talend.esbconsole.server.domain.repository.entity.BusinessSystemDO;
import org.talend.esbconsole.server.domain.repository.mapper.BusinessSystemDAO;
import org.talend.esbconsole.server.domain.repository.mapper.ServiceDAO;
import org.talend.esbconsole.server.tools.base.exception.DataOperationException;
import org.talend.esbconsole.server.tools.base.request.TimeInterval;
import org.talend.esbconsole.server.tools.base.result.PageResult;
import org.talend.esbconsole.server.tools.common.utils.UUIDUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 业务系统相关的功能服务接口 {@link BusinessSystemService} 的实现类
 *
 * @author xingzilong
 * @date 2023/08/22
 */
@Service
public class BusinessSystemServiceImpl implements BusinessSystemService {
    @Autowired
    private BusinessSystemDAO businessSystemDAO;
    @Autowired
    private ServiceDAO serviceDAO;
    @Autowired
    private BusinessSystemConverter businessSystemConverter;
    @Autowired
    private BusinessQueryConverter businessQueryConverter;

    @Override
    public void addBusiness(BusinessCreateParam businessCreateParam) {
        BusinessSystemDO businessSystemDO = businessSystemConverter.param2do(businessCreateParam);
        businessSystemDO.setId(UUIDUtil.getUUID());
        businessSystemDO.setCreateTime(LocalDateTime.now());
        businessSystemDO.setUpdateTime(LocalDateTime.now());
        businessSystemDO.setStatus("1");
        businessSystemDAO.saveBusinessSystem(businessSystemDO);
    }

    @Override
    public void removeBusiness(String id) {
        // 根据id查找该字典是否已经被使用了
        ServicePageQuery servicePageQuery = new ServicePageQuery();
        servicePageQuery.setCreateTime(new TimeInterval());
        servicePageQuery.setBusinessSystemId(id);
        List<ServiceDTO> serviceDTOS = serviceDAO.listServicesByConditions(servicePageQuery);
        if (serviceDTOS.size() > 0) {
            throw new DataOperationException("数据被使用");
        }
        businessSystemDAO.removeBusinessSystem(id);
    }

    @Override
    public void modifyBusiness(BusinessModifyParam businessModifyParam) {
        BusinessSystemDO businessSystemDO = businessSystemConverter.param2do(businessModifyParam);
        businessSystemDO.setUpdateTime(LocalDateTime.now());
        businessSystemDAO.updateBusinessSystem(businessSystemDO);
    }

    @Override
    public void disableBusiness(String id) {
        BusinessSystemDO businessSystemDO = BusinessSystemDO
                .builder()
                .id(id)
                .status("0")
                .updateTime(LocalDateTime.now())
                .build();
        businessSystemDAO.updateBusinessSystem(businessSystemDO);
    }

    @Override
    public void enableBusiness(String id) {
        BusinessSystemDO businessSystemDO = BusinessSystemDO
                .builder()
                .id(id)
                .status("1")
                .updateTime(LocalDateTime.now())
                .build();
        businessSystemDAO.updateBusinessSystem(businessSystemDO);
    }

    @Override
    public PageResult<BusinessSystemDTO> getBusinesss(BusinessPageQueryParam businessPageQueryParam) {
        BusinessPageQuery businessPageQuery = businessQueryConverter.param2query(businessPageQueryParam);
        PageHelper.startPage(businessPageQueryParam.getPageNum(), businessPageQueryParam.getPageSize());
        List<BusinessSystemDO> businessSystemDOS = businessSystemDAO.listAllBusinessSystemsByConditions(businessPageQuery);
        PageInfo<BusinessSystemDO> pageInfo = new PageInfo<BusinessSystemDO>(businessSystemDOS);
        List<BusinessSystemDTO> businessSystems = businessSystemConverter.do2dto(businessSystemDOS);
        return PageResult.of(businessSystems, pageInfo.getTotal());
    }

    @Override
    public BusinessSystemDTO getBusiness(String id) {
        BusinessSystemDO businessSystemDO = businessSystemDAO.getBusinessSystemById(id);
        BusinessSystemDTO businessSystemDTO = businessSystemConverter.do2dto(businessSystemDO);
        return businessSystemDTO;
    }

    @Override
    public List<BusinessSystemDTO> getAllBusinessSystemName() {
        List<BusinessSystemDO> businessSystemDOS = businessSystemDAO.getAllBusinessSystemName();
        List<BusinessSystemDTO> businessSystemDTOS = businessSystemConverter.do2dto(businessSystemDOS);
        return businessSystemDTOS;
    }

    @Override
    public BusinessSystemDTO getBusinessByBusinessName(String systemName) {
        BusinessSystemDO businessSystemDO = businessSystemDAO.getBusinessSystemBySystemName(systemName);
        BusinessSystemDTO businessSystemDTO = businessSystemConverter.do2dto(businessSystemDO);
        return businessSystemDTO;
    }

}
