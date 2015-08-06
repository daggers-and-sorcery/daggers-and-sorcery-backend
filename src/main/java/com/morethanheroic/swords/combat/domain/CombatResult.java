package com.morethanheroic.swords.combat.domain;

import java.util.LinkedList;

public class CombatResult {

    private LinkedList<CombatMessage> combatMessages = new LinkedList<>();

    private Winner winner;

    public void addMessage(CombatMessage message) {
        combatMessages.add(message);
    }

    public LinkedList<CombatMessage> getCombatMessages() {
        return combatMessages;
    }

    public Winner getWinner() {
        return winner;
    }

    public void setWinner(Winner winner) {
        this.winner = winner;
    }
}
