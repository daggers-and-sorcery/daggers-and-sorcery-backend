package com.morethanheroic.swords.item.service.loader.domain;

import com.morethanheroic.swords.attribute.domain.GeneralAttribute;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class GeneralAttributeRequirementDefinition extends AttributeRequirementDefinition {

    private GeneralAttribute attribute;

    public GeneralAttribute getAttribute() {
        return attribute;
    }
}
