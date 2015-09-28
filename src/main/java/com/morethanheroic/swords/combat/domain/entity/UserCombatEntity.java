package com.morethanheroic.swords.combat.domain.entity;

import com.morethanheroic.swords.user.domain.UserEntity;

public class UserCombatEntity extends CombatEntity {

    private UserEntity userEntity;

    public UserCombatEntity(UserEntity userEntity) {
        this.userEntity = userEntity;

        //TODO: use calculated max values
        this.setMaximumMana(userEntity.getMana());
        this.setActualMana(userEntity.getMana());

        this.setMaximumHealth(userEntity.getHealth());
        this.setActualHealth(userEntity.getHealth());
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    @Override
    public void terminate() {
        //TODO: save user data
    }
}
