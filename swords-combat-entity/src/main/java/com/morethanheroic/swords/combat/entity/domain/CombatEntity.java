package com.morethanheroic.swords.combat.entity.domain;

import com.morethanheroic.swords.attribute.domain.DiceAttribute;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class CombatEntity {

    private int maximumHealth;
    private int actualHealth;
    private int maximumMana;
    private int actualMana;
    private int maximumMovement;
    private int actualMovement;

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

    public void increaseActualMovement(final int amount) {
        if (actualMovement + amount > maximumMovement) {
            actualMovement = maximumMovement;
        } else {
            actualMovement += amount;
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

    public void decreaseActualMovement(int amount) {
        if (actualMovement - amount < 0) {
            actualMovement = 0;
        } else {
            actualMovement -= amount;
        }
    }

    public boolean isAlive() {
        return actualHealth > 0;
    }

    public abstract DiceAttribute getAttack();

    public abstract DiceAttribute getDefense();

    public abstract DiceAttribute getDamageReduction();

    public abstract DiceAttribute getDamage();

    public abstract DiceAttribute getRangedDamage();

    public abstract DiceAttribute getAiming();

    public abstract DiceAttribute getMagicAttack();

    public abstract DiceAttribute getMagicDamage();

    public abstract DiceAttribute getSpellResistance();

    public abstract String getName();
}
