package com.swordssorcery.server.game.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;

@Service
public class ItemDefinitionManager {

    @Autowired
    private ItemDefinitionLoader itemDefinitionLoader;

    private HashMap<Integer, ItemDefinition> itemDefinitionMap = new HashMap<>();

    @PostConstruct
    public void init() throws Exception {
        List<ItemDefinition> itemDefinitionList = itemDefinitionLoader.loadItemDefinitionsAsList();

        for (ItemDefinition itemDefinition : itemDefinitionList) {
            itemDefinitionMap.put(itemDefinition.getId(), itemDefinition);
        }
    }

    public ItemDefinition getItemDefinition(int itemId) {
        return itemDefinitionMap.get(itemId);
    }
}
