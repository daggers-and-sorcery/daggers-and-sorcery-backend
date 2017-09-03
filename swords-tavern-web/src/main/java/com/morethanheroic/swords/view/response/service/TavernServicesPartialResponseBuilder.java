package com.morethanheroic.swords.view.response.service;

import com.morethanheroic.response.domain.PartialResponse;
import com.morethanheroic.response.service.PartialResponseCollectionBuilder;
import com.morethanheroic.swords.inn.domain.service.ServiceType;
import com.morethanheroic.swords.view.response.service.domain.TavernServiceAvailabilityResponseBuilderConfiguration;
import com.morethanheroic.swords.view.response.service.domain.InnServicePartialResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class TavernServicesPartialResponseBuilder implements PartialResponseCollectionBuilder<TavernServiceAvailabilityResponseBuilderConfiguration> {

    @Override
    public Collection<? extends PartialResponse> build(TavernServiceAvailabilityResponseBuilderConfiguration tavernServiceAvailabilityResponseBuilderConfiguration) {
        final List<PartialResponse> result = new ArrayList<>();

        for (ServiceType serviceType : tavernServiceAvailabilityResponseBuilderConfiguration.getServices()) {
            result.add(
                    InnServicePartialResponse.builder()
                            .id(serviceType.name())
                            .name(serviceType.getName())
                            .build()
            );
        }

        return result;
    }
}
