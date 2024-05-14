package org.talend.esbconsole.server.domain.api.service;

import org.talend.esbconsole.server.domain.api.model.ServiceLogDTO;
import org.talend.esbconsole.server.domain.api.model.message.HttpMessage;
import org.talend.esbconsole.server.domain.api.param.ServiceLogPageQueryParam;
import org.talend.esbconsole.server.tools.base.result.PageResult;

/**
 * 服务日志相关的功能服务接口
 *
 * @author xingzilong
 * @date 2021/05/04
 */
public interface ServiceLogService {

    /**
     * 分页获取服务调用日志
     *
     * @param serviceLogPageQueryParam
     * @return
     */
    PageResult<ServiceLogDTO> getServiceLogs(ServiceLogPageQueryParam serviceLogPageQueryParam);

    /**
     * 获取请求报文
     *
     * @param flowId
     * @return
     */
    HttpMessage getRequestMessage(String flowId);

    /**
     * 获取响应报文
     *
     * @param flowId
     * @return
     */
    HttpMessage getResponseMessage(String flowId);
}
