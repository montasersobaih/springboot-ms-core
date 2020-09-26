package com.core.springboot.utils;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @Project ms-core-framework
 * @Author Montaser.Sobaih
 * @Date 1/29/19
 */

public final class MapUtil {

    private MapUtil() {
        //Empty Constructor
    }

    public static <K, V> Map<K, V> ignoreNullValues(final Map<K, V> map) {
        Map<K, V> newMap = null;
        if (Objects.nonNull(map)) {
            newMap = new LinkedHashMap<>(map);
            for (K key : map.keySet()) {
                if (Objects.isNull(map.get(key))) {
                    newMap.remove(key);
                }
            }
        }

        return newMap;
    }
}
