package com.morethanheroic.swords.statuseffect.service.definition.cache;

import com.google.common.collect.ImmutableMap;
import com.morethanheroic.swords.definition.cache.DefinitionCache;
import com.morethanheroic.swords.statuseffect.service.definition.domain.StatusEffectDefinition;
import com.morethanheroic.swords.statuseffect.service.definition.loader.StatusEffectDefinitionLoader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.collectingAndThen;

/**
 * A {@link DefinitionCache} implementation for {@link StatusEffectDefinition}.
 */
@Slf4j
@Service
public class StatusEffectDefinitionCache implements DefinitionCache<Integer, StatusEffectDefinition> {

    private Map<Integer, StatusEffectDefinition> statusEffectDefinitionMap = new HashMap<>();

    public StatusEffectDefinitionCache(final StatusEffectDefinitionLoader statusEffectDefinitionLoader) {
        statusEffectDefinitionMap = statusEffectDefinitionLoader.loadDefinitions().stream()
                .collect(collectingAndThen(Collectors.toMap(StatusEffectDefinition::getId, Function.identity()), ImmutableMap::copyOf));

        log.info("Loaded " + statusEffectDefinitionMap.size() + " status effect definitions.");
    }

    @Override
    public StatusEffectDefinition getDefinition(final Integer statusEffectId) {
        return statusEffectDefinitionMap.get(statusEffectId);
    }

    @Override
    public int getSize() {
        return statusEffectDefinitionMap.size();
    }

    @Override
    public List<StatusEffectDefinition> getDefinitions() {
        return Collections.unmodifiableList(new ArrayList<>(statusEffectDefinitionMap.values()));
    }

    @Override
    public boolean isDefinitionExists(final Integer statusEffectId) {
        return statusEffectDefinitionMap.containsKey(statusEffectId);
    }
}
