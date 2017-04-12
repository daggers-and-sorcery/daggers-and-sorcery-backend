package com.morethanheroic.swords.user.view.controller;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.swords.response.service.ResponseFactory;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserInfoController {

    private final ResponseFactory responseFactory;

    @GetMapping("/user/info")
    public Response info(UserEntity user) {
        final Response response = responseFactory.newResponse(user);

        //TODO: Use a real builder
        response.setData("loggedIn", user != null);

        return response;
    }
}
