package com.morethanheroic.swords.explore.service.cache;

import com.morethanheroic.swords.combat.service.calc.CombatCalculator;
import com.morethanheroic.swords.definition.cache.DefinitionCache;
import com.morethanheroic.swords.explore.service.event.ExplorationEventDefinition;
import com.morethanheroic.swords.monster.service.cache.MonsterDefinitionCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ExplorationEventDefinitionCache implements DefinitionCache<Integer, ExplorationEventDefinition> {

    @Autowired
    private CombatCalculator combatCalculator;

    @Autowired
    private MonsterDefinitionCache monsterDefinitionCache;

    @Autowired
    private List<ExplorationEventDefinition> explorationEventDefinitionList;

    private Map<Integer, ExplorationEventDefinition> explorationEventDefinitions = new HashMap<>();

    @PostConstruct
    private void initialize() {
        for (ExplorationEventDefinition explorationEventDefinition : explorationEventDefinitionList) {
            explorationEventDefinitions.put(explorationEventDefinition.getId(), explorationEventDefinition);
        }
    }

    @Override
    public ExplorationEventDefinition getDefinition(Integer key) {
        return explorationEventDefinitions.get(key);
    }

    public int size() {
        return explorationEventDefinitions.size();
    }
}
