package com.morethanheroic.swords.skill.leatherworking.view.response.service.curing;

import com.morethanheroic.response.service.PartialResponseCollectionBuilder;
import com.morethanheroic.swords.event.domain.EventDefinition;
import com.morethanheroic.swords.event.domain.EventEntity;
import com.morethanheroic.swords.event.service.cache.EventDefinitionCache;
import com.morethanheroic.swords.skill.leatherworking.view.response.domain.configuration.curing.CuringInfoResponseBuilderConfiguration;
import com.morethanheroic.swords.skill.leatherworking.view.response.domain.configuration.curing.CuringListPartialResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CuringListPartialResponseBuilder implements PartialResponseCollectionBuilder<CuringInfoResponseBuilderConfiguration> {

    private final EventDefinitionCache eventDefinitionCache;

    @Override
    public List<CuringListPartialResponse> build(CuringInfoResponseBuilderConfiguration curingListPartialResponseBuilderConfiguration) {
        final List<CuringListPartialResponse> result = new ArrayList<>();

        for (EventEntity eventEntity : curingListPartialResponseBuilderConfiguration.getEventEntities()) {
            final EventDefinition eventDefinition = eventDefinitionCache.getDefinition(eventEntity.getEventId());

            result.add(
                    CuringListPartialResponse.builder()
                            .item(eventDefinition.getName())
                            .timeLeft(eventEntity.getEnding().getTime() - Calendar.getInstance().getTimeInMillis())
                            .fullTime(eventDefinition.getLength())
                            .build()
            );
        }

        return result;
    }
}
