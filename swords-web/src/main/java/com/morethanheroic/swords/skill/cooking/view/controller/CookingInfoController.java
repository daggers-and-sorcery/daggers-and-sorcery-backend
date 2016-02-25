package com.morethanheroic.swords.skill.cooking.view.controller;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.swords.skill.cooking.service.CookingInfoResponseBuilder;
import com.morethanheroic.swords.skill.cooking.service.domain.configuration.CookingInfoResponseBuilderConfiguration;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CookingInfoController {

    private final CookingInfoResponseBuilder cookingInfoResponseBuilder;

    @RequestMapping(value = "/skill/cooking/info", method = RequestMethod.GET)
    public Response scavengingInfo(UserEntity userEntity) {
        return cookingInfoResponseBuilder.build(
                CookingInfoResponseBuilderConfiguration.builder()
                .userEntity(userEntity)
                .build()
        );
    }
}
