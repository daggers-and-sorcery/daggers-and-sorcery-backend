package com.morethanheroic.swords.zone.view.response.service;

import com.morethanheroic.response.domain.PartialResponse;
import com.morethanheroic.response.service.PartialResponseCollectionBuilder;
import com.morethanheroic.swords.zone.service.availability.ZoneAvailabilityEvaluator;
import com.morethanheroic.swords.zone.view.response.service.domain.ZoneDefinitionPartialResponse;
import com.morethanheroic.swords.zone.view.response.service.domain.ZoneListOnLocationResponseBuilderConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ZoneListOnLocationPartialResponseCollectionBuilder implements PartialResponseCollectionBuilder<ZoneListOnLocationResponseBuilderConfiguration> {

    private final ZoneAvailabilityEvaluator zoneAvailabilityEvaluator;

    @Override
    public List<PartialResponse> build(final ZoneListOnLocationResponseBuilderConfiguration zoneListOnLocationResponseBuilderConfiguration) {
        return zoneAvailabilityEvaluator.getAvailableZonesOnLocation(zoneListOnLocationResponseBuilderConfiguration.getUserEntity(), zoneListOnLocationResponseBuilderConfiguration.getLocation()).stream()
                .map(zoneDefinition ->
                        ZoneDefinitionPartialResponse.builder()
                                .id(zoneDefinition.getId().toString().toLowerCase().replace("_", "-"))
                                .name(zoneDefinition.getName())
                                .description(zoneDefinition.getDescription())
                                .enabled(zoneDefinition.isEnabled())
                                .minimumLevel(zoneDefinition.getMinimumLevel())
                                .maximumLevel(zoneDefinition.getMaximumLevel())
                                .build()
                )
                .collect(Collectors.toList());
    }
}
