package com.morethanheroic.swords.witchhuntersguild.service.definition.cache;

import com.google.common.collect.ImmutableMap;
import com.morethanheroic.swords.definition.cache.DefinitionCache;
import com.morethanheroic.swords.witchhuntersguild.domain.definition.WitchhuntersGuildJobDefinition;
import com.morethanheroic.swords.witchhuntersguild.service.definition.loader.WitchhuntersGuildJobDefinitionLoader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.collectingAndThen;

/**
 * A {@link DefinitionCache} implementation for {@link WitchhuntersGuildJobDefinition}.
 */
@Slf4j
@Service
public class WitchhuntersGuildJobDefinitionCache implements DefinitionCache<Integer, WitchhuntersGuildJobDefinition> {

    private final Map<Integer, WitchhuntersGuildJobDefinition> witchhuntersGuildJobDefinitionMap;

    public WitchhuntersGuildJobDefinitionCache(final WitchhuntersGuildJobDefinitionLoader witchhuntersGuildJobDefinitionLoader) {
        witchhuntersGuildJobDefinitionMap = witchhuntersGuildJobDefinitionLoader.loadDefinitions().stream()
                .collect(collectingAndThen(Collectors.toMap(WitchhuntersGuildJobDefinition::getId, Function.identity()), ImmutableMap::copyOf));

        log.info("Loaded " + witchhuntersGuildJobDefinitionMap.size() + " witchhunter's guild job definitions.");
    }

    @Override
    public WitchhuntersGuildJobDefinition getDefinition(final Integer witchhuntersGuildJobDefinitionId) {
        return witchhuntersGuildJobDefinitionMap.get(witchhuntersGuildJobDefinitionId);
    }

    @Override
    public int getSize() {
        return witchhuntersGuildJobDefinitionMap.size();
    }

    @Override
    public List<WitchhuntersGuildJobDefinition> getDefinitions() {
        return Collections.unmodifiableList(new ArrayList<>(witchhuntersGuildJobDefinitionMap.values()));
    }

    @Override
    public boolean isDefinitionExists(final Integer witchhuntersGuildJobDefinitionId) {
        return witchhuntersGuildJobDefinitionMap.containsKey(witchhuntersGuildJobDefinitionId);
    }
}
