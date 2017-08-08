package com.morethanheroic.swords.skill.leatherworking.view.controller.curing;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.swords.recipe.service.definition.cache.RecipeDefinitionCache;
import com.morethanheroic.swords.skill.leatherworking.domain.CuringResult;
import com.morethanheroic.swords.skill.leatherworking.service.CuringService;
import com.morethanheroic.swords.skill.leatherworking.view.request.domain.curing.CuringCreateRequest;
import com.morethanheroic.swords.skill.leatherworking.view.response.domain.configuration.curing.CuringStartResponseBuilderConfiguration;
import com.morethanheroic.swords.skill.leatherworking.view.response.service.curing.CuringStartResponseBuilder;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class StartCuringController {

    private final CuringService curingService;
    private final RecipeDefinitionCache recipeDefinitionCache;
    private final CuringStartResponseBuilder curingStartResponseBuilder;

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
