package com.core.springboot.utils.converter;

import com.fasterxml.jackson.databind.util.StdConverter;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * @Project ms-core-framework
 * @Author Montaser.Sobaih
 * @Date 2/15/19
 */

public final class LocalToZonedDateTimeConverter extends StdConverter<LocalDateTime, ZonedDateTime> {

    @Override
    public ZonedDateTime convert(LocalDateTime value) {
        ZonedDateTime dateTime = null;

        if (Objects.nonNull(value)) {
            dateTime = value.atZone(ZoneOffset.UTC);
        }

        return dateTime;
    }
}