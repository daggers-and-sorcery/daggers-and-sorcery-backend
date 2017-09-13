package com.morethanheroic.swords.zone.view.response.service;

import java.util.List;
import java.util.stream.Collectors;

import com.morethanheroic.response.domain.PartialResponse;
import com.morethanheroic.response.service.PartialResponseCollectionBuilder;
import com.morethanheroic.swords.zone.service.definition.cache.ZoneDefinitionCache;
import com.morethanheroic.swords.zone.view.response.service.domain.ZoneDefinitionPartialResponse;
import com.morethanheroic.swords.zone.view.response.service.domain.ZoneListOnLocationResponseBuilderConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ZoneListOnLocationPartialResponseCollectionBuilder implements PartialResponseCollectionBuilder<ZoneListOnLocationResponseBuilderConfiguration> {

    private final ZoneDefinitionCache zoneDefinitionCache;

    @Override
    public List<PartialResponse> build(final ZoneListOnLocationResponseBuilderConfiguration zoneListOnLocationResponseBuilderConfiguration) {
        return zoneDefinitionCache.getDefinitionsOnLocation(zoneListOnLocationResponseBuilderConfiguration.getLocation()).stream()
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
