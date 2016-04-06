package com.morethanheroic.swords.spell.service.loader.domain;

import com.morethanheroic.swords.attribute.domain.SkillAttribute;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class RawSkillAttributeRequirementDefinition extends RawAttributeRequirementDefinition {

    private SkillAttribute attribute;

    public SkillAttribute getAttribute() {
        return attribute;
    }
}
