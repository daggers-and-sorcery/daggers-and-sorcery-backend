package com.morethanheroic.swords.spell.service;

import com.morethanheroic.swords.definition.service.XMLDefinitionLoader;
import com.morethanheroic.swords.spell.service.domain.RawSpellDefinition;
import com.morethanheroic.swords.spell.service.domain.SpellDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;

@Service
public class SpellDefinitionManager {

    private HashMap<Integer, SpellDefinition> spellDefinitionMap = new HashMap<>();

    private final XMLDefinitionLoader xmlDefinitionLoader;

    @Autowired
    public SpellDefinitionManager(XMLDefinitionLoader xmlDefinitionLoader) {
        this.xmlDefinitionLoader = xmlDefinitionLoader;
    }

    @PostConstruct
    public void init() throws Exception {
        List<RawSpellDefinition> rawSpellDefinitionList = xmlDefinitionLoader.loadDefinitions(RawSpellDefinition.class, "classpath:data/spell/definition/", "classpath:data/spell/schema.xsd");

        for (RawSpellDefinition rawSpellDefinition : rawSpellDefinitionList) {
            spellDefinitionMap.put(rawSpellDefinition.getId(), new SpellDefinition(rawSpellDefinition));
        }
    }

    public SpellDefinition getSpellDefinition(int spellId) {
        return spellDefinitionMap.get(spellId);
    }
}
