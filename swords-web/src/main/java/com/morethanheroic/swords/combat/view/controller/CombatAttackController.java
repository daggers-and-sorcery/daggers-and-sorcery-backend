package com.morethanheroic.swords.combat.view.controller;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.swords.combat.domain.step.CombatStep;
import com.morethanheroic.swords.combat.service.newcb.CombatCalculator;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CombatAttackController {

    private final CombatCalculator combatCalculator;

    @RequestMapping(value = "/combat/attack", method = RequestMethod.GET)
    public Response attack(final UserEntity userEntity) {
        final List<CombatStep> attackResult = combatCalculator.attack(userEntity);

        //TODO: build response
        return null;
    }
}
