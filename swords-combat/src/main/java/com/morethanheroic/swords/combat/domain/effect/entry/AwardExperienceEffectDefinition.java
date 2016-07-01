package com.morethanheroic.swords.combat.domain.effect.entry;

import com.morethanheroic.swords.combat.domain.CombatEffectDataHolder;
import com.morethanheroic.swords.combat.domain.CombatEffectServiceAccessor;
import com.morethanheroic.swords.combat.domain.CombatResult;
import com.morethanheroic.swords.combat.domain.effect.CombatEffectApplyingContext;
import com.morethanheroic.swords.combat.domain.effect.CombatEffectDefinition;
import com.morethanheroic.swords.combat.domain.effect.CombatEffectTarget;
import com.morethanheroic.swords.combat.domain.entity.UserCombatEntity;
import com.morethanheroic.swords.effect.domain.EffectSettingDefinitionHolder;
import com.morethanheroic.swords.skill.domain.SkillEntity;
import com.morethanheroic.swords.skill.domain.SkillType;
import com.morethanheroic.swords.user.domain.UserEntity;

public class AwardExperienceEffectDefinition extends CombatEffectDefinition {

    private final SkillType skillType;
    private final int amount;

    public AwardExperienceEffectDefinition(EffectSettingDefinitionHolder effectSettingDefinitionHolder) {
        super(effectSettingDefinitionHolder);

        skillType = SkillType.valueOf(this.getEffectSetting("skill").getValue());
        amount = Integer.parseInt(this.getEffectSetting("amount").getValue());
    }

    @Override
    public void apply(CombatEffectApplyingContext effectApplyingContext, CombatEffectDataHolder combatEffectDataHolder,
            CombatEffectServiceAccessor combatEffectServiceAccessor) {
        if (!effectApplyingContext.getDestination().isUser()) {
            return;
        }

        final SkillEntity skillEntity = combatEffectServiceAccessor.getSkillEntityFactory()
                .getSkillEntity(getUserEntity(effectApplyingContext.getDestination()));

        effectApplyingContext.getCombatResult().addRewardXp(skillType, amount);
        skillEntity.increaseExperience(skillType, amount);
    }

    private UserEntity getUserEntity(final CombatEffectTarget combatEffectTarget) {
        return ((UserCombatEntity) combatEffectTarget.getCombatEntity()).getUserEntity();
    }
}
