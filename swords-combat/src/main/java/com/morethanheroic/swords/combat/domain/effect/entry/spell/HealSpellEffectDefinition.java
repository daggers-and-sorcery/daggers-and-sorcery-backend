package com.morethanheroic.swords.combat.domain.effect.entry.spell;

import org.springframework.stereotype.Service;

import com.morethanheroic.swords.combat.domain.CombatEffectDataHolder;
import com.morethanheroic.swords.combat.domain.effect.CombatEffectApplyingContext;
import com.morethanheroic.swords.combat.domain.effect.CombatEffectDefinition;
import com.morethanheroic.swords.combat.domain.entity.UserCombatEntity;
import com.morethanheroic.swords.combat.domain.step.DefaultCombatStep;
import com.morethanheroic.swords.combat.service.message.CombatMessageFactory;
import com.morethanheroic.swords.combat.service.CombatCalculator;
import com.morethanheroic.swords.skill.domain.SkillEntity;
import com.morethanheroic.swords.skill.domain.SkillType;
import com.morethanheroic.swords.skill.service.factory.SkillEntityFactory;
import com.morethanheroic.swords.user.domain.UserEntity;

import lombok.RequiredArgsConstructor;

/**
 * Heal the user for a given amount of health points but it's also give Restoration experience. Created specifically for
 * the Heal spell.
 */
@Service
@RequiredArgsConstructor
public class HealSpellEffectDefinition extends CombatEffectDefinition {

    private final SkillEntityFactory skillEntityFactory;
    private final CombatMessageFactory combatMessageFactory;
    private final CombatCalculator combatCalculator;

    @Override
    public void apply(CombatEffectApplyingContext effectApplyingContext, CombatEffectDataHolder combatEffectDataHolder) {
        if (effectApplyingContext.getSource().isUser()) {
            final UserEntity userEntity = ((UserCombatEntity) effectApplyingContext.getSource().getCombatEntity()).getUserEntity();

            int amount = Integer.parseInt(effectApplyingContext.getEffectSettings().getSetting("amount").getValue());
            int xp = Integer.parseInt(effectApplyingContext.getEffectSettings().getSetting("xp").getValue());

            final SkillEntity skillEntity = skillEntityFactory.getSkillEntity(userEntity);

            final int restorationAmount = calculateRestorationAmount(skillEntity, amount);

            effectApplyingContext.addCombatStep(
                DefaultCombatStep.builder()
                                 .message(combatMessageFactory.newMessage("heal", "HEAL_SPELL_HEALING_DONE", restorationAmount))
                                 .build()
            );

            effectApplyingContext.getSource().getCombatEntity().increaseActualHealth(restorationAmount);

            combatCalculator.addCombatExperience(userEntity, SkillType.RESTORATION, xp);

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
