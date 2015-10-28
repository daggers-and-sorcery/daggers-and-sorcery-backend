package com.morethanheroic.swords.journal.service;

import com.morethanheroic.swords.combat.domain.DiceAttribute;
import com.morethanheroic.swords.common.response.Response;
import com.morethanheroic.swords.common.response.ResponseFactory;
import com.morethanheroic.swords.item.service.ItemDefinitionManager;
import com.morethanheroic.swords.item.service.ItemEntryResponseBuilder;
import com.morethanheroic.swords.journal.model.JournalType;
import com.morethanheroic.swords.monster.service.MonsterDefinitionCache;
import com.morethanheroic.swords.monster.domain.MonsterDefinition;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class JournalEntryResponseBuilder {

    private final ResponseFactory responseFactory;
    private final ItemDefinitionManager itemDefinitionManager;
    private final MonsterDefinitionCache monsterDefinitionCache;
    private final ItemEntryResponseBuilder itemEntryResponseBuilder;

    @Autowired
    public JournalEntryResponseBuilder(ResponseFactory responseFactory, ItemDefinitionManager itemDefinitionManager, MonsterDefinitionCache monsterDefinitionCache, ItemEntryResponseBuilder itemEntryResponseBuilder) {
        this.responseFactory = responseFactory;
        this.itemDefinitionManager = itemDefinitionManager;
        this.monsterDefinitionCache = monsterDefinitionCache;
        this.itemEntryResponseBuilder = itemEntryResponseBuilder;
    }

    public Response build(UserEntity userEntity, JournalType journalType, int journalId) {
        Response response = responseFactory.newResponse(userEntity);

        switch (journalType) {
            case ITEM:
                response.setData("journal_entry", itemEntryResponseBuilder.buildItemEntry(itemDefinitionManager.getItemDefinition(journalId)));
                break;
            case MONSTER:
                //TODO: same builder as in items for monsters too
                response.setData("journal_entry", buildMonsterEntry(monsterDefinitionCache.getMonsterDefinition(journalId)));
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

    private HashMap<String, Object> buildMonsterEntry(MonsterDefinition monsterDefinition) {
        HashMap<String, Object> result = new HashMap<>();

        result.put("name", monsterDefinition.getName());
        result.put("level", monsterDefinition.getLevel());
        result.put("attack", formatDiceAttribute(monsterDefinition.getAttack()));
        result.put("defense", monsterDefinition.getDefense());
        result.put("initiation", formatDiceAttribute(monsterDefinition.getInitiation()));
        result.put("health", monsterDefinition.getHealth());
        result.put("aiming", formatDiceAttribute(monsterDefinition.getAiming()));
        result.put("rangedDamage", formatDiceAttribute(monsterDefinition.getRangedDamage()));
        result.put("damage", formatDiceAttribute(monsterDefinition.getDamage()));
        result.put("attackType", monsterDefinition.getAttackType());

        return result;
    }

    private String formatDiceAttribute(DiceAttribute diceAttribute) {
        String result = String.valueOf(diceAttribute.getValue());

        if (diceAttribute.getD2() > 0) {
            result += " + " + diceAttribute.getD2() + "d2";
        }
        if (diceAttribute.getD4() > 0) {
            result += " + " + diceAttribute.getD4() + "d4";
        }
        if (diceAttribute.getD6() > 0) {
            result += " + " + diceAttribute.getD6() + "d6";
        }
        if (diceAttribute.getD8() > 0) {
            result += " + " + diceAttribute.getD8() + "d8";
        }
        if (diceAttribute.getD10() > 0) {
            result += " + " + diceAttribute.getD10() + "d10";
        }

        return result;
    }
}
