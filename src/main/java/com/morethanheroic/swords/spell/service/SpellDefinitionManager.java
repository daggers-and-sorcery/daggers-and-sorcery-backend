package com.morethanheroic.swords.spell.service;

import com.morethanheroic.swords.combat.domain.CombatEffect;
import com.morethanheroic.swords.definition.service.XMLDefinitionLoader;
import com.morethanheroic.swords.effect.service.EffectDefinitionBuilder;
import com.morethanheroic.swords.spell.service.domain.RawSpellDefinition;
import com.morethanheroic.swords.spell.service.domain.SpellDefinition;
import com.morethanheroic.swords.spell.service.domain.SpellEffect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class SpellDefinitionManager {

    private HashMap<Integer, SpellDefinition> spellDefinitionMap = new HashMap<>();

    private final XMLDefinitionLoader xmlDefinitionLoader;
    private final EffectDefinitionBuilder effectDefinitionBuilder;

    @Autowired
    public SpellDefinitionManager(XMLDefinitionLoader xmlDefinitionLoader, EffectDefinitionBuilder effectDefinitionBuilder) {
        this.xmlDefinitionLoader = xmlDefinitionLoader;
        this.effectDefinitionBuilder = effectDefinitionBuilder;
    }

    @PostConstruct
    public void init() throws Exception {
        List<RawSpellDefinition> rawSpellDefinitionList = xmlDefinitionLoader.loadDefinitions(RawSpellDefinition.class, "classpath:data/spell/definition/", "classpath:data/spell/schema.xsd");

        for (RawSpellDefinition rawSpellDefinition : rawSpellDefinitionList) {
            ArrayList<CombatEffect> effects = new ArrayList<>();

            if(rawSpellDefinition.getEffectList() != null) {
                for (SpellEffect effect : rawSpellDefinition.getEffectList()) {
                    effects.add(effectDefinitionBuilder.build(effect));
                }
            }

            spellDefinitionMap.put(rawSpellDefinition.getId(), new SpellDefinition(rawSpellDefinition, effects));
        }
    }

    public SpellDefinition getSpellDefinition(int spellId) {
        return spellDefinitionMap.get(spellId);
    }
}
