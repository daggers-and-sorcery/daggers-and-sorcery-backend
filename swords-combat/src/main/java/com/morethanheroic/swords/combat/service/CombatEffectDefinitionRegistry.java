package com.morethanheroic.swords.combat.service;

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
        for(CombatEffectDefinition combatEffectDefinition : combatEffectDefinitions) {
            combatEffectDefinitionMap.put(combatEffectDefinition.getId(), combatEffectDefinition);
        }
    }

    public CombatEffectDefinition getDefinition(String id) {
        return combatEffectDefinitionMap.get(id);
    }
}
