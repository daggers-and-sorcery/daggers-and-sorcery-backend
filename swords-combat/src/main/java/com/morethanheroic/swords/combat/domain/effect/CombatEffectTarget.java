package com.morethanheroic.swords.combat.domain.effect;

import com.morethanheroic.swords.combat.domain.entity.CombatEntity;
import com.morethanheroic.swords.combat.domain.entity.UserCombatEntity;
import com.morethanheroic.swords.effect.domain.target.EffectTarget;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Wrapping a {@link com.morethanheroic.swords.combat.domain.entity.CombatEntity} into an {@link EffectTarget}.
 */
@Getter
@RequiredArgsConstructor
public class CombatEffectTarget implements EffectTarget {

    private final CombatEntity combatEntity;

    @Override
    public boolean isUser() {
        return combatEntity instanceof UserCombatEntity;
    }
}
