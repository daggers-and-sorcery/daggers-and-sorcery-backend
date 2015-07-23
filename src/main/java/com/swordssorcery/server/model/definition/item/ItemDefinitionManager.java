package com.swordssorcery.server.model.definition.item;

import com.swordssorcery.server.loader.definition.XMLDefinitionLoader;
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
        //TODO: rewrite!
        List<ItemDefinition> itemDefinitionList = xmlDefinitionLoader.loadDefinitions(ItemDefinitionList.class, "classpath:data/item/0.xml");

        for (ItemDefinition itemDefinition : itemDefinitionList) {
            itemDefinitionMap.put(itemDefinition.getId(), itemDefinition);
        }
    }

    public ItemDefinition getItemDefinition(int itemId) {
        return itemDefinitionMap.get(itemId);
    }
}
