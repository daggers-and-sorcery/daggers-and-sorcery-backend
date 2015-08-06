package com.morethanheroic.swords.combat.domain;

import com.morethanheroic.swords.monster.service.domain.MonsterDefinition;
import com.morethanheroic.swords.user.domain.UserEntity;

public class Combat {

    private final UserEntity userEntity;
    private final MonsterDefinition monsterDefinition;

    private int turn = 0;
    private int playerHealth;
    private int monsterHealth;

    public Combat(UserEntity userEntity, MonsterDefinition monsterDefinition) {
        this.userEntity = userEntity;
        this.monsterDefinition = monsterDefinition;

        this.setPlayerHealth(userEntity.getHealth());
        this.setMonsterHealth(monsterDefinition.getHealth());
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public MonsterDefinition getMonsterDefinition() {
        return monsterDefinition;
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public void increaseTurn() {
        this.turn++;
    }

    public int getPlayerHealth() {
        return playerHealth;
    }

    public void setPlayerHealth(int playerHealth) {
        this.playerHealth = playerHealth;
    }

    public void increasePlayerHealth(int amount) {
        this.playerHealth += amount;
    }

    public void decreasePlayerHealth(int amount) {
        this.playerHealth -= amount;
    }

    public int getMonsterHealth() {
        return monsterHealth;
    }

    public void setMonsterHealth(int monsterHealth) {
        this.monsterHealth = monsterHealth;
    }

    public void increaseMonsterHealth(int amount) {
        this.monsterHealth += amount;
    }

    public void decreaseMonsterHealth(int amount) {
        this.monsterHealth -= amount;
    }
}
