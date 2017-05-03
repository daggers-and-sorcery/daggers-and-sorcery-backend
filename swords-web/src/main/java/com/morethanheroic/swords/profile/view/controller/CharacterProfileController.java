package com.morethanheroic.swords.profile.view.controller;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.session.domain.SessionEntity;
import com.morethanheroic.swords.profile.response.service.ProfileInfoResponseBuilder;
import com.morethanheroic.swords.profile.response.service.ProfileInfoResponseBuilderConfiguration;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CharacterProfileController {

    private final ProfileInfoResponseBuilder profileInfoResponseBuilder;

    @GetMapping("/character/info")
    public Response info(final UserEntity user, final SessionEntity sessionEntity) {
        return profileInfoResponseBuilder.build(
                ProfileInfoResponseBuilderConfiguration.builder()
                        .userEntity(user)
                        .sessionEntity(sessionEntity)
                        .build()
        );
    }
}
