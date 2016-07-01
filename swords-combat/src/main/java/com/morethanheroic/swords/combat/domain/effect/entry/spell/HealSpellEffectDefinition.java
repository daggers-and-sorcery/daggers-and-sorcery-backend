package com.morethanheroic.swords.combat.domain.effect.entry.spell;

import com.morethanheroic.swords.combat.domain.*;
import com.morethanheroic.swords.combat.domain.effect.CombatEffectApplyingContext;
import com.morethanheroic.swords.combat.domain.effect.CombatEffectDefinition;
import com.morethanheroic.swords.combat.domain.entity.UserCombatEntity;
import com.morethanheroic.swords.effect.domain.EffectSettingDefinitionHolder;
import com.morethanheroic.swords.skill.domain.SkillEntity;
import com.morethanheroic.swords.skill.domain.SkillType;

/**
 * Heal the user for a given amount of health points but it's also give Restoration experience. Created specifically for
 * the Heal spell.
 */
public class HealSpellEffectDefinition extends CombatEffectDefinition {

    private final int amount;
    private final int xp;

    public HealSpellEffectDefinition(EffectSettingDefinitionHolder effectSettingDefinitionHolder) {
        super(effectSettingDefinitionHolder);

        amount = Integer.parseInt(this.getEffectSetting("amount").getValue());
        xp = Integer.parseInt(this.getEffectSetting("xp").getValue());
    }

    @Override
    public void apply(CombatEffectApplyingContext effectApplyingContext, CombatEffectDataHolder combatEffectDataHolder, CombatEffectServiceAccessor combatEffectServiceAccessor) {
        if (effectApplyingContext.getSource().isUser()) {
            final SkillEntity skillEntity = combatEffectServiceAccessor.getSkillEntityFactory().getSkillEntity(((UserCombatEntity) effectApplyingContext.getSource().getCombatEntity()).getUserEntity());

            final int restorationAmount = calculateRestorationAmount(skillEntity);

            final CombatMessage combatMessage = new CombatMessage();

            combatMessage.addData("amount", restorationAmount);
            combatMessage.addData("icon", "heal");
            combatMessage.addData("message", "You have been healed for ${amount} health!");

            effectApplyingContext.getCombatResult().addMessage(combatMessage);

            effectApplyingContext.getSource().getCombatEntity().increaseActualHealth(restorationAmount);

            effectApplyingContext.getCombatResult().addRewardXp(SkillType.RESTORATION, xp);

            skillEntity.increaseExperience(SkillType.RESTORATION, xp);
        } else {
            throw new IllegalArgumentException("Only users supported to cast this spell (yet).");
        }
    }

    private int calculateRestorationAmount(SkillEntity skillEntity) {
        int restorationBonus = skillEntity.getLevel(SkillType.RESTORATION) / 2;

        if (restorationBonus > 5) {
            restorationBonus = 5;
        }

        return amount + restorationBonus;
    }
}
