package com.morethanheroic.swords.user.view.controller;

import com.morethanheroic.swords.attribute.domain.BasicAttribute;
import com.morethanheroic.swords.attribute.domain.CombatAttribute;
import com.morethanheroic.swords.attribute.service.calc.GlobalAttributeCalculator;
import com.morethanheroic.swords.response.domain.CharacterRefreshResponse;
import com.morethanheroic.swords.response.service.ResponseFactory;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SuppressWarnings("checkstyle:multiplestringliterals")
@RequiredArgsConstructor
public class UserInfoController {

    private final ResponseFactory responseFactory;
    private final GlobalAttributeCalculator globalAttributeCalculator;

    @RequestMapping(value = "/user/info", method = RequestMethod.GET)
    public CharacterRefreshResponse info(UserEntity user) {
        final CharacterRefreshResponse response = responseFactory.newResponse(user);

        if (user != null) {
            response.setData("loggedIn", true);
            //TODO: Do we really need to set this data? It's automatically set afaik.
            response.setData("life", globalAttributeCalculator.calculateActualValue(user, CombatAttribute.LIFE).getValue());
            response.setData("max_life", globalAttributeCalculator.calculateMaximumValue(user, CombatAttribute.LIFE).getValue());
            response.setData("mana", globalAttributeCalculator.calculateActualValue(user, CombatAttribute.MANA).getValue());
            response.setData("max_mana", globalAttributeCalculator.calculateMaximumValue(user, CombatAttribute.MANA).getValue());
            response.setData("movement", globalAttributeCalculator.calculateActualValue(user, BasicAttribute.MOVEMENT).getValue());
            response.setData("max_movement", globalAttributeCalculator.calculateMaximumValue(user, BasicAttribute.MOVEMENT).getValue());
        } else {
            response.setData("loggedIn", false);
        }

        return response;
    }
}
