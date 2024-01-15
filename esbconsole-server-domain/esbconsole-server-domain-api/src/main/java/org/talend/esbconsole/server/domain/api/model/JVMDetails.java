package org.talend.esbconsole.server.domain.api.model;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * jvm详细信息描述对象
 *
 * @author xingzilong
 * @date 2023/05/04
 */
@Data
@NoArgsConstructor
public class JVMDetails {

    /**
     * JDK名称
     */
    private String name;

    /**
     * JDK版本
     */
    private String version;

    /**
     * JDK路径
     */
    private String home;

    /**
     * 项目路径
     */
    private String userDir;

    /**
     * JDK启动时间
     */
    private String startTime;

    /**
     * JDK运行时间
     */
    private String runTime;

    /**
     * 运行参数
     */
    private String inputArgs;

}
