package com.morethanheroic.swords.profile.service;

import com.morethanheroic.swords.attribute.domain.GeneralAttribute;
import com.morethanheroic.swords.attribute.enums.Attribute;
import com.morethanheroic.swords.attribute.enums.AttributeType;
import com.morethanheroic.swords.attribute.model.AttributeData;
import com.morethanheroic.swords.attribute.model.AttributeModifierData;
import com.morethanheroic.swords.attribute.model.GeneralAttributeData;
import com.morethanheroic.swords.attribute.model.SkillAttributeData;
import com.morethanheroic.swords.attribute.service.AttributeUtil;
import com.morethanheroic.swords.attribute.service.calc.GlobalAttributeCalculator;
import com.morethanheroic.swords.attribute.service.calc.domain.AttributeCalculationResult;
import com.morethanheroic.swords.attribute.service.calc.domain.AttributeModifierValue;
import com.morethanheroic.swords.common.response.Response;
import com.morethanheroic.swords.common.response.ResponseFactory;
import com.morethanheroic.swords.equipment.domain.EquipmentEntity;
import com.morethanheroic.swords.equipment.domain.EquipmentSlot;
import com.morethanheroic.swords.equipment.service.EquipmentManager;
import com.morethanheroic.swords.inventory.repository.dao.ItemDatabaseEntity;
import com.morethanheroic.swords.inventory.service.InventoryManager;
import com.morethanheroic.swords.item.service.ItemDefinitionManager;
import com.morethanheroic.swords.item.service.ItemEntryResponseBuilder;
import com.morethanheroic.swords.spell.repository.dao.SpellDatabaseEntity;
import com.morethanheroic.swords.spell.repository.domain.SpellMapper;
import com.morethanheroic.swords.spell.service.SpellDefinitionManager;
import com.morethanheroic.swords.spell.service.domain.SpellDefinition;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfileInfoResponseBuilder {

    private final GlobalAttributeCalculator globalAttributeCalculator;
    private final ItemDefinitionManager itemDefinitionManager;
    private final AttributeUtil attributeUtil;
    private final InventoryManager inventoryManager;
    private final EquipmentManager equipmentManager;
    private final ResponseFactory responseFactory;
    private final ProfileItemEntryResponseBuilder profileItemEntryResponseBuilder;
    private final SpellDefinitionManager spellDefinitionManager;
    private final SpellMapper spellMapper;

    @Autowired
    public ProfileInfoResponseBuilder(GlobalAttributeCalculator globalAttributeCalculator, ItemDefinitionManager itemDefinitionManager, AttributeUtil attributeUtil, InventoryManager inventoryManager, EquipmentManager equipmentManager, ResponseFactory responseFactory, ProfileItemEntryResponseBuilder profileItemEntryResponseBuilder, SpellDefinitionManager spellDefinitionManager, SpellMapper spellMapper) {
        this.globalAttributeCalculator = globalAttributeCalculator;
        this.itemDefinitionManager = itemDefinitionManager;
        this.attributeUtil = attributeUtil;
        this.inventoryManager = inventoryManager;
        this.equipmentManager = equipmentManager;
        this.responseFactory = responseFactory;
        this.profileItemEntryResponseBuilder = profileItemEntryResponseBuilder;
        this.spellDefinitionManager = spellDefinitionManager;
        this.spellMapper = spellMapper;
    }

    public Response build(UserEntity user) {
        Response response = responseFactory.newResponse(user);

        response.setData("attribute", buildAttributeResponse(user));
        response.setData("username", user.getUsername());
        response.setData("race", user.getRace());
        response.setData("registrationDate", user.getRegistrationDate());
        response.setData("lastLoginDate", user.getLastLoginDate());
        response.setData("inventory", buildInventoryResponse(inventoryManager.getInventory(user).getItems()));
        response.setData("equipment", buildEquipmentResponse(user));
        response.setData("spell", buildSpellResponse(spellMapper.getAllSpellsForUser(user.getId())));

        return response;
    }

    private HashMap<String, HashMap<String, Object>> buildEquipmentResponse(UserEntity userEntity) {
        HashMap<String, HashMap<String, Object>> equipmentHolder = new HashMap<>();

        EquipmentEntity equipmentEntity = equipmentManager.getEquipment(userEntity);

        for (EquipmentSlot slot : EquipmentSlot.values()) {
            int equipment = equipmentEntity.getEquipmentIdOnSlot(slot);

            HashMap<String, Object> slotData = new HashMap<>();

            if (equipment == 0) {
                slotData.put("empty", true);
            } else {
                slotData.put("description", itemDefinitionManager.getItemDefinition(equipment));
            }

            equipmentHolder.put(slot.name(), slotData);
        }

        return equipmentHolder;
    }

    private LinkedList<HashMap<String, Object>> buildAttributeResponse(UserEntity user) {
        return attributeUtil.getAllAttributes().stream().map(attribute -> buildAttributeResponse(user, attribute)).collect(Collectors.toCollection(LinkedList::new));
    }

    private ArrayList<HashMap<String, Object>> buildInventoryResponse(List<ItemDatabaseEntity> items) {
        ArrayList<HashMap<String, Object>> inventoryData = new ArrayList<>();

        for (ItemDatabaseEntity item : items) {
            HashMap<String, Object> itemData = new HashMap<>();

            itemData.put("item", item);
            itemData.put("definition", profileItemEntryResponseBuilder.buildItemEntry(itemDefinitionManager.getItemDefinition(item.getItemId())));

            inventoryData.add(itemData);
        }

        return inventoryData;
    }

    private HashMap<String, Object> buildAttributeResponse(UserEntity user, Attribute attribute) {
        AttributeData attributeData = globalAttributeCalculator.calculateAttributeValue(user, attribute);

        HashMap<String, Object> attributeResponse = new HashMap<>();

        if (attribute.getAttributeType() == AttributeType.COMBAT) {
            attributeResponse.put("actual", formatCombatAttributeModifier(attributeData.getActual()));
        } else {
            attributeResponse.put("actual", attributeData.getActual().getValue());
        }
        attributeResponse.put("maximum", attributeData.getMaximum());
        attributeResponse.put("attribute", buildAttributeInfo(attribute));
        attributeResponse.put("modifierDataArray", buildAttributeModifiers(attribute, attributeData.getModifierDataArray()));
        if (attribute.getAttributeType() == AttributeType.GENERAL) {
            attributeResponse.put("pointsToNextLevel", ((GeneralAttributeData) attributeData).getPointsToNextLevel());
        } else if (attribute.getAttributeType() == AttributeType.SKILL) {
            attributeResponse.put("actualXp", ((SkillAttributeData) attributeData).getActualXp());
            attributeResponse.put("nextLevelXp", ((SkillAttributeData) attributeData).getNextLevelXp());
            attributeResponse.put("xpBetweenLevels", ((SkillAttributeData) attributeData).getXpBetweenLevels());
        }

        return attributeResponse;
    }

    private String formatCombatAttributeModifier(AttributeCalculationResult attributeModifierValue) {
        String result = String.valueOf(attributeModifierValue.getValue());

        if (attributeModifierValue.getD2() > 0) {
            result += " + " + attributeModifierValue.getD2() + "d2";
        }
        if (attributeModifierValue.getD4() > 0) {
            result += " + " + attributeModifierValue.getD4() + "d4";
        }
        if (attributeModifierValue.getD6() > 0) {
            result += " + " + attributeModifierValue.getD6() + "d6";
        }
        if (attributeModifierValue.getD8() > 0) {
            result += " + " + attributeModifierValue.getD8() + "d8";
        }
        if (attributeModifierValue.getD10() > 0) {
            result += " + " + attributeModifierValue.getD2() + "d10";
        }

        return result;
    }

    private HashMap<String, Object> buildAttributeInfo(Attribute attribute) {
        HashMap<String, Object> attributeDataResponse = new HashMap<>();

        attributeDataResponse.put("name", attribute.getName());
        attributeDataResponse.put("initialValue", attribute.getInitialValue());
        attributeDataResponse.put("attributeType", attribute.getAttributeType().name());
        if (attribute.getAttributeType() == AttributeType.GENERAL) {
            attributeDataResponse.put("generalAttributeType", ((GeneralAttribute) attribute).getGeneralAttributeType().name());
        }

        return attributeDataResponse;
    }

    private LinkedList<HashMap<String, Object>> buildAttributeModifiers(Attribute attribute, AttributeModifierData[] attributeModifierDatas) {
        LinkedList<HashMap<String, Object>> bonusList = new LinkedList<>();

        for (AttributeModifierData attributeModifierData : attributeModifierDatas) {
            HashMap<String, Object> attributeModifierResponse = new HashMap<>();

            attributeModifierResponse.put("attributeModifierType", attributeModifierData.getAttributeModifierType().name());
            attributeModifierResponse.put("attributeModifierValueType", attributeModifierData.getAttributeModifierValueType().name());
            if (attribute.getAttributeType() == AttributeType.COMBAT) {
                attributeModifierResponse.put("attributeModifierValue", formatCombatAttributeModifier(attributeModifierData.getAttributeModifierValue()));
            } else {
                attributeModifierResponse.put("attributeModifierValue", attributeModifierData.getAttributeModifierValue().getValue());
            }

            bonusList.add(attributeModifierResponse);
        }

        return bonusList;
    }

    private String formatCombatAttributeModifier(AttributeModifierValue attributeModifierValue) {
        String result = String.valueOf(attributeModifierValue.getValue());

        if (attributeModifierValue.getD2() > 0) {
            result += " + " + attributeModifierValue.getD2() + "d2";
        }
        if (attributeModifierValue.getD4() > 0) {
            result += " + " + attributeModifierValue.getD4() + "d4";
        }
        if (attributeModifierValue.getD6() > 0) {
            result += " + " + attributeModifierValue.getD6() + "d6";
        }
        if (attributeModifierValue.getD8() > 0) {
            result += " + " + attributeModifierValue.getD8() + "d8";
        }
        if (attributeModifierValue.getD10() > 0) {
            result += " + " + attributeModifierValue.getD2() + "d10";
        }

        return result;
    }

    private LinkedList<HashMap<String, Object>> buildSpellResponse(List<SpellDatabaseEntity> spells) {
        LinkedList<HashMap<String, Object>> spellList = new LinkedList<>();

        for (SpellDatabaseEntity spell : spells) {
            SpellDefinition spellDefinition = spellDefinitionManager.getSpellDefinition(spell.getSpellId());

            HashMap<String, Object> spellData = new HashMap<>();

            spellData.put("id", spellDefinition.getId());
            spellData.put("name", spellDefinition.getName());
            spellData.put("combatSpell", spellDefinition.isCombatSpell());
            spellData.put("castingCost", spellDefinition.getSpellCosts());

            spellList.add(spellData);
        }

        return spellList;
    }
}
