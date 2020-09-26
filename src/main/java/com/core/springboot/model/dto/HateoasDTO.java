package com.core.springboot.model.dto;

/**
 * @Project ms-core-framework
 * @Author Montaser.Sobaih
 * @Date 1/23/19
 */

public final class HateoasDTO {

    private String href;

    private String method;

    private String rel;

    public HateoasDTO() {
    }

    public HateoasDTO(String href, String method) {
        this.href = href;
        this.method = method;
    }

    public HateoasDTO(String href, String method, String rel) {
        this.href = href;
        this.method = method;
        this.rel = rel;
    }

    public String getHref() {
        return href;
    }

    public HateoasDTO setHref(String href) {
        this.href = href;
        return this;
    }

    public String getMethod() {
        return method;
    }

    public HateoasDTO setMethod(String method) {
        this.method = method;
        return this;
    }

    public String getRel() {
        return rel;
    }

    public HateoasDTO setRel(String rel) {
        this.rel = rel;
        return this;
    }
}
