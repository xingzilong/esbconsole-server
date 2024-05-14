package org.talend.esbconsole.server.domain.core.impl;

import org.talend.esbconsole.server.domain.api.model.IPControlModel;
import org.talend.esbconsole.server.domain.api.model.ServiceDTO;
import org.talend.esbconsole.server.domain.api.param.AC4IPPageQueryParam;
import org.talend.esbconsole.server.domain.api.param.AC4IPStatusActionParam;
import org.talend.esbconsole.server.domain.api.query.AC4IPPageQuery;
import org.talend.esbconsole.server.domain.api.service.IPControlService;
import org.talend.esbconsole.server.domain.core.converter.ServiceConverter;
import org.talend.esbconsole.server.domain.core.converter.query.AC4IPQueryConverter;
import org.talend.esbconsole.server.domain.repository.entity.ServiceDO;
import org.talend.esbconsole.server.domain.repository.mapper.AccessControlIPDAO;
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
 * IP访问控制相关功能服务接口 {@link IPControlService} 的实现类
 *
 * @author xingzilong
 * @date 2021/05/04
 */
@Slf4j
@Service
public class IPControlServiceImpl implements IPControlService {

    @Value("${sam.ac.url}")
    private String AC_URL;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AccessControlIPDAO accessControlIPDAO;

    @Autowired
    private AC4IPQueryConverter ac4IPQueryConverter;

    @Autowired
    private ServiceConverter serviceConverter;

    @Override
    public PageResult<IPControlModel> getIPControls(AC4IPPageQueryParam ac4IPPageQueryParam) {
        AC4IPPageQuery ac4IPPageQuery = ac4IPQueryConverter.param2query(ac4IPPageQueryParam);
        PageHelper.startPage(ac4IPPageQueryParam.getPageNum(), ac4IPPageQueryParam.getPageSize());
        List<IPControlModel> listACIP = accessControlIPDAO.getAccessControlIPByConditions(ac4IPPageQuery);
        PageInfo<IPControlModel> pageInfo = new PageInfo<IPControlModel>(listACIP);
        return PageResult.of(listACIP, pageInfo.getTotal());
    }

    @Override
    public void addIPControl(AC4IPStatusActionParam ac4IPStatusActionParam) {
        // 设置请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<AC4IPStatusActionParam> requestEntity = new HttpEntity<>(ac4IPStatusActionParam, headers);
        // 发送POST请求
        ResponseEntity<ResponseResult> response = restTemplate.exchange(AC_URL + "/ip/add", HttpMethod.POST, requestEntity, ResponseResult.class);
        // 处理响应
        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new HTTPRequestFailedException(response.getBody().getData().toString());
        }
    }

    @Override
    public void enableIPControl(AC4IPStatusActionParam ac4IPStatusActionParam) {
        // 设置请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<AC4IPStatusActionParam> requestEntity = new HttpEntity<>(ac4IPStatusActionParam, headers);
        // 发送PUT请求
        ResponseEntity<ResponseResult> response = restTemplate.exchange(AC_URL + "/ip/enable", HttpMethod.PUT, requestEntity, ResponseResult.class);
        // 处理响应
        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new HTTPRequestFailedException(response.getBody().getData().toString());
        }
    }

    @Override
    public void disableIPControl(AC4IPStatusActionParam ac4IPStatusActionParam) {
        // 设置请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<AC4IPStatusActionParam> requestEntity = new HttpEntity<>(ac4IPStatusActionParam, headers);
        // 发送PUT请求
        ResponseEntity<ResponseResult> response = restTemplate.exchange(AC_URL + "/ip/disabled", HttpMethod.PUT, requestEntity, ResponseResult.class);
        // 处理响应
        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new HTTPRequestFailedException(response.getBody().getData().toString());
        }
    }

    @Override
    public void removeIPControl(AC4IPStatusActionParam ac4IPStatusActionParam) {
        // 设置请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<AC4IPStatusActionParam> requestEntity = new HttpEntity<>(ac4IPStatusActionParam, headers);
        // 发送DELETE请求
        ResponseEntity<ResponseResult> response = restTemplate.exchange(AC_URL + "/ip/remove", HttpMethod.DELETE, requestEntity, ResponseResult.class);
        // 处理响应
        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new HTTPRequestFailedException(response.getBody().getData().toString());
        }
    }

    @Override
    public void modifyIPControls(AC4IPStatusActionParam ac4IPStatusActionParam) {
        // 设置请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<AC4IPStatusActionParam> requestEntity = new HttpEntity<>(ac4IPStatusActionParam, headers);
        // 发送PUT请求
        ResponseEntity<ResponseResult> response = restTemplate.exchange(AC_URL + "/ip/update", HttpMethod.PUT, requestEntity, ResponseResult.class);
        // 处理响应
        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new HTTPRequestFailedException(response.getBody().getData().toString());
        }
    }

    @Override
    public List<ServiceDTO> getServiceNoBindIPControl() {
        List<ServiceDO> serviceNoBindIPControl = accessControlIPDAO.getServiceNoBindIPControl();
        List<ServiceDTO> serviceDTOS = serviceConverter.do2dto(serviceNoBindIPControl);
        return serviceDTOS;
    }

    @Override
    public IPControlModel getIPControlById(String id) {
        IPControlModel ipControl = accessControlIPDAO.getIPControlById(id);
        return ipControl;
    }
}
