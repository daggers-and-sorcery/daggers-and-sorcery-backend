package com.morethanheroic.swords.metadata.domain;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class MetadataValueDefinition {

    private int id;
    private String value;
}
