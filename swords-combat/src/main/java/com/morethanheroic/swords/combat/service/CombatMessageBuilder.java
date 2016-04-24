package com.morethanheroic.swords.combat.service;

import com.morethanheroic.swords.combat.domain.CombatMessage;
import org.apache.commons.lang3.text.WordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class CombatMessageBuilder {

    @Autowired
    private Random random;

    private final String[] FIGHT_INITIAL_MESSANGE_LIST = new String[]{
            "This is a lovely fight with ${monster}.",
            "Yeah ${monster} is fighting me!!!"
    };
    private final String[] DAMAGE_TO_MONSTER_MESSANGE_LIST = new String[]{
            "You hit ${monster} causing ${damage} points of damage.",
            "You hit the ${monster} with a sudden attack causing ${damage} damage"
    };
    private final String[] RANGED_DAMAGE_TO_MONSTER_MESSANGE_LIST = new String[]{
            "You shot ${monster} causing ${damage} points of damage.",
            "You shot the ${monster} with a sudden attack causing ${damage} damage"
    };
    private final String[] DAMAGE_TO_PLAYER_MESSANGE_LIST = new String[]{
            "${monster} attacked you and successfully dealt ${damage} points of damage."
    };
    private final String[] RANGED_DAMAGE_TO_PLAYER_MESSANGE_LIST = new String[]{
            "${monster} shot you and successfully dealt ${damage} points of damage."
    };
    private final String[] MAGIC_DAMAGE_TO_PLAYER_MESSANGE_LIST = new String[]{
            "${monster} shot you with a magic missile and successfully dealt ${damage} points of damage."
    };
    private final String[] MONSTER_MISS_MESSANGE_LIST = new String[]{
            "${monster} attacked you but missed."
    };
    private final String[] MONSTER_RANGED_MISS_MESSANGE_LIST = new String[]{
            "${monster} shot at you but missed."
    };
    private final String[] PLAYER_MISS_MESSANGE_LIST = new String[]{
            "You attacked ${monster} but missed."
    };
    private final String[] PLAYER_RANGED_MISS_MESSANGE_LIST = new String[]{
            "You attacked ${monster} but missed."
    };
    private final String[] MONSTER_DIE_MESSANGE_LIST = new String[]{
            "You killed ${monster}."
    };
    private final String[] PLAYER_DIE_MESSANGE_LIST = new String[]{
            "${monster} killed you!"
    };
    private final String[] DROP_MESSANGE_LIST = new String[]{
            "Found ${amount} ${item} after looting the opponents body."
    };
    private final String[] SCAVENGING_ITEM_MESSANGE_LIST = new String[]{
            "While scavenging you found ${amount} ${item}. Lucky you!"
    };
    private final String[] SCAVENGING_XP_MESSANGE_LIST = new String[]{
            "After the scavenge you got ${amount} xp on Scavenging!"
    };
    private final String[] NEW_TURN_LIST = new String[]{
            "New turn starts: ${turn}!"
    };
    private final String[] REWARD_XP_LIST = new String[]{
            "After the fight got ${amount} xp on ${skill}!"
    };
    private final String[] HEALTH_SETTING_TRIGGERED_MESSAGE_LIST = new String[] {
            "Your health went under ${percentage} percentage. Time to do something!"
    };
    private final String[] USE_ITEM_MESSAGE_LIST = new String[] {
            "You use the ${item} item."
    };
    private final String[] USE_SPELL_MESSAGE_LIST = new String[] {
            "You use the ${spell} spell."
    };
    private final String[] MANA_SETTING_TRIGGERED_MESSAGE_LIST = new String[] {
            "Your mana went under ${percentage} percentage. Time to do something!"
    };
    private final String[] MONSTER_SETTING_TRIGGERED_MESSAGE_LIST = new String[] {
            "You have a special settings against ${name}. Time to use it!"
    };
    private final String[] TURN_SETTING_TRIGGERED_MESSAGE_LIST = new String[] {
            "You have a special settings in ${turn} turn. Time to use it!"
    };

    public CombatMessage buildTurnSettingTriggeredMessage(int turn) {
        final CombatMessage combatMessage = new CombatMessage();

        combatMessage.addData("turn", turn);
        combatMessage.addData("message", TURN_SETTING_TRIGGERED_MESSAGE_LIST[random.nextInt(TURN_SETTING_TRIGGERED_MESSAGE_LIST.length)]);

        return combatMessage;
    }

    public CombatMessage buildMonsterSettingTriggeredMessage(String monsterName) {
        final CombatMessage combatMessage = new CombatMessage();

        combatMessage.addData("name", monsterName);
        combatMessage.addData("message", MONSTER_SETTING_TRIGGERED_MESSAGE_LIST[random.nextInt(MONSTER_SETTING_TRIGGERED_MESSAGE_LIST.length)]);

        return combatMessage;
    }

    public CombatMessage buildManaSettingTriggeredMessage(int percentage) {
        final CombatMessage combatMessage = new CombatMessage();

        combatMessage.addData("percentage", percentage);
        combatMessage.addData("message", MANA_SETTING_TRIGGERED_MESSAGE_LIST[random.nextInt(MANA_SETTING_TRIGGERED_MESSAGE_LIST.length)]);

        return combatMessage;
    }

    public CombatMessage buildUseSpellMessage(String spell) {
         final CombatMessage combatMessage = new CombatMessage();

        combatMessage.addData("spell", spell);
        combatMessage.addData("message", USE_SPELL_MESSAGE_LIST[random.nextInt(USE_SPELL_MESSAGE_LIST.length)]);

        return combatMessage;
    }

    public CombatMessage buildUseItemMessage(String item) {
         final CombatMessage combatMessage = new CombatMessage();

        combatMessage.addData("item", item);
        combatMessage.addData("message", USE_ITEM_MESSAGE_LIST[random.nextInt(USE_ITEM_MESSAGE_LIST.length)]);

        return combatMessage;
    }

    public CombatMessage buildHealthSettingTriggeredMessage(int percentage) {
        CombatMessage combatMessage = new CombatMessage();

        combatMessage.addData("percentage", percentage);
        combatMessage.addData("message", HEALTH_SETTING_TRIGGERED_MESSAGE_LIST[random.nextInt(HEALTH_SETTING_TRIGGERED_MESSAGE_LIST.length)]);

        return combatMessage;
    }

    public CombatMessage buildFightInitialisationMessage(String monster) {
        CombatMessage combatMessage = new CombatMessage();

        combatMessage.addData("monster", monster);
        combatMessage.addData("message", FIGHT_INITIAL_MESSANGE_LIST[random.nextInt(FIGHT_INITIAL_MESSANGE_LIST.length)]);

        return combatMessage;
    }

    public CombatMessage buildDamageToMonsterMessage(String monster, int damage) {
        CombatMessage combatMessage = new CombatMessage();

        combatMessage.addData("monster", monster);
        combatMessage.addData("damage", damage);
        combatMessage.addData("icon", "blood");
        combatMessage.addData("icon_color", "blue");
        combatMessage.addData("message", DAMAGE_TO_MONSTER_MESSANGE_LIST[random.nextInt(DAMAGE_TO_MONSTER_MESSANGE_LIST.length)]);

        return combatMessage;
    }

    public CombatMessage buildRangedDamageToMonsterMessage(String monster, int damage) {
        CombatMessage combatMessage = new CombatMessage();

        combatMessage.addData("monster", monster);
        combatMessage.addData("damage", damage);
        combatMessage.addData("icon", "blood");
        combatMessage.addData("icon_color", "blue");
        combatMessage.addData("message", RANGED_DAMAGE_TO_MONSTER_MESSANGE_LIST[random.nextInt(RANGED_DAMAGE_TO_MONSTER_MESSANGE_LIST.length)]);

        return combatMessage;
    }

    public CombatMessage buildDamageToPlayerMessage(String monster, int damage) {
        CombatMessage combatMessage = new CombatMessage();

        combatMessage.addData("monster", monster);
        combatMessage.addData("damage", damage);
        combatMessage.addData("icon", "blood");
        combatMessage.addData("icon_color", "red");
        combatMessage.addData("message", DAMAGE_TO_PLAYER_MESSANGE_LIST[random.nextInt(DAMAGE_TO_PLAYER_MESSANGE_LIST.length)]);

        return combatMessage;
    }

    public CombatMessage buildRangedDamageToPlayerMessage(String monster, int damage) {
        CombatMessage combatMessage = new CombatMessage();

        combatMessage.addData("monster", monster);
        combatMessage.addData("damage", damage);
        combatMessage.addData("icon", "blood");
        combatMessage.addData("icon_color", "red");
        combatMessage.addData("message", RANGED_DAMAGE_TO_PLAYER_MESSANGE_LIST[random.nextInt(RANGED_DAMAGE_TO_PLAYER_MESSANGE_LIST.length)]);

        return combatMessage;
    }

    public CombatMessage buildMagicDamageToPlayerMessage(String monster, int damage) {
        CombatMessage combatMessage = new CombatMessage();

        combatMessage.addData("monster", monster);
        combatMessage.addData("damage", damage);
        combatMessage.addData("icon", "blood");
        combatMessage.addData("icon_color", "red");
        combatMessage.addData("message", MAGIC_DAMAGE_TO_PLAYER_MESSANGE_LIST[random.nextInt(MAGIC_DAMAGE_TO_PLAYER_MESSANGE_LIST.length)]);

        return combatMessage;
    }

    public CombatMessage buildMonsterMissMessage(String monster) {
        CombatMessage combatMessage = new CombatMessage();

        combatMessage.addData("monster", monster);
        combatMessage.addData("icon", "shield");
        combatMessage.addData("icon_color", "red");
        combatMessage.addData("message", MONSTER_MISS_MESSANGE_LIST[random.nextInt(MONSTER_MISS_MESSANGE_LIST.length)]);

        return combatMessage;
    }

    public CombatMessage buildMonsterRangedMissMessage(String monster) {
        CombatMessage combatMessage = new CombatMessage();

        combatMessage.addData("monster", monster);
        combatMessage.addData("icon", "shield");
        combatMessage.addData("icon_color", "red");
        combatMessage.addData("message", MONSTER_RANGED_MISS_MESSANGE_LIST[random.nextInt(MONSTER_RANGED_MISS_MESSANGE_LIST.length)]);

        return combatMessage;
    }

    public CombatMessage buildPlayerMissMessage(String monster) {
        CombatMessage combatMessage = new CombatMessage();

        combatMessage.addData("monster", monster);
        combatMessage.addData("icon", "shield");
        combatMessage.addData("icon_color", "blue");
        combatMessage.addData("message", PLAYER_MISS_MESSANGE_LIST[random.nextInt(PLAYER_MISS_MESSANGE_LIST.length)]);

        return combatMessage;
    }

    public CombatMessage buildPlayerRangedMissMessage(String monster) {
        CombatMessage combatMessage = new CombatMessage();

        combatMessage.addData("monster", monster);
        combatMessage.addData("icon", "shield");
        combatMessage.addData("icon_color", "blue");
        combatMessage.addData("message", PLAYER_RANGED_MISS_MESSANGE_LIST[random.nextInt(PLAYER_RANGED_MISS_MESSANGE_LIST.length)]);

        return combatMessage;
    }

    public CombatMessage buildMonsterKilledMessage(String monster) {
        CombatMessage combatMessage = new CombatMessage();

        combatMessage.addData("monster", monster);
        combatMessage.addData("icon", "death");
        combatMessage.addData("icon_color", "blue");
        combatMessage.addData("message", MONSTER_DIE_MESSANGE_LIST[random.nextInt(MONSTER_DIE_MESSANGE_LIST.length)]);

        return combatMessage;
    }

    public CombatMessage buildPlayerKilledMessage(String monster) {
        CombatMessage combatMessage = new CombatMessage();

        combatMessage.addData("monster", monster);
        combatMessage.addData("icon", "death");
        combatMessage.addData("icon_color", "red");
        combatMessage.addData("message", PLAYER_DIE_MESSANGE_LIST[random.nextInt(PLAYER_DIE_MESSANGE_LIST.length)]);

        return combatMessage;
    }

    public CombatMessage buildDropMessage(String itemName, int amount) {
        CombatMessage combatMessage = new CombatMessage();

        combatMessage.addData("item", itemName);
        combatMessage.addData("amount", amount);
        combatMessage.addData("message", DROP_MESSANGE_LIST[random.nextInt(DROP_MESSANGE_LIST.length)]);

        return combatMessage;
    }

    public CombatMessage buildNewTurnMessage(int turn) {
        CombatMessage combatMessage = new CombatMessage();

        combatMessage.addData("turn", turn);
        combatMessage.addData("message", NEW_TURN_LIST[random.nextInt(NEW_TURN_LIST.length)]);

        return combatMessage;
    }

    public CombatMessage buildXpRewardMessage(String skillName, int amount) {
        CombatMessage combatMessage = new CombatMessage();

        combatMessage.addData("skill", WordUtils.capitalize(skillName.replace("_", " ").toLowerCase()));
        combatMessage.addData("amount", amount);
        combatMessage.addData("message", REWARD_XP_LIST[random.nextInt(REWARD_XP_LIST.length)]);

        return combatMessage;
    }

    public CombatMessage buildScavengeItemAwardMessage(String itemName, int amount) {
        CombatMessage combatMessage = new CombatMessage();

        combatMessage.addData("item", itemName);
        combatMessage.addData("amount", amount);
        combatMessage.addData("message", SCAVENGING_ITEM_MESSANGE_LIST[random.nextInt(SCAVENGING_ITEM_MESSANGE_LIST.length)]);

        return combatMessage;
    }

    public CombatMessage buildScavengeXpAwardMessage(int amount) {
        CombatMessage combatMessage = new CombatMessage();

        combatMessage.addData("amount", amount);
        combatMessage.addData("message", SCAVENGING_XP_MESSANGE_LIST[random.nextInt(SCAVENGING_XP_MESSANGE_LIST.length)]);

        return combatMessage;
    }
}
