package com.morethanheroic.swords.skill.leatherworking.view.controller.tanning;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.swords.recipe.service.definition.cache.RecipeDefinitionCache;
import com.morethanheroic.swords.skill.leatherworking.domain.TanningResult;
import com.morethanheroic.swords.skill.leatherworking.service.TanningService;
import com.morethanheroic.swords.skill.leatherworking.view.request.domain.tanning.TanningCreateRequest;
import com.morethanheroic.swords.skill.leatherworking.view.response.domain.configuration.tanning.TanningStartResponseBuilderConfiguration;
import com.morethanheroic.swords.skill.leatherworking.view.response.service.tanning.TanningStartResponseBuilder;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class StartTanningController {

    @Autowired
    private TanningStartResponseBuilder tanningStartResponseBuilder;

    @Autowired
    private RecipeDefinitionCache recipeDefinitionCache;

    @Autowired
    private TanningService tanningService;

    @RequestMapping(value = "/skill/leatherworking/tanning/start", method = RequestMethod.POST)
    public Response startTanning(final UserEntity userEntity, @RequestBody @Valid final TanningCreateRequest tanningCreateRequest) {
        final TanningResult tanningResult = tanningService.tan(userEntity, recipeDefinitionCache.getDefinition(tanningCreateRequest.getRecipeId()));

        return tanningStartResponseBuilder.build(
                TanningStartResponseBuilderConfiguration.builder()
                        .userEntity(userEntity)
                        .tanningResult(tanningResult)
                        .build()
        );
    }
}
