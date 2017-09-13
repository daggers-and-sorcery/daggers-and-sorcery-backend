package com.morethanheroic.swords.equipment.view.service.domain;

import com.morethanheroic.response.service.ResponseBuilderConfiguration;
import com.morethanheroic.swords.equipment.domain.EquipmentEntity;
import com.morethanheroic.swords.equipment.domain.EquipmentSlot;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EquipmentPartialResponseBuilderConfiguration implements ResponseBuilderConfiguration {

    private final EquipmentSlot equipmentSlot;
    private final EquipmentEntity equipmentEntity;
}
