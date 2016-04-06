package com.morethanheroic.swords.explore.service.cache;

import com.google.common.collect.Lists;
import com.morethanheroic.swords.combat.service.CombatManager;
import com.morethanheroic.swords.definition.cache.DefinitionCache;
import com.morethanheroic.swords.explore.domain.event.ExplorationEventDefinition;
import com.morethanheroic.swords.explore.domain.event.entry.impl.CombatExplorationEventEntry;
import com.morethanheroic.swords.explore.domain.event.entry.impl.TextExplorationEventEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ExplorationEventDefinitionCache implements DefinitionCache<Integer, ExplorationEventDefinition> {

    @Autowired
    private CombatManager combatManager;

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
                                        .monster(1)
                                        .combatManager(combatManager)
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
