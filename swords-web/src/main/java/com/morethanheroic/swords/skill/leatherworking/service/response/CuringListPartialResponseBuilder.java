package com.morethanheroic.swords.skill.leatherworking.service.response;

import com.morethanheroic.response.service.PartialResponseCollectionBuilder;
import com.morethanheroic.swords.event.domain.EventEntity;
import com.morethanheroic.swords.event.service.EventProvider;
import com.morethanheroic.swords.event.service.event.Event;
import com.morethanheroic.swords.skill.leatherworking.service.response.domain.configuration.CuringInfoResponseBuilderConfiguration;
import com.morethanheroic.swords.skill.leatherworking.service.response.domain.configuration.CuringListPartialResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class CuringListPartialResponseBuilder implements PartialResponseCollectionBuilder<CuringInfoResponseBuilderConfiguration> {

    @Autowired
    private EventProvider eventProvider;

    @Override
    public List<CuringListPartialResponse> build(CuringInfoResponseBuilderConfiguration curingListPartialResponseBuilderConfiguration) {
        final List<CuringListPartialResponse> result = new ArrayList<>();

        for (EventEntity eventEntity : curingListPartialResponseBuilderConfiguration.getEventEntities()) {
            final Event event = eventProvider.getEvent(eventEntity.getEventId());

            result.add(
                    CuringListPartialResponse.builder()
                            .item(event.getName())
                            .timeLeft(eventEntity.getEnding().getTime() - Calendar.getInstance().getTimeInMillis())
                            .fullTime(event.getLength())
                            .build()
            );
        }

        return result;
    }
}
