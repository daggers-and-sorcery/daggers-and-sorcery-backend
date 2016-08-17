package com.morethanheroic.swords.combat.domain;

import com.morethanheroic.entity.domain.Entity;
import com.morethanheroic.swords.monster.domain.MonsterDefinition;
import com.morethanheroic.swords.user.domain.UserEntity;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SavedCombatEntity implements Entity {

    private int id;
    private UserEntity user;
    private MonsterDefinition monster;
    private int monsterHealth;
    private int monsterMana;
}
