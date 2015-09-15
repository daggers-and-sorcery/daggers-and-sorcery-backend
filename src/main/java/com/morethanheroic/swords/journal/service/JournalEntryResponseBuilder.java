package com.morethanheroic.swords.journal.service;

import com.morethanheroic.swords.attribute.view.response.AttributeModifierResponseEntry;
import com.morethanheroic.swords.attribute.view.response.AttributeRequirementResponseEntry;
import com.morethanheroic.swords.common.response.Response;
import com.morethanheroic.swords.common.response.ResponseFactory;
import com.morethanheroic.swords.item.service.ItemDefinitionManager;
import com.morethanheroic.swords.item.service.domain.AttributeModifierDefinition;
import com.morethanheroic.swords.item.service.domain.AttributeRequirementDefinition;
import com.morethanheroic.swords.item.service.domain.ItemDefinition;
import com.morethanheroic.swords.journal.model.JournalType;
import com.morethanheroic.swords.monster.service.MonsterDefinitionManager;
import com.morethanheroic.swords.monster.service.domain.MonsterDefinition;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

@Service
public class JournalEntryResponseBuilder {

    private final ResponseFactory responseFactory;
    private final ItemDefinitionManager itemDefinitionManager;
    private final MonsterDefinitionManager monsterDefinitionManager;

    @Autowired
    public JournalEntryResponseBuilder(ResponseFactory responseFactory, ItemDefinitionManager itemDefinitionManager, MonsterDefinitionManager monsterDefinitionManager) {
        this.responseFactory = responseFactory;
        this.itemDefinitionManager = itemDefinitionManager;
        this.monsterDefinitionManager = monsterDefinitionManager;
    }

    public Response build(UserEntity userEntity, JournalType journalType, int journalId) {
        Response response = responseFactory.newResponse(userEntity);

        switch (journalType) {
            case ITEM:
                response.setData("journal_entry", buildItemEntry(itemDefinitionManager.getItemDefinition(journalId)));
                break;
            case MONSTER:
                //TODO: same builder as in items for monsters too
                response.setData("journal_entry", buildMonsterEntry(monsterDefinitionManager.getMonsterDefinition(journalId)));
                break;
            default:
                throw new IllegalArgumentException("Invalid journal type!");
        }

        return response;
    }

    public Response buildInvalidRequest(UserEntity userEntity) {
        Response response = responseFactory.newResponse(userEntity);

        response.setData("journal_entry", "Unavailable!");

        return response;
    }

    private HashMap<String, Object> buildItemEntry(ItemDefinition itemDefinition) {
        HashMap<String, Object> result = new HashMap<>();

        result.put("name", itemDefinition.getName());
        result.put("type", itemDefinition.getType().name());
        result.put("weight", itemDefinition.getWeight());
        result.put("equipment", itemDefinition.isEquipment());

        ArrayList<AttributeModifierResponseEntry> modifiers = new ArrayList<>();
        result.put("modifiers", modifiers);

        for (AttributeModifierDefinition modifierDefinition : itemDefinition.getAllModifiers()) {
            modifiers.add(new AttributeModifierResponseEntry(modifierDefinition.getAttribute(), modifierDefinition.getAmount()));
        }

        ArrayList<AttributeRequirementResponseEntry> requirements = new ArrayList<>();
        result.put("requirements", requirements);

        for (AttributeRequirementDefinition requirementDefinition : itemDefinition.getAllRequirements()) {
            requirements.add(new AttributeRequirementResponseEntry(requirementDefinition.getAttribute(), requirementDefinition.getAmount()));
        }

        return result;
    }

    private HashMap<String, Object> buildMonsterEntry(MonsterDefinition monsterDefinition) {
        HashMap<String, Object> result = new HashMap<>();

        result.put("name", monsterDefinition.getName());
        result.put("level", monsterDefinition.getLevel());
        result.put("attack", monsterDefinition.getAttack());
        result.put("defense", monsterDefinition.getDefense());
        result.put("initiation", monsterDefinition.getInitiation());
        result.put("health", monsterDefinition.getHealth());
        result.put("aiming", monsterDefinition.getAiming());
        result.put("rangedDamage", monsterDefinition.getRangedDamage());
        result.put("damage", monsterDefinition.getDamage());
        result.put("attackType", monsterDefinition.getAttackType());

        return result;
    }
}
