package com.morethanheroic.swords.item.service.domain;

import com.morethanheroic.swords.attribute.domain.BasicAttribute;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class BasicAttributeModifierDefinition extends AttributeModifierDefinition {

    private BasicAttribute attribute;

    public BasicAttribute getAttribute() {
        return attribute;
    }
}
