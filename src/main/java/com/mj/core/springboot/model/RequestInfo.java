package com.mj.core.springboot.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RequestInfo {
    String requestId;
    String channelName;
    String countryCode;
}
