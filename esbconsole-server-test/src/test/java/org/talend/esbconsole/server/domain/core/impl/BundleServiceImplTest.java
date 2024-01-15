package org.talend.esbconsole.server.domain.core.impl;

import org.talend.esbconsole.server.domain.api.param.BundlePageQueryParam;
import org.talend.esbconsole.server.domain.core.jmx.karaf.Bundle;
import org.talend.esbconsole.server.domain.core.jmx.osgi.BundleState;
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
 * {@link BundleServiceImpl} 单元测试
 *
 * @author xingzilong
 * @date 2023/10/11
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class BundleServiceImplTest {

    @InjectMocks
    BundleServiceImpl bundleService;

    @Mock
    Bundle bundle;

    @Mock
    BundleState bundleState;

    @Before
    public void setUp() {
        //初始化
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getAllBundlesForOSGITest() {
        TabularData bundleslist = mock(TabularData.class);
        when(bundleState.listBundles()).thenReturn(bundleslist);

        Set<?> ketSet = mock(Set.class);
        doReturn(ketSet).when(bundleslist).keySet();

        Iterator<?> iterator = mock(Iterator.class);
        doReturn(iterator).when(ketSet).iterator();

        when(iterator.hasNext()).thenReturn(true).thenReturn(false);

        List<CompositeData> bundleDatas = new ArrayList<CompositeData>();
        CompositeData compositeData = mock(CompositeData.class);
        bundleDatas.add(compositeData);
        doReturn(bundleDatas).when(iterator).next();

        CompositeData bundleData = mock(CompositeData.class);
        when(bundleslist.get(Mockito.any())).thenReturn(bundleData);

        TabularData headersTabularData = mock(TabularData.class);
        when(bundleData.get(Mockito.any())).thenReturn(headersTabularData);

        //headersBundleNameData 不为null
        CompositeData headersBundleNameData = mock(CompositeData.class);
        when(headersTabularData.get(Mockito.any())).thenReturn(headersBundleNameData);

        when(bundleData.get(Mockito.eq("Identifier"))).thenReturn(4561651561L);
        when(bundleData.get(Mockito.eq("SymbolicName"))).thenReturn("test");
        when(headersBundleNameData.get(Mockito.eq("Value"))).thenReturn("ttt");
        when(bundleData.get(Mockito.eq("State"))).thenReturn("1");
        when(bundleData.get(Mockito.eq("StartLevel"))).thenReturn(10);
        when(bundleData.get(Mockito.eq("Version"))).thenReturn("1");
        when(bundleData.get(Mockito.eq("Location"))).thenReturn("tt");
        when(bundleData.get(Mockito.eq("LastModified"))).thenReturn(123L);

        bundleService.getAllBundlesForOSGI();

        headersBundleNameData = null;
        when(headersTabularData.get(Mockito.any())).thenReturn(headersBundleNameData);
        when(iterator.hasNext()).thenReturn(true).thenReturn(false);

        bundleService.getAllBundlesForOSGI();

    }

    @Test
    public void getAllBundlesForKarafTest() {
        TabularData bundlesTabularData = mock(TabularData.class);
        when(bundle.bundlesAttribute()).thenReturn(bundlesTabularData);

        Set<?> ketSet = mock(Set.class);
        doReturn(ketSet).when(bundlesTabularData).keySet();

        Iterator<?> iterator = mock(Iterator.class);
        doReturn(iterator).when(ketSet).iterator();

        when(iterator.hasNext()).thenReturn(true).thenReturn(false);

        List<CompositeData> bundleDatas = new ArrayList<CompositeData>();
        CompositeData compositeData = mock(CompositeData.class);
        bundleDatas.add(compositeData);
        doReturn(bundleDatas).when(iterator).next();

        CompositeData bundleData = mock(CompositeData.class);
        when(bundlesTabularData.get(Mockito.any())).thenReturn(bundleData);

        when(bundleData.get(Mockito.eq("ID"))).thenReturn(541651L);

        bundleService.getAllBundlesForKaraf();

    }

    @Test
    public void getBundlesForKarafTest() {
        BundlePageQueryParam bundlePageQueryParam = new BundlePageQueryParam();
        bundlePageQueryParam.setPageNum(1);
        bundlePageQueryParam.setPageSize(10);

        TabularData bundlesTabularData = mock(TabularData.class);
        when(bundle.bundlesAttribute()).thenReturn(bundlesTabularData);

        Set<?> ketSet = mock(Set.class);
        doReturn(ketSet).when(bundlesTabularData).keySet();

        Iterator<?> iterator = mock(Iterator.class);
        doReturn(iterator).when(ketSet).iterator();

        when(iterator.hasNext()).thenReturn(true).thenReturn(false);

        List<CompositeData> bundleDatas = new ArrayList<CompositeData>();
        CompositeData compositeData = mock(CompositeData.class);
        bundleDatas.add(compositeData);
        doReturn(bundleDatas).when(iterator).next();

        CompositeData bundleData = mock(CompositeData.class);
        when(bundlesTabularData.get(Mockito.any())).thenReturn(bundleData);

        bundleService.getBundlesForKaraf(bundlePageQueryParam);

        verify(bundle).bundlesAttribute();
        verify(bundlesTabularData).keySet();
        verify(ketSet).iterator();
    }

    @Test
    public void installBundleTest() {
        when(bundle.install(Mockito.any(), Mockito.anyBoolean())).thenReturn(156156L);
        bundleService.installBundle("");
        verify(bundle).install(Mockito.any(), Mockito.anyBoolean());
    }

    @Test
    public void uninstallBundleByIDTest() {
        doNothing().when(bundle).uninstall(Mockito.any());
        bundleService.uninstallBundleByID(546156L);
        verify(bundle).uninstall(Mockito.any());
    }

    @Test
    public void uninstallBundleFileURLTest() {
        doNothing().when(bundle).uninstall(Mockito.any());
        bundleService.uninstallBundleFileURL("127.0.0.1");
        verify(bundle).uninstall(Mockito.any());
    }

    @Test
    public void uninstallBundleByMVNNameTest() {
        doNothing().when(bundle).uninstall("");
        bundleService.uninstallBundleByMVNName("");
        verify(bundle).uninstall("");
    }

    @Test
    public void uninstallBundleBySymbolicNameTest() {
        doNothing().when(bundle).uninstall("");
        bundleService.uninstallBundleBySymbolicName("");
        verify(bundle).uninstall("");
    }

    @Test
    public void uninstallBundleBundleNameTest() {
        doNothing().when(bundle).uninstall("");
        bundleService.uninstallBundleBundleName("");
        verify(bundle).uninstall("");
    }

    @Test
    public void startBundleByIDTest() {
        doNothing().when(bundle).start("56156");
        bundleService.startBundleByID(56156L);
        verify(bundle).start("56156");
    }

    @Test
    public void startBundleFileURLTest() {
        doNothing().when(bundle).start("");
        bundleService.startBundleFileURL("");
        verify(bundle).start("");
    }

    @Test
    public void startBundleByMVNNameTest() {
        doNothing().when(bundle).start("");
        bundleService.startBundleByMVNName("");
        verify(bundle).start("");
    }

    @Test
    public void startlBundleBySymbolicNameTest() {
        doNothing().when(bundle).start("");
        bundleService.startlBundleBySymbolicName("");
        verify(bundle).start("");
    }

    @Test
    public void startBundleBundleNameTest() {
        doNothing().when(bundle).start("");
        bundleService.startBundleBundleName("");
        verify(bundle).start("");
    }

    @Test
    public void stopBundleByIDTest() {
        doNothing().when(bundle).stop("45616");
        bundleService.stopBundleByID(45616L);
        verify(bundle).stop("45616");
    }

    @Test
    public void stopBundleFileURLTest() {
        doNothing().when(bundle).stop("");
        bundleService.stopBundleFileURL("");
        verify(bundle).stop("");
    }

    @Test
    public void stopBundleByMVNNameTest() {
        doNothing().when(bundle).stop("");
        bundleService.stopBundleByMVNName("");
        verify(bundle).stop("");
    }

    @Test
    public void stoplBundleBySymbolicNameTest() {
        doNothing().when(bundle).stop("");
        bundleService.stoplBundleBySymbolicName("");
        verify(bundle).stop("");
    }

    @Test
    public void stopBundleBundleNameTest() {
        doNothing().when(bundle).stop("");
        bundleService.stopBundleBundleName("");
        verify(bundle).stop("");
    }
}
