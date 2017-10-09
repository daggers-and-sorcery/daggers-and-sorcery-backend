package com.morethanheroic.swords.configuration.service.definition.loader;

import com.google.common.collect.ImmutableList;
import com.morethanheroic.swords.configuration.domain.Configuration;
import com.morethanheroic.swords.configuration.service.definition.domain.ConfigurationDefinition;
import com.morethanheroic.swords.configuration.service.definition.loader.domain.RawConfigurationDefinition;
import com.morethanheroic.swords.configuration.service.definition.transformer.ConfigurationDefinitionTransformer;
import com.morethanheroic.definition.loader.DefinitionLoader;

import com.morethanheroic.xml.service.loader.EnumXmlDefinitionLoader;
import com.morethanheroic.xml.service.loader.domain.EnumDefinitionLoadingContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class ConfigurationDefinitionLoader implements DefinitionLoader<ConfigurationDefinition> {

    private final EnumXmlDefinitionLoader enumXmlDefinitionLoader;
    private final ConfigurationDefinitionTransformer configurationDefinitionTransformer;

    @Override
    public List<ConfigurationDefinition> loadDefinitions() {
        return loadRawConfigurationDefinitions().stream()
                .map(configurationDefinitionTransformer::transform)
                .collect(
                        collectingAndThen(toList(), ImmutableList::copyOf)
                );
    }

    private List<RawConfigurationDefinition> loadRawConfigurationDefinitions() {
        return enumXmlDefinitionLoader.loadDefinitions(
                EnumDefinitionLoadingContext.<RawConfigurationDefinition>builder()
                        .clazz(RawConfigurationDefinition.class)
                        .resourcePath("classpath:data/configuration/definition/")
                        .schemaPath("classpath:data/configuration/schema.xsd")
                        .target(Configuration.class)
                        .build()
        );
    }
}
