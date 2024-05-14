package org.talend.esbconsole.server.domain.api.service;

import org.talend.esbconsole.server.domain.api.model.JVMDetails;
import org.talend.esbconsole.server.domain.api.model.JVMInfo;

/**
 * JVM相关服务
 *
 * @author xingzilong
 * @date 2021/05/04
 */
public interface JVMService {

    /**
     * 获取ESBServerJVM内存信息
     *
     * @return
     */
    JVMInfo getESBServerJVMMemory();

    /**
     * 获取ESBServerJVM信息
     *
     * @return
     */
    JVMDetails getESBServerJVMInfo();


}
