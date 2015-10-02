package com.morethanheroic.swords.item.service;

import com.morethanheroic.swords.combat.domain.CombatEffect;
import com.morethanheroic.swords.definition.service.XMLDefinitionLoader;
import com.morethanheroic.swords.effect.service.EffectDefinitionBuilder;
import com.morethanheroic.swords.item.domain.ItemDefinition;
import com.morethanheroic.swords.item.service.domain.ItemEffect;
import com.morethanheroic.swords.item.service.domain.RawItemDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class ItemDefinitionManager {

    private HashMap<Integer, ItemDefinition> itemDefinitionMap = new HashMap<>();

    private final XMLDefinitionLoader xmlDefinitionLoader;
    private final EffectDefinitionBuilder effectDefinitionBuilder;

    @Autowired
    public ItemDefinitionManager(XMLDefinitionLoader xmlDefinitionLoader,EffectDefinitionBuilder effectDefinitionBuilder) {
        this.xmlDefinitionLoader = xmlDefinitionLoader;
        this.effectDefinitionBuilder = effectDefinitionBuilder;
    }

    @PostConstruct
    public void init() throws Exception {
        List<RawItemDefinition> rawItemDefinitionList = xmlDefinitionLoader.loadDefinitions(RawItemDefinition.class, "classpath:data/item/definition/", "classpath:data/item/schema.xsd");

        for (RawItemDefinition rawItemDefinition : rawItemDefinitionList) {
            ArrayList<CombatEffect> effects = new ArrayList<>();

            if(rawItemDefinition.getEffectList() != null) {
                for (ItemEffect effect : rawItemDefinition.getEffectList()) {
                    effects.add(effectDefinitionBuilder.build(effect));
                }
            }

            itemDefinitionMap.put(rawItemDefinition.getId(), new ItemDefinition(rawItemDefinition, effects));
        }
    }

    public ItemDefinition getItemDefinition(int itemId) {
        return itemDefinitionMap.get(itemId);
    }
}
