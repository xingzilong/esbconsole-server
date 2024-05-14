package org.talend.esbconsole.server.domain.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * system_user表相应的实体类
 *
 * @author xingzilong
 * @date 2021/05/04
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SystemUserDO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private String id;

    /**
     * 名称
     */
    private String name;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机号码
     */
    private String phoneNumber;

    /**
     * 状态：0-禁用，1-正常
     */
    private String status;

    /**
     * 删除标志（0代表存在 1代表删除）
     */
    private String deleteFlag;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

}
