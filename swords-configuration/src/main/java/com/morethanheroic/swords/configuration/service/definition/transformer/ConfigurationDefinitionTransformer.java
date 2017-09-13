package com.morethanheroic.swords.configuration.service.definition.transformer;

import com.morethanheroic.swords.configuration.service.definition.domain.ConfigurationDefinition;
import com.morethanheroic.swords.configuration.service.definition.loader.domain.RawConfigurationDefinition;
import com.morethanheroic.swords.definition.transformer.DefinitionTransformer;
import org.springframework.stereotype.Service;

@Service
public class ConfigurationDefinitionTransformer implements DefinitionTransformer<ConfigurationDefinition, RawConfigurationDefinition> {

    @Override
    public ConfigurationDefinition transform(final RawConfigurationDefinition rawDefinition) {
        return ConfigurationDefinition.builder()
                .id(rawDefinition.getId())
                .value(rawDefinition.getValue())
                .build();
    }
}
