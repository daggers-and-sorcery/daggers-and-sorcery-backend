package com.morethanheroic.swords.item.view.response.service.domain.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UnidentifiedItemDefinitionPartialResponse extends ItemDefinitionPartialResponse {

    private final int id;
    private final String name;
    private final String type;
    private final String subtype;
    private final double weight;
    private final boolean usable;
    private final boolean equipment;
}
