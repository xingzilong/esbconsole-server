package org.talend.esbconsole.server.domain.core.converter;

import com.alibaba.fastjson2.JSON;
import org.talend.esbconsole.server.domain.api.model.message.HttpMessage;
import org.talend.esbconsole.server.domain.api.model.message.Line;
import org.talend.esbconsole.server.domain.repository.entity.EventsDO;
import org.talend.esbconsole.server.tools.common.constant.Constants;
import org.talend.esbconsole.server.tools.common.utils.StringUtil;
import org.mapstruct.Mapper;

import java.util.Map;

@Mapper(componentModel = "spring")
public abstract class EventsConverter {

    /**
     * 模型转换
     *
     * @param eventsDO
     * @return
     */
    public HttpMessage do2dto(EventsDO eventsDO) {
        if (eventsDO == null) {
            return null;
        }

        HttpMessage httpMessage = new HttpMessage();
        httpMessage.setHttpStatus(String.valueOf(eventsDO.getHttpStatus()));
        Line line = new Line();
        line.setMethod(eventsDO.getHttpMethod());
        line.setUri(eventsDO.getUri());
        if (StringUtil.isNotEmpty(eventsDO.getQuerystring())) {
            line.setUri(eventsDO.getUri() + Constants.QUERY + eventsDO.getQuerystring());
        }
        line.setProtocol(eventsDO.getProtocol());
        httpMessage.setLine(line);
        if (StringUtil.isNotEmpty(eventsDO.getHttpHeaders())) {
            httpMessage.setHeader(JSON.parseObject(eventsDO.getHttpHeaders(), Map.class));
        }
        if (StringUtil.isNotEmpty(eventsDO.getMessageContent())) {
            httpMessage.setBody(eventsDO.getMessageContent());
        }
        return httpMessage;
    }

}
