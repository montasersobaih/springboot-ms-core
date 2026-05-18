package com.mj.microservice.core.utils;

import com.mj.microservice.core.constant.Constants;
import com.mj.microservice.core.model.RequestInfo;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.messaging.MessageHeaders;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommonContext {

    private static final ThreadLocal<RequestInfo> CONTEXT = new ThreadLocal<>();

    public static RequestInfo getRequestInfo() {
        return CONTEXT.get();
    }

    public static void setRequestInfo(RequestInfo requestInfo) {
        CONTEXT.set(requestInfo);
    }

    public static void setRequestInfo(MessageHeaders headers) {
        // Add common headers to thread locale
        CommonContext.setRequestInfo(
                RequestInfo.builder()
                        .requestId(headers.get(Constants.REQUEST_DASH_ID, String.class))
                        .channelName(headers.get(Constants.CHANNEL_DASH_NAME, String.class))
                        .countryCode(headers.get(Constants.COUNTRY_DASH_CODE, String.class))
                        .build()
        );
    }

    public static void clear() {
        CONTEXT.remove();
    }
}
