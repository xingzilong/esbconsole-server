package org.talend.esbconsole.server.domain.api.param;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 修改用户信息所接受的参数
 *
 * @author xingzilong
 * @date 2021/05/04
 */
@Data
@NoArgsConstructor
public class UserModifyParam implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
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
     * 邮箱
     */
    private String email;

    /**
     * 手机号码
     */
    private String phoneNumber;

}
