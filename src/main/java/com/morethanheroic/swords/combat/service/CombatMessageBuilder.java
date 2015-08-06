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
    private final String[] DAMAGE_TO_PLAYER_MESSANGE_LIST = new String[]{
            "${monster} attacked you and successfully dealt ${damage} points of damage."
    };
    private final String[] MONSTER_MISS_MESSANGE_LIST = new String[]{
            "${monster} attacked you but missed."
    };
    private final String[] PLAYER_MISS_MESSANGE_LIST = new String[]{
            "You attacked ${monster} but missed."
    };
    private final String[] MONSTER_DIE_MESSANGE_LIST = new String[]{
            "You killed ${monster}."
    };
    private final String[] PLAYER_DIE_MESSANGE_LIST = new String[]{
            "${monster} killed you!"
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
        combatMessage.addData("message", DAMAGE_TO_MONSTER_MESSANGE_LIST[random.nextInt(DAMAGE_TO_MONSTER_MESSANGE_LIST.length)]);

        return combatMessage;
    }

    public CombatMessage buildDamageToPlayerMessage(String monster, int damage) {
        CombatMessage combatMessage = new CombatMessage();

        combatMessage.addData("monster", monster);
        combatMessage.addData("damage", damage);
        combatMessage.addData("icon", "blood");
        combatMessage.addData("message", DAMAGE_TO_PLAYER_MESSANGE_LIST[random.nextInt(DAMAGE_TO_PLAYER_MESSANGE_LIST.length)]);

        return combatMessage;
    }

    public CombatMessage buildMonsterMissMessage(String monster) {
        CombatMessage combatMessage = new CombatMessage();

        combatMessage.addData("monster", monster);
        combatMessage.addData("icon", "shield");
        combatMessage.addData("message", MONSTER_MISS_MESSANGE_LIST[random.nextInt(MONSTER_MISS_MESSANGE_LIST.length)]);

        return combatMessage;
    }

    public CombatMessage buildPlayerMissMessage(String monster) {
        CombatMessage combatMessage = new CombatMessage();

        combatMessage.addData("monster", monster);
        combatMessage.addData("icon", "shield");
        combatMessage.addData("message", PLAYER_MISS_MESSANGE_LIST[random.nextInt(PLAYER_MISS_MESSANGE_LIST.length)]);

        return combatMessage;
    }

    public CombatMessage buildMonsterKilledMessage(String monster) {
        CombatMessage combatMessage = new CombatMessage();

        combatMessage.addData("monster", monster);
        combatMessage.addData("icon", "death");
        combatMessage.addData("message", MONSTER_DIE_MESSANGE_LIST[random.nextInt(MONSTER_DIE_MESSANGE_LIST.length)]);

        return combatMessage;
    }

    public CombatMessage buildPlayerKilledMessage(String monster) {
        CombatMessage combatMessage = new CombatMessage();

        combatMessage.addData("monster", monster);
        combatMessage.addData("icon", "death");
        combatMessage.addData("message", PLAYER_DIE_MESSANGE_LIST[random.nextInt(PLAYER_DIE_MESSANGE_LIST.length)]);

        return combatMessage;
    }
}
