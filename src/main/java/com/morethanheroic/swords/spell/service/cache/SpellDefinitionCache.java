package com.morethanheroic.swords.spell.service.cache;

import com.morethanheroic.swords.spell.domain.SpellDefinition;
import com.morethanheroic.swords.spell.service.loader.SpellDefinitionLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;

@Service
public class SpellDefinitionCache {

    @Autowired
    private SpellDefinitionLoader spellDefinitionLoader;

    private HashMap<Integer, SpellDefinition> spellDefinitionMap = new HashMap<>();

    @PostConstruct
    @SuppressWarnings("unchecked")
    public void init() throws Exception {
        List<SpellDefinition> spellDefinitionList = spellDefinitionLoader.loadSpellDefinitions();

        for (SpellDefinition spellDefinition : spellDefinitionList) {
            spellDefinitionMap.put(spellDefinition.getId(), spellDefinition);
        }
    }

    public SpellDefinition getSpellDefinition(int spellId) {
        return spellDefinitionMap.get(spellId);
    }
}
