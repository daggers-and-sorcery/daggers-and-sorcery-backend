package com.morethanheroic.swords.statuseffect.service.definition.loader.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import com.morethanheroic.swords.statuseffect.service.definition.domain.StatusEffectModifier;

import lombok.Getter;

@Getter
@XmlAccessorType(XmlAccessType.FIELD)
public class RawStatusEffectBasicModifierDefinition implements RawStatusEffectModifierDefinition {

    private StatusEffectModifier modifier;
    private int amount;
    private int d2;
    private int d4;
    private int d6;
    private int d8;
    private int d10;
}
