package com.morethanheroic.swords.combat.domain.entity;

import com.morethanheroic.swords.attribute.domain.DiceAttribute;
import com.morethanheroic.swords.monster.domain.MonsterAttackType;
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

    public MonsterCombatEntity(MonsterDefinition monsterDefinition, int actualHealth, int actualMana) {
        this.monsterDefinition = monsterDefinition;

        this.setMaximumHealth(monsterDefinition.getHealth());
        this.setActualHealth(actualHealth);

        this.setMaximumMana(monsterDefinition.getMana());
        this.setActualMana(actualMana);
    }

    public MonsterDefinition getMonsterDefinition() {
        return monsterDefinition;
    }

    public int getLevel() {
        return monsterDefinition.getLevel();
    }

    public MonsterAttackType getAttackType() {
        return monsterDefinition.getAttackType();
    }

    @Override
    public DiceAttribute getAttack() {
        return monsterDefinition.getAttack();
    }

    @Override
    public DiceAttribute getDefense() {
        final DiceAttribute.DiceAttributeBuilder diceAttributeBuilder = new DiceAttribute.DiceAttributeBuilder();

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
        return monsterDefinition.getMagicDamage();
    }

    @Override
    public DiceAttribute getSpellResistance() {
        final DiceAttribute.DiceAttributeBuilder diceAttributeBuilder = new DiceAttribute.DiceAttributeBuilder();

        diceAttributeBuilder.setValue(monsterDefinition.getSpellResistance());

        return diceAttributeBuilder.build();
    }

    @Override
    public String getName() {
        return monsterDefinition.getName();
    }
}
