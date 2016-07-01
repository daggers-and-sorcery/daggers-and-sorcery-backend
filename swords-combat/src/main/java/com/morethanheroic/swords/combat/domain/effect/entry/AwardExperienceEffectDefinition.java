package com.morethanheroic.swords.combat.domain.effect.entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.morethanheroic.swords.combat.domain.CombatEffectDataHolder;
import com.morethanheroic.swords.combat.domain.effect.CombatEffectApplyingContext;
import com.morethanheroic.swords.combat.domain.effect.CombatEffectDefinition;
import com.morethanheroic.swords.combat.domain.effect.CombatEffectTarget;
import com.morethanheroic.swords.combat.domain.entity.UserCombatEntity;
import com.morethanheroic.swords.skill.domain.SkillEntity;
import com.morethanheroic.swords.skill.domain.SkillType;
import com.morethanheroic.swords.skill.service.factory.SkillEntityFactory;
import com.morethanheroic.swords.user.domain.UserEntity;

@Service
public class AwardExperienceEffectDefinition extends CombatEffectDefinition {

    @Autowired
    private SkillEntityFactory skillEntityFactory;

    @Override
    public void apply(CombatEffectApplyingContext effectApplyingContext, CombatEffectDataHolder combatEffectDataHolder) {
        final SkillType skillType = SkillType.valueOf(effectApplyingContext.getEffectSettings().getSetting("skill").getValue());
        final int amount = Integer.parseInt(effectApplyingContext.getEffectSettings().getSetting("amount").getValue());

        if (!effectApplyingContext.getDestination().isUser()) {
            return;
        }

        final SkillEntity skillEntity = skillEntityFactory.getSkillEntity(getUserEntity(effectApplyingContext.getDestination()));

        effectApplyingContext.getCombatResult().addRewardXp(skillType, amount);
        skillEntity.increaseExperience(skillType, amount);
    }

    private UserEntity getUserEntity(final CombatEffectTarget combatEffectTarget) {
        return ((UserCombatEntity) combatEffectTarget.getCombatEntity()).getUserEntity();
    }

    @Override
    public String getId() {
        return "award_experience";
    }
}
