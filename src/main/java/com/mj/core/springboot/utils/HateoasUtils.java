package com.mj.core.springboot.utils;

import com.mj.core.springboot.model.dto.HateoasDTO;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.UriComponentsBuilder;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Optional;
import java.util.Set;

import static java.util.Optional.empty;
import static java.util.Optional.of;

/**
 * @Project ms-core-framework
 * @Author Montaser.Sobaih
 * @Date 1/24/19
 */

public abstract class HateoasUtils {

    private HateoasUtils() {
    }

    public static Collection<HateoasDTO> preparingHateoas(Class<?> clz, Object... params) {
        Collection<HateoasDTO> links = new LinkedList<>();

        Optional<Class<?>> optional = RestUtils.getRestController(clz);
        if (optional.isPresent()) {
            Class<?> clz1 = optional.get();
            for (Method method : clz1.getDeclaredMethods()) {
                Optional<String> optionalApiMethod = getApiMethod(method);
                if (optionalApiMethod.isPresent()) {
                    UriComponentsBuilder builder = WebMvcLinkBuilder.linkTo(method, params).toUriComponentsBuilder();
                    links.add(new HateoasDTO(builder.build().getPath(), optionalApiMethod.get()));
                }
            }
        }
        return links;
    }

    private static Optional<String> getApiMethod(Method method) {
        if (method.getDeclaredAnnotations().length > 0) {
            for (Annotation annotation : method.getDeclaredAnnotations()) {
                Optional<RequestMapping> optional = isMappingPresent(annotation, new HashSet<>());
                if (optional.isPresent()) {
                    RequestMapping mapping = optional.get();
                    if (mapping.method().length > 0) {
                        return of(mapping.method()[0].name());
                    }
                }
            }
        }
        return empty();
    }

    private static Optional<RequestMapping> isMappingPresent(Annotation annotation, Set<String> visited) {
        if (annotation.annotationType().equals(RequestMapping.class)) {
            return of((RequestMapping) annotation);
        }

        for (Annotation annotation1 : annotation.annotationType().getDeclaredAnnotations()) {
            if (visited.add(annotation1.toString())) {
                Optional<RequestMapping> optional = isMappingPresent(annotation1, visited);
                if (optional.isPresent()) {
                    return optional;
                }
            }
        }

        return empty();
    }
}