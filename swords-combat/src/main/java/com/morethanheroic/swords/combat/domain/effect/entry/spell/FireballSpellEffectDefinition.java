package com.morethanheroic.swords.combat.domain.effect.entry.spell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.morethanheroic.swords.combat.domain.CombatEffectDataHolder;
import com.morethanheroic.swords.combat.domain.CombatMessage;
import com.morethanheroic.swords.combat.domain.effect.CombatEffectApplyingContext;
import com.morethanheroic.swords.combat.domain.effect.CombatEffectDefinition;
import com.morethanheroic.swords.combat.domain.entity.UserCombatEntity;
import com.morethanheroic.swords.dice.domain.DiceRollCalculationContext;
import com.morethanheroic.swords.dice.service.DiceRollCalculator;
import com.morethanheroic.swords.effect.domain.EffectSettingDefinitionHolder;
import com.morethanheroic.swords.skill.domain.SkillEntity;
import com.morethanheroic.swords.skill.domain.SkillType;
import com.morethanheroic.swords.skill.service.factory.SkillEntityFactory;

@Service
public class FireballSpellEffectDefinition extends CombatEffectDefinition {

    @Autowired
    private DiceRollCalculator diceRollCalculator;

    @Autowired
    private SkillEntityFactory skillEntityFactory;

    @Override
    public void apply(CombatEffectApplyingContext effectApplyingContext, CombatEffectDataHolder combatEffectDataHolder) {
        final DiceRollCalculationContext diceRollCalculationContext = buildDiceRollCalculationContext(effectApplyingContext.getEffectSettings());

        int damage = diceRollCalculator.rollDices(diceRollCalculationContext);

        if (effectApplyingContext.getSource().isUser()) {
            final SkillEntity skillEntity = skillEntityFactory
                    .getSkillEntity(((UserCombatEntity) effectApplyingContext.getSource().getCombatEntity()).getUserEntity());

            if (skillEntity.getLevel(SkillType.DESTRUCTION) < 5) {
                damage += skillEntity.getLevel(SkillType.DESTRUCTION);
            } else {
                damage += 5;
            }

            final CombatMessage combatMessage = new CombatMessage();

            combatMessage.addData("damage", damage);
            combatMessage.addData("icon", "blood");
            combatMessage.addData("icon_color", "blue");
            combatMessage.addData("message", "Your opponent is damaged for ${damage} health!");

            effectApplyingContext.getCombatResult().addMessage(combatMessage);

            effectApplyingContext.getSource().getCombatEntity().decreaseActualHealth(damage);
        } else {
            throw new IllegalArgumentException("Caster as a monster is not supported for fileball.");
        }
    }

    private DiceRollCalculationContext buildDiceRollCalculationContext(EffectSettingDefinitionHolder effectSettingDefinitionHolder) {
        return DiceRollCalculationContext.builder()
            .value(effectSettingDefinitionHolder.getSetting("value") == null ? 0 : Integer.parseInt(effectSettingDefinitionHolder.getSetting("value").getValue()))
            .d2(effectSettingDefinitionHolder.getSetting("d2") == null ? 0 : Integer.parseInt(effectSettingDefinitionHolder.getSetting("d2").getValue()))
            .d4(effectSettingDefinitionHolder.getSetting("d4") == null ? 0 : Integer.parseInt(effectSettingDefinitionHolder.getSetting("d4").getValue()))
            .d6(effectSettingDefinitionHolder.getSetting("d6") == null ? 0 : Integer.parseInt(effectSettingDefinitionHolder.getSetting("d6").getValue()))
            .d8(effectSettingDefinitionHolder.getSetting("d8") == null ? 0 : Integer.parseInt(effectSettingDefinitionHolder.getSetting("d8").getValue()))
            .d10(effectSettingDefinitionHolder.getSetting("d10") == null ? 0 : Integer.parseInt(effectSettingDefinitionHolder.getSetting("d10").getValue()))
            .build();
    }

    @Override
    public String getId() {
        return "fireball_spell";
    }
}