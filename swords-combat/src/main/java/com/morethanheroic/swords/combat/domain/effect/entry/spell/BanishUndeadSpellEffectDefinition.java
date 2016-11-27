package com.morethanheroic.swords.combat.domain.effect.entry.spell;

import com.morethanheroic.swords.combat.domain.effect.CombatEffectApplyingContext;
import com.morethanheroic.swords.combat.domain.effect.DiceRollFromDamageSettingsBuilder;
import com.morethanheroic.swords.combat.domain.effect.ImprovedCombatEffectDefinition;
import com.morethanheroic.swords.combat.domain.entity.MonsterCombatEntity;
import com.morethanheroic.swords.combat.domain.entity.UserCombatEntity;
import com.morethanheroic.swords.combat.domain.step.DefaultCombatStep;
import com.morethanheroic.swords.combat.service.CombatCalculator;
import com.morethanheroic.swords.combat.service.message.CombatMessageFactory;
import com.morethanheroic.swords.dice.domain.DiceRollCalculationContext;
import com.morethanheroic.swords.dice.service.DiceRollCalculator;
import com.morethanheroic.swords.monster.domain.MonsterDefinition;
import com.morethanheroic.swords.monster.domain.MonsterType;
import com.morethanheroic.swords.skill.domain.SkillType;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BanishUndeadSpellEffectDefinition extends ImprovedCombatEffectDefinition {

    private static final int AWARDED_DESTRUCTION_XP = 60;

    private final CombatMessageFactory combatMessageFactory;
    private final DiceRollCalculator diceRollCalculator;
    private final CombatCalculator combatCalculator;
    private final DiceRollFromDamageSettingsBuilder diceRollFromDamageSettingsBuilder;

    @Override
    public void apply(CombatEffectApplyingContext effectApplyingContext) {
        if (effectApplyingContext.getSource().isUser()) {
            final MonsterDefinition opponentMonsterDefinition = ((MonsterCombatEntity) effectApplyingContext.getDestination().getCombatEntity()).getMonsterDefinition();

            if (opponentMonsterDefinition.getType() == MonsterType.UNDEAD) {
                final DiceRollCalculationContext diceRollCalculationContext = diceRollFromDamageSettingsBuilder.buildDiceRollCalculationContext(effectApplyingContext.getEffectSettings());
                final int damage = diceRollCalculator.rollDices(diceRollCalculationContext);

                effectApplyingContext.addCombatStep(
                        DefaultCombatStep.builder()
                                .message(combatMessageFactory.newMessage("damage_done", "BANISH_UNDEAD_DAMAGE_DONE", damage))
                                .build()
                );

                final UserEntity userEntity = ((UserCombatEntity) effectApplyingContext.getSource().getCombatEntity()).getUserEntity();

                effectApplyingContext.getDestination().getCombatEntity().decreaseActualHealth(damage);

                combatCalculator.addCombatExperience(userEntity, SkillType.DESTRUCTION, AWARDED_DESTRUCTION_XP);
            } else {
                effectApplyingContext.addCombatStep(
                        DefaultCombatStep.builder()
                                .message(combatMessageFactory.newMessage("damage_done", "BANISH_UNDEAD_INVALID_TYPE", opponentMonsterDefinition.getName()))
                                .build()
                );
            }
        } else {
            throw new IllegalArgumentException("Caster as a monster is not supported for banish undead.");
        }
    }

    @Override
    public String getId() {
        return "banish_undead_spell";
    }
}
