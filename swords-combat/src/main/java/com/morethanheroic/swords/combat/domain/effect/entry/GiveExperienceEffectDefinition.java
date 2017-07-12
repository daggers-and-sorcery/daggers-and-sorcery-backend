package com.morethanheroic.swords.combat.domain.effect.entry;

import org.springframework.stereotype.Service;

import com.morethanheroic.swords.combat.domain.effect.CombatEffectApplyingContext;
import com.morethanheroic.swords.combat.domain.effect.ImprovedCombatEffectDefinition;
import com.morethanheroic.swords.combat.entity.domain.UserCombatEntity;
import com.morethanheroic.swords.skill.domain.SkillEntity;
import com.morethanheroic.swords.skill.domain.SkillType;
import com.morethanheroic.swords.skill.service.factory.SkillEntityFactory;

import lombok.RequiredArgsConstructor;

/**
 * Give experience to the target if the target is an user. If it's a monster then nthing going to happen.
 */
@Service
@RequiredArgsConstructor
public class GiveExperienceEffectDefinition extends ImprovedCombatEffectDefinition {

    private final SkillEntityFactory skillEntityFactory;

    @Override
    public void apply(final CombatEffectApplyingContext effectApplyingContext) {
        if (effectApplyingContext.getDestination().isUser()) {
            final SkillType targetSkill = SkillType.valueOf(effectApplyingContext.getEffectSettings().getSetting("target-skill").getValue());
            final int experienceAmount = effectApplyingContext.getEffectSettings().getSettingAsInt("experience-amount");

            final SkillEntity skillEntity = skillEntityFactory
                    .getEntity(((UserCombatEntity) effectApplyingContext.getDestination().getCombatEntity()).getUserEntity());

            skillEntity.increaseExperience(targetSkill, experienceAmount);
        }
    }

    @Override
    public String getId() {
        return "give_experience";
    }
}
