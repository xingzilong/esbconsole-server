package org.talend.esbconsole.server.domain.core.util.listpage.strategy;

import org.talend.esbconsole.server.domain.api.model.FeatureInfo;
import org.talend.esbconsole.server.domain.api.param.FeaturePageQueryParam;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * {@link FeatureMatchStrategy} 单元测试
 *
 * @author xingzilong
 * @date 2023/10/17
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class FeatureMatchStrategyTest {

    @InjectMocks
    private FeatureMatchStrategy featureMatchStrategy;

    @Test
    public void featureMatchStrategyTest() {
        FeaturePageQueryParam featurePageQueryParam = new FeaturePageQueryParam();
        FeatureMatchStrategy featureMatchStrategy = new FeatureMatchStrategy();
        FeatureMatchStrategy featureMatchStrategy1 = new FeatureMatchStrategy(featurePageQueryParam);
        featureMatchStrategy.setQuery(featurePageQueryParam);
        featureMatchStrategy.getQuery();
    }

    @Test
    public void isMatchTest() {
        FeatureInfo featureInfo = new FeatureInfo();
        featureInfo.setInstalled(true);
        featureInfo.setName("name");
        featureInfo.setVersion("1.0");
        FeaturePageQueryParam featurePageQueryParam = new FeaturePageQueryParam();
        FeatureMatchStrategy featureMatchStrategy = new FeatureMatchStrategy(featurePageQueryParam);

        featureMatchStrategy.isMatch(featureInfo);

        featurePageQueryParam.setInstalled(true);
        featurePageQueryParam.setName("name");
        featurePageQueryParam.setVersion("1.0");

        featureMatchStrategy.isMatch(featureInfo);
    }
}
