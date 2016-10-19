package com.morethanheroic.swords.skill.cooking.view.controller;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.swords.skill.cooking.service.response.CookingInfoResponseBuilder;
import com.morethanheroic.swords.skill.cooking.service.response.domain.configuration.CookingInfoResponseBuilderConfiguration;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CookingInfoController {

    private final CookingInfoResponseBuilder cookingInfoResponseBuilder;

    @GetMapping(value = "/skill/cooking/info")
    public Response cookingInfo(UserEntity userEntity) {
        return cookingInfoResponseBuilder.build(
                CookingInfoResponseBuilderConfiguration.builder()
                        .userEntity(userEntity)
                        .build()
        );
    }
}
