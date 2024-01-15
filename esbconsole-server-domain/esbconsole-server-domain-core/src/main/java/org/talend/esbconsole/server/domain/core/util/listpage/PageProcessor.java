package org.talend.esbconsole.server.domain.core.util.listpage;

import org.talend.esbconsole.server.domain.core.util.listpage.strategy.MatchStrategy;
import org.talend.esbconsole.server.tools.base.request.BasePageQueryRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 分页处理器  策略模式的上下文
 *
 * @param <T> 泛型，约束传入的数据参数的类型
 * @author xingzilong
 * @date 2023/05/04
 */
public class PageProcessor<T> {

    /**
     * 匹配策略
     */
    private MatchStrategy matchStrategy;

    /**
     * 源数据的集合
     */
    private List<T> sourceList;

    /**
     * 过滤后的集合
     */
    private List<T> filterList = new ArrayList<T>();

    /**
     * 分页后的集合
     */
    private List<T> resultList = new ArrayList<T>();

    /**
     * 分页信息，必须包含pageNum和pageSize
     */
    private BasePageQueryRequest pageInfo;

    /**
     * 初始化分页处理器，用于将源数据列表 sourceList 按照 matchStrategy 匹配策略进行过滤，并按照 PageQueryRequest 里的分页信息进行分页
     *
     * @param matchStrategy 过滤源数据列表时使用的匹配策略
     * @param sourceList    元数据列表
     * @param pageInfo      分页信息，必须包含pageNum和pageSize
     */
    public PageProcessor(MatchStrategy matchStrategy, List<T> sourceList, BasePageQueryRequest pageInfo) {
        this.matchStrategy = matchStrategy;
        this.sourceList = sourceList;
        this.pageInfo = pageInfo;
    }

    public PageProcessor(MatchStrategy matchStrategy, List<T> sourceList, List<T> filterList, List<T> resultList, BasePageQueryRequest pageInfo) {
        this.matchStrategy = matchStrategy;
        this.sourceList = sourceList;
        this.filterList = filterList;
        this.resultList = resultList;
        this.pageInfo = pageInfo;
    }

    /**
     * 执行过滤和分页
     *
     * @return 返回过滤和分页后的结果集，即分页信息 pageInfo 中描述的页信息集合
     */
    public List<T> execute() {
        // 过滤
        filterList = (List<T>) sourceList.stream()
                .filter(dataInfo -> matchStrategy.isMatch(dataInfo))
                .collect(Collectors.toList());
        // 分页
        // 过滤后的数据总数大于所需要返回分页数据的最后一个游标
        if (filterList.size() >= pageInfo.getPageNum() * pageInfo.getPageSize()) {
            resultList = filterList.subList((pageInfo.getPageNum() - 1) * pageInfo.getPageSize(), pageInfo.getPageNum() * pageInfo.getPageSize());
        } else {
            // 选中的页数大于总页数，返回最后一页数据
            if ((pageInfo.getPageNum() - 1) > filterList.size() / pageInfo.getPageSize()) {
                resultList = filterList.subList((filterList.size() / pageInfo.getPageSize()) * pageInfo.getPageSize(), filterList.size());
            } else {
                // 选择的为最后一页，且最后一页不是满页
                resultList = filterList.subList((pageInfo.getPageNum() - 1) * pageInfo.getPageSize(), filterList.size());
            }
        }
        return resultList;
    }

    /**
     * 获取符合过滤条件的结果集的总和
     *
     * @return
     */
    private int getFilterListSize() {
        return filterList.size();
    }

    /**
     * 获取分页处理后的总数据量
     *
     * @return
     */
    public int getTotal() {
        return getFilterListSize();
    }
}
