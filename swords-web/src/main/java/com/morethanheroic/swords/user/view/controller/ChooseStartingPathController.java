package com.morethanheroic.swords.user.view.controller;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.morethanheroic.swords.starter.service.StarterService;
import com.morethanheroic.swords.starter.service.domain.StartingEquipmentAwardingContext;
import com.morethanheroic.swords.user.domain.UserEntity;
import com.morethanheroic.swords.user.view.request.domain.starter.StarterPathRequest;

import lombok.RequiredArgsConstructor;

/**
 * This controller is responsible for handling the starter path chosing logic of the user.
 */
@RestController
@RequiredArgsConstructor
public class ChooseStartingPathController {

    private final StarterService starterService;

    @PostMapping("/user/starter-path")
    public void chooseStarter(final UserEntity userEntity, @RequestBody @Valid final StarterPathRequest starterPathRequest) {
        starterService.awardStartingEquipment(
            StartingEquipmentAwardingContext.builder()
                .userEntity(userEntity)
                .startingWeapon(starterPathRequest.getStartingWeapon())
                .startingArmor(starterPathRequest.getStartingArmor())
                .build()
        );
    }
}
