package com.morethanheroic.swords.zone.view.response.service.domain;

import com.morethanheroic.response.domain.PartialResponse;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ZoneDefinitionPartialResponse extends PartialResponse {

    private final String id;
    private final String name;
    private boolean enabled;
    private String description;
    private int minimumLevel;
    private int maximumLevel;
}
