package com.mj.microservice.core.utils;

import java.util.Objects;
import java.util.Optional;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @Project ms-core-framework
 * @Author Montaser.Sobaih
 * @Date 1/20/19
 */

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class StringUtils extends org.springframework.util.StringUtils {

    public static boolean isNullOrEmpty(String text) {
        return text == null || text.trim().isEmpty();
    }

    public static boolean haveValue(String string) {
        return Objects.nonNull(string) && isNotEmpty(string) && isNotBlank(string);
    }

    public static boolean isBlank(String string) {
        return string.length() == 1 && string.trim().isEmpty();
    }

    public static boolean isNotBlank(String string) {
        return !isBlank(string);
    }

    public static boolean isNotEmpty(String string) {
        return !string.isEmpty();
    }

    public static String toUpperCase(String string) {
        return Optional.ofNullable(string).map(String::toUpperCase).orElse(null);
    }

    public static String toLowerCase(String string) {
        return Optional.ofNullable(string).map(String::toLowerCase).orElse(null);
    }

    public static String capitalize(String string) {
        String newString = null;

        if (haveValue(string)) {
            char c = string.charAt(0);
            newString = String.format("%c%s", Character.toUpperCase(c), string.substring(1));
        }

        return newString;
    }

    public static String lastDigits(String value, int digits) {
        if (Objects.isNull(value) || value.length() <= digits) {
            return value;
        } else {
            return value.substring(value.length() - digits);
        }
    }
}