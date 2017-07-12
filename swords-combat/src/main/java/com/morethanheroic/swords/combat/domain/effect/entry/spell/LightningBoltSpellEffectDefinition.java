package com.morethanheroic.swords.combat.domain.effect.entry.spell;

import com.morethanheroic.swords.combat.domain.effect.CombatEffectApplyingContext;
import com.morethanheroic.swords.combat.domain.effect.DiceRollFromDamageSettingsBuilder;
import com.morethanheroic.swords.combat.domain.effect.ImprovedCombatEffectDefinition;
import com.morethanheroic.swords.combat.entity.domain.UserCombatEntity;
import com.morethanheroic.swords.combat.step.domain.DefaultCombatStep;
import com.morethanheroic.swords.combat.service.CombatCalculator;
import com.morethanheroic.swords.combat.step.message.CombatMessageFactory;
import com.morethanheroic.swords.dice.domain.DiceRollCalculationContext;
import com.morethanheroic.swords.dice.service.DiceRollCalculator;
import com.morethanheroic.swords.skill.domain.SkillEntity;
import com.morethanheroic.swords.skill.domain.SkillType;
import com.morethanheroic.swords.skill.service.factory.SkillEntityFactory;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LightningBoltSpellEffectDefinition extends ImprovedCombatEffectDefinition {

    private static final int AWARDED_DESTRUCTION_XP = 50;

    private final DiceRollCalculator diceRollCalculator;
    private final SkillEntityFactory skillEntityFactory;
    private final CombatMessageFactory combatMessageFactory;
    private final CombatCalculator combatCalculator;
    private final DiceRollFromDamageSettingsBuilder diceRollFromDamageSettingsBuilder;

    @Override
    public void apply(CombatEffectApplyingContext effectApplyingContext) {
        final DiceRollCalculationContext diceRollCalculationContext = diceRollFromDamageSettingsBuilder.buildDiceRollCalculationContext(effectApplyingContext.getEffectSettings());

        int damage = diceRollCalculator.rollDices(diceRollCalculationContext);

        if (effectApplyingContext.getSource().isUser()) {
            final UserEntity userEntity = ((UserCombatEntity) effectApplyingContext.getSource().getCombatEntity()).getUserEntity();

            final SkillEntity skillEntity = skillEntityFactory.getEntity(userEntity);

            if (skillEntity.getLevel(SkillType.DESTRUCTION) <= 15) {
                damage += Math.floor(skillEntity.getLevel(SkillType.DESTRUCTION) / 3);
            } else {
                damage += 7;
            }

            effectApplyingContext.addCombatStep(
                    DefaultCombatStep.builder()
                            .message(combatMessageFactory.newMessage("damage_done", "LIGHTNING_BOLT_SPELL_DAMAGE_DONE", damage))
                            .build()
            );

            effectApplyingContext.getDestination().getCombatEntity().decreaseActualHealth(damage);

            combatCalculator.addCombatExperience(userEntity, SkillType.DESTRUCTION, AWARDED_DESTRUCTION_XP);
        } else {
            throw new IllegalArgumentException("Caster as a monster is not supported for fireball.");
        }
    }

    @Override
    public String getId() {
        return "lightning_bolt_spell";
    }
}
