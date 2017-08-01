package com.morethanheroic.swords.item.view.response.service.domain.configuration;

import com.morethanheroic.response.service.ResponseBuilderConfiguration;
import com.morethanheroic.swords.item.domain.modifier.ItemModifier;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ItemModifierPartialResponseBuilderConfiguration implements ResponseBuilderConfiguration {

    private final int amount;
    private final int d2;
    private final int d4;
    private final int d6;
    private final int d8;
    private final int d10;
    private final ItemModifier itemModifier;
}
