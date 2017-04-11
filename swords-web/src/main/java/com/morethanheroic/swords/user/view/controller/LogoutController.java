package com.morethanheroic.swords.user.view.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.session.domain.SessionEntity;
import com.morethanheroic.swords.response.service.ResponseFactory;
import com.morethanheroic.swords.user.domain.UserEntity;

import lombok.RequiredArgsConstructor;

/**
 * A controller that contains the logic for logging out the user.
 */
@RestController
@RequiredArgsConstructor
public class LogoutController {

    private static final UserEntity LOGGED_OUT_USER = null;

    private final ResponseFactory responseFactory;

    @GetMapping("/user/logout")
    public Response logout(final SessionEntity sessionEntity) {
        sessionEntity.close();

        final Response response = responseFactory.newResponse(LOGGED_OUT_USER);

        response.setData("loggedIn", "false");

        return response;
    }
}
