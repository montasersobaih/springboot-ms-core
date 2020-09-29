package com.mj.core.springboot.utils;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * @Project ms-core-framework
 * @Author Montaser.Sobaih
 * @Date 1/28/19
 */

public final class ObjectUtil {

    private ObjectUtil() {
        //Empty Constructor
    }

    public static <F, T> void copy(final F from, final T to, final boolean ignoreNulls) {
        final Map<String, Object> FROM_PROP = FieldExtractor.getFields(from, ignoreNulls);
        final Map<String, Field> TO_PROP = getReflectedFields(to);

        final Set<String> ASSIGNED_FIELDS = new HashSet<>();
        for (final Map.Entry<String, Object> ENTRY : FROM_PROP.entrySet()) {
            final String FIELD_NAME = ENTRY.getKey();
            if (ASSIGNED_FIELDS.add(FIELD_NAME)) {
                final Field FIELD = TO_PROP.get(FIELD_NAME);
                if (Objects.nonNull(FIELD)) {
                    try {
                        final boolean IS_ACCESSIBLE = FIELD.isAccessible();
                        FIELD.setAccessible(true);
                        FIELD.set(to, ENTRY.getValue());
                        FIELD.setAccessible(IS_ACCESSIBLE);
                    } catch (IllegalAccessException e) {
                        //Ignored catch
                    }
                }
            }
        }
    }

    public static <T> Map<String, Field> getReflectedFields(final T object) {
        final Map<String, Field> FIELDS = new LinkedHashMap<>();

        final Set<String> ASSIGNED_FIELDS = new HashSet<>();
        for (Class<?> cls = object.getClass(); Objects.nonNull(cls); cls = cls.getSuperclass()) {
            for (final Field FIELD : cls.getDeclaredFields()) {
                final String FIELD_NAME = FIELD.getName();
                if (ASSIGNED_FIELDS.add(FIELD_NAME)) {
                    FIELDS.put(FIELD_NAME, FIELD);
                }
            }
        }

        return FIELDS;
    }

    public static <T> Map<String, Object> getValuesOfFields(final T object) {
        final Map<String, Object> FROM_FIELDS = new LinkedHashMap<>();

        final Set<String> SET = new HashSet<>();
        for (Class<?> clz = object.getClass(); clz != null; clz = clz.getSuperclass()) {
            for (Field field : clz.getDeclaredFields()) {
                if (SET.add(field.getName())) {
                    try {
                        boolean access = field.isAccessible();
                        field.setAccessible(true);
                        FROM_FIELDS.put(field.getName(), field.get(object));
                        field.setAccessible(access);
                    } catch (IllegalAccessException e) {
                        //Ignored catch
                    }
                }
            }
        }

        return FROM_FIELDS;
    }
}
