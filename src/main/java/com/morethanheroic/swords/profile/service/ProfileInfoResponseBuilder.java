package com.morethanheroic.swords.profile.service;

import com.morethanheroic.swords.attribute.domain.GeneralAttribute;
import com.morethanheroic.swords.attribute.domain.SkillAttribute;
import com.morethanheroic.swords.attribute.enums.Attribute;
import com.morethanheroic.swords.attribute.enums.AttributeType;
import com.morethanheroic.swords.attribute.model.AttributeData;
import com.morethanheroic.swords.attribute.model.AttributeModifierData;
import com.morethanheroic.swords.attribute.model.GeneralAttributeData;
import com.morethanheroic.swords.attribute.model.SkillAttributeData;
import com.morethanheroic.swords.attribute.service.AttributeUtil;
import com.morethanheroic.swords.attribute.service.calc.GlobalAttributeCalculator;
import com.morethanheroic.swords.common.response.Response;
import com.morethanheroic.swords.equipment.domain.EquipmentEntity;
import com.morethanheroic.swords.equipment.domain.EquipmentSlot;
import com.morethanheroic.swords.equipment.service.EquipmentManager;
import com.morethanheroic.swords.inventory.repository.dao.ItemDatabaseEntity;
import com.morethanheroic.swords.inventory.service.InventoryManager;
import com.morethanheroic.swords.item.service.ItemDefinitionManager;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

@Service
public class ProfileInfoResponseBuilder {

    private final GlobalAttributeCalculator globalAttributeCalculator;
    private final ItemDefinitionManager itemDefinitionManager;
    private final AttributeUtil attributeUtil;
    private final InventoryManager inventoryManager;
    private final EquipmentManager equipmentManager;

    @Autowired
    public ProfileInfoResponseBuilder(GlobalAttributeCalculator globalAttributeCalculator, ItemDefinitionManager itemDefinitionManager, AttributeUtil attributeUtil, InventoryManager inventoryManager, EquipmentManager equipmentManager) {
        this.globalAttributeCalculator = globalAttributeCalculator;
        this.itemDefinitionManager = itemDefinitionManager;
        this.attributeUtil = attributeUtil;
        this.inventoryManager = inventoryManager;
        this.equipmentManager = equipmentManager;
    }

    public Response build(UserEntity user) {
        Response response = new Response();

        response.setData("attribute", buildAttributeResponse(user));
        response.setData("username", user.getUsername());
        response.setData("race", user.getRace());
        response.setData("registrationDate", user.getRegistrationDate());
        response.setData("lastLoginDate", user.getLastLoginDate());
        response.setData("inventory", buildInventoryResponse(inventoryManager.getInventory(user).getItems()));
        response.setData("equipment", buildEquipmentResponse(user));

        return response;
    }

    private HashMap<String, HashMap<String, Object>> buildEquipmentResponse(UserEntity userEntity) {
        HashMap<String, HashMap<String, Object>> equipmentHolder = new HashMap<>();

        EquipmentEntity equipmentEntity = equipmentManager.getEquipment(userEntity);

        for (EquipmentSlot slot : EquipmentSlot.values()) {
            int equipment = equipmentEntity.getEquipmentOnSlot(slot);

            HashMap<String, Object> slotData = new HashMap<>();

            if(equipment == 0) {
                slotData.put("empty", true);
            } else {
                slotData.put("description", itemDefinitionManager.getItemDefinition(equipment));
            }

            equipmentHolder.put(slot.name(), slotData);
        }

        return equipmentHolder;
    }

    private LinkedList<HashMap<String, Object>> buildAttributeResponse(UserEntity user) {
        LinkedList<HashMap<String, Object>> attributeData = new LinkedList<>();

        for (Attribute attribute : attributeUtil.getAllAttributes()) {
            attributeData.add(buildAttributeResponse(user, attribute));
        }

        return attributeData;
    }

    private ArrayList<HashMap<String, Object>> buildInventoryResponse(List<ItemDatabaseEntity> items) {
        ArrayList<HashMap<String, Object>> inventoryData = new ArrayList<>();

        for (ItemDatabaseEntity item : items) {
            HashMap<String, Object> itemData = new HashMap<>();

            itemData.put("item", item);
            itemData.put("definition", itemDefinitionManager.getItemDefinition(item.getItemId()));

            inventoryData.add(itemData);
        }

        return inventoryData;
    }

    private HashMap<String, Object> buildAttributeResponse(UserEntity user, Attribute attribute) {
        AttributeData attributeData = globalAttributeCalculator.calculateAttributeValue(user, attribute);

        HashMap<String, Object> attributeResponse = new HashMap<>();

        attributeResponse.put("actual", attributeData.getActual());
        attributeResponse.put("maximum", attributeData.getMaximum());
        attributeResponse.put("attribute", buildAttributeInfo(attribute));
        attributeResponse.put("modifierDataArray", buildAttributeModifiers(attributeData.getModifierDataArray()));
        if (attribute.getAttributeType() == AttributeType.GENERAL) {
            attributeResponse.put("pointsToNextLevel", ((GeneralAttributeData) attributeData).getPointsToNextLevel());
        } else if (attribute.getAttributeType() == AttributeType.SKILL) {
            attributeResponse.put("actualXp", ((SkillAttributeData) attributeData).getActualXp());
            attributeResponse.put("nextLevelXp", ((SkillAttributeData) attributeData).getNextLevelXp());
        }

        return attributeResponse;
    }

    private HashMap<String, Object> buildAttributeInfo(Attribute attribute) {
        //Can be cached!
        HashMap<String, Object> attributeDataResponse = new HashMap<>();

        attributeDataResponse.put("name", attribute.getName());
        attributeDataResponse.put("initialValue", attribute.getInitialValue());
        attributeDataResponse.put("attributeType", attribute.getAttributeType().name());
        if (attribute.getAttributeType() == AttributeType.GENERAL) {
            attributeDataResponse.put("generalAttributeType", ((GeneralAttribute) attribute).getGeneralAttributeType().name());
        }

        return attributeDataResponse;
    }

    private LinkedList<HashMap<String, Object>> buildAttributeModifiers(AttributeModifierData[] attributeModifierDatas) {
        LinkedList<HashMap<String, Object>> bonusList = new LinkedList<>();

        for (AttributeModifierData attributeModifierData : attributeModifierDatas) {
            HashMap<String, Object> attributeModifierResponse = new HashMap<>();

            attributeModifierResponse.put("attributeModifierType", attributeModifierData.getAttributeModifierType().name());
            attributeModifierResponse.put("attributeModifierValueType", attributeModifierData.getAttributeModifierValueType().name());
            attributeModifierResponse.put("attributeModifierValue", attributeModifierData.getAttributeModifierValue());

            bonusList.add(attributeModifierResponse);
        }

        return bonusList;
    }
}
