package com.mj.core.springboot.interceptor;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.mj.core.springboot.constant.Constants;
import com.mj.core.springboot.utils.CommonContext;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.ChannelInterceptorAdapter;

import java.util.Objects;

@Slf4j
public class MessagesInterceptor extends ChannelInterceptorAdapter {

    private static ObjectNode getPayloadAsJsonNodes(Object payload) {
        try {
            String json = new String((byte[]) payload);
            return new ObjectMapper().readValue(json, ObjectNode.class);

        } catch (Exception e) {
            return new ObjectNode(JsonNodeFactory.instance);
        }
    }

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel messageChannel) {
        MessageHeaders headers = message.getHeaders();

        MDC.put(Constants.REQUEST_ID, headers.get(Constants.REQUEST_DASH_ID, String.class));
        MDC.put(Constants.CHANNEL_NAME, headers.get(Constants.CHANNEL_DASH_NAME, String.class));
        MDC.put(Constants.COUNTRY, headers.get(Constants.COUNTRY_DASH_CODE, String.class));

        if (Objects.isNull(CommonContext.getRequestInfo())) {
            // set common headers
            CommonContext.setRequestInfo(headers);
        }

        return super.preSend(message, messageChannel);
    }
}
