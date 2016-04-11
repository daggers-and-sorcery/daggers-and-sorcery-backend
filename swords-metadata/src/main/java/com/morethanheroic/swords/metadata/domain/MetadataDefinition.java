package com.morethanheroic.swords.metadata.domain;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
public class MetadataDefinition {

    @Getter
    private int id;

    @Getter
    private String name;

    private List<MetadataValueDefinition> values;

    public MetadataValueDefinition getValueDefinition(int valueId) {
        return values.get(valueId);
    }

    public MetadataValueDefinition getValueDefinition(String valueName) {
        for (MetadataValueDefinition metadataValueDefinition : values) {
            if (metadataValueDefinition.getValue().equals(valueName)) {
                return metadataValueDefinition;
            }
        }

        throw new IllegalArgumentException("Unknown value definition: " + valueName + " for: " + name);
    }
}
