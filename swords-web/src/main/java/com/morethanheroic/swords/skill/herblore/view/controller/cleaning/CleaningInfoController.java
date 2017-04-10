package com.morethanheroic.swords.skill.herblore.view.controller.cleaning;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.swords.skill.herblore.view.response.domain.configuration.cleaning.CleaningInfoResponseBuilderConfiguration;
import com.morethanheroic.swords.skill.herblore.view.response.service.cleaning.CleaningInfoResponseBuilder;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.function.Function;

@RestController
@RequiredArgsConstructor
public class CleaningInfoController {

    private final CleaningInfoResponseBuilder cleaningInfoResponseBuilder;

    @GetMapping("/skill/herblore/cleaning/info")
    public Response curingInfo(UserEntity userEntity) {
        return cleaningInfoResponseBuilder.build(
                CleaningInfoResponseBuilderConfiguration.builder()
                        .userEntity(userEntity)
                        .build()
        );
    }

    public void ass(int val) {
        Function<Integer, Integer> asd = myval -> myval + val;

        System.out.println(asd);
    }
}
