package com.morethanheroic.swords.statuseffect.service.definition.domain;

import com.morethanheroic.swords.effect.domain.EffectSettingDefinitionHolder;
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
    private List<EffectSettingDefinitionHolder> combatEffects;
}
