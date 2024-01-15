package org.talend.esbconsole.server.domain.api.param;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 修改服务信息的参数
 *
 * @author xingzilong
 * @date 2023/05/04
 */
@Data
@NoArgsConstructor
public class ServiceModifyParam implements Serializable {
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
     * 所属业务系统的id
     */
    private String businessSystemId;

    /**
     * 描述
     */
    private String description;

    /**
     * 责任人
     */
    private String responsiblePerson;


}
