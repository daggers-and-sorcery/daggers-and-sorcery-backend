package com.morethanheroic.swords.configuration.service.definition.cache;

import com.morethanheroic.swords.configuration.domain.Configuration;
import com.morethanheroic.swords.configuration.service.definition.domain.ConfigurationDefinition;
import com.morethanheroic.swords.configuration.service.definition.loader.ConfigurationDefinitionLoader;
import com.morethanheroic.definition.cache.impl.MapBasedDefinitionCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ConfigurationDefinitionCache extends MapBasedDefinitionCache<Configuration, ConfigurationDefinition> {

    protected ConfigurationDefinitionCache(final ConfigurationDefinitionLoader configurationDefinitionLoader) {
        super(
                configurationDefinitionLoader.loadDefinitions().stream()
                        .collect(Collectors.toMap(ConfigurationDefinition::getId, Function.identity()))
        );

        log.info("Loaded " + this.getSize() + " zone definitions.");
    }
}
