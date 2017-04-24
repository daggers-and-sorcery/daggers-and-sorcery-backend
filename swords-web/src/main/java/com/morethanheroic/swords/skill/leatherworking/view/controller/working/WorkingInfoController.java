package com.morethanheroic.swords.skill.leatherworking.view.controller.working;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.swords.skill.leatherworking.view.response.domain.configuration.working.WorkingInfoResponseBuilderConfiguration;
import com.morethanheroic.swords.skill.leatherworking.view.response.service.working.WorkingInfoResponseBuilder;
import com.morethanheroic.swords.user.domain.UserEntity;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class WorkingInfoController {

    private final WorkingInfoResponseBuilder workingInfoResponseBuilder;

    @GetMapping("/skill/leatherworking/working/info")
    public Response workingInfo(UserEntity userEntity) {
        return workingInfoResponseBuilder.build(
                WorkingInfoResponseBuilderConfiguration.builder()
                        .userEntity(userEntity)
                        .build()
        );
    }
}
