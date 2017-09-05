package com.morethanheroic.swords.statuseffect.service.definition.loader.domain;

import com.morethanheroic.swords.statuseffect.service.attribute.custom.domain.CustomModifier;
import com.morethanheroic.swords.statuseffect.service.definition.domain.StatusEffectModifier;
import lombok.Getter;
import lombok.ToString;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@Getter
@ToString
@XmlAccessorType(XmlAccessType.FIELD)
public class StatusEffectModifierDefinitionPlaceholder {

    private StatusEffectModifier modifier;
    private int amount;
    private int d2;
    private int d4;
    private int d6;
    private int d8;
    private int d10;

    @XmlElement(name = "effect-id")
    private CustomModifier effectId;
}
