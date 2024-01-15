package org.talend.esbconsole.server.domain.core.util.listpage.strategy;

/**
 * 匹配策略接口  策略模式
 *
 * @author xingzilong
 * @date 2023/05/04
 */
public interface MatchStrategy<T> {
    /**
     * 匹配策略验判断
     *
     * @param info 数据
     * @return
     */
    boolean isMatch(T info);
}
