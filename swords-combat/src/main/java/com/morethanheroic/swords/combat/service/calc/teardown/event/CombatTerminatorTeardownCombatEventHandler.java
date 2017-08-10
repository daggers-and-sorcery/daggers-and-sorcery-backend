package com.morethanheroic.swords.combat.service.calc.teardown.event;

import java.util.Collections;
import java.util.List;

import com.google.common.collect.Lists;
import com.morethanheroic.swords.combat.domain.Winner;
import com.morethanheroic.swords.combat.service.calc.terminate.CombatTerminator;
import com.morethanheroic.swords.combat.service.calc.terminate.domain.CombatTerminationContext;
import com.morethanheroic.swords.combat.service.event.terminate.TeardownCombatEventHandler;
import com.morethanheroic.swords.combat.service.event.terminate.domain.TeardownCombatEventContext;
import com.morethanheroic.swords.combat.step.domain.CombatStep;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

/**
 * This {@link TeardownCombatEventHandler} terminate the the combat.
 */
@Order(10)
@Service
@RequiredArgsConstructor
public class CombatTerminatorTeardownCombatEventHandler implements TeardownCombatEventHandler {

    private final CombatTerminator combatTerminator;

    @Override
    public List<CombatStep> handleEvent(final TeardownCombatEventContext teardownCombatEventContext) {
        if (teardownCombatEventContext.isCombatEnded()) {
            return Lists.newArrayList(
                    combatTerminator.terminate(
                            CombatTerminationContext.builder()
                                    .userVictory(teardownCombatEventContext.isUserVictory())
                                    .user(teardownCombatEventContext.getUser())
                                    .opponent(teardownCombatEventContext.getMonster())
                                    .build()
                    )
            );
        }

        return Collections.emptyList();
    }
}
