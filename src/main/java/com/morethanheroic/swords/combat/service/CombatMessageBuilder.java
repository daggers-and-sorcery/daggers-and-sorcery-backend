package com.morethanheroic.swords.combat.service;

import com.morethanheroic.swords.combat.domain.CombatMessage;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class CombatMessageBuilder {

    private final Random random = new Random();
    private final String[] FIGHT_INITIAL_MESSANGE_LIST = new String[] {
            "This is a lovely fight with ${monster}.",
            "Yeah ${monster} is fighting me!!!"
    };

    public CombatMessage buildFightInitialisationMessage(String monster) {
        CombatMessage combatMessage = new CombatMessage();

        combatMessage.addData("monster", monster);
        combatMessage.addData("message", FIGHT_INITIAL_MESSANGE_LIST[random.nextInt(FIGHT_INITIAL_MESSANGE_LIST.length)]);

        return combatMessage;
    }
}
