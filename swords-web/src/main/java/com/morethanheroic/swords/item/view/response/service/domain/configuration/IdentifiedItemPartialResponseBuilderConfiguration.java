package com.morethanheroic.swords.item.view.response.service.domain.configuration;

import com.morethanheroic.response.service.ResponseBuilderConfiguration;
import com.morethanheroic.swords.item.domain.ItemDefinition;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class IdentifiedItemPartialResponseBuilderConfiguration implements ResponseBuilderConfiguration {

    private final ItemDefinition item;
    private final int maximumAmount;
}
