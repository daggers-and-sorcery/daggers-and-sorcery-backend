package com.morethanheroic.swords.zone.view.response.service.domain;

import com.morethanheroic.response.domain.PartialResponse;
import com.morethanheroic.swords.location.domain.Location;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ZoneDefinitionPartialResponse extends PartialResponse {

    private final String id;
    private final String name;
    private boolean enabled;
    private Location location;
    private String description;
    private int minimumLevel;
    private int maximumLevel;
}
