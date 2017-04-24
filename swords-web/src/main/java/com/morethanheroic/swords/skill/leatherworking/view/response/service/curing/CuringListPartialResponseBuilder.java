package com.morethanheroic.swords.skill.leatherworking.view.response.service.curing;

import com.morethanheroic.response.domain.PartialResponse;
import com.morethanheroic.response.service.PartialResponseCollectionBuilder;
import com.morethanheroic.swords.event.domain.EventDefinition;
import com.morethanheroic.swords.event.service.cache.EventDefinitionCache;
import com.morethanheroic.swords.skill.leatherworking.view.response.domain.configuration.curing.CuringInfoResponseBuilderConfiguration;
import com.morethanheroic.swords.skill.leatherworking.view.response.domain.configuration.curing.CuringListPartialResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CuringListPartialResponseBuilder implements PartialResponseCollectionBuilder<CuringInfoResponseBuilderConfiguration> {

    private final EventDefinitionCache eventDefinitionCache;

    @Override
    public List<PartialResponse> build(CuringInfoResponseBuilderConfiguration curingListPartialResponseBuilderConfiguration) {
        return curingListPartialResponseBuilderConfiguration.getEventEntities().stream()
                .map(eventEntity -> {
                    final EventDefinition eventDefinition = eventDefinitionCache.getDefinition(eventEntity.getEventId());

                    return CuringListPartialResponse.builder()
                            .item(eventDefinition.getName())
                            .timeLeft(eventEntity.getEnding().getTime() - Calendar.getInstance().getTimeInMillis())
                            .fullTime(eventDefinition.getLength())
                            .build();
                })
                .collect(Collectors.toList());
    }
}
