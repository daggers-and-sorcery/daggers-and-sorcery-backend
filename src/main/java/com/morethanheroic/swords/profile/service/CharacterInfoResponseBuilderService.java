package com.morethanheroic.swords.profile.service;

import com.morethanheroic.swords.attribute.enums.Attribute;
import com.morethanheroic.swords.attribute.service.AttributeUtil;
import com.morethanheroic.swords.attribute.service.calc.GlobalAttributeCalculator;
import com.morethanheroic.swords.attribute.model.AttributeData;
import com.morethanheroic.swords.item.service.ItemDefinitionManager;
import com.morethanheroic.swords.inventory.repository.dao.ItemDatabaseEntity;
import com.morethanheroic.swords.user.domain.UserEntity;
import com.morethanheroic.swords.common.response.Response;
import com.morethanheroic.swords.common.response.ResponseBuilderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

@Service
public class CharacterInfoResponseBuilderService implements ResponseBuilderService {

    @Autowired
    private GlobalAttributeCalculator globalAttributeCalculator;

    @Autowired
    private ItemDefinitionManager itemDefinitionManager;

    @Autowired
    private AttributeUtil attributeUtil;

    @Override
    public Response build(UserEntity user) {
        Response response = new Response();

        LinkedList<AttributeData> attributeData = new LinkedList<>();
        response.setData("attribute", attributeData);

        for (Attribute attribute : attributeUtil.getAllAttributes()) {
            attributeData.add(globalAttributeCalculator.calculateAttributeValue(user, attribute));
        }

        response.setData("username", user.getUsername());
        response.setData("race", user.getRace());
        response.setData("registrationDate", user.getRegistrationDate());
        response.setData("lastLoginDate", user.getLastLoginDate());

        ArrayList<HashMap<String, Object>> inventoryData = new ArrayList<>();

        for (ItemDatabaseEntity item : user.getInventory().getItemList()) {
            HashMap<String, Object> itemData = new HashMap<>();

            itemData.put("item", item);
            itemData.put("definition", itemDefinitionManager.getItemDefinition(item.getId()));

            inventoryData.add(itemData);
        }

        response.setData("inventory", inventoryData);

        return response;
    }
}
