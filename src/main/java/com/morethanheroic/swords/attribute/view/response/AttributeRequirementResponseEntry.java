package com.morethanheroic.swords.attribute.view.response;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.morethanheroic.swords.attribute.enums.Attribute;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class AttributeRequirementResponseEntry {

    private final Attribute attribute;
    private final int value;

    public AttributeRequirementResponseEntry(Attribute attribute, int value) {
        this.attribute = attribute;
        this.value = value;
    }
}
