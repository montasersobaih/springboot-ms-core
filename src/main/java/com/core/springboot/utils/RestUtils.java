package com.core.springboot.utils;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import static java.util.Objects.nonNull;
import static java.util.Optional.empty;
import static java.util.Optional.of;

/**
 * @Project ms-core-framework
 * @Author Montaser.Sobaih
 * @Date 1/20/19
 */

public final class RestUtils {

    public static Optional<Class<?>> getRestController(Class<?> clz) {
        if (nonNull(clz)) {
            if (isRestControllerPresent(clz)) {
                return of(clz);
            }

            for (Class<?> clz1 : clz.getInterfaces()) {
                if (isRestControllerPresent(clz1)) {
                    return of(clz1);
                }
            }

            return getRestController(clz.getSuperclass());
        }

        return empty();
    }

    public static boolean isRestControllerPresent(Class<?> clz) {
        boolean controllerPresent = clz.isAnnotationPresent(Controller.class);
        boolean responseBodyPresent = clz.isAnnotationPresent(ResponseBody.class);
        boolean restControllerPresent = clz.isAnnotationPresent(RestController.class);

        return controllerPresent && responseBodyPresent || restControllerPresent;
    }
}
