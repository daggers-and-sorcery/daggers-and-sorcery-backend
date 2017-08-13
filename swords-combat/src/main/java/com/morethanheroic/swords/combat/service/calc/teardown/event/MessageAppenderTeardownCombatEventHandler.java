package com.morethanheroic.swords.combat.service.calc.teardown.event;

import java.util.Collections;
import java.util.List;

import com.google.common.collect.Lists;
import com.morethanheroic.swords.combat.service.event.terminate.TeardownCombatEventHandler;
import com.morethanheroic.swords.combat.service.event.terminate.domain.TeardownCombatEventContext;
import com.morethanheroic.swords.combat.step.domain.CombatStep;
import com.morethanheroic.swords.combat.step.domain.DefaultCombatStep;
import com.morethanheroic.swords.combat.step.message.CombatMessageFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

/**
 * This {@link TeardownCombatEventHandler} adds the texts about the winner of the combat.
 */
@Order(0)
@Service
@RequiredArgsConstructor
public class MessageAppenderTeardownCombatEventHandler implements TeardownCombatEventHandler {

    private final CombatMessageFactory combatMessageFactory;

    @Override
    public List<CombatStep> handleEvent(TeardownCombatEventContext teardownCombatEventContext) {
        if (!teardownCombatEventContext.getUser().isAlive()) {
            return Lists.newArrayList(
                    DefaultCombatStep.builder()
                            .message(combatMessageFactory.newMessage("monster_death", "COMBAT_MESSAGE_PLAYER_DEAD", teardownCombatEventContext.getMonster().getName()))
                            .build()
            );
        } else if (!teardownCombatEventContext.getMonster().isAlive()) {
            Lists.newArrayList(
                    DefaultCombatStep.builder()
                            .message(combatMessageFactory.newMessage("monster_death", "COMBAT_MESSAGE_MONSTER_DEAD", teardownCombatEventContext.getMonster().getName()))
                            .build()
            );
        }

        return Collections.emptyList();
    }
}
