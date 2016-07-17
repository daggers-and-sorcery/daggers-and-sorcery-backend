package com.morethanheroic.swords.combat.domain.effect.entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.morethanheroic.swords.attribute.domain.DiceAttribute;
import com.morethanheroic.swords.combat.domain.*;
import com.morethanheroic.swords.combat.domain.effect.CombatEffectApplyingContext;
import com.morethanheroic.swords.combat.domain.effect.CombatEffectDefinition;
import com.morethanheroic.swords.dice.domain.DiceRollCalculationContext;
import com.morethanheroic.swords.dice.service.DiceRollCalculator;
import com.morethanheroic.swords.effect.domain.EffectSettingDefinitionHolder;

@Service
public class DamageCombatEffectDefinition extends CombatEffectDefinition {

    @Autowired
    private DiceRollCalculator diceRollCalculator;

    @Override
    public void apply(CombatEffectApplyingContext effectApplyingContext, CombatEffectDataHolder combatEffectDataHolder) {
        final DiceRollCalculationContext diceRollCalculationContext = buildDiceRollCalculationContext(effectApplyingContext.getEffectSettings());

        int damage = diceRollCalculator.rollDices(diceRollCalculationContext);

        final CombatMessage combatMessage = new CombatMessage();

        combatMessage.addData("damage", damage);
        combatMessage.addData("icon", "blood");
        combatMessage.addData("icon_color", "blue");
        combatMessage.addData("message", "Your opponent is damaged for ${damage} health!");

        effectApplyingContext.getCombatResult().addMessage(combatMessage);
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
