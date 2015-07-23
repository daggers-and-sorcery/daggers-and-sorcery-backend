package com.swordssorcery.server.game.item;

import com.swordssorcery.server.loader.definition.XMLDefinitionLoader;
import com.swordssorcery.server.model.definition.item.ItemDefinition;
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
        List<ItemDefinition> itemDefinitionList = xmlDefinitionLoader.loadDefinitions(ItemDefinition.class, "classpath:data/item/definition/", "classpath:data/item/schema.xsd");

        for (ItemDefinition itemDefinition : itemDefinitionList) {
            itemDefinitionMap.put(itemDefinition.getId(), itemDefinition);
        }
    }

    public ItemDefinition getItemDefinition(int itemId) {
        return itemDefinitionMap.get(itemId);
    }
}
