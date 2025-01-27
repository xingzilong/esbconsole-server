package org.talend.esbconsole.server.web.api.controller.user.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 删除用户信息所接受的参数
 *
 * @author xingzilong
 * @date 2021/05/04
 */
@Data
@NoArgsConstructor
public class UserRemoveRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @NotNull
    private String id;

}
