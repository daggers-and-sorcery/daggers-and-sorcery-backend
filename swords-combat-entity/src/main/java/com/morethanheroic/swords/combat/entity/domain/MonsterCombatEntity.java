package com.morethanheroic.swords.combat.entity.domain;

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
        return DiceAttribute.builder()
                .value(monsterDefinition.getDefense())
                .build();
    }

    @Override
    public DiceAttribute getDamageReduction() {
        //TODO: Enable monsters to have this attribute too.
        return DiceAttribute.builder()
                .build();
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
        return DiceAttribute.builder()
                .value(monsterDefinition.getSpellResistance())
                .build();
    }

    @Override
    public String getName() {
        return monsterDefinition.getName();
    }
}
