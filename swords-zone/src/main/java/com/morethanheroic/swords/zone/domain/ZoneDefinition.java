package com.morethanheroic.swords.zone.domain;

import com.morethanheroic.swords.location.domain.Location;
import com.morethanheroic.swords.zone.service.definition.cache.accessibility.ZoneAccessibilityDefinition;
import lombok.Builder;
import lombok.Getter;

import java.util.Optional;

@Getter
@Builder
public class ZoneDefinition {

    private final ExplorationZone id;
    private final String name;
    private boolean enabled;
    private Location location;
    private String description;
    private ZoneAccessibilityDefinition accessibility;
    private int minimumLevel;
    private int maximumLevel;

    public Optional<ZoneAccessibilityDefinition> getAccessibility() {
        return Optional.ofNullable(accessibility);
    }
}
