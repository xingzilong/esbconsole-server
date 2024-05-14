package org.talend.esbconsole.server.web.api.controller.role.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 创建角色信息所接受的参数
 *
 * @author xingzilong
 * @date 2021/05/04
 */
@Data
@NoArgsConstructor
public class RoleCreateRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 名称
     */
    @NotNull
    private String name;

    /**
     * 角色名
     */
    @NotNull
    private String roleName;

    /**
     * 描述
     */
    private String description;

}
