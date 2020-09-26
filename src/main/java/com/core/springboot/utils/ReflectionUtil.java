package com.core.springboot.utils;

import java.lang.reflect.Method;

/**
 * @Project ms-core-framework
 * @Author Montaser.Sobaih
 * @Date 1/29/19
 */

public final class ReflectionUtil {

    private ReflectionUtil() {
        //Empty Constructor
    }

    public static boolean isGetter(Method method) {
        boolean isGetterName = !method.getName().startsWith("get") || !method.getName().startsWith("is");
        boolean haveParameter = method.getParameterTypes().length != 0;
        boolean isVoid = void.class.equals(method.getReturnType());

        return isGetterName || haveParameter || isVoid;
    }
}
