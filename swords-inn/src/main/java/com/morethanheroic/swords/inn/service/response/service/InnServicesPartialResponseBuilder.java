package com.morethanheroic.swords.inn.service.response.service;

import com.morethanheroic.response.domain.PartialResponse;
import com.morethanheroic.response.service.PartialResponseCollectionBuilder;
import com.morethanheroic.swords.inn.domain.ServiceType;
import com.morethanheroic.swords.inn.service.response.service.domain.InnServiceAvailabilityResponseBuilderConfiguration;
import com.morethanheroic.swords.inn.service.response.service.domain.InnServicePartialResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class InnServicesPartialResponseBuilder implements PartialResponseCollectionBuilder<InnServiceAvailabilityResponseBuilderConfiguration> {

    @Override
    public Collection<? extends PartialResponse> build(InnServiceAvailabilityResponseBuilderConfiguration explorationResponseBuilderConfiguration) {
        final List<PartialResponse> result = new ArrayList<>();

        for (ServiceType serviceType : explorationResponseBuilderConfiguration.getServices()) {
            result.add(
                    InnServicePartialResponse.builder()
                            .id(serviceType.getId())
                            .name(serviceType.getName())
                            .money(serviceType.getPriceDefinition().getAmount())
                            .moneyType(serviceType.getPriceDefinition().getType())
                            .movement(serviceType.getMovement())
                            .build()
            );
        }

        return result;
    }
}
