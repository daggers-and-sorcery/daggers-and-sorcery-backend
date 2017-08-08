package com.morethanheroic.swords.item.view.response.service;

import com.morethanheroic.response.service.PartialResponseBuilder;
import com.morethanheroic.swords.item.view.response.service.domain.configuration.ItemRequirementPartialResponseBuilderConfiguration;
import com.morethanheroic.swords.item.view.response.service.domain.response.ItemRequirementPartialResponse;
import org.springframework.stereotype.Service;

@Service
public class ItemRequirementPartialResponseBuilder implements PartialResponseBuilder<ItemRequirementPartialResponseBuilderConfiguration> {

    @Override
    public ItemRequirementPartialResponse build(ItemRequirementPartialResponseBuilderConfiguration responseBuilderConfiguration) {
        return ItemRequirementPartialResponse.builder()
                .attribute(responseBuilderConfiguration.getItemRequirement().getName())
                .value(responseBuilderConfiguration.getAmount())
                .build();
    }
}
