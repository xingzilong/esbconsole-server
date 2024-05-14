package org.talend.esbconsole.server.web.api.controller.user.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 用户验证和修改密码请求参数
 *
 * @author xingzilong
 * @date 2021/05/04
 */
@Data
@NoArgsConstructor
public class VerifyAndChangePasswordRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 密码
     */
    @NotNull
    private String password;
}
