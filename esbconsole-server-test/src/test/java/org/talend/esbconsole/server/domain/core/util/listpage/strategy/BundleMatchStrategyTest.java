package org.talend.esbconsole.server.domain.core.util.listpage.strategy;

import org.talend.esbconsole.server.domain.api.model.BundleInfo;
import org.talend.esbconsole.server.domain.api.param.BundlePageQueryParam;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * {@link BundleMatchStrategy} 单元测试
 *
 * @author xingzilong
 * @date 2021/10/17
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class BundleMatchStrategyTest {

    @InjectMocks
    private BundleMatchStrategy bundleMatchStrategy;

    @Test
    public void bundleMatchStrategyTest() {
        BundlePageQueryParam bundlePageQueryParam = new BundlePageQueryParam();
        BundleMatchStrategy bundleMatchStrategy = new BundleMatchStrategy();
        BundleMatchStrategy bundleMatchStrategy1 = new BundleMatchStrategy(bundlePageQueryParam);
        bundleMatchStrategy.setQuery(bundlePageQueryParam);
        bundleMatchStrategy.getQuery();
    }

    @Test
    public void isMatchTest() {
        BundleInfo bundleInfo = new BundleInfo();
        bundleInfo.setSymbolicName("symbolicname");
        bundleInfo.setBundleName("bundlename");
        bundleInfo.setState("state");
        bundleInfo.setStartLevel(80);
        bundleInfo.setUpdateLocation("16");
        BundlePageQueryParam bundlePageQueryParam = new BundlePageQueryParam();
        BundleMatchStrategy bundleMatchStrategy = new BundleMatchStrategy(bundlePageQueryParam);

        bundleMatchStrategy.isMatch(bundleInfo);

        bundlePageQueryParam.setSymbolicName("symbolicname");
        bundlePageQueryParam.setBundleName("bundlename");
        bundlePageQueryParam.setState("state");
        bundlePageQueryParam.setStartLevel(80);
        bundlePageQueryParam.setUpdateLocation("16");

        bundleMatchStrategy.isMatch(bundleInfo);
    }
}
