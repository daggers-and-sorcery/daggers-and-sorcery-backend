package com.morethanheroic.swords.profile.service.response;

import com.morethanheroic.swords.attribute.domain.Attribute;
import com.morethanheroic.swords.attribute.domain.GeneralAttribute;
import com.morethanheroic.swords.attribute.domain.type.AttributeType;
import com.morethanheroic.swords.attribute.service.AttributeUtil;
import com.morethanheroic.swords.attribute.service.calc.GlobalAttributeCalculator;
import com.morethanheroic.swords.attribute.service.calc.domain.AttributeCalculationResult;
import com.morethanheroic.swords.attribute.service.calc.domain.AttributeData;
import com.morethanheroic.swords.attribute.service.calc.domain.GeneralAttributeData;
import com.morethanheroic.swords.attribute.service.calc.domain.SkillAttributeData;
import com.morethanheroic.swords.attribute.service.modifier.domain.AttributeModifierEntry;
import com.morethanheroic.swords.attribute.service.modifier.domain.AttributeModifierValue;
import com.morethanheroic.swords.equipment.domain.EquipmentEntity;
import com.morethanheroic.swords.equipment.domain.EquipmentSlot;
import com.morethanheroic.swords.equipment.service.EquipmentManager;
import com.morethanheroic.swords.inventory.repository.dao.ItemDatabaseEntity;
import com.morethanheroic.swords.inventory.service.InventoryFacade;
import com.morethanheroic.swords.inventory.service.UnidentifiedItemIdCalculator;
import com.morethanheroic.swords.item.service.cache.ItemDefinitionCache;
import com.morethanheroic.swords.profile.service.response.item.ProfileIdentifiedItemEntryResponseBuilder;
import com.morethanheroic.swords.profile.service.response.item.ProfileUnidentifiedItemEntryResponseBuilder;
import com.morethanheroic.swords.race.service.RaceDefinitionCache;
import com.morethanheroic.swords.response.domain.Response;
import com.morethanheroic.swords.response.service.ResponseFactory;
import com.morethanheroic.swords.scavenging.service.ScavengingFacade;
import com.morethanheroic.swords.skill.service.SkillFacade;
import com.morethanheroic.swords.spell.domain.SpellDefinition;
import com.morethanheroic.swords.spell.repository.dao.SpellDatabaseEntity;
import com.morethanheroic.swords.spell.repository.domain.SpellMapper;
import com.morethanheroic.swords.spell.service.cache.SpellDefinitionCache;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProfileInfoResponseBuilder {

    private final GlobalAttributeCalculator globalAttributeCalculator;
    private final ItemDefinitionCache itemDefinitionCache;
    private final AttributeUtil attributeUtil;
    private final InventoryFacade inventoryFacade;
    private final EquipmentManager equipmentManager;
    private final ResponseFactory responseFactory;
    private final ProfileIdentifiedItemEntryResponseBuilder profileIdentifiedItemEntryResponseBuilder;
    private final SpellDefinitionCache spellDefinitionCache;
    private final SpellMapper spellMapper;

    @Autowired
    private UnidentifiedItemIdCalculator unidentifiedItemIdCalculator;

    @Autowired
    private ProfileUnidentifiedItemEntryResponseBuilder profileUnidentifiedItemEntryResponseBuilder;

    @Autowired
    private RaceDefinitionCache raceDefinitionCache;

    @Autowired
    private SkillFacade skillFacade;

    @Autowired
    private ScavengingFacade scavengingFacade;

    @Autowired
    public ProfileInfoResponseBuilder(GlobalAttributeCalculator globalAttributeCalculator, ItemDefinitionCache itemDefinitionCache, AttributeUtil attributeUtil, InventoryFacade inventoryFacade, EquipmentManager equipmentManager, ResponseFactory responseFactory, ProfileIdentifiedItemEntryResponseBuilder profileIdentifiedItemEntryResponseBuilder, SpellDefinitionCache spellDefinitionCache, SpellMapper spellMapper) {
        this.globalAttributeCalculator = globalAttributeCalculator;
        this.itemDefinitionCache = itemDefinitionCache;
        this.attributeUtil = attributeUtil;
        this.inventoryFacade = inventoryFacade;
        this.equipmentManager = equipmentManager;
        this.responseFactory = responseFactory;
        this.profileIdentifiedItemEntryResponseBuilder = profileIdentifiedItemEntryResponseBuilder;
        this.spellDefinitionCache = spellDefinitionCache;
        this.spellMapper = spellMapper;
    }

    public Response build(UserEntity user, HttpSession session) {
        Response response = responseFactory.newResponse(user);

        response.setData("attribute", buildAttributeResponse(user));
        response.setData("username", user.getUsername());
        response.setData("race", raceDefinitionCache.getDefinition(user.getRace()).getName());
        response.setData("registrationDate", user.getRegistrationDate());
        response.setData("lastLoginDate", user.getLastLoginDate());
        response.setData("scavengingPoints", scavengingFacade.getEntity(user).getScavengingPoint());
        response.setData("inventory", buildInventoryResponse(inventoryFacade.getInventory(user).getItems(), session));
        response.setData("equipment", buildEquipmentResponse(user, session));
        response.setData("spell", buildSpellResponse(spellMapper.getAllSpellsForUser(user.getId())));

        return response;
    }

    private HashMap<String, HashMap<String, Object>> buildEquipmentResponse(UserEntity userEntity, HttpSession session) {
        HashMap<String, HashMap<String, Object>> equipmentHolder = new HashMap<>();

        EquipmentEntity equipmentEntity = equipmentManager.getEquipment(userEntity);

        for (EquipmentSlot slot : EquipmentSlot.values()) {
            int equipment = equipmentEntity.getEquipmentIdOnSlot(slot);

            HashMap<String, Object> slotData = new HashMap<>();

            if (equipment == 0) {
                slotData.put("empty", true);
            } else {
                if (equipmentEntity.isEquipmentIdentifiedOnSlot(slot)) {
                    slotData.put("description", profileIdentifiedItemEntryResponseBuilder.buildItemEntry(itemDefinitionCache.getItemDefinition(equipment)));
                } else {
                    slotData.put("description", profileUnidentifiedItemEntryResponseBuilder.buildItemEntry(itemDefinitionCache.getItemDefinition(equipment), unidentifiedItemIdCalculator.getUnidentifiedItemId(session, equipment)));
                }
            }

            equipmentHolder.put(slot.name(), slotData);
        }

        return equipmentHolder;
    }

    private LinkedList<HashMap<String, Object>> buildAttributeResponse(UserEntity user) {
        return attributeUtil.getAllAttributes().stream().map(attribute -> buildAttributeResponse(user, attribute)).collect(Collectors.toCollection(LinkedList::new));
    }

    private ArrayList<HashMap<String, Object>> buildInventoryResponse(List<ItemDatabaseEntity> items, HttpSession session) {
        ArrayList<HashMap<String, Object>> inventoryData = new ArrayList<>();

        for (ItemDatabaseEntity item : items) {
            HashMap<String, Object> itemData = new HashMap<>();

            itemData.put("item", convertItemDatabaseEntityToSendableObject(item));

            if (item.isIdentified()) {
                itemData.put("definition", profileIdentifiedItemEntryResponseBuilder.buildItemEntry(itemDefinitionCache.getItemDefinition(item.getItemId())));
            } else {
                itemData.put("definition", profileUnidentifiedItemEntryResponseBuilder.buildItemEntry(itemDefinitionCache.getItemDefinition(item.getItemId()), unidentifiedItemIdCalculator.getUnidentifiedItemId(session, item.getItemId())));
            }

            inventoryData.add(itemData);
        }

        return inventoryData;
    }

    private Map<String, Object> convertItemDatabaseEntityToSendableObject(ItemDatabaseEntity itemDatabaseEntity) {
        Map<String, Object> result = new HashMap<>();

        result.put("itemId", itemDatabaseEntity.getItemId());
        result.put("amount", itemDatabaseEntity.getAmount());

        return result;
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
            result += " + " + attributeModifierValue.getD10() + "d10";
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

    private LinkedList<HashMap<String, Object>> buildAttributeModifiers(Attribute attribute, List<AttributeModifierEntry> attributeModifierEntries) {
        LinkedList<HashMap<String, Object>> bonusList = new LinkedList<>();

        for (AttributeModifierEntry attributeModifierEntry : attributeModifierEntries) {
            HashMap<String, Object> attributeModifierResponse = new HashMap<>();

            attributeModifierResponse.put("attributeModifierType", attributeModifierEntry.getAttributeModifierType().name());
            attributeModifierResponse.put("attributeModifierValueType", attributeModifierEntry.getAttributeModifierValueType().name());
            if (attribute.getAttributeType() == AttributeType.COMBAT) {
                attributeModifierResponse.put("attributeModifierValue", formatCombatAttributeModifier(attributeModifierEntry.getAttributeModifierValue()));
            } else {
                attributeModifierResponse.put("attributeModifierValue", attributeModifierEntry.getAttributeModifierValue().getValue());
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
            result += " + " + attributeModifierValue.getD10() + "d10";
        }

        return result;
    }

    private LinkedList<HashMap<String, Object>> buildSpellResponse(List<SpellDatabaseEntity> spells) {
        LinkedList<HashMap<String, Object>> spellList = new LinkedList<>();

        for (SpellDatabaseEntity spell : spells) {
            SpellDefinition spellDefinition = spellDefinitionCache.getSpellDefinition(spell.getSpellId());

            HashMap<String, Object> spellData = new HashMap<>();

            spellData.put("id", spellDefinition.getId());
            spellData.put("name", spellDefinition.getName());
            spellData.put("combatSpell", spellDefinition.isCombatSpell());
            spellData.put("castingCost", spellDefinition.getSpellCosts());
            spellData.put("openPage", spellDefinition.isOpenPage());

            spellList.add(spellData);
        }

        return spellList;
    }
}
