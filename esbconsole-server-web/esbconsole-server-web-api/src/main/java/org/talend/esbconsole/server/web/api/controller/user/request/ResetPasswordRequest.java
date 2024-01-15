package org.talend.esbconsole.server.web.api.controller.user.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 为用户分配权限所接受的参数
 *
 * @author xingzilong
 * @date 2023/05/04
 */
@Data
@NoArgsConstructor
public class ResetPasswordRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @NotNull
    private String id;

    /**
     * 用户密码
     */
    @NotNull
    private String password;

}
