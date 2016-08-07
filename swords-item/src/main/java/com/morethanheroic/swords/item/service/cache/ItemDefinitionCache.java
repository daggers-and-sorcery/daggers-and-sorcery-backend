package com.morethanheroic.swords.item.service.cache;

import com.morethanheroic.swords.definition.cache.DefinitionCache;
import com.morethanheroic.swords.item.domain.ItemDefinition;
import com.morethanheroic.swords.item.service.loader.ItemDefinitionLoader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.*;

/**
 * Holds the loaded {@link ItemDefinition}s in a cached manner.
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
