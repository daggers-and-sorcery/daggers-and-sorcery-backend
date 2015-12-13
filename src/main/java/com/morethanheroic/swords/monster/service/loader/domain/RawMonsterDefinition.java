package com.morethanheroic.swords.monster.service.loader.domain;

import com.morethanheroic.swords.combat.service.calc.attack.AttackType;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
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
    @XmlElement(name = "spell-resistance")
    private int spellResistance;
    private RawDiceAttribute attack;
    private RawDiceAttribute aiming;
    @XmlElement(name = "ranged-damage")
    private RawDiceAttribute rangedDamage;
    @XmlElement(name = "magic-damage")
    private RawDiceAttribute magicDamage;
    @XmlElement(name = "magic-attack")
    private RawDiceAttribute magicAttack;
    private RawDiceAttribute damage;
    @XmlElement(name = "attack-type")
    private AttackType attackType;

    @XmlElementWrapper(name = "droplist")
    @XmlElement(name = "drop")
    private List<RawDropDefinition> rawDropDefinitions;

    @XmlElementWrapper(name = "scavengelist")
    @XmlElement(name = "scavenge")
    private List<RawScavengingDefinition> rawScavengingDefinitions;

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

    public List<RawDropDefinition> getDropDefinitions() {
        if (rawDropDefinitions == null) {
            return Collections.emptyList();
        }

        return Collections.unmodifiableList(rawDropDefinitions);
    }

    public List<RawScavengingDefinition> getScavengeDefinitions() {
        if (rawScavengingDefinitions == null) {
            return Collections.emptyList();
        }

        return Collections.unmodifiableList(rawScavengingDefinitions);
    }

    public int getMana() {
        return mana;
    }

    public int getSpellResistance() {
        return spellResistance;
    }

    public RawDiceAttribute getMagicDamage() {
        return magicDamage;
    }

    public RawDiceAttribute getMagicAttack() {
        return magicAttack;
    }
}
