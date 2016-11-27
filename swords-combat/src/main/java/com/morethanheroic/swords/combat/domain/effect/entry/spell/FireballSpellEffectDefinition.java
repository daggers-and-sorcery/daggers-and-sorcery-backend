package com.morethanheroic.swords.combat.domain.effect.entry.spell;

import com.morethanheroic.swords.combat.domain.effect.CombatEffectApplyingContext;
import com.morethanheroic.swords.combat.domain.effect.DiceRollFromDamageSettingsBuilder;
import com.morethanheroic.swords.combat.domain.effect.ImprovedCombatEffectDefinition;
import com.morethanheroic.swords.combat.domain.entity.UserCombatEntity;
import com.morethanheroic.swords.combat.domain.step.DefaultCombatStep;
import com.morethanheroic.swords.combat.service.CombatCalculator;
import com.morethanheroic.swords.combat.service.message.CombatMessageFactory;
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
public class FireballSpellEffectDefinition extends ImprovedCombatEffectDefinition {

    private static final int AWARDED_DESTRUCTION_XP = 40;

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

            final SkillEntity skillEntity = skillEntityFactory.getEntity(userEntity.getId());

            if (skillEntity.getLevel(SkillType.DESTRUCTION) <= 10) {
                damage += Math.floor(skillEntity.getLevel(SkillType.DESTRUCTION) / 2);
            } else {
                damage += 5;
            }

            effectApplyingContext.addCombatStep(
                    DefaultCombatStep.builder()
                            .message(combatMessageFactory.newMessage("damage_done", "FIREBALL_SPELL_DAMAGE_DONE", damage))
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
        return "fireball_spell";
    }
}
