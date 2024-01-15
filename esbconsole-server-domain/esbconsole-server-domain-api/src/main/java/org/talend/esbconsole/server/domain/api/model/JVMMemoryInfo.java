package org.talend.esbconsole.server.domain.api.model;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * jvm内存描述
 *
 * @author xingzilong
 * @date 2023/05/04
 */
@Data
@NoArgsConstructor
public class JVMMemoryInfo {

    /**
     * 名称
     */
    private String name = "";

    /**
     * 类型
     */
    private String type = "";

    /**
     * 已提交大小
     */
    private Long committed = 0L;

    /**
     * 初始化大小
     */
    private Long init = 0L;

    /**
     * 最大值
     */
    private Long max = 0L;

    /**
     * 已使用
     */
    private Long used = 0L;

}
