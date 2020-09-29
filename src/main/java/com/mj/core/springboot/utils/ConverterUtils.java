package com.mj.core.springboot.utils;

import org.slf4j.Logger;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import static java.util.Objects.nonNull;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * @Project ms-core-framework
 * @Author Montaser.Sobaih
 * @Date 1/31/19
 */

public final class ConverterUtils {

    private static Logger log = getLogger(ConverterUtils.class);

    private ConverterUtils() {
        //Empty Constructor
    }

    public static <T> T convert(Object object, Class<T> data) {
        T result = null;
        try {
            Map<String, Object> fromFields = prepareFields(object);

            result = data.newInstance();

            Set<String> assignedFields = new HashSet<>();

            Class<?> to = data;
            while (nonNull(to)) {
                for (Field field : to.getDeclaredFields()) {
                    boolean access = field.isAccessible();
                    field.setAccessible(true);

                    String fieldName = field.getName();
                    if (assignedFields.add(fieldName)) {
                        Object value = fromFields.get(field.getName());
                        if (nonNull(value)) {
                            try {
                                field.set(result, value);
                            } catch (Exception ex) {
                                if (ex instanceof IllegalArgumentException) {
                                    Object newValue = convert(value, field.getType());
                                    field.set(result, newValue);
                                }
                            }
                        }
                    }

                    field.setAccessible(access);
                }

                to = to.getSuperclass();
            }
        } catch (Exception ignored) {
            //Ignored Exception
        }

        return result;
    }

    private static Map<String, Object> prepareFields(Object object) {
        Map<String, Object> fromFields = new LinkedHashMap<>();
        try {
            Set<String> set = new HashSet<>();

            Class<?> from = object.getClass();
            while (nonNull(from)) {
                for (Field field : from.getDeclaredFields()) {
                    if (set.add(field.getName())) {
                        boolean access = field.isAccessible();
                        field.setAccessible(true);
                        try {
                            fromFields.put(field.getName(), field.get(object));
                        } catch (Exception ex) {
                            //Ignored Exception
                        }
                        field.setAccessible(access);
                    }
                }

                from = from.getSuperclass();
            }
        } catch (Exception e) {
            //Ignored Exception
        }

        return fromFields;
    }
}
