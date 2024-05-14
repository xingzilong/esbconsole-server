package org.talend.esbconsole.server.web.api.controller.server.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * jvm信息响应对象
 *
 * @author xingzilong
 * @date 2021/05/04
 */
@Data
@NoArgsConstructor
public class JVMInfoVO {

    /**
     * JVM内存池相关信息
     */
    private List<JVMMemoryInfoVO> memoryPool;

    /**
     * JVM堆内存信息
     */
    private JVMMemoryInfoVO heapMemory;

    /**
     * JVM非堆内存信息
     */
    private JVMMemoryInfoVO noHeapMemory;
}
