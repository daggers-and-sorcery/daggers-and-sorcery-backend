package com.morethanheroic.swords.combat.domain.entity;

import com.morethanheroic.swords.combat.domain.DiceAttribute;
import com.morethanheroic.swords.monster.domain.MonsterDefinition;

public class MonsterCombatEntity extends CombatEntity {

    private MonsterDefinition monsterDefinition;

    public MonsterCombatEntity(MonsterDefinition monsterDefinition) {
        this.monsterDefinition = monsterDefinition;

        this.setMaximumHealth(monsterDefinition.getHealth());
        this.setActualHealth(monsterDefinition.getHealth());

        this.setMaximumMana(monsterDefinition.getMana());
        this.setActualMana(monsterDefinition.getMana());
    }

    public MonsterDefinition getMonsterDefinition() {
        return monsterDefinition;
    }

    @Override
    public DiceAttribute getAttack() {
        return monsterDefinition.getAttack();
    }

    @Override
    public DiceAttribute getDefense() {
        DiceAttribute.DiceAttributeBuilder diceAttributeBuilder = new DiceAttribute.DiceAttributeBuilder();

        diceAttributeBuilder.setValue(monsterDefinition.getDefense());

        return diceAttributeBuilder.build();
    }

    @Override
    public DiceAttribute getDamage() {
        return monsterDefinition.getDamage();
    }

    @Override
    public DiceAttribute getRangedDamage() {
        return monsterDefinition.getRangedDamage();
    }

    @Override
    public DiceAttribute getAiming() {
        return monsterDefinition.getAiming();
    }

    @Override
    public DiceAttribute getMagicAttack() {
        return monsterDefinition.getMagicAttack();
    }

    @Override
    public DiceAttribute getMagicDamage() {
        return monsterDefinition.getRangedDamage();
    }

    @Override
    public DiceAttribute getSpellResistance() {
        return monsterDefinition.getSpellResistance();
    }

    @Override
    public String getName() {
        return monsterDefinition.getName();
    }
}
