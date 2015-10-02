package com.morethanheroic.swords.spell.service;

import com.morethanheroic.swords.definition.service.XMLDefinitionLoader;
import com.morethanheroic.swords.item.service.domain.ItemDefinition;
import com.morethanheroic.swords.item.service.domain.RawItemDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;

@Service
public class SpellDefinitionManager {

    private HashMap<Integer, ItemDefinition> spellDefinitionMap = new HashMap<>();

    private final XMLDefinitionLoader xmlDefinitionLoader;

    @Autowired
    public SpellDefinitionManager(XMLDefinitionLoader xmlDefinitionLoader) {
        this.xmlDefinitionLoader = xmlDefinitionLoader;
    }

    @PostConstruct
    public void init() throws Exception {
        /*List<RawItemDefinition> rawSpellDefinitionList = xmlDefinitionLoader.loadDefinitions(RawItemDefinition.class, "classpath:data/spell/definition/", "classpath:data/spell/schema.xsd");

        for (RawSpellDefinition rawSpellDefinition : rawSpellDefinitionList) {
            spellDefinitionMap.put(rawSpellDefinition.getId(), new SpellDefinition(rawSpellDefinition));
        }*/
    }

    public ItemDefinition getItemDefinition(int itemId) {
        return spellDefinitionMap.get(itemId);
    }
}
