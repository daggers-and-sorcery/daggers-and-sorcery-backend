package com.morethanheroic.swords.item.service.domain;

import com.morethanheroic.swords.attribute.enums.Attribute;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public abstract class AttributeModifierDefinition {

    private int amount;

    public int getAmount() {
        return amount;
    }

    public abstract Attribute getAttribute();
}
