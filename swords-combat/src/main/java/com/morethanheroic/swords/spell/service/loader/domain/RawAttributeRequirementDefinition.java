package com.morethanheroic.swords.spell.service.loader.domain;

import com.morethanheroic.swords.attribute.domain.Attribute;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public abstract class RawAttributeRequirementDefinition {

    private int amount;

    public int getAmount() {
        return amount;
    }

    public abstract Attribute getAttribute();
}
