package com.morethanheroic.swords.equipment.view.controller;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.swords.equipment.EquipmentEntityFactory;
import com.morethanheroic.swords.equipment.domain.EquipmentSlot;
import com.morethanheroic.swords.response.service.ResponseFactory;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class EquipmentUnequipController {

    private final EquipmentEntityFactory equipmentEntityFactory;
    private final ResponseFactory responseFactory;

    @GetMapping("/unequip/{equipmentSlot}")
    public Response unequip(final UserEntity userEntity, final @PathVariable EquipmentSlot equipmentSlot) {
        equipmentEntityFactory.getEntity(userEntity).unequipItem(equipmentSlot);

        return responseFactory.successfulResponse(userEntity);
    }
}
