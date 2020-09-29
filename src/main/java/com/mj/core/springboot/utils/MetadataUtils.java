package com.mj.core.springboot.utils;

import com.mj.core.springboot.model.dto.MetadataDTO;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @Project ms-core-framework
 * @Author Montaser.Sobaih
 * @Date 1/24/19
 */

public abstract class MetadataUtils {

    private MetadataUtils() {
    }

    public static MetadataDTO getParameters(HttpServletRequest request) {
        Map<String, Object> parameters = new HashMap<>();

        try {
            Enumeration<String> paramNames = request.getParameterNames();
            while (paramNames.hasMoreElements()) {
                final String NAME = paramNames.nextElement();
                final String VALUE = request.getParameter(NAME);
                if (Objects.nonNull(VALUE)) {
                    parameters.put(NAME, VALUE);
                }
            }
        } catch (Exception ex) {
            //Ignored catch
        }

        return new MetadataDTO(parameters);
    }
}
