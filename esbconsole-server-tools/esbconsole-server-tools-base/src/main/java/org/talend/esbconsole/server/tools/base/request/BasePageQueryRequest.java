package org.talend.esbconsole.server.tools.base.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 分页查询参数基础类
 *
 * @author xingzilong
 * @date 2023/05/04
 */
@Data
@NoArgsConstructor
public class BasePageQueryRequest implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 查询页数
     */
    @Min(value = 0, message = "页数必须大于0")
    @NotNull(message = "页数值为空")
    private Integer pageNum;

    /**
     * 每页条数
     */
    @Min(value = 0, message = "每页条数必须大于0")
    @Max(value = 100)
    @NotNull(message = "每页条数值为空")
    private Integer pageSize;

}
