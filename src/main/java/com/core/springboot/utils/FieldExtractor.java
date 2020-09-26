package com.core.springboot.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * @Project ms-core-framework
 * @Author Montaser.Sobaih
 * @Date 1/29/19
 */

@SuppressWarnings("unchecked")
public interface FieldExtractor {

    static Map<String, Object> getFields(final Object object, final boolean ignoreNulls) {
        Map<String, Object> fields = getFields(object);
        return ignoreNulls ? MapUtil.ignoreNullValues(fields) : fields;
    }

    static Map<String, Object> getFields(final Object object) {
        LinkedHashMap fields = new LinkedHashMap();

        Set<String> set = new HashSet();
        for (Class from = object.getClass(); Objects.nonNull(from); from = from.getSuperclass()) {
            for (Field field : from.getDeclaredFields()) {
                try {
                    final String FIELD_NAME = field.getName();
                    final String ENLARGE_NAME = StringUtil.capitalize(field.getName());

                    Method method;
                    try {

                        method = from.getDeclaredMethod("get".concat(ENLARGE_NAME));
                    } catch (Exception ex) {
                        method = from.getDeclaredMethod("is".concat(ENLARGE_NAME));
                    }

                    if (Modifier.isPublic(method.getModifiers())) {
                        if (set.add(FIELD_NAME)) {
                            final boolean IS_ACCESSIBLE = field.isAccessible();
                            field.setAccessible(true);
                            fields.put(FIELD_NAME, method.invoke(object));
                            field.setAccessible(IS_ACCESSIBLE);
                        }
                    }
                } catch (NoSuchMethodException |
                        IllegalAccessException |
                        ClassCastException |
                        InvocationTargetException e
                ) {
                    //Ignored catch
                }
            }
        }

        return fields;
    }

    default Map<String, Object> getFields(final boolean ignoreNulls) {
        Map<String, Object> fields = getFields(this);
        return ignoreNulls ? MapUtil.ignoreNullValues(fields) : fields;
    }
}
