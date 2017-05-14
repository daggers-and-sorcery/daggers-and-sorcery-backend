package com.morethanheroic.swords.combat.service.calc.death;

import com.morethanheroic.swords.combat.domain.CombatContext;
import com.morethanheroic.swords.combat.domain.Winner;
import com.morethanheroic.swords.combat.domain.entity.CombatEntity;
import com.morethanheroic.swords.combat.domain.entity.MonsterCombatEntity;
import com.morethanheroic.swords.combat.domain.step.CombatStep;
import com.morethanheroic.swords.combat.service.event.MonsterDeathCombatEvent;
import com.morethanheroic.swords.combat.service.event.PlayerDeathCombatEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Handle death in combat.
 */
@Service
@RequiredArgsConstructor
public class DeathCalculator {

    private final List<MonsterDeathCombatEvent> monsterDeathCombatEvents;
    private final List<PlayerDeathCombatEvent> playerDeathCombatEvents;

    public List<CombatStep> handleDeath(final CombatEntity killer, final CombatEntity killed, final CombatContext combatContext) {
        if (killer instanceof MonsterCombatEntity) {
            combatContext.setWinner(Winner.MONSTER);

            return playerDeathCombatEvents.stream()
                    .flatMap(playerDeathCombatEvent -> playerDeathCombatEvent.handleEvent(killed, killer).stream())
                    .collect(Collectors.toList());
        } else {
            combatContext.setWinner(Winner.PLAYER);

            return monsterDeathCombatEvents.stream()
                    .flatMap(monsterDeathCombatEvent -> monsterDeathCombatEvent.handleEvent(killed, killer).stream())
                    .collect(Collectors.toList());
        }
    }
}
