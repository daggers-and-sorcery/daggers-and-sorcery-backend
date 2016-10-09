package com.morethanheroic.swords.loot.service.cache;

import com.morethanheroic.swords.definition.cache.DefinitionCache;
import com.morethanheroic.swords.loot.domain.LootDefinition;
import com.morethanheroic.swords.loot.service.loader.LootDefinitionLoader;
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

@Slf4j
@Service
@RequiredArgsConstructor
public class LootDefinitionCache implements DefinitionCache<Integer, LootDefinition> {

    private final LootDefinitionLoader lootDefinitionLoader;

    private Map<Integer, LootDefinition> lootDefinitionMap = new HashMap<>();

    @PostConstruct
    public void init() throws IOException {
        final List<LootDefinition> lootDefinitions = lootDefinitionLoader.loadDefinitions();

        log.info("Loaded " + lootDefinitions.size() + " loot definitions.");

        for (LootDefinition lootDefinition : lootDefinitions) {
            lootDefinitionMap.put(lootDefinition.getId(), lootDefinition);
        }
    }

    @Override
    public LootDefinition getDefinition(Integer key) {
        return lootDefinitionMap.get(key);
    }

    @Override
    public int getSize() {
        return lootDefinitionMap.size();
    }

    @Override
    public List<LootDefinition> getDefinitions() {
        return Collections.unmodifiableList(new ArrayList<>(lootDefinitionMap.values()));
    }

    @Override
    public boolean isDefinitionExists(Integer key) {
        return lootDefinitionMap.containsKey(key);
    }
}
