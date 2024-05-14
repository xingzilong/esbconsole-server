package org.talend.esbconsole.server.tools.base.result;

import lombok.Data;

import java.util.List;

/**
 * 分页数据结果集
 *
 * @author xingzilong
 * @date 2021/05/04
 */
@Data
public class PageResult<T> {

    /**
     * 数据集
     */
    private List<T> list;

    /**
     * 总数
     */
    private Long total;

    /**
     * 当前页
     */
    private Long pageNum;
    /**
     * 每页的数量
     */
    private Long pageSize;

    public PageResult() {
    }

    public PageResult(List<T> data, Long total, Long pageNum, Long pageSize) {
        this.list = data;
        this.total = total;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    /**
     * 构建分页返回对象
     *
     * @return 分页返回对象
     */
    public static PageResult of() {
        return new PageResult(null, 0L, null, null);
    }

    /**
     * 构建分页返回对象
     *
     * @param data  返回的对象
     * @param total 总的条数
     * @param <T>   返回的对象类型
     * @return 分页返回对象
     */
    public static <T> PageResult<T> of(List<T> data, Long total) {
        return new PageResult<>(data, total, null, null);
    }

    /**
     * 构建分页返回对象
     *
     * @param data     返回的对象
     * @param total    总的条数
     * @param pageNum  页码
     * @param pageSize 分页大小
     * @param <T>      返回的对象类型
     * @return 分页返回对象
     */
    public static <T> PageResult<T> of(List<T> data, Long total, Long pageNum, Long pageSize) {
        return new PageResult<>(data, total, pageNum, pageSize);
    }

}
