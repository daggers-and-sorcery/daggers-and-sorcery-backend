package com.morethanheroic.swords.item.service.loader.domain;

import com.morethanheroic.swords.attribute.domain.SkillAttribute;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class SkillAttributeRequirementDefinition extends AttributeRequirementDefinition {

    private SkillAttribute attribute;

    public SkillAttribute getAttribute() {
        return attribute;
    }
}
