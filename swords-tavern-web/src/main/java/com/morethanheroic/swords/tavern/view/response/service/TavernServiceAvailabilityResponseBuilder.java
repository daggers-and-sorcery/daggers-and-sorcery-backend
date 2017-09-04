package com.morethanheroic.swords.tavern.view.response.service;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.response.service.ResponseBuilder;
import com.morethanheroic.swords.tavern.service.quest.TavernQuestCalculator;
import com.morethanheroic.swords.response.service.ResponseFactory;
import com.morethanheroic.swords.tavern.view.response.quest.TavernQuestInfoPartialResponseBuilder;
import com.morethanheroic.swords.tavern.view.response.quest.domain.TavernQuestInfoPartialResponseBuilderConfiguration;
import com.morethanheroic.swords.tavern.view.response.service.domain.TavernServiceAvailabilityResponseBuilderConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TavernServiceAvailabilityResponseBuilder implements ResponseBuilder<TavernServiceAvailabilityResponseBuilderConfiguration> {

    private final ResponseFactory responseFactory;
    private final TavernServicesPartialResponseBuilder tavernServicesPartialResponseBuilder;
    private final TavernQuestInfoPartialResponseBuilder tavernQuestInfoPartialResponseBuilder;
    private final TavernQuestCalculator tavernQuestCalculator;

    @Override
    public Response build(final TavernServiceAvailabilityResponseBuilderConfiguration tavernServiceAvailabilityResponseBuilderConfiguration) {
        final Response response = responseFactory.newResponse(tavernServiceAvailabilityResponseBuilderConfiguration.getUserEntity());

        response.setData("services", tavernServicesPartialResponseBuilder.build(tavernServiceAvailabilityResponseBuilderConfiguration));
        response.setData("quest", tavernQuestInfoPartialResponseBuilder.build(
                TavernQuestInfoPartialResponseBuilderConfiguration.builder()
                        .shouldShowSmugglersHeavenQuest(tavernQuestCalculator.shouldShowSmugglersHeavenQuest(tavernServiceAvailabilityResponseBuilderConfiguration.getUserEntity()))
                        .build()
                )
        );

        return response;
    }
}
