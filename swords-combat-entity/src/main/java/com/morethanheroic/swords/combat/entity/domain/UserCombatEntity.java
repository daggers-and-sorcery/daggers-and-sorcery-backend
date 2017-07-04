package com.morethanheroic.swords.combat.entity.domain;

import com.morethanheroic.swords.attribute.domain.CombatAttribute;
import com.morethanheroic.swords.attribute.domain.DiceAttribute;
import com.morethanheroic.swords.attribute.service.calc.GlobalAttributeCalculator;
import com.morethanheroic.swords.attribute.service.calc.domain.calculation.AttributeCalculationResult;
import com.morethanheroic.swords.attribute.service.calc.domain.calculation.CombatAttributeCalculationResult;
import com.morethanheroic.swords.user.domain.UserEntity;

public class UserCombatEntity extends CombatEntity {

    private final GlobalAttributeCalculator globalAttributeCalculator;
    private final UserEntity userEntity;

    public UserCombatEntity(UserEntity userEntity, GlobalAttributeCalculator globalAttributeCalculator) {
        this.globalAttributeCalculator = globalAttributeCalculator;
        this.userEntity = userEntity;

        this.setActualHealth(userEntity.getHealthPoints());
        this.setMaximumHealth(globalAttributeCalculator.calculateMaximumValue(userEntity, CombatAttribute.LIFE).getValue());

        this.setActualMana(userEntity.getManaPoints());
        this.setMaximumMana(globalAttributeCalculator.calculateMaximumValue(userEntity, CombatAttribute.MANA).getValue());
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    @Override
    public DiceAttribute getAttack() {
        return attributeCalculationToDiceAttribute(globalAttributeCalculator.calculateActualValue(userEntity, CombatAttribute.ATTACK));
    }

    @Override
    public DiceAttribute getDefense() {
        return attributeCalculationToDiceAttribute(globalAttributeCalculator.calculateActualValue(userEntity, CombatAttribute.DEFENSE));
    }

    @Override
    public DiceAttribute getDamageReduction() {
        return attributeCalculationToDiceAttribute(globalAttributeCalculator.calculateActualValue(userEntity, CombatAttribute.DAMAGE_REDUCTION));
    }

    @Override
    public DiceAttribute getDamage() {
        return attributeCalculationToDiceAttribute(globalAttributeCalculator.calculateActualValue(userEntity, CombatAttribute.DAMAGE));
    }

    @Override
    public DiceAttribute getRangedDamage() {
        return attributeCalculationToDiceAttribute(globalAttributeCalculator.calculateActualValue(userEntity, CombatAttribute.RANGED_DAMAGE));
    }

    @Override
    public DiceAttribute getAiming() {
        return attributeCalculationToDiceAttribute(globalAttributeCalculator.calculateActualValue(userEntity, CombatAttribute.AIMING));
    }

    @Override
    public DiceAttribute getMagicAttack() {
        return attributeCalculationToDiceAttribute(globalAttributeCalculator.calculateActualValue(userEntity, CombatAttribute.MAGIC_ATTACK));
    }

    @Override
    public DiceAttribute getMagicDamage() {
        return attributeCalculationToDiceAttribute(globalAttributeCalculator.calculateActualValue(userEntity, CombatAttribute.MAGIC_DAMAGE));
    }

    @Override
    public DiceAttribute getSpellResistance() {
        return attributeCalculationToDiceAttribute(globalAttributeCalculator.calculateActualValue(userEntity, CombatAttribute.SPELL_RESISTANCE));
    }

    @Override
    public String getName() {
        return userEntity.getUsername();
    }

    private DiceAttribute attributeCalculationToDiceAttribute(AttributeCalculationResult attributeCalculationResult) {
        final DiceAttribute.DiceAttributeBuilder diceAttributeBuilder = new DiceAttribute.DiceAttributeBuilder();

        diceAttributeBuilder.setValue(attributeCalculationResult.getValue());

        if (attributeCalculationResult instanceof CombatAttributeCalculationResult) {
            diceAttributeBuilder.setD2(((CombatAttributeCalculationResult) attributeCalculationResult).getD2());
            diceAttributeBuilder.setD4(((CombatAttributeCalculationResult) attributeCalculationResult).getD4());
            diceAttributeBuilder.setD6(((CombatAttributeCalculationResult) attributeCalculationResult).getD6());
            diceAttributeBuilder.setD8(((CombatAttributeCalculationResult) attributeCalculationResult).getD8());
            diceAttributeBuilder.setD10(((CombatAttributeCalculationResult) attributeCalculationResult).getD10());
        }

        return diceAttributeBuilder.build();
    }
}
