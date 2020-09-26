package com.core.springboot.utils;

import com.core.springboot.constant.Constants;
import com.core.springboot.model.RequestInfo;
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
