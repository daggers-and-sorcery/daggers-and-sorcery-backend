package com.morethanheroic.swords.skill.leatherworking.view.controller;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.swords.recipe.service.cache.RecipeDefinitionCache;
import com.morethanheroic.swords.skill.leatherworking.domain.CuringResult;
import com.morethanheroic.swords.skill.leatherworking.service.CuringService;
import com.morethanheroic.swords.skill.leatherworking.view.request.domain.CuringCreateRequest;
import com.morethanheroic.swords.skill.leatherworking.view.response.CuringStartResponseBuilder;
import com.morethanheroic.swords.skill.leatherworking.view.response.domain.configuration.CuringStartResponseBuilderConfiguration;
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

    @Autowired
    private CuringStartResponseBuilder curingStartResponseBuilder;

    @RequestMapping(value = "/skill/leatherworking/curing/start", method = RequestMethod.POST)
    public Response startCuring(final UserEntity userEntity, @RequestBody @Valid final CuringCreateRequest curingCreateRequest) {
        final CuringResult result = curingService.cure(userEntity, recipeDefinitionCache.getDefinition(curingCreateRequest.getRecipeId()));

        return curingStartResponseBuilder.build(
                CuringStartResponseBuilderConfiguration.builder()
                .userEntity(userEntity)
                .curingResult(result)
                .build()
        );
    }
}
