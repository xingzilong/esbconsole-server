package org.talend.esbconsole.server.web.api.controller.service.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 * service其他操作请求参数
 *
 * @author xingzilong
 * @date 2023/05/04
 */
@Data
@NoArgsConstructor
public class ServiceStatusActionRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @NotNull
    @Size(max = 36)
    private String id;

    /**
     * 文件名
     */
    @NotNull
    private String fileName;

    /**
     * 文件类型 两种 kar 或 jar
     */
    @Pattern(regexp = "^(kar|jar)$", message = "文件类型不正确")
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
     * 服务的类型，目前有五种类型：
     * "restful_ws"             kar文件的形式、 wsAPI接口
     * "soap_ws"                jar文件形式、 wsAPI接口
     * "timed_route"            kar文件的形式、 定时任务
     * "conventional_route"     kar文件的形式、 常规路由
     * "proxy_route"            kar文件的形式 需要在部署时传入代理后的地址 代理路由
     */
    private String serviceType;

}
