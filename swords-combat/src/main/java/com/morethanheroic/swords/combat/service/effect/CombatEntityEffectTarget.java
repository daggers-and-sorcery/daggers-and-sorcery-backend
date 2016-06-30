package com.morethanheroic.swords.combat.service.effect;

import com.morethanheroic.swords.combat.domain.entity.CombatEntity;
import com.morethanheroic.swords.effect.domain.EffectTarget;

import lombok.RequiredArgsConstructor;

/**
 * Wrapping a {@link com.morethanheroic.swords.combat.domain.entity.CombatEntity} into an {@link com.morethanheroic.swords.effect.domain.EffectTarget}.
 */
@RequiredArgsConstructor
public class CombatEntityEffectTarget implements EffectTarget{

    private final CombatEntity combatEntity;
}
