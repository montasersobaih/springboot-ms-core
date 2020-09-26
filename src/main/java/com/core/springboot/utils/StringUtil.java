package com.core.springboot.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import java.util.Objects;
import java.util.Optional;

/**
 * @Project ms-core-framework
 * @Author Montaser.Sobaih
 * @Date 1/20/19
 */

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class StringUtil extends StringUtils {

    public static String lastDigits(String value, int digits) {
        if (Objects.isNull(value) || value.length() <= digits) {
            return value;
        } else {
            return value.substring(value.length() - digits);
        }
    }

    public static boolean isNullOrEmpty(String text) {
        return text == null || text.trim().length() == 0;
    }

    public static String toUpperCase(String string) {
        return Optional.ofNullable(string).map(String::toUpperCase).orElse(null);
    }

    public static boolean haveValue(String string) {
        return Objects.nonNull(string) && isNotEmpty(string) && isNotBlank(string);
    }

    public static boolean isBlank(String string) {
        return string.length() == 1 && string.trim().length() == 0;
    }

    public static boolean isNotBlank(String string) {
        return string.length() >= 1 && string.trim().length() >= 1;
    }

    public static boolean isNotEmpty(String string) {
        return !string.isEmpty();
    }

    public static String capitalize(final String string) {
        String newString = null;

        if (haveValue(string)) {
            char c = string.charAt(0);
            newString = String.format("%c%s", Character.toUpperCase(c), string.substring(1));
        }

        return newString;
    }
}