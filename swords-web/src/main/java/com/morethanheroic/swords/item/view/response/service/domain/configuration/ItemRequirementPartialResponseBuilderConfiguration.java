package com.morethanheroic.swords.item.view.response.service.domain.configuration;

import com.morethanheroic.response.service.ResponseBuilderConfiguration;
import com.morethanheroic.swords.item.domain.requirement.ItemRequirement;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ItemRequirementPartialResponseBuilderConfiguration implements ResponseBuilderConfiguration {

    private final int amount;
    private final ItemRequirement itemRequirement;
}
