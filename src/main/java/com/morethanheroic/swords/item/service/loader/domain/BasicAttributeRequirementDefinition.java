package com.morethanheroic.swords.item.service.loader.domain;

import com.morethanheroic.swords.attribute.domain.BasicAttribute;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class BasicAttributeRequirementDefinition extends AttributeRequirementDefinition {

    private BasicAttribute attribute;

    public BasicAttribute getAttribute() {
        return attribute;
    }
}
