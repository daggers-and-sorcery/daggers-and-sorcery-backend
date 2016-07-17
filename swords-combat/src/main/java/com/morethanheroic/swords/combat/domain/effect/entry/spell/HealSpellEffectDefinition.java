package com.morethanheroic.swords.combat.domain.effect.entry.spell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.morethanheroic.swords.combat.domain.CombatEffectDataHolder;
import com.morethanheroic.swords.combat.domain.CombatMessage;
import com.morethanheroic.swords.combat.domain.effect.CombatEffectApplyingContext;
import com.morethanheroic.swords.combat.domain.effect.CombatEffectDefinition;
import com.morethanheroic.swords.combat.domain.entity.UserCombatEntity;
import com.morethanheroic.swords.skill.domain.SkillEntity;
import com.morethanheroic.swords.skill.domain.SkillType;
import com.morethanheroic.swords.skill.service.factory.SkillEntityFactory;

/**
 * Heal the user for a given amount of health points but it's also give Restoration experience. Created specifically for
 * the Heal spell.
 */
@Service
public class HealSpellEffectDefinition extends CombatEffectDefinition {

    @Autowired
    private SkillEntityFactory skillEntityFactory;

    @Override
    public void apply(CombatEffectApplyingContext effectApplyingContext, CombatEffectDataHolder combatEffectDataHolder) {
        if (effectApplyingContext.getSource().isUser()) {
            int amount = Integer.parseInt(effectApplyingContext.getEffectSettings().getSetting("amount").getValue());
            int xp = Integer.parseInt(effectApplyingContext.getEffectSettings().getSetting("xp").getValue());

            final SkillEntity skillEntity = skillEntityFactory
                    .getSkillEntity(((UserCombatEntity) effectApplyingContext.getSource().getCombatEntity()).getUserEntity());

            final int restorationAmount = calculateRestorationAmount(skillEntity, amount);

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

    private int calculateRestorationAmount(SkillEntity skillEntity, int amount) {
        int restorationBonus = skillEntity.getLevel(SkillType.RESTORATION) / 2;

        if (restorationBonus > 5) {
            restorationBonus = 5;
        }

        return amount + restorationBonus;
    }

    @Override
    public String getId() {
        return "heal_spell";
    }
}
