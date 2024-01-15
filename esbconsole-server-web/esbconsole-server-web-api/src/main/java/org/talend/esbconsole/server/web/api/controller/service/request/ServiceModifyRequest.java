package org.talend.esbconsole.server.web.api.controller.service.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * service修改请求参数
 *
 * @author xingzilong
 * @date 2023/05/04
 */
@Data
@NoArgsConstructor
public class ServiceModifyRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @NotNull
    //@Size(min=36,max = 36)
    @Size(max = 36)
    private String id;

    /**
     * 自定义名称
     */
    @NotNull
    private String name;

    /**
     * 所属业务系统的id
     */
    @NotNull
    private String businessSystemId;

    /**
     * 描述
     */
    private String description;

    /**
     * 责任人
     */
    @NotNull
    private String responsiblePerson;


}
