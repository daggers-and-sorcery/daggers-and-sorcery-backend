package com.morethanheroic.swords.skill.cooking.view.controller;

import com.morethanheroic.swords.response.domain.Response;
import com.morethanheroic.swords.skill.cooking.service.CookingCreateRequestFacade;
import com.morethanheroic.swords.skill.cooking.view.request.CookingCreateRequest;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CookingCreateController {

    private final CookingCreateRequestFacade cookingCreateRequestFacade;

    @RequestMapping(value = "/skill/cooking/create", method = RequestMethod.POST)
    public Response create(UserEntity userEntity, @RequestBody @Valid CookingCreateRequest cookingCreateRequest) {
        return cookingCreateRequestFacade.handleCookingCreateRequest(userEntity, cookingCreateRequest);
    }
}
