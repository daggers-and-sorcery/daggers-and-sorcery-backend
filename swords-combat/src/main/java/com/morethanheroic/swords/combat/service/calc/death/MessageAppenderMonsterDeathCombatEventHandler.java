package com.morethanheroic.swords.combat.service.calc.death;

import com.google.common.collect.Lists;
import com.morethanheroic.swords.combat.domain.entity.CombatEntity;
import com.morethanheroic.swords.combat.domain.step.CombatStep;
import com.morethanheroic.swords.combat.domain.step.DefaultCombatStep;
import com.morethanheroic.swords.combat.service.event.death.MonsterDeathCombatEventHandler;
import com.morethanheroic.swords.combat.service.message.CombatMessageFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Appends the proper monster death texts
 */
@Order(0)
@Service
@RequiredArgsConstructor
public class MessageAppenderMonsterDeathCombatEventHandler implements MonsterDeathCombatEventHandler {

    private final CombatMessageFactory combatMessageFactory;

    @Override
    public List<CombatStep> handleEvent(final CombatEntity deathEntity, final CombatEntity killerEntity) {
        return Lists.newArrayList(
                DefaultCombatStep.builder()
                        .message(combatMessageFactory.newMessage("monster_death", "COMBAT_MESSAGE_MONSTER_DEAD", deathEntity.getName()))
                        .build()
        );
    }
}
