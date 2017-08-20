package com.morethanheroic.swords.statuseffect.service.definition.domain;

import com.morethanheroic.swords.statuseffect.service.definition.domain.modifier.StatusEffectModifierDefinition;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

/**
 * Contains the static data of a status effect.
 */
@Builder
@Getter
public class StatusEffectDefinition {

    private final int id;
    private final String name;
    private final String description;
    private final List<StatusEffectModifierDefinition> modifiers;
}
