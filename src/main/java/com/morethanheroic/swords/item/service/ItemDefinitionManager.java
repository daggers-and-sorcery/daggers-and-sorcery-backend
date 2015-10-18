package com.morethanheroic.swords.item.service;

import com.morethanheroic.swords.definition.service.XMLDefinitionLoader;
import com.morethanheroic.swords.item.domain.ItemDefinition;
import com.morethanheroic.swords.item.service.loader.domain.RawItemDefinition;
import com.morethanheroic.swords.item.service.transformer.ItemDefinitionTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;

@Service
public class ItemDefinitionManager {

    private HashMap<Integer, ItemDefinition> itemDefinitionMap = new HashMap<>();

    private final XMLDefinitionLoader xmlDefinitionLoader;
    private final ItemDefinitionTransformer itemDefinitionTransformer;

    @Autowired
    public ItemDefinitionManager(XMLDefinitionLoader xmlDefinitionLoader, ItemDefinitionTransformer itemDefinitionTransformer) {
        this.xmlDefinitionLoader = xmlDefinitionLoader;
        this.itemDefinitionTransformer = itemDefinitionTransformer;
    }

    @PostConstruct
    public void init() throws Exception {
        List<RawItemDefinition> rawItemDefinitionList = xmlDefinitionLoader.loadDefinitions(RawItemDefinition.class, "classpath:data/item/definition/", "classpath:data/item/schema.xsd");

        for (RawItemDefinition rawItemDefinition : rawItemDefinitionList) {
            itemDefinitionMap.put(rawItemDefinition.getId(), itemDefinitionTransformer.transform(rawItemDefinition));
        }
    }

    public ItemDefinition getItemDefinition(int itemId) {
        return itemDefinitionMap.get(itemId);
    }
}
