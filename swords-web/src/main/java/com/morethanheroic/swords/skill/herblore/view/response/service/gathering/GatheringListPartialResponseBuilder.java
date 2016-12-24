package com.morethanheroic.swords.skill.herblore.view.response.service.gathering;

import com.morethanheroic.response.domain.PartialResponse;
import com.morethanheroic.response.service.PartialResponseCollectionBuilder;
import com.morethanheroic.swords.event.domain.EventDefinition;
import com.morethanheroic.swords.event.service.cache.EventDefinitionCache;
import com.morethanheroic.swords.skill.herblore.view.response.domain.configuration.gathering.GatheringInfoResponseBuilderConfiguration;
import com.morethanheroic.swords.skill.herblore.view.response.domain.configuration.gathering.GatheringListPartialResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GatheringListPartialResponseBuilder implements PartialResponseCollectionBuilder<GatheringInfoResponseBuilderConfiguration> {

    private final EventDefinitionCache eventDefinitionCache;

    @Override
    public List<PartialResponse> build(GatheringInfoResponseBuilderConfiguration gatheringInfoResponseBuilderConfiguration) {
        return gatheringInfoResponseBuilderConfiguration.getEventEntities().stream()
                .map(eventEntity -> {
                    final EventDefinition eventDefinition = eventDefinitionCache.getDefinition(eventEntity.getEventId());

                    return GatheringListPartialResponse.builder()
                            .item(eventDefinition.getName())
                            .timeLeft(eventEntity.getEnding().getTime() - Calendar.getInstance().getTimeInMillis())
                            .fullTime(eventDefinition.getLength())
                            .build();
                })
                .collect(Collectors.toList());
    }
}
