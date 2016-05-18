package com.morethanheroic.swords.inn.service.response.service;

import com.morethanheroic.response.domain.PartialResponse;
import com.morethanheroic.response.service.PartialResponseCollectionBuilder;
import com.morethanheroic.swords.inn.domain.service.ServiceType;
import com.morethanheroic.swords.inn.service.response.service.domain.InnServiceAvailabilityResponseBuilderConfiguration;
import com.morethanheroic.swords.inn.service.response.service.domain.InnServicePartialResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class InnServicesPartialResponseBuilder implements PartialResponseCollectionBuilder<InnServiceAvailabilityResponseBuilderConfiguration> {

    @Override
    public Collection<? extends PartialResponse> build(InnServiceAvailabilityResponseBuilderConfiguration innServiceAvailabilityResponseBuilderConfiguration) {
        final List<PartialResponse> result = new ArrayList<>();

        for (ServiceType serviceType : innServiceAvailabilityResponseBuilderConfiguration.getServices()) {
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
