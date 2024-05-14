package org.talend.esbconsole.server.web.api.controller.dictionary.converter;

import org.talend.esbconsole.server.web.api.controller.dictionary.request.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.talend.esbconsole.server.domain.api.param.BusinessCreateParam;
import org.talend.esbconsole.server.domain.api.param.BusinessModifyParam;
import org.talend.esbconsole.server.domain.api.param.BusinessPageQueryParam;
import org.talend.esbconsole.server.domain.api.param.ConsumerCreateParam;
import org.talend.esbconsole.server.domain.api.param.ConsumerModifyParam;
import org.talend.esbconsole.server.domain.api.param.ConsumerPageQueryParam;
import org.talend.esbconsole.server.web.api.controller.dictionary.request.BusinessCreateRequest;
import org.talend.esbconsole.server.web.api.controller.dictionary.request.BusinessModifyRequest;
import org.talend.esbconsole.server.web.api.controller.dictionary.request.BusinessPageQueryRequest;
import org.talend.esbconsole.server.web.api.controller.dictionary.request.ConsumerCreateRequest;
import org.talend.esbconsole.server.web.api.controller.dictionary.request.ConsumerModifyRequest;
import org.talend.esbconsole.server.web.api.controller.dictionary.request.ConsumerPageQueryRequest;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * {@link DictionaryWebConverterImpl} 单元测试
 *
 * @author xingzilong
 * @date 2021/10/17
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class DictionaryWebConverterImplTest {

    @InjectMocks
    private DictionaryWebConverterImpl dictionaryWebConverterImpl;

    @Test
    public void req2param4BusinessPageQueryRequestTest() {
        BusinessPageQueryRequest request = new BusinessPageQueryRequest();
        BusinessPageQueryParam businessPageQueryParam = dictionaryWebConverterImpl.req2param(request);
        assertNotNull(businessPageQueryParam);
        request = null;
        BusinessPageQueryParam businessPageQueryParam1 = dictionaryWebConverterImpl.req2param(request);
        assertNull(businessPageQueryParam1);
    }

    @Test
    public void req2param4BusinessCreateRequestTest() {
        BusinessCreateRequest request = new BusinessCreateRequest();
        BusinessCreateParam businessCreateParam = dictionaryWebConverterImpl.req2param(request);
        assertNotNull(businessCreateParam);
        request = null;
        BusinessCreateParam businessCreateParam1 = dictionaryWebConverterImpl.req2param(request);
        assertNull(businessCreateParam1);
    }

    @Test
    public void req2param4BusinessModifyRequestTest() {
        BusinessModifyRequest request = new BusinessModifyRequest();
        BusinessModifyParam businessModifyParam = dictionaryWebConverterImpl.req2param(request);
        assertNotNull(businessModifyParam);
        request = null;
        BusinessModifyParam businessModifyParam1 = dictionaryWebConverterImpl.req2param(request);
        assertNull(businessModifyParam1);
    }

    @Test
    public void req2param4ConsumerPageQueryRequestTest() {
        ConsumerPageQueryRequest request = new ConsumerPageQueryRequest();
        ConsumerPageQueryParam consumerPageQueryParam = dictionaryWebConverterImpl.req2param(request);
        assertNotNull(consumerPageQueryParam);
        request = null;
        ConsumerPageQueryParam consumerPageQueryParam1 = dictionaryWebConverterImpl.req2param(request);
        assertNull(consumerPageQueryParam1);
    }

    @Test
    public void req2param4ConsumerCreateRequestTest() {
        ConsumerCreateRequest request = new ConsumerCreateRequest();
        ConsumerCreateParam consumerCreateParam = dictionaryWebConverterImpl.req2param(request);
        assertNotNull(consumerCreateParam);
        request = null;
        ConsumerCreateParam consumerCreateParam1 = dictionaryWebConverterImpl.req2param(request);
        assertNull(consumerCreateParam1);
    }

    @Test
    public void req2param4ConsumerModifyRequestTest() {
        ConsumerModifyRequest request = new ConsumerModifyRequest();
        ConsumerModifyParam consumerModifyParam = dictionaryWebConverterImpl.req2param(request);
        assertNotNull(consumerModifyParam);
        request = null;
        ConsumerModifyParam consumerModifyParam1 = dictionaryWebConverterImpl.req2param(request);
        assertNull(consumerModifyParam1);
    }
}
