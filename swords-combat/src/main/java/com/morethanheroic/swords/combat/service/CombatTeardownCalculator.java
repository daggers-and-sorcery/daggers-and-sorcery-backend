package com.morethanheroic.swords.combat.service;

import java.util.ArrayList;
import java.util.List;

import com.morethanheroic.swords.combat.step.message.CombatMessageFactory;
import org.springframework.stereotype.Service;

import com.morethanheroic.swords.combat.domain.CombatContext;
import com.morethanheroic.swords.combat.domain.Winner;
import com.morethanheroic.swords.combat.entity.domain.MonsterCombatEntity;
import com.morethanheroic.swords.combat.step.domain.CombatStep;
import com.morethanheroic.swords.combat.step.domain.DefaultCombatStep;
import com.morethanheroic.swords.combat.repository.domain.CombatMapper;
import com.morethanheroic.swords.combat.service.calc.CombatTerminator;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CombatTeardownCalculator {

    private final CombatMessageFactory combatMessageFactory;
    private final CombatTerminator combatTerminator;
    private final CombatMapper combatMapper;

    public List<CombatStep> teardown(final CombatContext combatContext) {
        final List<CombatStep> combatSteps = new ArrayList<>();

        if (combatContext.getUser().getActualHealth() <= 0) {
            combatContext.setWinner(Winner.MONSTER);

            combatSteps.add(DefaultCombatStep.builder()
                    .message(combatMessageFactory.newMessage("monster_death", "COMBAT_MESSAGE_PLAYER_DEAD", combatContext.getOpponent().getName()))
                    .build());
        } else if (combatContext.getOpponent().getActualHealth() <= 0) {
            combatContext.setWinner(Winner.PLAYER);

            combatSteps.add(DefaultCombatStep.builder()
                    .message(combatMessageFactory.newMessage("monster_death", "COMBAT_MESSAGE_MONSTER_DEAD", combatContext.getOpponent().getName()))
                    .build());
        }

        if (combatContext.getWinner() != null) {
            combatSteps.addAll(combatTerminator.terminate(combatContext));

            combatMapper.removeCombat(combatContext.getCombatId());
        } else {
            final MonsterCombatEntity monsterCombatEntity = combatContext.getOpponent();

            combatMapper.updateCombat(combatContext.getCombatId(), monsterCombatEntity.getActualHealth(), monsterCombatEntity.getActualMana());
        }

        return combatSteps;
    }
}
