package com.morethanheroic.swords.response.domain;

public class CharacterData {

    private final int movement;
    private final int health;
    private final int mana;

    public CharacterData(int movement, int health, int mana) {
        this.movement = movement;
        this.mana = mana;
        this.health = health;
    }

    public int getMovement() {
        return movement;
    }

    public int getHealth() {
        return health;
    }

    public int getMana() {
        return mana;
    }
}
