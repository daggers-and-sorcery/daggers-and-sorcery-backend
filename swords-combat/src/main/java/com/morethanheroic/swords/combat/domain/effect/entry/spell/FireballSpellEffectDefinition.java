package com.morethanheroic.swords.combat.domain.effect.entry.spell;

import com.morethanheroic.swords.attribute.domain.DiceAttribute;
import com.morethanheroic.swords.combat.domain.CombatEffectDataHolder;
import com.morethanheroic.swords.combat.domain.CombatEffectServiceAccessor;
import com.morethanheroic.swords.combat.domain.CombatMessage;
import com.morethanheroic.swords.combat.domain.effect.CombatEffectApplyingContext;
import com.morethanheroic.swords.combat.domain.effect.CombatEffectDefinition;
import com.morethanheroic.swords.combat.domain.entity.UserCombatEntity;
import com.morethanheroic.swords.effect.domain.EffectSettingDefinitionHolder;
import com.morethanheroic.swords.skill.domain.SkillEntity;
import com.morethanheroic.swords.skill.domain.SkillType;

public class FireballSpellEffectDefinition extends CombatEffectDefinition {

    //TODO: create a DiceCalculationContext instead of directly tieing everything to DiceAttribute.
    private final DiceAttribute diceAttribute;

    public FireballSpellEffectDefinition(EffectSettingDefinitionHolder effectSettingDefinitionHolder) {
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
    public void apply(CombatEffectApplyingContext effectApplyingContext, CombatEffectDataHolder combatEffectDataHolder,
            CombatEffectServiceAccessor combatEffectServiceAccessor) {
        int damage = combatEffectServiceAccessor.getDiceRollCalculator()
                .rollDices(combatEffectServiceAccessor.getDiceAttributeToDiceRollCalculationContextConverter().convert(diceAttribute));

        if (effectApplyingContext.getSource().isUser()) {
            final SkillEntity skillEntity = combatEffectServiceAccessor.getSkillEntityFactory()
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

    @Override
    public String getId() {
        return "fireball";
    }
}