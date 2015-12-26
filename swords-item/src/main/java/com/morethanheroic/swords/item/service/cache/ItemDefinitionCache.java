package com.morethanheroic.swords.item.service.cache;

import com.morethanheroic.swords.definition.cache.DefinitionCache;
import com.morethanheroic.swords.item.domain.ItemDefinition;
import com.morethanheroic.swords.item.service.loader.ItemDefinitionLoader;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Holds the loaded {@link ItemDefinition}s in a cached manner.
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ItemDefinitionCache implements DefinitionCache<Integer, ItemDefinition> {

    @NonNull
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

    public boolean isItemExists(int itemId) {
        return itemDefinitionMap.containsKey(itemId);
    }
}
