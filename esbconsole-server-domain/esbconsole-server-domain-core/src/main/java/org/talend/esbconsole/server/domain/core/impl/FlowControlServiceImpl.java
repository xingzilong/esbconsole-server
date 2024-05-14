package org.talend.esbconsole.server.domain.core.impl;

import org.talend.esbconsole.server.domain.api.model.FlowControlModel;
import org.talend.esbconsole.server.domain.api.model.ServiceDTO;
import org.talend.esbconsole.server.domain.api.param.AC4FlowPageQueryParam;
import org.talend.esbconsole.server.domain.api.param.AC4FlowStatusActionParam;
import org.talend.esbconsole.server.domain.api.query.AC4FlowPageQuery;
import org.talend.esbconsole.server.domain.api.service.FlowControlService;
import org.talend.esbconsole.server.domain.core.converter.ServiceConverter;
import org.talend.esbconsole.server.domain.core.converter.query.AC4FlowQueryConverter;
import org.talend.esbconsole.server.domain.repository.entity.ServiceDO;
import org.talend.esbconsole.server.domain.repository.mapper.AccessControlFlowDAO;
import org.talend.esbconsole.server.tools.base.exception.HTTPRequestFailedException;
import org.talend.esbconsole.server.tools.base.result.PageResult;
import org.talend.esbconsole.server.tools.base.result.ResponseResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * 流量访问控制相关功能服务接口 {@link FlowControlService} 的实现类
 *
 * @author xingzilong
 * @date 2021/05/04
 */
@Slf4j
@Service
public class FlowControlServiceImpl implements FlowControlService {

    @Value("${sam.ac.url}")
    private String AC_URL;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AccessControlFlowDAO accessControlFlowDAO;

    @Autowired
    private AC4FlowQueryConverter ac4FlowQueryConverter;

    @Autowired
    private ServiceConverter serviceConverter;

    @Override
    public PageResult<FlowControlModel> getFlowControls(AC4FlowPageQueryParam ac4FlowPageQueryParam) {
        AC4FlowPageQuery ac4FlowPageQuery = ac4FlowQueryConverter.param2query(ac4FlowPageQueryParam);
        PageHelper.startPage(ac4FlowPageQueryParam.getPageNum(), ac4FlowPageQueryParam.getPageSize());
        List<FlowControlModel> listACFlow = accessControlFlowDAO.getAccessControlFlowByConditions(ac4FlowPageQuery);
        PageInfo<FlowControlModel> pageInfo = new PageInfo<FlowControlModel>(listACFlow);
        return PageResult.of(listACFlow, pageInfo.getTotal());
    }

    @Override
    public void addFlowControl(AC4FlowStatusActionParam ac4FlowStatusActionParam) {
        // 设置请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<AC4FlowStatusActionParam> requestEntity = new HttpEntity<>(ac4FlowStatusActionParam, headers);
        // 发送POST请求
        ResponseEntity<ResponseResult> response = restTemplate.exchange(AC_URL + "/flow/add", HttpMethod.POST, requestEntity, ResponseResult.class);
        // 处理响应
        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new HTTPRequestFailedException(response.getBody().getData().toString());
        }
    }

    @Override
    public void enableFlowControl(AC4FlowStatusActionParam ac4FlowStatusActionParam) {
        // 设置请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<AC4FlowStatusActionParam> requestEntity = new HttpEntity<>(ac4FlowStatusActionParam, headers);
        // 发送PUT请求
        ResponseEntity<ResponseResult> response = restTemplate.exchange(AC_URL + "/flow/enable", HttpMethod.PUT, requestEntity, ResponseResult.class);
        // 处理响应
        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new HTTPRequestFailedException(response.getBody().getData().toString());
        }
    }

    @Override
    public void disableFlowControl(AC4FlowStatusActionParam ac4FlowStatusActionParam) {
        // 设置请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<AC4FlowStatusActionParam> requestEntity = new HttpEntity<>(ac4FlowStatusActionParam, headers);
        // 发送PUT请求
        ResponseEntity<ResponseResult> response = restTemplate.exchange(AC_URL + "/flow/disabled", HttpMethod.PUT, requestEntity, ResponseResult.class);
        // 处理响应
        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new HTTPRequestFailedException(response.getBody().getData().toString());
        }
    }

    @Override
    public void removeFlowControl(AC4FlowStatusActionParam ac4FlowStatusActionParam) {
        // 设置请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<AC4FlowStatusActionParam> requestEntity = new HttpEntity<>(ac4FlowStatusActionParam, headers);
        // 发送DELETE请求
        ResponseEntity<ResponseResult> response = restTemplate.exchange(AC_URL + "/flow/remove", HttpMethod.DELETE, requestEntity, ResponseResult.class);
        // 处理响应
        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new HTTPRequestFailedException(response.getBody().getData().toString());
        }
    }

    @Override
    public void modifyFlowControls(AC4FlowStatusActionParam ac4FlowStatusActionParam) {
        // 设置请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<AC4FlowStatusActionParam> requestEntity = new HttpEntity<>(ac4FlowStatusActionParam, headers);
        // 发送PUT请求
        ResponseEntity<ResponseResult> response = restTemplate.exchange(AC_URL + "/flow/update", HttpMethod.PUT, requestEntity, ResponseResult.class);
        // 处理响应
        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new HTTPRequestFailedException(response.getBody().getData().toString());
        }
    }

    @Override
    public List<ServiceDTO> getServiceNoBindFlowControl() {
        List<ServiceDO> serviceNoBindFlowControl = accessControlFlowDAO.getServiceNoBindFlowControl();
        List<ServiceDTO> serviceNoBindFlowControl4DTO = serviceConverter.do2dto(serviceNoBindFlowControl);
        return serviceNoBindFlowControl4DTO;
    }

    @Override
    public FlowControlModel getFlowControlById(String id) {
        FlowControlModel flowControl = accessControlFlowDAO.getFlowControlById(id);
        return flowControl;
    }
}
