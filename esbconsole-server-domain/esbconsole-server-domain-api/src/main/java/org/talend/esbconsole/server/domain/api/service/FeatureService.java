package org.talend.esbconsole.server.domain.api.service;

import org.talend.esbconsole.server.domain.api.model.BundleInfo;
import org.talend.esbconsole.server.domain.api.model.FeatureInfo;
import org.talend.esbconsole.server.domain.api.param.FeaturePageQueryParam;
import org.talend.esbconsole.server.tools.base.result.PageResult;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 特性 相关的功能服务接口
 *
 * @author xingzilong
 * @date 2023/08/22
 */
@Service
public interface FeatureService {

    /**
     * 获取全部的 feature 信息
     *
     * @return
     */
    List<FeatureInfo> getAllFeaturesForKaraf();

    /**
     * 根据查询条件分页获取Feature信息列表
     *
     * @param featurePageQueryParam
     * @return
     */
    PageResult<FeatureInfo> getFeaturesForKaraf(FeaturePageQueryParam featurePageQueryParam);


    /**
     * 获取 feature 下的 bundle name 的集合
     *
     * @param featureNamrForKar feature name
     * @return {@link List}类型的一个集合
     */
    List<String> listKarBundleMVNNames(String featureNamrForKar);

    /**
     * 卸载 feature 仅仅只是卸载，并不会从karaf容器中移除
     *
     * @param featureNamr feature name
     * @return
     */
    void installFeature(String featureNamr);

    /**
     * 安装 feature 将卸载的 feature 重新安装回来
     *
     * @param featureNamr feature name
     * @return
     */
    void uninstallFeature(String featureNamr);

    /**
     * 根据某个Feature下的mvnBundleName获取某个Feature下的Bundle的详细信息
     *
     * @param mvnBundleNameList feature所包含的所有的bundle的MVNBundleName
     * @return
     */
    ArrayList<BundleInfo> getBundlesForFeature(List<String> mvnBundleNameList);
}
