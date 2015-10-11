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
    private RawDiceAttribute initiation;
    private int level;
    private RawDiceAttribute defense;
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

    public int getInitiation() {
        return initiation.getValue();
    }

    public int getLevel() {
        return level;
    }

    public int getDefense() {
        return defense.getValue();
    }

    public int getAttack() {
        return attack.getValue();
    }

    public int getDamage() {
        return damage.getValue();
    }

    public int getAiming() {
        return aiming.getValue();
    }

    public int getRangedDamage() {
        return rangedDamage.getValue();
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
