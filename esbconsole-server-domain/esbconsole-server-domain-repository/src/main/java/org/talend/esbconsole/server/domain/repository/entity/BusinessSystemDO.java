package org.talend.esbconsole.server.domain.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 数据库表 business_system 对应的实体类
 *
 * @author xingzilong
 * @date 2021/08/22
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BusinessSystemDO implements Serializable {
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
    private LocalDateTime createTime;
    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}
