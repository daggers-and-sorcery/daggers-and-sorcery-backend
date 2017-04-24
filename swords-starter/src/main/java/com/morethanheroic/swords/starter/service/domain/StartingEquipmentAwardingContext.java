package com.morethanheroic.swords.starter.service.domain;

import com.morethanheroic.swords.user.domain.UserEntity;

import lombok.Builder;
import lombok.Getter;

/**
 * Contains the context of the equipment awarding. The {@link UserEntity} is the user we awarding the equipment to.
 */
@Builder
@Getter
public class StartingEquipmentAwardingContext {

    private final UserEntity userEntity;
    private final StartingArmor startingArmor;
    private final StartingWeapon startingWeapon;
}
