package com.morethanheroic.swords.combat.domain;

import java.util.LinkedList;

public class CombatResult {

    private LinkedList<CombatMessage> combatMessages = new LinkedList<>();

    public void addMessage(CombatMessage message) {
        combatMessages.add(message);
    }

    public LinkedList<CombatMessage> getCombatMessages() {
        return combatMessages;
    }
}
