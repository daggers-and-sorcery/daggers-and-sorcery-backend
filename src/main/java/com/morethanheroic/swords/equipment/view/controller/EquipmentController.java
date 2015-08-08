package com.morethanheroic.swords.equipment.view.controller;

import com.morethanheroic.swords.common.response.Response;
import com.morethanheroic.swords.equipment.service.EquipmentManager;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EquipmentController {

    private EquipmentManager equipmentManager;

    @Autowired
    public EquipmentController(EquipmentManager equipmentManager) {
        this.equipmentManager = equipmentManager;
    }

    @RequestMapping(value = "/equip/{itemId}", method = RequestMethod.GET)
    public Response equip(UserEntity user, @PathVariable int itemId) {
        boolean result = equipmentManager.equip(user, itemId);

        Response response = new Response();
        response.setData("success", result);

        return response;
    }
}
