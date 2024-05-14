package org.talend.esbconsole.server.domain.core.impl;

import org.talend.esbconsole.server.domain.api.model.FrequencyControlModel;
import org.talend.esbconsole.server.domain.api.model.ServiceDTO;
import org.talend.esbconsole.server.domain.api.param.AC4FrequencyPageQueryParam;
import org.talend.esbconsole.server.domain.api.param.AC4FrequencyStatusActionParam;
import org.talend.esbconsole.server.domain.api.query.AC4FrequencyPageQuery;
import org.talend.esbconsole.server.domain.api.service.FrequencyControlService;
import org.talend.esbconsole.server.domain.core.converter.ServiceConverter;
import org.talend.esbconsole.server.domain.core.converter.query.AC4FrequencyQueryConverter;
import org.talend.esbconsole.server.domain.repository.entity.ServiceDO;
import org.talend.esbconsole.server.domain.repository.mapper.AccessControlFrequencyDAO;
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
 * 频次访问控制相关功能服务接口 {@link FrequencyControlService} 的实现类
 *
 * @author xingzilong
 * @date 2021/05/04
 */
@Slf4j
@Service
public class FrequencyControlServiceImpl implements FrequencyControlService {

    @Value("${sam.ac.url}")
    private String AC_URL;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AccessControlFrequencyDAO accessControlFrequencyDAO;

    @Autowired
    private AC4FrequencyQueryConverter ac4FrequencyQueryConverter;

    @Autowired
    private ServiceConverter serviceConverter;

    @Override
    public PageResult<FrequencyControlModel> getFrequencyControls(AC4FrequencyPageQueryParam ac4FrequencyPageQueryParam) {
        AC4FrequencyPageQuery ac4FrequencyPageQuery = ac4FrequencyQueryConverter.param2query(ac4FrequencyPageQueryParam);
        PageHelper.startPage(ac4FrequencyPageQueryParam.getPageNum(), ac4FrequencyPageQueryParam.getPageSize());
        List<FrequencyControlModel> listACFrequency = accessControlFrequencyDAO.getAccessControlFrequencyByConditions(ac4FrequencyPageQuery);
        PageInfo<FrequencyControlModel> pageInfo = new PageInfo<FrequencyControlModel>(listACFrequency);
        return PageResult.of(listACFrequency, pageInfo.getTotal());
    }

    @Override
    public void addFrequencyControl(AC4FrequencyStatusActionParam ac4FrequencyStatusActionParam) {
        // 设置请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<AC4FrequencyStatusActionParam> requestEntity = new HttpEntity<>(ac4FrequencyStatusActionParam, headers);
        // 发送POST请求
        ResponseEntity<ResponseResult> response = restTemplate.exchange(AC_URL + "/frequency/add", HttpMethod.POST, requestEntity, ResponseResult.class);
        // 处理响应
        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new HTTPRequestFailedException(response.getBody().getData().toString());
        }
    }

    @Override
    public void enableFrequencyControl(AC4FrequencyStatusActionParam ac4FrequencyStatusActionParam) {
        // 设置请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<AC4FrequencyStatusActionParam> requestEntity = new HttpEntity<>(ac4FrequencyStatusActionParam, headers);
        // 发送PUT请求
        ResponseEntity<ResponseResult> response = restTemplate.exchange(AC_URL + "/frequency/enable", HttpMethod.PUT, requestEntity, ResponseResult.class);
        // 处理响应
        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new HTTPRequestFailedException(response.getBody().getData().toString());
        }
    }

    @Override
    public void disableFrequencyControl(AC4FrequencyStatusActionParam ac4FrequencyStatusActionParam) {
        // 设置请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<AC4FrequencyStatusActionParam> requestEntity = new HttpEntity<>(ac4FrequencyStatusActionParam, headers);
        // 发送PUT请求
        ResponseEntity<ResponseResult> response = restTemplate.exchange(AC_URL + "/frequency/disabled", HttpMethod.PUT, requestEntity, ResponseResult.class);
        // 处理响应
        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new HTTPRequestFailedException(response.getBody().getData().toString());
        }
    }

    @Override
    public void removeFrequencyControl(AC4FrequencyStatusActionParam ac4FrequencyStatusActionParam) {
        // 设置请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<AC4FrequencyStatusActionParam> requestEntity = new HttpEntity<>(ac4FrequencyStatusActionParam, headers);
        // 发送DELETE请求
        ResponseEntity<ResponseResult> response = restTemplate.exchange(AC_URL + "/frequency/remove", HttpMethod.DELETE, requestEntity, ResponseResult.class);
        // 处理响应
        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new HTTPRequestFailedException(response.getBody().getData().toString());
        }
    }

    @Override
    public void modifyFrequencyControls(AC4FrequencyStatusActionParam ac4FrequencyStatusActionParam) {
        // 设置请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<AC4FrequencyStatusActionParam> requestEntity = new HttpEntity<>(ac4FrequencyStatusActionParam, headers);
        // 发送PUT请求
        ResponseEntity<ResponseResult> response = restTemplate.exchange(AC_URL + "/frequency/update", HttpMethod.PUT, requestEntity, ResponseResult.class);
        // 处理响应
        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new HTTPRequestFailedException(response.getBody().getData().toString());
        }
    }

    @Override
    public List<ServiceDTO> getServiceNoBindFrequencyControl() {
        List<ServiceDO> serviceNoBindFrequencyControl = accessControlFrequencyDAO.getServiceNoBindFrequencyControl();
        List<ServiceDTO> serviceNoBindFrequencyControlDTOS = serviceConverter.do2dto(serviceNoBindFrequencyControl);
        return serviceNoBindFrequencyControlDTOS;
    }

    @Override
    public FrequencyControlModel getFrequencyControlById(String id) {
        FrequencyControlModel frequencyControl = accessControlFrequencyDAO.getFrequencyControlById(id);
        return frequencyControl;
    }
}
