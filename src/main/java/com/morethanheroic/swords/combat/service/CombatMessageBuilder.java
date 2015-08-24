package com.morethanheroic.swords.combat.service;

import com.morethanheroic.swords.combat.domain.CombatMessage;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class CombatMessageBuilder {

    private final Random random = new Random();
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
    private final String[] NEW_TURN_LIST = new String[]{
            "New turn starts: ${turn}!"
    };
    private final String[] REWARD_XP_LIST = new String[]{
            "After the fight got ${amount} xp on ${skill}!"
    };

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

        combatMessage.addData("skill", skillName);
        combatMessage.addData("amount", amount);
        combatMessage.addData("message", NEW_TURN_LIST[random.nextInt(NEW_TURN_LIST.length)]);

        return combatMessage;
    }
}
