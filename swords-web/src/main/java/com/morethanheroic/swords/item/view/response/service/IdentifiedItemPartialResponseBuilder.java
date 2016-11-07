package com.morethanheroic.swords.item.view.response.service;

import com.morethanheroic.response.service.PartialResponseBuilder;
import com.morethanheroic.swords.item.domain.ItemDefinition;
import com.morethanheroic.swords.item.view.response.service.domain.configuration.IdentifiedItemPartialResponseBuilderConfiguration;
import com.morethanheroic.swords.item.view.response.service.domain.configuration.ItemModifierPartialResponseBuilderConfiguration;
import com.morethanheroic.swords.item.view.response.service.domain.configuration.ItemRequirementPartialResponseBuilderConfiguration;
import com.morethanheroic.swords.item.view.response.service.domain.response.IdentifiedItemDefinitionPartialResponse;
import com.morethanheroic.swords.item.view.response.service.domain.response.ItemDefinitionPartialResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class IdentifiedItemPartialResponseBuilder implements PartialResponseBuilder<IdentifiedItemPartialResponseBuilderConfiguration> {

    private static final int WEIGHT_DIVIDER = 100;

    @Autowired
    private ItemRequirementPartialResponseBuilder itemRequirementPartialResponseBuilder;

    @Autowired
    private ItemModifierPartialResponseBuilder itemModifierPartialResponseBuilder;

    @Override
    public ItemDefinitionPartialResponse build(IdentifiedItemPartialResponseBuilderConfiguration identifiedItemPartialResponseBuilderConfiguration) {
        final ItemDefinition itemDefinition = identifiedItemPartialResponseBuilderConfiguration.getItem();

        return IdentifiedItemDefinitionPartialResponse.builder()
                .id(itemDefinition.getId())
                .name(itemDefinition.getName())
                .equipment(itemDefinition.isEquipment())
                .usable(itemDefinition.isUsable())
                .weight((double) itemDefinition.getWeight() / WEIGHT_DIVIDER)
                .type(itemDefinition.getType().getName())
                .subtype(itemDefinition.getSubtype() != null ? itemDefinition.getSubtype().getName() : null)
                .requirements(
                        itemDefinition.getRequirements().stream()
                                .map(itemRequirementDefinition -> itemRequirementPartialResponseBuilder.build(
                                        ItemRequirementPartialResponseBuilderConfiguration.builder()
                                                .amount(itemRequirementDefinition.getAmount())
                                                .itemRequirement(itemRequirementDefinition.getRequirement())
                                                .build()
                                        )
                                ).collect(Collectors.toList())
                )
                .modifiers(
                        itemDefinition.getModifiers().stream()
                                .map(itemModifierDefinition -> itemModifierPartialResponseBuilder.build(
                                        ItemModifierPartialResponseBuilderConfiguration.builder()
                                                .amount(itemModifierDefinition.getAmount())
                                                .d2(itemModifierDefinition.getD2())
                                                .d4(itemModifierDefinition.getD4())
                                                .d6(itemModifierDefinition.getD6())
                                                .d8(itemModifierDefinition.getD8())
                                                .d10(itemModifierDefinition.getD10())
                                                .itemModifier(itemModifierDefinition.getModifier())
                                                .build()
                                        )
                                ).collect(Collectors.toList())
                )
                .description(itemDefinition.getDescription())
                .flavour(itemDefinition.getFlavour())
                .build();
    }
}
