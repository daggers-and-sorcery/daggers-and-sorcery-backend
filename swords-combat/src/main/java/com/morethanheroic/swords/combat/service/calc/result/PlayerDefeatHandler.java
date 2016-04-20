package com.morethanheroic.swords.combat.service.calc.result;

import com.morethanheroic.swords.combat.domain.Combat;
import com.morethanheroic.swords.combat.domain.CombatResult;
import com.morethanheroic.swords.skill.domain.SkillEntity;
import com.morethanheroic.swords.skill.domain.SkillType;
import com.morethanheroic.swords.skill.service.HighestSkillCalculator;
import com.morethanheroic.swords.skill.service.factory.SkillEntityFactory;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class PlayerDefeatHandler {

    private final SkillEntityFactory skillEntityFactory;
    private final HighestSkillCalculator highestSkillCalculator;

    public void handleDefeat(Combat combat, CombatResult combatResult) {
        handleDeath(combat, combatResult);
    }

    private void handleDeath(Combat combat, CombatResult combatResult) {
        final UserEntity userEntity = combat.getUserCombatEntity().getUserEntity();
        //death mechanism
        final SkillEntity skillEntity = skillEntityFactory.getSkillEntity(userEntity);

        List<SkillType> highestTreeSkill = highestSkillCalculator.getHighestSkills(skillEntity);

        for (SkillType skill : highestTreeSkill) {
            //TODO:
            //skillEntity.removeSkillXp(skill);
        }
    }
}
