package com.morethanheroic.swords.explore.domain.event.entry.impl;

import com.morethanheroic.swords.combat.domain.CombatResult;
import com.morethanheroic.swords.combat.service.calc.CombatCalculator;
import com.morethanheroic.swords.explore.domain.context.ExplorationContext;
import com.morethanheroic.swords.explore.domain.event.entry.ExplorationEventEntry;
import com.morethanheroic.swords.explore.domain.event.result.ExplorationEventEntryResult;
import com.morethanheroic.swords.explore.domain.event.result.impl.CombatExplorationEventEntryResult;
import com.morethanheroic.swords.monster.domain.MonsterDefinition;
import lombok.Builder;

@Builder
public class CombatExplorationEventEntry implements ExplorationEventEntry {

    private final MonsterDefinition monsterDefinition;
    private final CombatCalculator combatCalculator;

    @Override
    public ExplorationEventEntryResult handleExploration(ExplorationContext explorationContext) {
        CombatResult combatResult = combatCalculator.doFight(explorationContext.getUserEntity(), monsterDefinition);

        return CombatExplorationEventEntryResult.builder()
                .combatMessages(combatResult.getCombatMessages())
                .build();
    }
}
