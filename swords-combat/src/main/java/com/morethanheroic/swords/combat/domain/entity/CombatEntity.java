package com.morethanheroic.swords.combat.domain.entity;

import com.morethanheroic.swords.attribute.domain.DiceAttribute;

public abstract class CombatEntity {

    private int maximumHealth;
    private int actualHealth;
    private int maximumMana;
    private int actualMana;

    public void increaseActualHealth(int amount) {
        if (actualHealth + amount > maximumHealth) {
            actualHealth = maximumHealth;
        } else {
            actualHealth += amount;
        }
    }

    public void increaseActualMana(int amount) {
        if (actualMana + amount > maximumMana) {
            actualMana = maximumMana;
        } else {
            actualMana += amount;
        }
    }

    public void decreaseActualHealth(int amount) {
        if (actualHealth - amount < 0) {
            actualHealth = 0;
        } else {
            actualHealth -= amount;
        }
    }

    public void decreaseActualMana(int amount) {
        if (actualMana - amount < 0) {
            actualMana = 0;
        } else {
            actualMana -= amount;
        }
    }

    public int getMaximumHealth() {
        return maximumHealth;
    }

    public void setMaximumHealth(int maximumHealth) {
        this.maximumHealth = maximumHealth;
    }

    public int getActualHealth() {
        return actualHealth;
    }

    public void setActualHealth(int actualHealth) {
        this.actualHealth = actualHealth;
    }

    public int getMaximumMana() {
        return maximumMana;
    }

    public void setMaximumMana(int maximumMana) {
        this.maximumMana = maximumMana;
    }

    public int getActualMana() {
        return actualMana;
    }

    public void setActualMana(int actualMana) {
        this.actualMana = actualMana;
    }

    public abstract DiceAttribute getAttack();

    public abstract DiceAttribute getDefense();

    public abstract DiceAttribute getDamage();

    public abstract DiceAttribute getRangedDamage();

    public abstract DiceAttribute getAiming();

    public abstract DiceAttribute getMagicAttack();

    public abstract DiceAttribute getMagicDamage();

    public abstract DiceAttribute getSpellResistance();

    public abstract String getName();
}
