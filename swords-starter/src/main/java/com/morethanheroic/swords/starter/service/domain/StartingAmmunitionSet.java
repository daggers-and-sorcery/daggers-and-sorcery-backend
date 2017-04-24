package com.morethanheroic.swords.starter.service.domain;

import com.morethanheroic.swords.item.domain.ItemDefinition;

import lombok.Builder;
import lombok.Getter;

/**
 * Contains the info of the starting ammunition of the user.
 */
@Getter
@Builder
public class StartingAmmunitionSet {

    private final ItemDefinition ammunition;
    private final int amount;
}
