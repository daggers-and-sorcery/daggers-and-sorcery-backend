package com.morethanheroic.swords.attribute.view.response;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.morethanheroic.swords.attribute.domain.Attribute;
import com.morethanheroic.swords.item.domain.ItemRequirement;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class AttributeRequirementResponseEntry {

    private final ItemRequirement attribute;
    private final int value;

    public AttributeRequirementResponseEntry(ItemRequirement attribute, int value) {
        this.attribute = attribute;
        this.value = value;
    }
}
