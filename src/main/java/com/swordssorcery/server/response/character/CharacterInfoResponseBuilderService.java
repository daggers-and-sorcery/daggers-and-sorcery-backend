package com.swordssorcery.server.response.character;

import com.swordssorcery.server.model.definition.item.ItemDefinitionManager;
import com.swordssorcery.server.game.attribute.Attribute;
import com.swordssorcery.server.game.attribute.calc.GlobalAttributeCalculator;
import com.swordssorcery.server.game.attribute.AttributeUtil;
import com.swordssorcery.server.game.attribute.data.AttributeData;
import com.swordssorcery.server.model.db.Item;
import com.swordssorcery.server.model.db.user.UserDatabaseEntity;
import com.swordssorcery.server.model.entity.user.UserEntity;
import com.swordssorcery.server.response.Response;
import com.swordssorcery.server.response.ResponseBuilderService;
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

        for (Item item : user.getInventory().getItemList()) {
            HashMap<String, Object> itemData = new HashMap<>();

            itemData.put("item", item);
            itemData.put("definition", itemDefinitionManager.getItemDefinition(item.getId()));

            inventoryData.add(itemData);
        }

        response.setData("inventory", inventoryData);

        return response;
    }
}
