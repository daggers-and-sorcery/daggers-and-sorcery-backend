package com.morethanheroic.swords.item.service.domain;

import com.morethanheroic.swords.attribute.domain.GeneralAttribute;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class GeneralAttributeModifierDefinition extends AttributeModifierDefinition {

    private GeneralAttribute attribute;

    public GeneralAttribute getAttribute() {
        return attribute;
    }
}
