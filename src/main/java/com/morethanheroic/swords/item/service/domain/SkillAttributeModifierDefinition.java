package com.morethanheroic.swords.item.service.domain;

import com.morethanheroic.swords.attribute.domain.SkillAttribute;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class SkillAttributeModifierDefinition extends AttributeModifierDefinition {

    private SkillAttribute attribute;

    public SkillAttribute getAttribute() {
        return attribute;
    }
}