package com.morethanheroic.swords.skill.leatherworking.view.controller;

import com.morethanheroic.swords.recipe.service.cache.RecipeDefinitionCache;
import com.morethanheroic.swords.skill.leatherworking.domain.CuringResult;
import com.morethanheroic.swords.skill.leatherworking.service.CuringService;
import com.morethanheroic.swords.skill.leatherworking.view.request.domain.CuringCreateRequest;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class StartCuringController {

    @Autowired
    private CuringService curingService;

    @Autowired
    private RecipeDefinitionCache recipeDefinitionCache;

    @RequestMapping(value = "/skill/leatherworking/curing/start", method = RequestMethod.POST)
    public CuringResult startCuring(final UserEntity userEntity, @RequestBody @Valid final CuringCreateRequest curingCreateRequest) {
        //TODO: Return success or error message!
        //TODO: use real response builders...
        return curingService.cure(userEntity, recipeDefinitionCache.getDefinition(curingCreateRequest.getRecipeId()));
    }
}
