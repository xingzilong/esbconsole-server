package org.talend.esbconsole.server.domain.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 该类为根据角色Id查询到的用户
 *
 * @author xingzilong
 * @date 2023/08/29
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SystemUserModel implements Serializable {
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
}
