package org.talend.esbconsole.server.domain.api.service;

import java.util.List;

/**
 * kar相关的功能服务接口
 *
 * @author xingzilong
 * @date 2021/05/04
 */
public interface KarService {

    /**
     * 安装 kar
     *
     * @param karFile 要部署的Kar的文件路径。 例如：file:/D:/talend/bundles/db_timer_task_0.1.kar
     */
    void installKar(String karFile);

    /**
     * 卸载 kar
     *
     * @param fileName 值为 kar 部署时的文件名称。例如：若部署时的kar为file:/D:/talend/bundles/db_timer_task_0.1.kar， 则卸载此kar的时候，name应为
     *                 db_timer_task_0.1
     */
    void uninstallKar(String fileName);

    /**
     * 启动 kar
     *
     * @param karBundlesMVNName kar包含的所有 bundle 在mvn本地库的坐标。例如：mvn:org.talend.esb.test_project/db_timer_task/0.1.0
     */
    void startKar(List<String> karBundlesMVNName);

    /**
     * 停止 kar
     *
     * @param karBundlesMVNName kar包含的所有 bundle 在mvn本地库的坐标。例如：mvn:org.talend.esb.test_project/db_timer_task/0.1.0
     */
    void stopKar(List<String> karBundlesMVNName);
}
