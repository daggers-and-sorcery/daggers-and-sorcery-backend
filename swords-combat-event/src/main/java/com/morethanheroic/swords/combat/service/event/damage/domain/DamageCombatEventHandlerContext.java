package com.morethanheroic.swords.combat.service.event.damage.domain;

import com.morethanheroic.swords.combat.bonus.domain.CombatBonus;
import com.morethanheroic.swords.combat.domain.CombatType;
import com.morethanheroic.swords.combat.entity.domain.CombatEntity;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DamageCombatEventHandlerContext {

    private final CombatEntity attacker;
    private final CombatEntity defender;
    /**
     * This is the damage bonus that's provided by the before events.
     */
    private final CombatBonus damageBonus;
    private final DamageType damageType;
    private final int damageAlreadyDone;
}
