package com.morethanheroic.swords.monster.service.loader.domain;

import com.morethanheroic.swords.combat.service.calc.attack.AttackType;

import javax.xml.bind.annotation.*;
import java.util.Collections;
import java.util.List;

@XmlRootElement(name = "monster")
@XmlAccessorType(XmlAccessType.FIELD)
public class RawMonsterDefinition {

    private int id;
    private String name;
    private int health;
    private int mana;
    private RawDiceAttribute initiation;
    private int level;
    private int defense;
    private RawDiceAttribute attack;
    private RawDiceAttribute aiming;
    @XmlElement(name = "ranged-damage")
    private RawDiceAttribute rangedDamage;
    private RawDiceAttribute damage;
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

    public RawDiceAttribute getInitiation() {
        return initiation;
    }

    public int getLevel() {
        return level;
    }

    public int getDefense() {
        return defense;
    }

    public RawDiceAttribute getAttack() {
        return attack;
    }

    public RawDiceAttribute getDamage() {
        return damage;
    }

    public RawDiceAttribute getAiming() {
        return aiming;
    }

    public RawDiceAttribute getRangedDamage() {
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
