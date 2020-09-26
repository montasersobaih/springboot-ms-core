package com.core.springboot.model.dto;

import java.util.Collection;

/**
 * @Project ms-core-framework
 * @Author Montaser.Sobaih
 * @Date 1/23/19
 */

public abstract class GeneralDTO {

    private MetadataDTO metadata;

    private Collection<HateoasDTO> links;

    protected GeneralDTO() {
    }

    protected GeneralDTO(MetadataDTO metadata) {
        this.metadata = metadata;
    }

    protected GeneralDTO(Collection<HateoasDTO> links) {
        this.links = links;
    }

    protected GeneralDTO(MetadataDTO metadata, Collection<HateoasDTO> links) {
        this.metadata = metadata;
        this.links = links;
    }

    public MetadataDTO getMetadata() {
        return metadata;
    }

    public Collection<HateoasDTO> getLinks() {
        return links;
    }
}