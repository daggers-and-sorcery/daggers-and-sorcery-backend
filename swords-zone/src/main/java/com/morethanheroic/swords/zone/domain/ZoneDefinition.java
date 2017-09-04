package com.morethanheroic.swords.zone.domain;

import com.morethanheroic.swords.location.domain.Location;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ZoneDefinition {

    private final ExplorationZone id;
    private final String name;
    private boolean enabled;
    private Location location;
    private String description;
    private int minimumLevel;
    private int maximumLevel;
}
