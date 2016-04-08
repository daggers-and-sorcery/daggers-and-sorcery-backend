package com.morethanheroic.swords.user.view.controller;

import com.morethanheroic.swords.attribute.domain.BasicAttribute;
import com.morethanheroic.swords.attribute.domain.CombatAttribute;
import com.morethanheroic.swords.attribute.service.AttributeFacade;
import com.morethanheroic.swords.response.domain.CharacterRefreshResponse;
import com.morethanheroic.swords.response.service.ResponseFactory;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SuppressWarnings("checkstyle:multiplestringliterals")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserInfoController {

    @NonNull
    private final AttributeFacade attributeFacade;

    @NonNull
    private final ResponseFactory responseFactory;

    @RequestMapping(value = "/user/info", method = RequestMethod.GET)
    public CharacterRefreshResponse info(UserEntity user) {
        final CharacterRefreshResponse response = responseFactory.newResponse(user);

        if (user != null) {
            response.setData("loggedIn", true);
            //TODO: Do we really need to set this data? It's automatically set afaik.
            response.setData("life", attributeFacade.calculateAttributeValue(user, CombatAttribute.LIFE).getValue());
            response.setData("max_life", attributeFacade.calculateAttributeMaximumValue(user, CombatAttribute.LIFE).getValue());
            response.setData("mana", attributeFacade.calculateAttributeValue(user, CombatAttribute.MANA).getValue());
            response.setData("max_mana", attributeFacade.calculateAttributeMaximumValue(user, CombatAttribute.MANA).getValue());
            response.setData("movement", attributeFacade.calculateAttributeValue(user, BasicAttribute.MOVEMENT).getValue());
            response.setData("max_movement", attributeFacade.calculateAttributeMaximumValue(user, BasicAttribute.MOVEMENT).getValue());
        } else {
            response.setData("loggedIn", false);
        }

        return response;
    }
}
