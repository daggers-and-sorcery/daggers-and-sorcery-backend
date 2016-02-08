package com.morethanheroic.swords.combat.domain;

import com.morethanheroic.swords.skill.domain.SkillType;

import java.util.Collections;
import java.util.EnumMap;
import java.util.LinkedList;
import java.util.Map;

public class CombatResult {

    private LinkedList<CombatMessage> combatMessages = new LinkedList<>();

    private EnumMap<SkillType, Integer> rewardXp = new EnumMap<>(SkillType.class);

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

    public void addRewardXp(SkillType skill, int amount) {
        if (rewardXp.containsKey(skill)) {
            rewardXp.put(skill, rewardXp.get(skill) + amount);
        } else {
            rewardXp.put(skill, amount);
        }
    }

    public Map<SkillType, Integer> getRewardXpMap() {
        return Collections.unmodifiableMap(rewardXp);
    }
}
