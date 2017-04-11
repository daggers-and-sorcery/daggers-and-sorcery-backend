package com.morethanheroic.swords.user.view.controller;

import com.morethanheroic.login.domain.LoginRequest;
import com.morethanheroic.response.domain.Response;
import com.morethanheroic.session.domain.SessionEntity;
import com.morethanheroic.swords.metadata.domain.TextMetadataEntity;
import com.morethanheroic.swords.metadata.service.MetadataEntityFactory;
import com.morethanheroic.swords.session.SessionAttributeType;
import com.morethanheroic.swords.user.domain.UserEntity;
import com.morethanheroic.swords.user.service.UserEntityFactory;
import com.morethanheroic.swords.user.view.response.domain.configuration.LoginResponseBuilderConfiguration;
import com.morethanheroic.swords.user.view.response.service.LoginResponseBuilder;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

/**
 * Handles all login and user information related requests.
 */
@RestController
@SuppressWarnings("checkstyle:multiplestringliterals")
@RequiredArgsConstructor
public class LoginController {


    private final UserEntityFactory userEntityFactory;
    private final MetadataEntityFactory metadataEntityFactory;
    private final LoginResponseBuilder loginResponseBuilder;

    @PostMapping("/user/login")
    public Response login(final SessionEntity sessionEntity, @RequestBody final LoginRequest loginRequest) throws UnsupportedEncodingException {
        final UserEntity userEntity = userEntityFactory.getEntity(loginRequest.getUsername(), loginRequest.getPassword());

        if (userEntity != null) {
            final TextMetadataEntity preludeMetadata = metadataEntityFactory.getTextEntity(userEntity, "PRELUDE_SHOWN");

            if (preludeMetadata.getValue().equals("NOT_SHOWN")) {
                preludeMetadata.setValue("ALREADY_SHOWN");
            }

            sessionEntity.setAttribute(SessionAttributeType.USER_ID.name(), userEntity.getId());

            userEntity.setLastLoginDateToNow();

            return loginResponseBuilder.build(
                LoginResponseBuilderConfiguration.builder()
                    .successful(true)
                    .userEntity(userEntity)
                    .showPrelude(preludeMetadata.getValue().equals("NOT_SHOWN"))
                    .build()
            );
        } else {
            return loginResponseBuilder.build(
                LoginResponseBuilderConfiguration.builder()
                     .successful(false)
                     .error("Wrong username or password!")
                     .build()
            );
        }
    }
}
