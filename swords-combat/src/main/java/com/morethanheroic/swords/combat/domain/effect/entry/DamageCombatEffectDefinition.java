package com.morethanheroic.swords.combat.domain.effect.entry;

import org.springframework.stereotype.Service;

import com.morethanheroic.swords.combat.domain.*;
import com.morethanheroic.swords.combat.domain.effect.CombatEffectApplyingContext;
import com.morethanheroic.swords.combat.domain.effect.CombatEffectDefinition;
import com.morethanheroic.swords.combat.domain.step.DefaultCombatStep;
import com.morethanheroic.swords.combat.service.message.CombatMessageFactory;
import com.morethanheroic.swords.dice.domain.DiceRollCalculationContext;
import com.morethanheroic.swords.dice.service.DiceRollCalculator;
import com.morethanheroic.swords.effect.domain.EffectSettingDefinitionHolder;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DamageCombatEffectDefinition extends CombatEffectDefinition {

    private final DiceRollCalculator diceRollCalculator;
    private final CombatMessageFactory combatMessageFactory;

    @Override
    public void apply(CombatEffectApplyingContext effectApplyingContext, CombatEffectDataHolder combatEffectDataHolder) {
        final DiceRollCalculationContext diceRollCalculationContext = buildDiceRollCalculationContext(effectApplyingContext.getEffectSettings());

        int damage = diceRollCalculator.rollDices(diceRollCalculationContext);

        effectApplyingContext.addCombatStep(
            DefaultCombatStep.builder()
                             .message(combatMessageFactory.newMessage("damage_done", "DAMAGE_SPELL_DAMAGE_DONE", damage))
                             .build()
        );

        effectApplyingContext.getDestination().getCombatEntity().decreaseActualHealth(damage);
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
        return "damage";
    }
}
