package com.morethanheroic.swords.journal.view.response;

import com.morethanheroic.swords.attribute.domain.DiceAttribute;
import com.morethanheroic.swords.monster.domain.MonsterDefinition;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class MonsterEntryResponseBuilder {

    public HashMap<String, Object> buildMonsterEntry(MonsterDefinition monsterDefinition) {
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
