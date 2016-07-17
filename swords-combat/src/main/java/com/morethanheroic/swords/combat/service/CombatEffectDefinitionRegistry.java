package com.morethanheroic.swords.combat.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.morethanheroic.swords.combat.domain.effect.CombatEffectDefinition;

@Service
public class CombatEffectDefinitionRegistry {

    private Map<String, CombatEffectDefinition> combatEffectDefinitionMap;

    @Autowired
    public CombatEffectDefinitionRegistry(List<CombatEffectDefinition> combatEffectDefinitions) {
        final Map<String, CombatEffectDefinition> result = new HashMap<>();
        for (CombatEffectDefinition combatEffectDefinition : combatEffectDefinitions) {
            result.put(combatEffectDefinition.getId(), combatEffectDefinition);
        }
        combatEffectDefinitionMap = Collections.unmodifiableMap(result);
    }

    public CombatEffectDefinition getDefinition(String id) {
        return combatEffectDefinitionMap.get(id);
    }
}
