package com.morethanheroic.swords.skill.jewelcrafting.gemcutting.view.controller;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.swords.skill.jewelcrafting.gemcutting.view.response.service.GemcuttingInfoResponseBuilder;
import com.morethanheroic.swords.skill.jewelcrafting.gemcutting.view.response.service.domain.GemcuttingInfoResponseBuilderConfiguration;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class JewelcraftingGemcuttingInfoController {

    private final GemcuttingInfoResponseBuilder gemcuttingInfoResponseBuilder;

    @GetMapping("/skill/jewelcrafting/gemcutting/info")
    public Response cleaningInfo(UserEntity userEntity) {
        return gemcuttingInfoResponseBuilder.build(
                GemcuttingInfoResponseBuilderConfiguration.builder()
                        .userEntity(userEntity)
                        .build()
        );
    }
}
