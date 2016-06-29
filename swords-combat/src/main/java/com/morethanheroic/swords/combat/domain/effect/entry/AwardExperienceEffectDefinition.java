package com.morethanheroic.swords.combat.domain.effect.entry;

import com.morethanheroic.swords.combat.domain.CombatEffectDataHolder;
import com.morethanheroic.swords.combat.domain.CombatEffectServiceAccessor;
import com.morethanheroic.swords.combat.domain.CombatMessage;
import com.morethanheroic.swords.combat.domain.CombatResult;
import com.morethanheroic.swords.combat.domain.effect.CombatEffectDefinition;
import com.morethanheroic.swords.combat.domain.entity.CombatEntity;
import com.morethanheroic.swords.combat.domain.entity.UserCombatEntity;
import com.morethanheroic.swords.effect.domain.EffectSettingDefinitionHolder;
import com.morethanheroic.swords.skill.domain.SkillEntity;
import com.morethanheroic.swords.skill.domain.SkillType;

public class AwardExperienceEffectDefinition extends CombatEffectDefinition {

    private final SkillType skillType;
    private final int amount;

    public AwardExperienceEffectDefinition(EffectSettingDefinitionHolder effectSettingDefinitionHolder) {
        super(effectSettingDefinitionHolder);

        skillType = SkillType.valueOf(this.getEffectSetting("skill").getValue());
        amount = Integer.parseInt(this.getEffectSetting("amount").getValue());
    }

    @Override
    public void apply(CombatEntity combatEntity, CombatResult combatResult, CombatEffectDataHolder combatEffectDataHolder, CombatEffectServiceAccessor combatEffectServiceAccessor) {
        if (combatEntity instanceof UserCombatEntity) {
            final SkillEntity skillEntity = combatEffectServiceAccessor.getSkillEntityFactory().getSkillEntity(((UserCombatEntity) combatEntity).getUserEntity());

            skillEntity.increaseExperience(skillType, amount);
        } else {
            throw new IllegalArgumentException("Only users supported to cast this spell (yet).");
        }
    }
}
