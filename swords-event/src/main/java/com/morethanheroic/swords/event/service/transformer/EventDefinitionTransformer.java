package com.morethanheroic.swords.event.service.transformer;

import com.morethanheroic.definition.transformer.DefinitionTransformer;
import com.morethanheroic.swords.event.domain.EventDefinition;
import com.morethanheroic.swords.event.service.loader.domain.RawEventDefinition;
import org.springframework.stereotype.Service;

/**
 * Transform an {@link RawEventDefinition} to a domain entity {@link EventDefinition}.
 */
@Service
public class EventDefinitionTransformer implements DefinitionTransformer<EventDefinition, RawEventDefinition> {

    /**
     * Transform an {@link RawEventDefinition} to a domain entity {@link EventDefinition}.
     *
     * @param rawDefinition the raw definition to transform
     * @return the result of the transformation
     */
    @Override
    public EventDefinition transform(RawEventDefinition rawDefinition) {
        return EventDefinition.builder()
                .id(rawDefinition.getId())
                .name(rawDefinition.getName())
                .type(rawDefinition.getType())
                .length(rawDefinition.getLength())
                .build();
    }
}
