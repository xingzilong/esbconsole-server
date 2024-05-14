package org.talend.esbconsole.server.domain.core.util.listpage;

import org.talend.esbconsole.server.domain.core.util.listpage.strategy.FeatureMatchStrategy;
import org.talend.esbconsole.server.tools.base.request.BasePageQueryRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

/**
 * {@link PageProcessor} 单元测试
 *
 * @author xingzilong
 * @date 2021/10/17
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class PageProcessorTest {

    @InjectMocks
    private PageProcessor pageProcessor;

    @Test
    public void pageProcessorTest() {
        PageProcessor pageProcessor = new PageProcessor(new FeatureMatchStrategy(),
                new ArrayList(),
                new ArrayList(),
                new ArrayList(),
                new BasePageQueryRequest());
    }
}
