package org.talend.esbconsole.server.web.api.controller.service.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * service的描述对象
 *
 * @author xingzilong
 * @date 2023/05/04
 */
@Data
@NoArgsConstructor
public class ServiceVO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private String id;

    /**
     * 自定义名称
     */
    private String name;

    /**
     * 所属业务系统
     */
    private String businessSystem;

    /**
     * 服务的类型，目前有五种类型：
     * "restful_ws"             kar文件的形式、 wsAPI接口
     * "soap_ws"                jar文件形式、 wsAPI接口
     * "timed_route"            kar文件的形式、 定时任务
     * "conventional_route"     kar文件的形式、 常规路由
     * "proxy_route"            kar文件的形式 需要在部署时传入代理后的地址 代理路由
     */
    private String serviceType;

    /**
     * 对应的任务名的数组
     */
    private List<String> job;

    /**
     * 文件名
     */
    private String fileName;

    /**
     * 文件类型 两种 kar 或 jar
     */
    private String fileType;

    /**
     * bundle id 只有文件类型为jar的时，存在有效值
     */
    private Long bundleId;

    /**
     * 当文件类型为kar是存在有效值 如果是kar类型的，会存在一个xxx-features.xml文件， 其中有个feature标签，其下name属性便是此字段值。 可以通过该值获取kar文件所包含的所有的bundle
     */
    private String featureName;

    /**
     * 当文件类型为kar是存在有效值，是个字符串数组 bundle对应的MVN本地库的坐标名称。
     */
    private List<String> bundleName;

    /**
     * 服务的唯一标识，配合访问控制策略进行使用
     */
    private String serviceKey;

    /**
     * 0 表示未启用SAM监控， 1 表示启用了SAM监控
     */
    private String enabledSAM;

    /**
     * 描述
     */
    private String description;

    /**
     * 责任人
     */
    private String responsiblePerson;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 更新时间
     */
    private String updateTime;

}
