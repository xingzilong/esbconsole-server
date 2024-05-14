package org.talend.esbconsole.server.domain.api.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 消费方系统字典信息
 *
 * @author xingzilong
 * @date 2021/08/25
 */
@Data
@NoArgsConstructor
public class ConsumerSystemDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 主键ID
     */
    private String id;
    /**
     * 消费方的IP
     */
    private String ip;
    /**
     * 消费方系统名称
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
