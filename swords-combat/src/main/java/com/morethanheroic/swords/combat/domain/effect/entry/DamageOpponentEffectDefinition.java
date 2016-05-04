package com.morethanheroic.swords.combat.domain.effect.entry;

import com.morethanheroic.swords.attribute.domain.DiceAttribute;
import com.morethanheroic.swords.combat.domain.CombatEffectDataHolder;
import com.morethanheroic.swords.combat.domain.CombatEffectServiceAccessor;
import com.morethanheroic.swords.combat.domain.CombatMessage;
import com.morethanheroic.swords.combat.domain.CombatResult;
import com.morethanheroic.swords.combat.domain.effect.CombatEffectDefinition;
import com.morethanheroic.swords.combat.domain.entity.CombatEntity;
import com.morethanheroic.swords.effect.domain.EffectSettingDefinitionHolder;

public class DamageOpponentEffectDefinition extends CombatEffectDefinition {

    //TODO: create a DiceCalculationContext instead of directly tieing everything to DiceAttribute.
    private final DiceAttribute diceAttribute;

    public DamageOpponentEffectDefinition(EffectSettingDefinitionHolder effectSettingDefinitionHolder) {
        super(effectSettingDefinitionHolder);

        DiceAttribute.DiceAttributeBuilder diceAttributeBuilder = new DiceAttribute.DiceAttributeBuilder();

        diceAttributeBuilder.setValue(Integer.parseInt(this.getEffectSetting("value").getValue()));
        diceAttributeBuilder.setD2(Integer.parseInt(this.getEffectSetting("d2").getValue()));
        diceAttributeBuilder.setD4(Integer.parseInt(this.getEffectSetting("d4").getValue()));
        diceAttributeBuilder.setD6(Integer.parseInt(this.getEffectSetting("d6").getValue()));
        diceAttributeBuilder.setD8(Integer.parseInt(this.getEffectSetting("d8").getValue()));
        diceAttributeBuilder.setD10(Integer.parseInt(this.getEffectSetting("d10").getValue()));

        diceAttribute = diceAttributeBuilder.build();
    }

    @Override
    public void apply(CombatEntity combatEntity, CombatResult combatResult, CombatEffectDataHolder combatEffectDataHolder, CombatEffectServiceAccessor combatEffectServiceAccessor) {
        final int damage = combatEffectServiceAccessor.getDiceUtil().rollValueFromDiceAttribute(diceAttribute);

        final CombatMessage combatMessage = new CombatMessage();

        combatMessage.addData("damage", damage);
        combatMessage.addData("icon", "blood");
        combatMessage.addData("icon_color", "blue");
        combatMessage.addData("message", "You have been healed for ${damage} health!");

        combatResult.addMessage(combatMessage);

        //TODO: take care that the opponent is damaged not the player.
        combatEntity.decreaseActualHealth(damage);
    }
}
