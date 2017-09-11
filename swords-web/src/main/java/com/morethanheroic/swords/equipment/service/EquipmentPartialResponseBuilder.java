package com.morethanheroic.swords.equipment.service;

import com.morethanheroic.response.domain.PartialResponse;
import com.morethanheroic.response.service.PartialResponseBuilder;
import com.morethanheroic.swords.equipment.domain.EquipmentEntity;
import com.morethanheroic.swords.equipment.domain.EquipmentSlot;
import com.morethanheroic.swords.equipment.domain.EquipmentSlotEntity;
import com.morethanheroic.swords.equipment.service.domain.EquipmentPartialResponse;
import com.morethanheroic.swords.equipment.service.domain.EquipmentPartialResponseBuilderConfiguration;
import com.morethanheroic.swords.item.view.response.service.IdentifiedItemPartialResponseBuilder;
import com.morethanheroic.swords.item.view.response.service.UnidentifiedItemPartialResponseBuilder;
import com.morethanheroic.swords.item.view.response.service.domain.configuration.IdentifiedItemPartialResponseBuilderConfiguration;
import com.morethanheroic.swords.item.view.response.service.domain.configuration.UnidentifiedItemPartialResponseBuilderConfiguration;
import com.morethanheroic.swords.item.view.response.service.domain.response.ItemDefinitionPartialResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EquipmentPartialResponseBuilder implements PartialResponseBuilder<EquipmentPartialResponseBuilderConfiguration> {

    private final IdentifiedItemPartialResponseBuilder identifiedItemPartialResponseBuilder;
    private final UnidentifiedItemPartialResponseBuilder unidentifiedItemPartialResponseBuilder;

    @Override
    public PartialResponse build(EquipmentPartialResponseBuilderConfiguration equipmentPartialResponseBuilderConfiguration) {
        final EquipmentSlot equipmentSlot = equipmentPartialResponseBuilderConfiguration.getEquipmentSlot();
        final EquipmentSlotEntity equipmentSlotEntity = equipmentPartialResponseBuilderConfiguration.getEquipmentEntity().getEquipmentSlot(equipmentSlot);

        return EquipmentPartialResponse.builder()
                .slot(equipmentSlot)
                .amount(equipmentSlotEntity.getAmount())
                .empty(!equipmentSlotEntity.hasItem())
                .itemDefinition(buildItemResponse(equipmentSlotEntity))
                .build();
    }

    private ItemDefinitionPartialResponse buildItemResponse(final EquipmentSlotEntity equipmentSlotEntity) {
        if (equipmentSlotEntity.hasItem()) {
            if (equipmentSlotEntity.isIdentified()) {
                return identifiedItemPartialResponseBuilder.build(
                        IdentifiedItemPartialResponseBuilderConfiguration.builder()
                                .item(equipmentSlotEntity.getItem().get())
                                .build()
                );
            } else {
                return unidentifiedItemPartialResponseBuilder.build(
                        UnidentifiedItemPartialResponseBuilderConfiguration.builder()
                                .item(equipmentSlotEntity.getItem().get())
                                .build()
                );
            }
        }

        return null;
    }
}
