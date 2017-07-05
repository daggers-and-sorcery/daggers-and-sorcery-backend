package com.morethanheroic.swords.monster.service.cache;

import com.morethanheroic.swords.definition.cache.impl.MapBasedDefinitionCache;
import com.morethanheroic.swords.monster.domain.MonsterDefinition;
import com.morethanheroic.swords.monster.service.loader.MonsterDefinitionLoader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Service
public class MonsterDefinitionCache extends MapBasedDefinitionCache<Integer, MonsterDefinition> {

    public MonsterDefinitionCache(final MonsterDefinitionLoader monsterDefinitionLoader) {
        super(
                monsterDefinitionLoader.loadDefinitions().stream()
                        .collect(Collectors.toMap(MonsterDefinition::getId, Function.identity()))
        );

        log.info("Loaded " + this.getSize() + " monster definitions.");
    }
}
