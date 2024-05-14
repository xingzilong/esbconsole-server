package org.talend.esbconsole.server.domain.core.util.fileanalysis;

import org.talend.esbconsole.server.domain.core.util.fileanalysis.strategy.*;
import org.talend.esbconsole.server.tools.common.constant.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.talend.esbconsole.server.domain.core.util.fileanalysis.strategy.AnalysisStrategy;
import org.talend.esbconsole.server.domain.core.util.fileanalysis.strategy.ConventionalRouteAnalysisStrategy;
import org.talend.esbconsole.server.domain.core.util.fileanalysis.strategy.ProxyRouteAnalysisStrategy;
import org.talend.esbconsole.server.domain.core.util.fileanalysis.strategy.RestFulWSAnalysisStrategy;
import org.talend.esbconsole.server.domain.core.util.fileanalysis.strategy.SOAPWSAnalysisStrategy;
import org.talend.esbconsole.server.domain.core.util.fileanalysis.strategy.TimedJobRouteAnalysisStrategy;

/**
 * 策略工厂，用于获取策略对象
 *
 * @author xingzilong
 * @date 2021/05/04
 */
@Slf4j
@Component
public class StrategyFactory {

    @Value("${service.dir}")
    private String SERVICE_DIR;

    /**
     * 根据服务类型获取分析策略对象
     *
     * @param type 服务类型
     * @return 返回与类型匹配的分析策略对象，若无与类型匹配的分析策略对象则返回 null
     */
    public AnalysisStrategy getAnalysisStrategy(String type) {
        switch (type) {
            case Constants.RESTFUL_WS:
                return new RestFulWSAnalysisStrategy(SERVICE_DIR);
            case Constants.SOAP_WS:
                return new SOAPWSAnalysisStrategy(SERVICE_DIR);
            case Constants.TIMED_ROUTE:
                return new TimedJobRouteAnalysisStrategy(SERVICE_DIR);
            case Constants.CONVENTIONAL_ROUTE:
                return new ConventionalRouteAnalysisStrategy(SERVICE_DIR);
            case Constants.PROXY_ROUTE:
                return new ProxyRouteAnalysisStrategy(SERVICE_DIR);
            default:
                return null;
        }
    }
}
