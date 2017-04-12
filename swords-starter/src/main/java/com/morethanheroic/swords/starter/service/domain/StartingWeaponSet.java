package com.morethanheroic.swords.starter.service.domain;

import com.morethanheroic.swords.item.domain.ItemDefinition;

import lombok.Builder;
import lombok.Getter;

/**
 * Contains the starting weapon information of the new user.
 */
@Getter
@Builder
public class StartingWeaponSet {

    private final ItemDefinition weapon;
    private final StartingAmmunitionSet quiver;
}
