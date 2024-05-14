package org.talend.esbconsole.server.domain.core.util.fileanalysis;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * {@link StrategyFactory} 单元测试
 *
 * @author xingzilong
 * @date 2021/10/13
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class StrategyFactoryTest {

    @InjectMocks
    StrategyFactory strategyFactory;

    @Test
    public void getAnalysisStrategyTest() {
        strategyFactory.getAnalysisStrategy("restful_ws");

        strategyFactory.getAnalysisStrategy("soap_ws");

        strategyFactory.getAnalysisStrategy("timed_route");

        strategyFactory.getAnalysisStrategy("conventional_route");

        strategyFactory.getAnalysisStrategy("proxy_route");

        strategyFactory.getAnalysisStrategy("");
    }
}
