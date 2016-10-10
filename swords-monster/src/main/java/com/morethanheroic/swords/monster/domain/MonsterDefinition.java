package com.morethanheroic.swords.monster.domain;

import com.morethanheroic.swords.attribute.domain.DiceAttribute;
import com.morethanheroic.swords.loot.domain.DropDefinition;

import java.util.List;

public class MonsterDefinition {

    private int id;
    private String name;
    private int health;
    private int mana;
    private int level;
    private int defense;
    private int spellResistance;
    private DiceAttribute initiation;
    private DiceAttribute attack;
    private DiceAttribute aiming;
    private DiceAttribute rangedDamage;
    private DiceAttribute damage;
    private DiceAttribute magicAttack;
    private DiceAttribute magicDamage;
    private MonsterAttackType attackType;
    private List<DropDefinition> dropDefinitions;
    private List<ScavengingDefinition> scavengingDefinitions;

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

    public MonsterAttackType getAttackType() {
        return attackType;
    }

    public List<DropDefinition> getDropDefinitions() {
        return dropDefinitions;
    }

    public List<ScavengingDefinition> getScavengingDefinitions() {
        return scavengingDefinitions;
    }

    public DiceAttribute getMagicDamage() {
        return magicDamage;
    }

    public DiceAttribute getMagicAttack() {
        return magicAttack;
    }

    public int getSpellResistance() {
        return spellResistance;
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

        public void setMagicAttack(DiceAttribute attack) {
            monsterDefinition.magicAttack = attack;
        }

        public void setMagicDamage(DiceAttribute damage) {
            monsterDefinition.magicDamage = damage;
        }

        public void setSpellResistance(int spellResistance) {
            monsterDefinition.spellResistance = spellResistance;
        }

        public void setAttackType(MonsterAttackType attackType) {
            monsterDefinition.attackType = attackType;
        }

        public void setDropDefinitions(List<DropDefinition> dropDefinitions) {
            monsterDefinition.dropDefinitions = dropDefinitions;
        }

        public void setScavengeDefinitions(List<ScavengingDefinition> scavengingDefinitions) {
            monsterDefinition.scavengingDefinitions = scavengingDefinitions;
        }

        public MonsterDefinition build() {
            return monsterDefinition;
        }
    }
}
