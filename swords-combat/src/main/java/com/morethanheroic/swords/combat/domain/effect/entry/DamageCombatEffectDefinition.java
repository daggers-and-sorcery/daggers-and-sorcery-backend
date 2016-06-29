package com.morethanheroic.swords.combat.domain.effect.entry;

import com.morethanheroic.swords.attribute.domain.DiceAttribute;
import com.morethanheroic.swords.combat.domain.CombatEffectDataHolder;
import com.morethanheroic.swords.combat.domain.CombatEffectServiceAccessor;
import com.morethanheroic.swords.combat.domain.CombatMessage;
import com.morethanheroic.swords.combat.domain.CombatResult;
import com.morethanheroic.swords.combat.domain.effect.CombatEffectDefinition;
import com.morethanheroic.swords.combat.domain.entity.CombatEntity;
import com.morethanheroic.swords.effect.domain.EffectSettingDefinitionHolder;

public class DamageCombatEffectDefinition extends CombatEffectDefinition {

    //TODO: create a DiceCalculationContext instead of directly tieing everything to DiceAttribute.
    private final DiceAttribute diceAttribute;

    public DamageCombatEffectDefinition(EffectSettingDefinitionHolder effectSettingDefinitionHolder) {
        super(effectSettingDefinitionHolder);

        final DiceAttribute.DiceAttributeBuilder diceAttributeBuilder = new DiceAttribute.DiceAttributeBuilder();

        if (this.getEffectSetting("value") != null) {
            diceAttributeBuilder.setValue(Integer.parseInt(this.getEffectSetting("value").getValue()));
        }

        if (this.getEffectSetting("d2") != null) {
            diceAttributeBuilder.setD2(Integer.parseInt(this.getEffectSetting("d2").getValue()));
        }

        if (this.getEffectSetting("d4") != null) {
            diceAttributeBuilder.setD4(Integer.parseInt(this.getEffectSetting("d4").getValue()));
        }

        if (this.getEffectSetting("d6") != null) {
            diceAttributeBuilder.setD6(Integer.parseInt(this.getEffectSetting("d6").getValue()));
        }

        if (this.getEffectSetting("d8") != null) {
            diceAttributeBuilder.setD8(Integer.parseInt(this.getEffectSetting("d8").getValue()));
        }

        if (this.getEffectSetting("d10") != null) {
            diceAttributeBuilder.setD10(Integer.parseInt(this.getEffectSetting("d10").getValue()));
        }

        diceAttribute = diceAttributeBuilder.build();
    }

    @Override
    public void apply(CombatEntity combatEntity, CombatResult combatResult, CombatEffectDataHolder combatEffectDataHolder, CombatEffectServiceAccessor combatEffectServiceAccessor) {
        final int damage = combatEffectServiceAccessor.getDiceRollCalculator().rollDices(combatEffectServiceAccessor.getDiceAttributeToDiceRollCalculationContextConverter().convert(diceAttribute));

        final CombatMessage combatMessage = new CombatMessage();

        combatMessage.addData("damage", damage);
        combatMessage.addData("icon", "blood");
        combatMessage.addData("icon_color", "blue");
        combatMessage.addData("message", "Your opponent is damaged for ${damage} health!");

        combatResult.addMessage(combatMessage);

        combatEntity.decreaseActualHealth(damage);
    }
}
