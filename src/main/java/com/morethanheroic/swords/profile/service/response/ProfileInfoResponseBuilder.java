package com.morethanheroic.swords.profile.service.response;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.response.service.ResponseBuilder;
import com.morethanheroic.session.domain.SessionEntity;
import com.morethanheroic.swords.attribute.service.calc.type.SkillTypeCalculator;
import com.morethanheroic.swords.equipment.domain.EquipmentEntity;
import com.morethanheroic.swords.equipment.domain.EquipmentSlot;
import com.morethanheroic.swords.equipment.service.EquipmentFacade;
import com.morethanheroic.swords.inventory.domain.InventoryItem;
import com.morethanheroic.swords.inventory.service.InventoryEntityFactory;
import com.morethanheroic.swords.inventory.service.InventoryFacade;
import com.morethanheroic.swords.inventory.service.InventoryItemTypeSorter;
import com.morethanheroic.swords.inventory.service.UnidentifiedItemIdCalculator;
import com.morethanheroic.swords.item.domain.ItemType;
import com.morethanheroic.swords.item.service.cache.ItemDefinitionCache;
import com.morethanheroic.swords.profile.service.response.inventory.InventoryPartialResponseBuilder;
import com.morethanheroic.swords.profile.service.response.inventory.domain.configuration.InventoryPartialResponseBuilderConfiguration;
import com.morethanheroic.swords.profile.service.response.item.ProfileIdentifiedItemEntryResponseBuilder;
import com.morethanheroic.swords.profile.service.response.item.ProfileUnidentifiedItemEntryResponseBuilder;
import com.morethanheroic.swords.profile.service.response.skill.SkillPartialResponseBuilder;
import com.morethanheroic.swords.profile.service.response.skill.domain.SkillPartialResponseBuilderConfiguration;
import com.morethanheroic.swords.race.service.RaceDefinitionCache;
import com.morethanheroic.swords.response.service.ResponseFactory;
import com.morethanheroic.swords.spell.domain.SpellDefinition;
import com.morethanheroic.swords.spell.repository.dao.SpellDatabaseEntity;
import com.morethanheroic.swords.spell.repository.domain.SpellMapper;
import com.morethanheroic.swords.spell.service.cache.SpellDefinitionCache;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class ProfileInfoResponseBuilder implements ResponseBuilder<ProfileInfoResponseBuilderConfiguration> {

    private final ItemDefinitionCache itemDefinitionCache;
    private final InventoryFacade inventoryFacade;
    private final EquipmentFacade equipmentFacade;
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
    private AttributeValuePartialResponseBuilder attributeValuePartialResponseBuilder;

    @Autowired
    private SkillPartialResponseBuilder skillPartialResponseBuilder;

    @Autowired
    private SkillTypeCalculator skillTypeCalculator;

    @Autowired
    private InventoryPartialResponseBuilder inventoryPartialResponseBuilder;

    @Autowired
    private InventoryItemTypeSorter inventoryItemTypeSorter;

    @Autowired
    private InventoryEntityFactory inventoryEntityFactory;

    @Autowired
    public ProfileInfoResponseBuilder(ItemDefinitionCache itemDefinitionCache, InventoryFacade inventoryFacade, EquipmentFacade equipmentFacade, ResponseFactory responseFactory, ProfileIdentifiedItemEntryResponseBuilder profileIdentifiedItemEntryResponseBuilder, SpellDefinitionCache spellDefinitionCache, SpellMapper spellMapper) {
        this.itemDefinitionCache = itemDefinitionCache;
        this.inventoryFacade = inventoryFacade;
        this.equipmentFacade = equipmentFacade;
        this.responseFactory = responseFactory;
        this.profileIdentifiedItemEntryResponseBuilder = profileIdentifiedItemEntryResponseBuilder;
        this.spellDefinitionCache = spellDefinitionCache;
        this.spellMapper = spellMapper;
    }

    public Response build(ProfileInfoResponseBuilderConfiguration profileInfoResponseBuilderConfiguration) {
        final UserEntity userEntity = profileInfoResponseBuilderConfiguration.getUserEntity();
        final SessionEntity sessionEntity = profileInfoResponseBuilderConfiguration.getSessionEntity();

        final Response response = responseFactory.newResponse(userEntity);

        response.setData("attribute", attributeValuePartialResponseBuilder.build(profileInfoResponseBuilderConfiguration));
        response.setData("skill", skillPartialResponseBuilder.build(
                SkillPartialResponseBuilderConfiguration.builder()
                        .userEntity(userEntity)
                        .tradeSkills(skillTypeCalculator.getTradeSkills())
                        .combatSkills(skillTypeCalculator.getCombatSkills())
                        .build()
        ));
        response.setData("username", userEntity.getUsername());
        response.setData("race", raceDefinitionCache.getDefinition(userEntity.getRace()).getName());
        response.setData("registrationDate", userEntity.getRegistrationDate().getEpochSecond() * 1000);
        response.setData("lastLoginDate", userEntity.getLastLoginDate().getEpochSecond() * 1000);
        response.setData("inventory", inventoryPartialResponseBuilder.build(
                InventoryPartialResponseBuilderConfiguration.builder()
                        .userEntity(userEntity)
                        .sessionEntity(profileInfoResponseBuilderConfiguration.getSessionEntity())
                        .inventoryItems(getSortedItems(userEntity))
                        .build()
                )
        );
        response.setData("equipment", buildEquipmentResponse(userEntity, sessionEntity));
        response.setData("spell", buildSpellResponse(spellMapper.getAllSpellsForUser(userEntity.getId())));

        return response;
    }

    private Map<ItemType, List<InventoryItem>> getSortedItems(final UserEntity userEntity) {
        return inventoryItemTypeSorter.sortByType(inventoryEntityFactory.getEntity(userEntity.getId()).getItems());
    }

    private Map<String, Map<String, Object>> buildEquipmentResponse(UserEntity userEntity, SessionEntity sessionEntity) {
        final Map<String, Map<String, Object>> equipmentHolder = new HashMap<>();

        final EquipmentEntity equipmentEntity = equipmentFacade.getEquipment(userEntity);

        for (EquipmentSlot slot : EquipmentSlot.values()) {
            final int equipment = equipmentEntity.getEquipmentIdOnSlot(slot);

            final Map<String, Object> slotData = new HashMap<>();

            if (equipment == 0) {
                slotData.put("empty", true);
            } else {
                if(slot == EquipmentSlot.QUIVER) {
                    slotData.put("amount", equipmentEntity.getAmountOnSlot(slot));
                }

                if (equipmentEntity.isEquipmentIdentifiedOnSlot(slot)) {
                    slotData.put("description", profileIdentifiedItemEntryResponseBuilder.buildItemEntry(itemDefinitionCache.getDefinition(equipment)));
                } else {
                    slotData.put("description", profileUnidentifiedItemEntryResponseBuilder.buildItemEntry(itemDefinitionCache.getDefinition(equipment), unidentifiedItemIdCalculator.getUnidentifiedItemId(sessionEntity, equipment)));
                }
            }

            equipmentHolder.put(slot.name(), slotData);
        }

        return equipmentHolder;
    }

    private Map<String, Object> convertInventoryItemToSendableObject(InventoryItem itemDatabaseEntity) {
        final Map<String, Object> result = new HashMap<>();

        result.put("itemId", itemDatabaseEntity.getItem().getId());
        result.put("amount", itemDatabaseEntity.getAmount());

        return result;
    }

    private LinkedList<HashMap<String, Object>> buildSpellResponse(List<SpellDatabaseEntity> spells) {
        LinkedList<HashMap<String, Object>> spellList = new LinkedList<>();

        for (SpellDatabaseEntity spell : spells) {
            SpellDefinition spellDefinition = spellDefinitionCache.getSpellDefinition(spell.getSpellId());

            HashMap<String, Object> spellData = new HashMap<>();

            spellData.put("id", spellDefinition.getId());
            spellData.put("name", spellDefinition.getName());
            spellData.put("description", spellDefinition.getDescription());
            spellData.put("combatSpell", spellDefinition.isCombatSpell());
            spellData.put("castingCost", spellDefinition.getSpellCosts());
            spellData.put("openPage", spellDefinition.isOpenPage());

            spellList.add(spellData);
        }

        return spellList;
    }
}
