package org.talend.esbconsole.server.domain.core.impl;

import org.talend.esbconsole.server.domain.api.model.JVMDetails;
import org.talend.esbconsole.server.domain.api.model.JVMInfo;
import org.talend.esbconsole.server.domain.api.model.JVMMemoryInfo;
import org.talend.esbconsole.server.domain.api.service.JVMService;
import org.talend.esbconsole.server.domain.core.jmx.java.Memory;
import org.talend.esbconsole.server.domain.core.jmx.java.MemoryPool;
import org.talend.esbconsole.server.domain.core.jmx.java.Runtime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * JVM相关功能服务接口 {@link JVMService} 的实现类
 *
 * @author xingzilong
 * @date 2021/05/04
 */
@Service
public class JVMServiceImpl implements JVMService {

    @Autowired
    private Memory memory;

    @Autowired
    private MemoryPool memoryPool;

    @Autowired
    private Runtime runtime;

    @Override
    public JVMInfo getESBServerJVMMemory() {
        JVMInfo jvmInfo = new JVMInfo();
        List<JVMMemoryInfo> memoryInfo = memoryPool.getMemoryInfo();
        JVMMemoryInfo heapMemoryInfo = memory.getHeapMemoryInfo();
        JVMMemoryInfo noHeapMemoryInfo = memory.getNoHeapMemoryInfo();
        jvmInfo.setMemoryPool(memoryInfo);
        jvmInfo.setHeapMemory(heapMemoryInfo);
        jvmInfo.setNoHeapMemory(noHeapMemoryInfo);
        return jvmInfo;
    }

    @Override
    public JVMDetails getESBServerJVMInfo() {
        JVMDetails jvmDetails = runtime.getJVMDetails();
        return jvmDetails;
    }
}
