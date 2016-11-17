package com.morethanheroic.swords.combat.domain.effect.entry.spell;

import org.springframework.stereotype.Service;

import com.morethanheroic.swords.combat.domain.CombatEffectDataHolder;
import com.morethanheroic.swords.combat.domain.effect.CombatEffectApplyingContext;
import com.morethanheroic.swords.combat.domain.effect.CombatEffectDefinition;
import com.morethanheroic.swords.combat.domain.entity.UserCombatEntity;
import com.morethanheroic.swords.combat.domain.step.DefaultCombatStep;
import com.morethanheroic.swords.combat.service.message.CombatMessageFactory;
import com.morethanheroic.swords.combat.service.CombatCalculator;
import com.morethanheroic.swords.dice.domain.DiceRollCalculationContext;
import com.morethanheroic.swords.dice.service.DiceRollCalculator;
import com.morethanheroic.swords.effect.domain.EffectSettingDefinitionHolder;
import com.morethanheroic.swords.skill.domain.SkillEntity;
import com.morethanheroic.swords.skill.domain.SkillType;
import com.morethanheroic.swords.skill.service.factory.SkillEntityFactory;
import com.morethanheroic.swords.user.domain.UserEntity;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FireballSpellEffectDefinition extends CombatEffectDefinition {

    private final DiceRollCalculator diceRollCalculator;
    private final SkillEntityFactory skillEntityFactory;
    private final CombatMessageFactory combatMessageFactory;
    private final CombatCalculator combatCalculator;

    @Override
    public void apply(CombatEffectApplyingContext effectApplyingContext, CombatEffectDataHolder combatEffectDataHolder) {
        final DiceRollCalculationContext diceRollCalculationContext = buildDiceRollCalculationContext(effectApplyingContext.getEffectSettings());

        int damage = diceRollCalculator.rollDices(diceRollCalculationContext);

        if (effectApplyingContext.getSource().isUser()) {
            final UserEntity userEntity = ((UserCombatEntity) effectApplyingContext.getSource().getCombatEntity()).getUserEntity();

            final SkillEntity skillEntity = skillEntityFactory.getSkillEntity(userEntity);

            if (skillEntity.getLevel(SkillType.DESTRUCTION) < 5) {
                damage += skillEntity.getLevel(SkillType.DESTRUCTION);
            } else {
                damage += 5;
            }

            effectApplyingContext.addCombatStep(
                DefaultCombatStep.builder()
                .message(combatMessageFactory.newMessage("damage_done", "FIREBALL_SPELL_DAMAGE_DONE", damage))
                .build()
            );

            effectApplyingContext.getDestination().getCombatEntity().decreaseActualHealth(damage);

            combatCalculator.addCombatExperience(userEntity,SkillType.DESTRUCTION, 20);

            skillEntity.increaseExperience(SkillType.DESTRUCTION, 20);
        } else {
            throw new IllegalArgumentException("Caster as a monster is not supported for fileball.");
        }
    }

    private DiceRollCalculationContext buildDiceRollCalculationContext(EffectSettingDefinitionHolder effectSettingDefinitionHolder) {
        return DiceRollCalculationContext.builder()
                .value(effectSettingDefinitionHolder.getSetting("value") == null ? 0
                        : Integer.parseInt(effectSettingDefinitionHolder.getSetting("value").getValue()))
                .d2(effectSettingDefinitionHolder.getSetting("d2") == null ? 0
                        : Integer.parseInt(effectSettingDefinitionHolder.getSetting("d2").getValue()))
                .d4(effectSettingDefinitionHolder.getSetting("d4") == null ? 0
                        : Integer.parseInt(effectSettingDefinitionHolder.getSetting("d4").getValue()))
                .d6(effectSettingDefinitionHolder.getSetting("d6") == null ? 0
                        : Integer.parseInt(effectSettingDefinitionHolder.getSetting("d6").getValue()))
                .d8(effectSettingDefinitionHolder.getSetting("d8") == null ? 0
                        : Integer.parseInt(effectSettingDefinitionHolder.getSetting("d8").getValue()))
                .d10(effectSettingDefinitionHolder.getSetting("d10") == null ? 0
                        : Integer.parseInt(effectSettingDefinitionHolder.getSetting("d10").getValue()))
                .build();
    }

    @Override
    public String getId() {
        return "fireball_spell";
    }
}