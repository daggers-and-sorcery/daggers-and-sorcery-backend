package com.morethanheroic.swords.user.view.request.domain.starter;

import javax.validation.constraints.NotNull;

import com.morethanheroic.swords.starter.service.domain.StartingArmor;
import com.morethanheroic.swords.starter.service.domain.StartingWeapon;

import lombok.Data;

/**
 * Contains the data of the starting equipment of the user.
 */
@Data
public class StarterPathRequest {

    @NotNull
    private StartingWeapon startingWeapon;

    @NotNull
    private StartingArmor startingArmor;
}
