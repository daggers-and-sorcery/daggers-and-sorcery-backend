package com.morethanheroic.swords.equipment.view.service.domain;

import com.morethanheroic.response.domain.PartialResponse;
import com.morethanheroic.swords.equipment.domain.EquipmentSlot;
import com.morethanheroic.swords.item.view.response.service.domain.response.ItemDefinitionPartialResponse;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EquipmentPartialResponse extends PartialResponse {

    private final EquipmentSlot slot;
    private final boolean empty;
    private final int amount;
    private final ItemDefinitionPartialResponse itemDefinition;
}
