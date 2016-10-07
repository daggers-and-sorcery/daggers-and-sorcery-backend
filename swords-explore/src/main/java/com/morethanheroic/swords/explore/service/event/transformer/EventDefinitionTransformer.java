package com.morethanheroic.swords.explore.service.event.transformer;

import com.morethanheroic.swords.definition.transformer.DefinitionTransformer;
import com.morethanheroic.swords.explore.domain.EventDefinition;
import com.morethanheroic.swords.explore.service.event.loader.domain.RawEventDefinition;
import org.springframework.stereotype.Service;

/**
 * Transform a {@link RawEventDefinition} to an {@link EventDefinition}.
 */
@Service
public class EventDefinitionTransformer implements DefinitionTransformer<EventDefinition, RawEventDefinition> {

    @Override
    public EventDefinition transform(RawEventDefinition rawDefinition) {
        return EventDefinition.builder()
                .id(rawDefinition.getId())
                .location(rawDefinition.getLocation())
                .terrain(rawDefinition.getTerrain())
                .rarity(rawDefinition.getRarity())
                .build();
    }
}
