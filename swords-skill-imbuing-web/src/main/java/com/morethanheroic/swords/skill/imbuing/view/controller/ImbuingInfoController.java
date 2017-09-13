package com.morethanheroic.swords.skill.imbuing.view.controller;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.swords.skill.imbuing.view.response.service.ImbuingInfoResponseBuilder;
import com.morethanheroic.swords.skill.imbuing.view.response.service.domain.ImbuingInfoResponseBuilderConfiguration;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ImbuingInfoController {

    private final ImbuingInfoResponseBuilder imbuingInfoResponseBuilder;

    @GetMapping("/skill/imbuing/recipe/info")
    public Response recipeInfo(final UserEntity userEntity) {
        return imbuingInfoResponseBuilder.build(
                ImbuingInfoResponseBuilderConfiguration.builder()
                        .userEntity(userEntity)
                        .build()
        );
    }
}