package org.talend.esbconsole.server.domain.core.util.fileanalysis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * {@link ServiceTypeEnum} 单元测试
 *
 * @author xingzilong
 * @date 2023/10/17
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class ServiceTypeEnumTest {

    @Test
    public void serviceTypeEnumTest() {
        ServiceTypeEnum restfulWs = ServiceTypeEnum.RESTFUL_WS;
        ServiceTypeEnum soapWs = ServiceTypeEnum.SOAP_WS;
        ServiceTypeEnum conventionRoute = ServiceTypeEnum.CONVENTION_ROUTE;
        ServiceTypeEnum proxyRoute = ServiceTypeEnum.PROXY_ROUTE;
        ServiceTypeEnum unknown = ServiceTypeEnum.UNKNOWN;
    }
}
