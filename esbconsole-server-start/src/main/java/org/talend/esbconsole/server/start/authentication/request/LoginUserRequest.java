package org.talend.esbconsole.server.start.authentication.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 登录请求的输入参数的映射实体类
 *
 * @author xingzilong
 * @date 2021/05/04
 */
@Data
@NoArgsConstructor
public class LoginUserRequest implements Serializable {
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
