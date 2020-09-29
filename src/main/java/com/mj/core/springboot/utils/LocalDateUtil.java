package com.mj.core.springboot.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @Project ms-core-framework
 * @Author Montaser.Sobaih
 * @Date 1/20/19
 */

public final class LocalDateUtil {

    private LocalDateUtil() {
        //Empty Constructor
    }

    public static String toString(LocalDate date) {
        return Objects.isNull(date) ? null : date.toString();
    }

    public static LocalDateTime toDateTime(LocalDate date) {
        return Objects.nonNull(date) ? date.atStartOfDay() : null;
    }

    public static LocalDateTime toDateTime(Object date) {
        LocalDateTime dateTime = null;
        if (Objects.nonNull(date)) {
            if (date instanceof LocalDate) {
                dateTime = toDateTime((LocalDate) date);
            } else if (date instanceof String) {
                //Not implemented yet
            }
        }

        return dateTime;
    }

    public static String toStringDateTime(LocalDate date) {
        return Objects.nonNull(date) ? date.atStartOfDay().toString() : null;
    }
}
