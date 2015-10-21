package com.morethanheroic.swords.monster.domain;

import com.morethanheroic.swords.combat.domain.DiceAttribute;
import com.morethanheroic.swords.combat.service.calc.attack.AttackType;

import java.util.List;

public class MonsterDefinition {

    private int id;
    private String name;
    private int health;
    private int mana;
    private int level;
    private int defense;
    private DiceAttribute initiation;
    private DiceAttribute attack;
    private DiceAttribute aiming;
    private DiceAttribute rangedDamage;
    private DiceAttribute damage;
    private AttackType attackType;
    private List<DropDefinition> dropDefinitions;

    private MonsterDefinition() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getMana() {
        return mana;
    }

    public int getLevel() {
        return level;
    }

    public DiceAttribute getInitiation() {
        return initiation;
    }

    public int getDefense() {
        return defense;
    }

    public DiceAttribute getAttack() {
        return attack;
    }

    public DiceAttribute getAiming() {
        return aiming;
    }

    public DiceAttribute getRangedDamage() {
        return rangedDamage;
    }

    public DiceAttribute getDamage() {
        return damage;
    }

    public AttackType getAttackType() {
        return attackType;
    }

    public List<DropDefinition> getDropDefinitions() {
        return dropDefinitions;
    }

    public static class MonsterDefinitionBuilder {

        private final MonsterDefinition monsterDefinition = new MonsterDefinition();

        public void setId(int id) {
            monsterDefinition.id = id;
        }

        public void setName(String name) {
            monsterDefinition.name = name;
        }

        public void setHealth(int health) {
            monsterDefinition.health = health;
        }

        public void setMana(int mana) {
            monsterDefinition.mana = mana;
        }

        public void setLevel(int level) {
            monsterDefinition.level = level;
        }

        public void setInitiation(DiceAttribute initiation) {
            monsterDefinition.initiation = initiation;
        }

        public void setDefense(int defense) {
            monsterDefinition.defense = defense;
        }

        public void setAttack(DiceAttribute attack) {
            monsterDefinition.attack = attack;
        }

        public void setAiming(DiceAttribute aiming) {
            monsterDefinition.aiming = aiming;
        }

        public void setRangedDamage(DiceAttribute rangedDamage) {
            monsterDefinition.rangedDamage = rangedDamage;
        }

        public void setDamage(DiceAttribute damage) {
            monsterDefinition.damage = damage;
        }

        public void setAttackType(AttackType attackType) {
            monsterDefinition.attackType = attackType;
        }

        public void setDropDefinitions(List<DropDefinition> dropDefinitions) {
            monsterDefinition.dropDefinitions = dropDefinitions;
        }

        public MonsterDefinition build() {
            return monsterDefinition;
        }
    }
}
