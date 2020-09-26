package com.core.springboot.interceptor;

import com.core.springboot.constant.Constants;
import com.core.springboot.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

@Slf4j
public class RequestResponseInterceptor implements HandlerInterceptor {


    private final LocaleResolver resolver;

    public RequestResponseInterceptor(LocaleResolver resolver) {
        this.resolver = resolver;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String requestId = request.getHeader(Constants.X_REQUEST_DASH_ID);
        String channelName = request.getHeader("channel-name");

        String logString = "new {} request with uri {}, requestId {} and channelName {} has been received.";
        log.info(logString, request.getMethod(), request.getRequestURI(), requestId, channelName);

        MDC.put("Request ID", requestId);
        MDC.put("Channel Name", channelName);

        setLocale(request, response, null, null);

        return true;
    }


    private void setLocale(HttpServletRequest request, HttpServletResponse response, String lang, String country) {
        String localeCountry = StringUtil.isEmpty(country) ? "JO" : country;
        String localeLang = StringUtil.isEmpty(lang) ? "" : lang;

        resolver.setLocale(request, response, new Locale(localeLang, localeCountry));
    }
}
