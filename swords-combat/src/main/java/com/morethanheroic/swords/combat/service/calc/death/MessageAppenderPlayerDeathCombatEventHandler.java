package com.morethanheroic.swords.combat.service.calc.death;

import com.google.common.collect.Lists;
import com.morethanheroic.swords.combat.entity.domain.CombatEntity;
import com.morethanheroic.swords.combat.step.domain.CombatStep;
import com.morethanheroic.swords.combat.step.domain.DefaultCombatStep;
import com.morethanheroic.swords.combat.service.event.death.PlayerDeathCombatEventHandler;
import com.morethanheroic.swords.combat.step.message.CombatMessageFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Appends the proper player death texts.
 */
@Order(0)
@Service
@RequiredArgsConstructor
public class MessageAppenderPlayerDeathCombatEventHandler implements PlayerDeathCombatEventHandler {

    private final CombatMessageFactory combatMessageFactory;

    @Override
    public List<CombatStep> handleEvent(final CombatEntity deathEntity, final CombatEntity killerEntity) {
        return Lists.newArrayList(
                DefaultCombatStep.builder()
                        .message(combatMessageFactory.newMessage("monster_death", "COMBAT_MESSAGE_PLAYER_DEAD", killerEntity.getName()))
                        .build()
        );
    }
}
