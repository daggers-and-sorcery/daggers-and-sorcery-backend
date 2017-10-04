package com.morethanheroic.swords.explore.service.event.transformer;

import com.morethanheroic.definition.transformer.DefinitionTransformer;
import com.morethanheroic.swords.explore.domain.ExplorationEventDefinition;
import com.morethanheroic.swords.explore.service.event.loader.domain.RawEventDefinition;
import org.springframework.stereotype.Service;

/**
 * Transform a {@link RawEventDefinition} to an {@link ExplorationEventDefinition}.
 */
@Service
public class ExplorationEventDefinitionTransformer implements DefinitionTransformer<ExplorationEventDefinition, RawEventDefinition> {

    @Override
    public ExplorationEventDefinition transform(final RawEventDefinition rawDefinition) {
        return ExplorationEventDefinition.builder()
                .id(rawDefinition.getId())
                .name(rawDefinition.getName())
                .location(rawDefinition.getLocation())
                .terrain(rawDefinition.getTerrain())
                .rarity(rawDefinition.getRarity())
                .build();
    }
}
