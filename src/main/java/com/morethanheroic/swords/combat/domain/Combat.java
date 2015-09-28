package com.morethanheroic.swords.combat.domain;

import com.morethanheroic.swords.combat.domain.entity.MonsterCombatEntity;
import com.morethanheroic.swords.combat.domain.entity.UserCombatEntity;
import com.morethanheroic.swords.monster.service.domain.MonsterDefinition;
import com.morethanheroic.swords.user.domain.UserEntity;

public class Combat {

    private final UserCombatEntity userCombatEntity;
    private final MonsterCombatEntity monsterCombatEntity;

    private int turn = 0;

    public Combat(UserEntity userEntity, MonsterDefinition monsterDefinition) {
        this.userCombatEntity = new UserCombatEntity(userEntity);
        this.monsterCombatEntity = new MonsterCombatEntity(monsterDefinition);
    }

    public UserEntity getUserEntity() {
        return userCombatEntity.getUserEntity();
    }

    public MonsterDefinition getMonsterDefinition() {
        return monsterCombatEntity.getMonsterDefinition();
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
        return userCombatEntity.getActualHealth();
    }

    public void setPlayerHealth(int playerHealth) {
        this.userCombatEntity.setActualHealth(playerHealth);
    }

    public void increasePlayerHealth(int amount) {
        userCombatEntity.increaseActualHealth(amount);
    }

    public void decreasePlayerHealth(int amount) {
        userCombatEntity.decreaseActualHealth(amount);
    }

    public int getMonsterHealth() {
        return monsterCombatEntity.getActualHealth();
    }

    public void setMonsterHealth(int monsterHealth) {
        monsterCombatEntity.setActualHealth(monsterHealth);
    }

    public void increaseMonsterHealth(int amount) {
        monsterCombatEntity.increaseActualHealth(amount);
    }

    public void decreaseMonsterHealth(int amount) {
        monsterCombatEntity.decreaseActualHealth(amount);
    }
}
