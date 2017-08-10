package com.morethanheroic.swords.combat.service.calc.teardown.event;

import java.util.Collections;
import java.util.List;

import com.morethanheroic.swords.combat.entity.domain.MonsterCombatEntity;
import com.morethanheroic.swords.combat.repository.domain.CombatExperienceMapper;
import com.morethanheroic.swords.combat.repository.domain.CombatMapper;
import com.morethanheroic.swords.combat.service.event.terminate.TeardownCombatEventHandler;
import com.morethanheroic.swords.combat.service.event.terminate.domain.TeardownCombatEventContext;
import com.morethanheroic.swords.combat.step.domain.CombatStep;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

/**
 * This {@link TeardownCombatEventHandler} reset the state of the combat by either deleting it or resetting to the starter values.
 */
@Order(20)
@Service
@RequiredArgsConstructor
public class ResetCombatTeardownCombatEventHandler implements TeardownCombatEventHandler {

    private final CombatMapper combatMapper;
    private final CombatExperienceMapper combatExperienceMapper;

    @Override
    public List<CombatStep> handleEvent(final TeardownCombatEventContext teardownCombatEventContext) {
        if (teardownCombatEventContext.isCombatEnded()) {
            resetCombat(teardownCombatEventContext);
        } else {
            updateCombat(teardownCombatEventContext);
        }

        return Collections.emptyList();
    }


    private void resetCombat(final TeardownCombatEventContext teardownCombatEventContext) {
        if (teardownCombatEventContext.isQuestCombat()) {
            handleQuestCombat(teardownCombatEventContext);
        } else {
            handleExplorationCombat(teardownCombatEventContext);
        }
    }

    private void handleQuestCombat(final TeardownCombatEventContext teardownCombatEventContext) {
        if (!teardownCombatEventContext.isUserVictory()) {
            final MonsterCombatEntity monsterCombatEntity = teardownCombatEventContext.getMonster();

            combatExperienceMapper.removeAll(teardownCombatEventContext.getUser().getUserEntity().getId());

            combatMapper.updateCombat(teardownCombatEventContext.getCombatId(), monsterCombatEntity.getMaximumHealth(), monsterCombatEntity.getMaximumMana());
        }
    }

    private void handleExplorationCombat(final TeardownCombatEventContext teardownCombatEventContext) {
        combatMapper.removeCombat(teardownCombatEventContext.getCombatId());
    }

    private void updateCombat(final TeardownCombatEventContext teardownCombatEventContext) {
        final MonsterCombatEntity monsterCombatEntity = teardownCombatEventContext.getMonster();

        combatMapper.updateCombat(teardownCombatEventContext.getCombatId(), monsterCombatEntity.getActualHealth(), monsterCombatEntity.getActualMana());
    }
}
