package com.morethanheroic.swords.item.view.response.service.domain.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class IdentifiedItemDefinitionPartialResponse extends ItemDefinitionPartialResponse {

    private final int id;
    private final String name;
    private final String type;
    private final String subtype;
    private final double weight;
    private final boolean usable;
    private final boolean equipment;
    private final String flavour;
    private final String description;
    private final List<ItemModifierPartialResponse> modifiers;
    private final List<ItemRequirementPartialResponse> requirements;
}
