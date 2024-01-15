package org.talend.esbconsole.server.domain.api.service;

import org.talend.esbconsole.server.domain.api.model.BundleInfo;
import org.talend.esbconsole.server.domain.api.param.BundlePageQueryParam;
import org.talend.esbconsole.server.tools.base.result.PageResult;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 构件 相关的功能服务接口
 *
 * @author xingzilong
 * @date 2023/08/22
 */
@Service
public interface BundleService {

    /**
     * 获取全部的bundle信息
     *
     * @return
     */
    List<BundleInfo> getAllBundlesForOSGI();

    /**
     * 获取全部的bundle信息
     *
     * @return
     */
    List<BundleInfo> getAllBundlesForKaraf();

    /**
     * 根据查询条件分页获取bundle信息列表
     *
     * @param bundlePageQueryParam
     * @return
     */
    PageResult<BundleInfo> getBundlesForKaraf(BundlePageQueryParam bundlePageQueryParam);

    /**
     * 部署 bundle ，且将部署后的 bundle 状态设置为 start
     *
     * @param bundleFile 可以是部署的bundle的文件路径。 例如：file:/D:/talend/bundles/db_timer_task_0.1.jar
     *                   可以是bundle在mvn本地库的坐标。例如：mvn:org.talend.esb/db_timer_task/0.1.0
     * @return 部署成功返回bundleId且数值大于 0L 失败则返回 -1L
     */
    Long installBundle(String bundleFile);

    /**
     * 卸载 bundle
     *
     * @param bundleID 可以是部署的bundle的bundleID。 例如：500 一定是大于O的值
     */
    void uninstallBundleByID(Long bundleID);

    /**
     * 卸载 bundle
     *
     * @param fileURL 可以是部署的bundle的文件路径。 例如：file:/D:/talend/bundles/db_timer_task_0.1.jar
     */
    void uninstallBundleFileURL(String fileURL);

    /**
     * 卸载 bundle
     *
     * @param mvnName 可以是bundle在mvn本地库的坐标。例如：mvn:org.talend.esb.test_project/db_timer_task/0.1.0
     */
    void uninstallBundleByMVNName(String mvnName);

    /**
     * 卸载 bundle
     *
     * @param symbolicName 可以是bundle的SymbolicName属性值。 例如：test_project.db_timer_task
     */
    void uninstallBundleBySymbolicName(String symbolicName);

    /**
     * 卸载 bundle
     *
     * @param bundleName 可以是bundle的Headers中的Bundle-Name属性值。 例如： db_timer_task
     */
    void uninstallBundleBundleName(String bundleName);

    /**
     * 启动 bundle
     *
     * @param bundleID 可以是部署的bundle的bundleID。 例如：500 一定是大于O的值
     */
    void startBundleByID(Long bundleID);

    /**
     * 启动 bundle
     *
     * @param fileURL 可以是部署的bundle的文件路径。 例如：file:/D:/talend/bundles/db_timer_task_0.1.jar
     */
    void startBundleFileURL(String fileURL);

    /**
     * 启动 bundle
     *
     * @param mvnName 可以是bundle在mvn本地库的坐标。例如：mvn:org.talend.esb.test_project/db_timer_task/0.1.0
     */
    void startBundleByMVNName(String mvnName);

    /**
     * 启动 bundle
     *
     * @param symbolicName 可以是bundle的SymbolicName属性值。 例如：test_project.db_timer_task
     */
    void startlBundleBySymbolicName(String symbolicName);

    /**
     * 启动 bundle
     *
     * @param bundleName 可以是bundle的Headers中的Bundle-Name属性值。 例如： db_timer_task
     */
    void startBundleBundleName(String bundleName);

    /**
     * 停止 bundle
     *
     * @param bundleID 可以是部署的bundle的bundleID。 例如：500 一定是大于O的值
     */
    void stopBundleByID(Long bundleID);

    /**
     * 停止 bundle
     *
     * @param fileURL 可以是部署的bundle的文件路径。 例如：file:/D:/talend/bundles/db_timer_task_0.1.jar
     */
    void stopBundleFileURL(String fileURL);

    /**
     * 停止 bundle
     *
     * @param mvnName 可以是bundle在mvn本地库的坐标。例如：mvn:org.talend.esb.test_project/db_timer_task/0.1.0
     */
    void stopBundleByMVNName(String mvnName);

    /**
     * 停止 bundle
     *
     * @param symbolicName 可以是bundle的SymbolicName属性值。 例如：test_project.db_timer_task
     */
    void stoplBundleBySymbolicName(String symbolicName);

    /**
     * 停止 bundle
     *
     * @param bundleName 可以是bundle的Headers中的Bundle-Name属性值。 例如： db_timer_task
     */
    void stopBundleBundleName(String bundleName);


}
