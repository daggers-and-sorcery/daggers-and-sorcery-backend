package com.morethanheroic.swords.monster.service.domain;

import com.morethanheroic.swords.combat.service.calc.attack.AttackType;

import javax.xml.bind.annotation.*;
import java.util.Collections;
import java.util.List;

@XmlRootElement(name = "monster")
@XmlAccessorType(XmlAccessType.FIELD)
public class MonsterDefinition {

    private int id;
    private String name;
    private int health;
    private int mana;
    private int initiation;
    private int level;
    private int defense;
    private int attack;
    private int aiming;
    @XmlElement(name = "ranged-damage")
    private int rangedDamage;
    private int damage;
    @XmlElement(name = "attack-type")
    private AttackType attackType;

    @XmlElementWrapper(name = "droplist")
    @XmlElement(name = "drop")
    private List<DropDefinition> dropDefinitions;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getInitiation() {
        return initiation;
    }

    public int getLevel() {
        return level;
    }

    public int getDefense() {
        return defense;
    }

    public int getAttack() {
        return attack;
    }

    public int getDamage() {
        return damage;
    }

    public int getAiming() {
        return aiming;
    }

    public int getRangedDamage() {
        return rangedDamage;
    }

    public AttackType getAttackType() {
        return attackType;
    }

    public List<DropDefinition> getDropDefinitions() {
        if(dropDefinitions == null) {
            return Collections.EMPTY_LIST;
        }

        return Collections.unmodifiableList(dropDefinitions);
    }

    public int getMana() {
        return mana;
    }
}
