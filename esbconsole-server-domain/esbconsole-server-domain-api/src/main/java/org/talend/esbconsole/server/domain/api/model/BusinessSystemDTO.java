package org.talend.esbconsole.server.domain.api.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 业务系统字典信息
 *
 * @author xingzilong
 * @date 2023/08/22
 */
@Data
@NoArgsConstructor
public class BusinessSystemDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 主键ID
     */
    private String id;
    /**
     * 业务系统名称
     */
    private String systemName;
    /**
     * 状态
     */
    private String status;
    /**
     * 责任人
     */
    private String responsiblePerson;
    /**
     * 备注
     */
    private String remark;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 更新时间
     */
    private String updateTime;
}
