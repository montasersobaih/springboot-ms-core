package com.core.springboot.model.dto;

import com.core.springboot.model.Pagination;

import java.util.Map;

/**
 * @Project ms-core-framework
 * @Author Montaser.Sobaih
 * @Date 1/23/19
 */

public final class MetadataDTO {

    private Map<String, Object> queryParameters;

    private Pagination pagination;

    public MetadataDTO() {
    }

    public MetadataDTO(Map<String, Object> queryParameters) {
        this.queryParameters = queryParameters;
    }

    public MetadataDTO(Pagination pagination) {
        this.pagination = pagination;
    }

    public MetadataDTO(Map<String, Object> queryParameters, Pagination pagination) {
        this.queryParameters = queryParameters;
        this.pagination = pagination;
    }

    public Map<String, Object> getQueryParameters() {
        return queryParameters;
    }

    public MetadataDTO setQueryParameters(Map<String, Object> queryParameters) {
        this.queryParameters = queryParameters;
        return this;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public MetadataDTO setPagination(Pagination pagination) {
        this.pagination = pagination;
        return this;
    }
}