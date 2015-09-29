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

    @Autowired
    private XMLDefinitionLoader xmlDefinitionLoader;

    private HashMap<Integer, ItemDefinition> itemDefinitionMap = new HashMap<>();

    @PostConstruct
    public void init() throws Exception {
        List<RawItemDefinition> rawItemDefinitionList = xmlDefinitionLoader.loadDefinitions(RawItemDefinition.class, "classpath:data/item/definition/", "classpath:data/item/schema.xsd");

        for (RawItemDefinition rawItemDefinition : rawItemDefinitionList) {
            itemDefinitionMap.put(rawItemDefinition.getId(), new ItemDefinition(rawItemDefinition));
        }
    }

    public ItemDefinition getItemDefinition(int itemId) {
        return itemDefinitionMap.get(itemId);
    }
}
