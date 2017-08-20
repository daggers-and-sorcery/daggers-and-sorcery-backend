package com.morethanheroic.swords.statuseffect.service.definition.domain.modifier;

import com.morethanheroic.swords.statuseffect.service.attribute.custom.domain.CustomModifier;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class StatusEffectCustomModifierDefinition implements StatusEffectModifierDefinition {

    private final CustomModifier effectId;
}
