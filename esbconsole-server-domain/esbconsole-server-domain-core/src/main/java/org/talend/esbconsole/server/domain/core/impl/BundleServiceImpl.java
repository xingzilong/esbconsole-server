package org.talend.esbconsole.server.domain.core.impl;

import org.talend.esbconsole.server.domain.api.model.BundleInfo;
import org.talend.esbconsole.server.domain.api.param.BundlePageQueryParam;
import org.talend.esbconsole.server.domain.api.service.BundleService;
import org.talend.esbconsole.server.domain.core.jmx.karaf.Bundle;
import org.talend.esbconsole.server.domain.core.jmx.osgi.BundleState;
import org.talend.esbconsole.server.domain.core.util.listpage.PageProcessor;
import org.talend.esbconsole.server.domain.core.util.listpage.strategy.BundleMatchStrategy;
import org.talend.esbconsole.server.tools.base.result.PageResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.openmbean.CompositeData;
import javax.management.openmbean.TabularData;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * 构件相关功能服务接口 {@link BundleService} 的实现类
 *
 * @author xingzilong
 * @date 2021/05/04
 */
@Slf4j
@Service
public class BundleServiceImpl implements BundleService {

    private static final String STATE = "State";

    private static final String VERSION = "Version";

    @Autowired
    private Bundle bundle;

    @Autowired
    private BundleState bundleState;

    @Override
    public List<BundleInfo> getAllBundlesForOSGI() {
        ArrayList<BundleInfo> bundleInfoList = null;
        TabularData bundleslist = bundleState.listBundles();
        Set<?> ketSet = bundleslist.keySet();
        Iterator<?> iterator = ketSet.iterator();
        bundleInfoList = new ArrayList<>();
        while (iterator.hasNext()) {
            CompositeData bundleData = bundleslist.get(((List) iterator.next()).toArray());
            TabularData headersTabularData = (TabularData) bundleData.get("Headers");
            CompositeData headersBundleNameData = headersTabularData.get(new Object[]{"Bundle-Name"});
            BundleInfo bundleInfo =
                    new BundleInfo((Long) bundleData.get("Identifier"),
                            (String) bundleData.get("SymbolicName"),
                            (headersBundleNameData == null) ? "" : (String) headersBundleNameData.get("Value"),
                            (String) bundleData.get(STATE),
                            (Integer) bundleData.get("StartLevel"),
                            (String) bundleData.get(VERSION),
                            (String) bundleData.get("Location"),
                            (Long) bundleData.get("LastModified"));
            bundleInfoList.add(bundleInfo);
        }
        return bundleInfoList;
    }

    @Override
    public List<BundleInfo> getAllBundlesForKaraf() {
        ArrayList<BundleInfo> bundleInfoList = new ArrayList<>();
        TabularData bundlesTabularData = bundle.bundlesAttribute();
        Set<?> ketSet = bundlesTabularData.keySet();
        Iterator<?> iterator = ketSet.iterator();
        while (iterator.hasNext()) {
            CompositeData bundleData = bundlesTabularData.get(((List) iterator.next()).toArray());
            BundleInfo bundleInfo = new BundleInfo(
                    (Long) bundleData.get("ID"),
                    (String) bundleData.get("Symbolic Name"),
                    (String) bundleData.get("Name"),
                    (String) bundleData.get(STATE),
                    (Integer) bundleData.get("Start Level"),
                    (String) bundleData.get(VERSION),
                    (String) bundleData.get("Update Location"),
                    0L);
            bundleInfoList.add(bundleInfo);
        }
        return bundleInfoList;
    }

    @Override
    public PageResult<BundleInfo> getBundlesForKaraf(BundlePageQueryParam bundlePageQueryParam) {
        ArrayList<BundleInfo> bundleInfoList = new ArrayList<>();
        TabularData bundlesTabularData = bundle.bundlesAttribute();
        Set<?> ketSet = bundlesTabularData.keySet();
        Iterator<?> iterator = ketSet.iterator();
        while (iterator.hasNext()) {
            CompositeData bundleData = bundlesTabularData.get(((List) iterator.next()).toArray());
            BundleInfo bundleInfo = new BundleInfo(
                    (Long) bundleData.get("ID"),
                    (String) bundleData.get("Symbolic Name"),
                    (String) bundleData.get("Name"),
                    (String) bundleData.get(STATE),
                    (Integer) bundleData.get("Start Level"),
                    (String) bundleData.get(VERSION),
                    (String) bundleData.get("Update Location"),
                    0L);
            bundleInfoList.add(bundleInfo);
        }

        BundleMatchStrategy bundleMatchStrategy = new BundleMatchStrategy(bundlePageQueryParam);
        // 初始化分页处理器
        PageProcessor pageProcessor = new PageProcessor<BundleInfo>(bundleMatchStrategy,
                bundleInfoList,
                bundlePageQueryParam);

        List<BundleInfo> resultList = pageProcessor.execute();

        return PageResult.of(resultList, (long) pageProcessor.getTotal());
    }

    @Override
    public Long installBundle(String bundleFile) {
        Long rs = -1L;
        rs = bundle.install(bundleFile, true);
        return rs;
    }

    @Override
    public void uninstallBundleByID(Long bundleID) {
        bundle.uninstall(String.valueOf(bundleID));
    }

    @Override
    public void uninstallBundleFileURL(String fileURL) {
        bundle.uninstall(fileURL);

    }

    @Override
    public void uninstallBundleByMVNName(String mvnName) {
        bundle.uninstall(mvnName);
    }

    @Override
    public void uninstallBundleBySymbolicName(String symbolicName) {
        bundle.uninstall(symbolicName);
    }

    @Override
    public void uninstallBundleBundleName(String bundleName) {
        bundle.uninstall(bundleName);
    }

    @Override
    public void startBundleByID(Long bundleID) {
        bundle.start(String.valueOf(bundleID));
    }

    @Override
    public void startBundleFileURL(String fileURL) {
        bundle.start(fileURL);
    }

    @Override
    public void startBundleByMVNName(String mvnName) {
        bundle.start(mvnName);
    }

    @Override
    public void startlBundleBySymbolicName(String symbolicName) {
        bundle.start(symbolicName);
    }

    @Override
    public void startBundleBundleName(String bundleName) {
        bundle.start(bundleName);
    }

    @Override
    public void stopBundleByID(Long bundleID) {
        bundle.stop(String.valueOf(bundleID));
    }

    @Override
    public void stopBundleFileURL(String fileURL) {
        bundle.stop(fileURL);
    }

    @Override
    public void stopBundleByMVNName(String mvnName) {
        bundle.stop(mvnName);

    }

    @Override
    public void stopBundleBySymbolicName(String symbolicName) {
        bundle.stop(symbolicName);

    }

    @Override
    public void stopBundleBundleName(String bundleName) {
        bundle.stop(bundleName);

    }
}
