package org.talend.esbconsole.server.domain.api.param;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 登录请求的输入参数
 *
 * @author xingzilong
 * @date 2021/05/04
 */
@Data
@NoArgsConstructor
public class LoginUserParam implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 用户输入的用户名
     */
    private String userName;

    /**
     * 用户输入的密码
     */
    private String password;

}
