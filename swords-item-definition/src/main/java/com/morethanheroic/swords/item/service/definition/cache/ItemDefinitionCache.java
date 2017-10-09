package com.morethanheroic.swords.item.service.definition.cache;

import com.morethanheroic.definition.cache.DefinitionCache;
import com.morethanheroic.swords.item.domain.ItemDefinition;
import com.morethanheroic.swords.item.service.definition.loader.ItemDefinitionLoader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Holds the loaded {@link ItemDefinition}s.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ItemDefinitionCache implements DefinitionCache<Integer, ItemDefinition> {

    private final ItemDefinitionLoader itemDefinitionLoader;

    private Map<Integer, ItemDefinition> itemDefinitionMap = new HashMap<>();

    @PostConstruct
    public void init() throws IOException {
        final List<ItemDefinition> itemDefinitions = itemDefinitionLoader.loadDefinitions();

        log.info("Loaded " + itemDefinitions.size() + " item definitions.");

        for (ItemDefinition itemDefinition : itemDefinitions) {
            itemDefinitionMap.put(itemDefinition.getId(), itemDefinition);
        }
    }

    @Override
    public ItemDefinition getDefinition(Integer itemId) {
        return itemDefinitionMap.get(itemId);
    }

    @Override
    public List<ItemDefinition> getDefinitions() {
        return Collections.unmodifiableList(new ArrayList<>(itemDefinitionMap.values()));
    }

    @Override
    public int getSize() {
        return itemDefinitionMap.size();
    }

    @Override
    public boolean isDefinitionExists(Integer itemId) {
        return itemDefinitionMap.containsKey(itemId);
    }
}
