package com.morethanheroic.swords.profile.view.controller;

import com.morethanheroic.swords.profile.service.response.ProfileInfoResponseBuilder;
import com.morethanheroic.swords.profile.service.response.ProfileInfoResponseBuilderConfiguration;
import com.morethanheroic.swords.response.domain.Response;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CharacterProfileController {

    @NonNull
    private final ProfileInfoResponseBuilder profileInfoResponseBuilder;

    @RequestMapping(value = "/character/info", method = RequestMethod.GET)
    public Response info(UserEntity user, HttpSession session) {
        final ProfileInfoResponseBuilderConfiguration profileInfoResponseBuilderConfiguration =
                ProfileInfoResponseBuilderConfiguration.builder()
                        .userEntity(user)
                        .httpSession(session)
                        .build();

        return profileInfoResponseBuilder.build(profileInfoResponseBuilderConfiguration);
    }
}
