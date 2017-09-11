package com.morethanheroic.swords.equipment.view.controller;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.swords.equipment.EquipmentEntityFactory;
import com.morethanheroic.swords.equipment.service.ListEquipmentResponseBuilder;
import com.morethanheroic.swords.equipment.service.domain.ListEquipmentResponseBuilderConfiguration;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class EquipmentInfoController {

    private final ListEquipmentResponseBuilder listEquipmentResponseBuilder;
    private final EquipmentEntityFactory equipmentEntityFactory;

    @GetMapping("/character/equipment")
    public Response listEquipment(final UserEntity userEntity) {
        return listEquipmentResponseBuilder.build(
                ListEquipmentResponseBuilderConfiguration.builder()
                        .userEntity(userEntity)
                        .equipmentEntity(equipmentEntityFactory.getEntity(userEntity))
                        .build()
        );
    }
}
