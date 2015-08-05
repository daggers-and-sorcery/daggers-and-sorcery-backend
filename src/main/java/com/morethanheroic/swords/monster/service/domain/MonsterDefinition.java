package com.morethanheroic.swords.monster.service.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "monster")
@XmlAccessorType(XmlAccessType.FIELD)
public class MonsterDefinition {

    private int id;
    private String name;
    private int health;
    private int initiation;
    private int level;
    private int defense;
    private int attack;
    private int damage;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return  health;
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
}
