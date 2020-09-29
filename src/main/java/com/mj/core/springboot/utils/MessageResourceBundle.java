package com.mj.core.springboot.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MessageResourceBundle {

    private static final ResourceBundleMessageSource messageSource;

    static {
        messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("messages");
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setUseCodeAsDefaultMessage(false);
        messageSource.setFallbackToSystemLocale(true);
    }

    public static String getMessage(String baseKeyName) {
        return messageSource.getMessage(baseKeyName.concat("-MESSAGE"), null, LocaleContextHolder.getLocale());
    }

    public static String getCode(String baseKeyName) {
        return messageSource.getMessage(baseKeyName.concat("-CODE"), null, LocaleContextHolder.getLocale());
    }

    public static String getSource(String baseKeyName) {
        return messageSource.getMessage(baseKeyName.concat("-SOURCE"), null, null);
    }

    public static String getPath(String baseKeyName) {
        return messageSource.getMessage(baseKeyName.concat("-PATH"), null, null);
    }
}
