package org.talend.esbconsole.server.web.api.controller.user.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 修改用户信息所接受的参数
 *
 * @author xingzilong
 * @date 2021/05/04
 */
@Data
@NoArgsConstructor
public class UserModifyRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @NotNull
    private String id;

    /**
     * 名称
     */
    @NotNull
    private String name;

    /**
     * 用户名
     */
    @NotNull
    private String userName;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机号码
     */
    private String phoneNumber;

}
