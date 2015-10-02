package com.morethanheroic.swords.item.service;

import com.morethanheroic.swords.definition.service.XMLDefinitionLoader;
import com.morethanheroic.swords.item.service.domain.ItemDefinition;
import com.morethanheroic.swords.item.service.domain.RawItemDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;

@Service
public class ItemDefinitionManager {

    private HashMap<Integer, ItemDefinition> itemDefinitionMap = new HashMap<>();

    private final XMLDefinitionLoader xmlDefinitionLoader;

    @Autowired
    public ItemDefinitionManager(XMLDefinitionLoader xmlDefinitionLoader) {
        this.xmlDefinitionLoader = xmlDefinitionLoader;
    }

    @PostConstruct
    public void init() throws Exception {
        List<RawItemDefinition> rawItemDefinitionList = xmlDefinitionLoader.loadDefinitions(RawItemDefinition.class, "classpath:data/item/definition/", "classpath:data/item/schema.xsd");

        for (RawItemDefinition rawItemDefinition : rawItemDefinitionList) {
            if(rawItemDefinition.getEffectList() != null) {
                System.out.println("EFFECT: "+rawItemDefinition.getEffectList().get(0).getTarget());
                System.out.println("EFFECT: "+rawItemDefinition.getEffectList().get(0).getEffectSettings().size());
            }

            itemDefinitionMap.put(rawItemDefinition.getId(), new ItemDefinition(rawItemDefinition));
        }
    }

    public ItemDefinition getItemDefinition(int itemId) {
        return itemDefinitionMap.get(itemId);
    }
}
