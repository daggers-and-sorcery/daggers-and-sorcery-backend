package com.morethanheroic.swords.statuseffect.service.definition.loader.domain;

import com.morethanheroic.swords.statuseffect.service.attribute.custom.domain.CustomModifier;
import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
public class RawStatusEffectCustomModifierDefinition implements RawStatusEffectModifierDefinition {

    @XmlElement(name = "effect-id")
    private CustomModifier effectId;
}
