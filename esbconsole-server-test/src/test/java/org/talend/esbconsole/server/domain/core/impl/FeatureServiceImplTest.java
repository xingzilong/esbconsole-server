package org.talend.esbconsole.server.domain.core.impl;

import org.talend.esbconsole.server.domain.api.model.BundleInfo;
import org.talend.esbconsole.server.domain.api.param.FeaturePageQueryParam;
import org.talend.esbconsole.server.domain.api.service.BundleService;
import org.talend.esbconsole.server.domain.core.jmx.karaf.Feature;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import javax.management.openmbean.CompositeData;
import javax.management.openmbean.TabularData;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.*;

/**
 * {@link FeatureServiceImpl} 单元测试
 *
 * @author xingzilong
 * @date 2021/10/11
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class FeatureServiceImplTest {

    @Mock
    BundleService bundleService;

    @Mock
    Feature feature;

    @InjectMocks
    FeatureServiceImpl featureServiceImpl;

    @Before
    public void setUp() {
        //初始化
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getAllFeaturesForKarafTest() {
        TabularData featuresTabularData = mock(TabularData.class);
        when(feature.featuresAttribute()).thenReturn(featuresTabularData);

        Set<?> ketSet = mock(Set.class);
        doReturn(ketSet).when(featuresTabularData).keySet();

        Iterator<?> iterator = mock(Iterator.class);
        doReturn(iterator).when(ketSet).iterator();

        when(iterator.hasNext()).thenReturn(true).thenReturn(false);

        List<CompositeData> bundleDatas = new ArrayList<CompositeData>();
        CompositeData compositeData = mock(CompositeData.class);
        bundleDatas.add(compositeData);
        doReturn(bundleDatas).when(iterator).next();

        CompositeData bundleData = mock(CompositeData.class);
        when(featuresTabularData.get(Mockito.any())).thenReturn(bundleData);

        when(bundleData.get(Mockito.eq("Blacklisted"))).thenReturn(true);
        String[] strings = new String[10];
        strings[0] = "test";
        when(bundleData.get(Mockito.eq("Bundles"))).thenReturn(strings);
        when(bundleData.get(Mockito.eq("Installed"))).thenReturn(true);
        when(bundleData.get(Mockito.eq("Name"))).thenReturn("test");
        when(bundleData.get(Mockito.eq("Required"))).thenReturn(true);
        when(bundleData.get(Mockito.eq("Version"))).thenReturn("1");

        featureServiceImpl.getAllFeaturesForKaraf();

        verify(feature).featuresAttribute();
        verify(featuresTabularData).keySet();
        verify(ketSet).iterator();

    }

    @Test
    public void getFeaturesForKarafTest() {
        FeaturePageQueryParam featurePageQueryParam = new FeaturePageQueryParam();
        featurePageQueryParam.setPageNum(1);
        featurePageQueryParam.setPageSize(10);

        TabularData featuresTabularData = mock(TabularData.class);
        when(feature.featuresAttribute()).thenReturn(featuresTabularData);

        Set<?> ketSet = mock(Set.class);
        doReturn(ketSet).when(featuresTabularData).keySet();

        Iterator<?> iterator = mock(Iterator.class);
        doReturn(iterator).when(ketSet).iterator();

        when(iterator.hasNext()).thenReturn(true).thenReturn(false);

        List<CompositeData> bundleDatas = new ArrayList<CompositeData>();
        CompositeData compositeData = mock(CompositeData.class);
        bundleDatas.add(compositeData);
        doReturn(bundleDatas).when(iterator).next();

        CompositeData bundleData = mock(CompositeData.class);
        when(featuresTabularData.get(Mockito.any())).thenReturn(bundleData);

        when(bundleData.get(Mockito.eq("Blacklisted"))).thenReturn(true);
        String[] strings = new String[10];
        strings[0] = "test";
        when(bundleData.get(Mockito.eq("Bundles"))).thenReturn(strings);
        when(bundleData.get(Mockito.eq("Installed"))).thenReturn(true);
        when(bundleData.get(Mockito.eq("Name"))).thenReturn("test");
        when(bundleData.get(Mockito.eq("Required"))).thenReturn(true);
        when(bundleData.get(Mockito.eq("Version"))).thenReturn("1");

        featureServiceImpl.getFeaturesForKaraf(featurePageQueryParam);

        verify(feature).featuresAttribute();
        verify(featuresTabularData).keySet();
        verify(ketSet).iterator();
        verify(bundleData).get(Mockito.eq("Bundles"));

    }

    @Test
    public void listKarBundleMVNNamesTest() {
        TabularData tabularData = mock(TabularData.class);
        when(feature.infoFeature(Mockito.any())).thenReturn(tabularData);

        Set<?> ketSet = mock(Set.class);
        doReturn(ketSet).when(tabularData).keySet();

        Iterator<?> iterator = mock(Iterator.class);
        doReturn(iterator).when(ketSet).iterator();

        when(iterator.hasNext()).thenReturn(true).thenReturn(false);

        List<CompositeData> bundleDatas = new ArrayList<CompositeData>();
        CompositeData compositeData = mock(CompositeData.class);
        bundleDatas.add(compositeData);
        doReturn(bundleDatas).when(iterator).next();

        CompositeData bundleData = mock(CompositeData.class);
        when(tabularData.get(Mockito.any())).thenReturn(bundleData);

        String[] strings = new String[10];
        strings[0] = "test";
        when(bundleData.get(Mockito.eq("Bundles"))).thenReturn(strings);

        featureServiceImpl.listKarBundleMVNNames("");

        verify(feature).infoFeature(Mockito.any());
        verify(tabularData).keySet();
        verify(ketSet).iterator();
        verify(bundleData).get(Mockito.eq("Bundles"));

    }

    @Test
    public void installFeatureTest() {
        doNothing().when(feature).installFeature("");
        featureServiceImpl.installFeature("");
        verify(feature).installFeature("");
    }

    @Test
    public void uninstallFeatureTest() {
        doNothing().when(feature).uninstallFeature("");
        featureServiceImpl.uninstallFeature("");
        verify(feature).uninstallFeature("");
    }

    @Test
    public void getBundlesForFeatureTest() {
        List<String> mvnBundleNameList = new ArrayList<String>();
        mvnBundleNameList.add("C");
        mvnBundleNameList.add("D");

        List<BundleInfo> allListBundles = new ArrayList<BundleInfo>();
        BundleInfo bundleInfo = new BundleInfo();
        bundleInfo.setUpdateLocation("C");
        BundleInfo bundle = new BundleInfo();
        bundle.setUpdateLocation("A");
        allListBundles.add(bundleInfo);

        when(bundleService.getAllBundlesForKaraf()).thenReturn(allListBundles);

        featureServiceImpl.getBundlesForFeature(mvnBundleNameList);

        verify(bundleService).getAllBundlesForKaraf();

    }


}
