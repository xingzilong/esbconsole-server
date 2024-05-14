package org.talend.esbconsole.server.web.api.controller.server.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * jvm内存信息响应对象
 *
 * @author xingzilong
 * @date 2021/05/04
 */
@Data
@NoArgsConstructor
public class JVMMemoryInfoVO {

    /**
     * 名称
     */
    private String name;

    /**
     * 类型
     */
    private String type;

    /**
     * 已提交大小
     */
    private double committed;

    /**
     * 初始化大小
     */
    private double init;

    /**
     * 最大值
     */
    private double max;

    /**
     * 已使用
     */
    private double used;

}
