package com.morethanheroic.swords.user.view.controller;

import com.morethanheroic.login.view.login.username.request.domain.UsernameBasedLoginRequest;
import com.morethanheroic.response.domain.Response;
import com.morethanheroic.session.domain.SessionEntity;
import com.morethanheroic.swords.session.SessionAttributeType;
import com.morethanheroic.swords.user.domain.UserEntity;
import com.morethanheroic.swords.user.service.UserEntityFactory;
import com.morethanheroic.swords.user.view.response.domain.configuration.LoginResponseBuilderConfiguration;
import com.morethanheroic.swords.user.view.response.service.login.LoginResponseBuilder;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

/**
 * Handles all login and user information related requests.
 */
@RestController
@RequiredArgsConstructor
public class LoginController {

    private final UserEntityFactory userEntityFactory;
    private final LoginResponseBuilder loginResponseBuilder;

    @PostMapping("/user/login")
    public Response login(final SessionEntity sessionEntity, @RequestBody final UsernameBasedLoginRequest loginRequest) throws UnsupportedEncodingException {
        final UserEntity userEntity = userEntityFactory.getEntity(loginRequest.getUsername(), loginRequest.getPassword());

        if (userEntity != null) {
            //TODO: Move the logic to a service!
            sessionEntity.setAttribute(SessionAttributeType.USER_ID.name(), userEntity.getId());

            userEntity.setLastLoginDateToNow();

            return loginResponseBuilder.build(
                LoginResponseBuilderConfiguration.builder()
                    .successful(true)
                    .userEntity(userEntity)
                    .build()
            );
        } else {
            return loginResponseBuilder.build(
                LoginResponseBuilderConfiguration.builder()
                     .successful(false)
                     .build()
            );
        }
    }
}
