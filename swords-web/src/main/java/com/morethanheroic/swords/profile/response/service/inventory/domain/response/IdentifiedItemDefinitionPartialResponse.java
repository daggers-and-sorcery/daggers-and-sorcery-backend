package com.morethanheroic.swords.profile.response.service.inventory.domain.response;

import com.morethanheroic.swords.item.view.response.service.domain.response.ItemDefinitionPartialResponse;
import com.morethanheroic.swords.item.view.response.service.domain.response.ItemModifierPartialResponse;
import com.morethanheroic.swords.item.view.response.service.domain.response.ItemRequirementPartialResponse;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class IdentifiedItemDefinitionPartialResponse extends ItemDefinitionPartialResponse {

    private final int id;
    private final int amount;
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
