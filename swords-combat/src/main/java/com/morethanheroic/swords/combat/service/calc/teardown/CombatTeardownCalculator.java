package com.morethanheroic.swords.combat.service.calc.teardown;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.morethanheroic.swords.combat.domain.CombatContext;
import com.morethanheroic.swords.combat.domain.Winner;
import com.morethanheroic.swords.combat.service.event.terminate.TeardownCombatEventHandler;
import com.morethanheroic.swords.combat.service.event.terminate.domain.TeardownCombatEventContext;
import com.morethanheroic.swords.combat.step.domain.CombatStep;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CombatTeardownCalculator {

    private final List<TeardownCombatEventHandler> teardownCombatEventHandlers;

    public List<CombatStep> teardown(final CombatContext combatContext) {
        if (!combatContext.getUser().isAlive()) {
            combatContext.setWinner(Winner.MONSTER);
        } else if (!combatContext.getOpponent().isAlive()) {
            combatContext.setWinner(Winner.PLAYER);
        }

        return teardownCombatEventHandlers.stream()
                .map(teardownCombatEventHandler -> teardownCombatEventHandler.handleEvent(
                        TeardownCombatEventContext.builder()
                                .combatId(combatContext.getCombatId())
                                .user(combatContext.getUser())
                                .monster(combatContext.getOpponent())
                                .type(combatContext.getType())
                                .winner(combatContext.getWinner())
                                .build()
                        )
                )
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }
}
