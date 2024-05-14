package org.talend.esbconsole.server.domain.api.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * jvm内存相关详细信息描述对象
 *
 * @author xingzilong
 * @date 2021/05/04
 */
@Data
@NoArgsConstructor
public class JVMInfo {

    /**
     * JVM内存池相关信息
     */
    private List<JVMMemoryInfo> memoryPool;

    /**
     * JVM堆内存信息
     */
    private JVMMemoryInfo heapMemory;

    /**
     * JVM非堆内存信息
     */
    private JVMMemoryInfo noHeapMemory;
}
