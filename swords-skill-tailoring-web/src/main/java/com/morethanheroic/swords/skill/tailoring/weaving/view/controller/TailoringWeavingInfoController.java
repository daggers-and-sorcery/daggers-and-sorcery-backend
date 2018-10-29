package com.morethanheroic.swords.skill.tailoring.weaving.view.controller;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.swords.skill.tailoring.weaving.view.response.service.TailortingWeavingInfoResponseBuilder;
import com.morethanheroic.swords.skill.tailoring.weaving.view.response.service.domain.TailoringWeavingInfoResponseBuilderConfiguration;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TailoringWeavingInfoController {

    private final TailortingWeavingInfoResponseBuilder tailortingWeavingInfoResponseBuilder;

    @GetMapping("/skill/tailoring/weaving/info")
    public Response cleaningInfo(UserEntity userEntity) {
        return tailortingWeavingInfoResponseBuilder.build(
                TailoringWeavingInfoResponseBuilderConfiguration.builder()
                        .userEntity(userEntity)
                        .build()
        );
    }
}
