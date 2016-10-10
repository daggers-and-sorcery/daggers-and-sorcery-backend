package com.morethanheroic.swords.user.view.controller;

import com.morethanheroic.login.domain.LoginRequest;
import com.morethanheroic.swords.metadata.domain.TextMetadataEntity;
import com.morethanheroic.swords.metadata.factory.MetadataEntityFactory;
import com.morethanheroic.swords.response.domain.CharacterRefreshResponse;
import com.morethanheroic.swords.response.service.ResponseFactory;
import com.morethanheroic.swords.session.SessionAttributeType;
import com.morethanheroic.swords.user.domain.UserEntity;
import com.morethanheroic.swords.user.service.UserFacade;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;

/**
 * Handles all login and user information related requests.
 */
@RestController
@SuppressWarnings("checkstyle:multiplestringliterals")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserLoginController {

    private static final UserEntity LOGGED_OUT_USER = null;

    @NonNull
    private final UserFacade userFacade;

    @NonNull
    private final ResponseFactory responseFactory;

    @NonNull
    private final MetadataEntityFactory metadataEntityFactory;

    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    public CharacterRefreshResponse login(HttpSession session, @RequestBody LoginRequest loginRequest) throws UnsupportedEncodingException {
        final UserEntity userEntity = userFacade.getUser(loginRequest.getUsername(), loginRequest.getPassword());

        //TODO: Create response builders for this and separate action from response building.
        final CharacterRefreshResponse response = responseFactory.newResponse(userEntity);
        if (userEntity != null) {
            response.setData("success", "true");

            session.setAttribute(SessionAttributeType.USER_ID.name(), userEntity.getId());

            final TextMetadataEntity metadataEntity = metadataEntityFactory.getTextEntity(userEntity, "PRELUDE_SHOWN");

            if (metadataEntity.getValue().equals("NOT_SHOWN")) {
                response.setData("prelude", true);

                metadataEntity.setValue("ALREADY_SHOWN");
            }

            userEntity.setLastLoginDateToNow();
        } else {
            response.setData("success", "false");
            response.setData("error", "Wrong username or password!");
        }

        return response;
    }

    @RequestMapping(value = "/user/logout", method = RequestMethod.GET)
    public CharacterRefreshResponse logout(HttpSession session) {
        session.invalidate();

        final CharacterRefreshResponse response = responseFactory.newResponse(LOGGED_OUT_USER);

        response.setData("loggedIn", "false");

        return response;
    }
}
