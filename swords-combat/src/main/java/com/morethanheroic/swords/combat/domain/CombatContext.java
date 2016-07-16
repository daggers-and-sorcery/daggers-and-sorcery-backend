package com.morethanheroic.swords.combat.domain;

import com.morethanheroic.swords.combat.domain.entity.MonsterCombatEntity;
import com.morethanheroic.swords.combat.domain.entity.UserCombatEntity;
import com.morethanheroic.swords.skill.domain.SkillType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.EnumMap;

@Getter
@Builder
public class CombatContext {

    //TODO: later if we introduce pvp use CombatEntity here only
    private final UserCombatEntity user;
    private final MonsterCombatEntity opponent;

    @Setter
    private Winner winner;

    private final EnumMap<SkillType, Integer> rewardXp = new EnumMap<>(SkillType.class);

    public void addRewardXp(SkillType skill, int amount) {
        if (rewardXp.containsKey(skill)) {
            rewardXp.put(skill, rewardXp.get(skill) + amount);
        } else {
            rewardXp.put(skill, amount);
        }
    }
}
