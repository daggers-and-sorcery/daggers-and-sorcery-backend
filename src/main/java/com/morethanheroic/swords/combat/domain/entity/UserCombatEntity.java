package com.morethanheroic.swords.combat.domain.entity;

import com.morethanheroic.swords.user.domain.UserEntity;

public class UserCombatEntity extends CombatEntity {

    private UserEntity userEntity;

    public UserCombatEntity(UserEntity userEntity) {
        this.userEntity = userEntity;

        //TODO: use real (calculated) max value here
        this.setActualHealth(userEntity.getHealth());
        this.setMaximumHealth(30);

        //TODO: use real (calculated) max value here
        this.setActualMana(userEntity.getMana());
        this.setMaximumMana(30);
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    @Override
    public void terminate() {
        //TODO: save user data
        System.out.println("New health: " + getActualHealth());
    }
}
