package org.talend.esbconsole.server.domain.core.impl;

import org.talend.esbconsole.server.domain.api.model.BundleInfo;
import org.talend.esbconsole.server.domain.api.model.FeatureInfo;
import org.talend.esbconsole.server.domain.api.param.FeaturePageQueryParam;
import org.talend.esbconsole.server.domain.api.service.BundleService;
import org.talend.esbconsole.server.domain.api.service.FeatureService;
import org.talend.esbconsole.server.domain.core.jmx.karaf.Feature;
import org.talend.esbconsole.server.domain.core.util.listpage.PageProcessor;
import org.talend.esbconsole.server.domain.core.util.listpage.strategy.FeatureMatchStrategy;
import org.talend.esbconsole.server.tools.base.result.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.openmbean.CompositeData;
import javax.management.openmbean.TabularData;
import java.util.*;

/**
 * 特性（Feature）相关功能服务接口 {@link FeatureService} 的实现类
 *
 * @author xingzilong
 * @date 2023/05/04
 */
@Service
public class FeatureServiceImpl implements FeatureService {

    private static final String BUNDLES = "Bundles";

    private static final String BLACKLISTED = "Blacklisted";

    private static final String INSTALLED = "Installed";

    private static final String NAME = "Name";

    private static final String REQUIRED = "Required";

    private static final String VERSION = "Version";

    @Autowired
    private BundleService bundleService;

    @Autowired
    private Feature feature;

    @Override
    public List<FeatureInfo> getAllFeaturesForKaraf() {
        ArrayList<FeatureInfo> featureInfoList = null;
        TabularData featuresTabularData = feature.featuresAttribute();
        Set<?> ketSet = featuresTabularData.keySet();
        Iterator<?> iterator = ketSet.iterator();
        featureInfoList = new ArrayList<>();
        while (iterator.hasNext()) {
            CompositeData bundleData = featuresTabularData.get(((List) iterator.next()).toArray());
            FeatureInfo featureInfo = new FeatureInfo((Boolean) bundleData.get(BLACKLISTED),
                    Arrays.asList((String[]) bundleData.get(BUNDLES)), (Boolean) bundleData.get(INSTALLED),
                    (String) bundleData.get(NAME), (Boolean) bundleData.get(REQUIRED), (String) bundleData.get(VERSION));
            featureInfoList.add(featureInfo);
        }
        return featureInfoList;
    }

    @Override
    public PageResult<FeatureInfo> getFeaturesForKaraf(FeaturePageQueryParam featurePageQueryParam) {
        ArrayList<FeatureInfo> featureInfoList = null;
        TabularData featuresTabularData = feature.featuresAttribute();
        Set<?> ketSet = featuresTabularData.keySet();
        Iterator<?> iterator = ketSet.iterator();
        featureInfoList = new ArrayList<>();
        while (iterator.hasNext()) {
            CompositeData bundleData = featuresTabularData.get(((List) iterator.next()).toArray());
            FeatureInfo featureInfo = new FeatureInfo((Boolean) bundleData.get(BLACKLISTED),
                    Arrays.asList((String[]) bundleData.get(BUNDLES)), (Boolean) bundleData.get(INSTALLED),
                    (String) bundleData.get(NAME), (Boolean) bundleData.get(REQUIRED), (String) bundleData.get(VERSION));
            featureInfoList.add(featureInfo);
        }

        FeatureMatchStrategy featureMatchStrategy = new FeatureMatchStrategy(featurePageQueryParam);
        PageProcessor pageProcessor = new PageProcessor<FeatureInfo>(featureMatchStrategy,
                featureInfoList,
                featurePageQueryParam);

        List<FeatureInfo> resultList = pageProcessor.execute();

        return PageResult.of(resultList, (long) pageProcessor.getTotal());
    }

    @Override
    public List<String> listKarBundleMVNNames(String featureNamrForKar) {
        List<String> bundleMVNNames = null;
        TabularData tabularData = feature.infoFeature(featureNamrForKar);
        Set<?> ketSet = tabularData.keySet();
        Iterator<?> iterator = ketSet.iterator();
        while (iterator.hasNext()) {
            CompositeData bundleData = tabularData.get(((List) iterator.next()).toArray());
            String[] names = (String[]) bundleData.get(BUNDLES);
            bundleMVNNames = Arrays.asList(names);
        }
        return bundleMVNNames;
    }

    @Override
    public void installFeature(String featureNamr) {
        feature.installFeature(featureNamr);
    }

    @Override
    public void uninstallFeature(String featureNamr) {
        feature.uninstallFeature(featureNamr);
    }

    @Override
    public ArrayList<BundleInfo> getBundlesForFeature(List<String> mvnBundleNameList) {
        ArrayList<BundleInfo> listBundles = new ArrayList<>();
        List<BundleInfo> allListBundles = bundleService.getAllBundlesForKaraf();
        for (BundleInfo bundle : allListBundles) {
            for (String mvnBundleName : mvnBundleNameList) {
                if (mvnBundleName.equals(bundle.getUpdateLocation())) {
                    listBundles.add(bundle);
                }
            }
        }
        return listBundles;
    }
}
