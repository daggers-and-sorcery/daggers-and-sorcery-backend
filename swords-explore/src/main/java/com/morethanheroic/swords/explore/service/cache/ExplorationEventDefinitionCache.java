package com.morethanheroic.swords.explore.service.cache;

import com.google.common.collect.Lists;
import com.morethanheroic.swords.combat.service.calc.CombatCalculator;
import com.morethanheroic.swords.definition.cache.DefinitionCache;
import com.morethanheroic.swords.explore.domain.event.ExplorationEventDefinition;
import com.morethanheroic.swords.explore.domain.event.entry.impl.CombatExplorationEventEntry;
import com.morethanheroic.swords.explore.domain.event.entry.impl.TextExplorationEventEntry;
import com.morethanheroic.swords.monster.service.cache.MonsterDefinitionCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExplorationEventDefinitionCache implements DefinitionCache<Integer, ExplorationEventDefinition> {

    @Autowired
    private CombatCalculator combatCalculator;

    @Autowired
    private MonsterDefinitionCache monsterDefinitionCache;

    private List<ExplorationEventDefinition> explorationEventDefinitions = new ArrayList<>();

    @PostConstruct
    private void initialize() {
        explorationEventDefinitions.add(
                ExplorationEventDefinition.builder()
                        .explorationEventEntries(Lists.newArrayList(
                                TextExplorationEventEntry.builder()
                                        .content("asd")
                                        .build(),

                                CombatExplorationEventEntry.builder()
                                        .monsterDefinition(monsterDefinitionCache.getMonsterDefinition(1))
                                        .combatCalculator(combatCalculator)
                                        .build()
                                )
                        )
                        .build()
        );
    }

    @Override
    public ExplorationEventDefinition getDefinition(Integer key) {
        return explorationEventDefinitions.get(key);
    }
}
