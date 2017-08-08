package com.morethanheroic.swords.skill.smithing.view.controller.working;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.swords.recipe.service.definition.cache.RecipeDefinitionCache;
import com.morethanheroic.swords.skill.smithing.domain.SmithingResult;
import com.morethanheroic.swords.skill.smithing.service.SmithingService;
import com.morethanheroic.swords.skill.smithing.view.request.domain.smelting.SmithingCreateRequest;
import com.morethanheroic.swords.skill.smithing.view.response.domain.configuration.working.SmithingStartResponseBuilderConfiguration;
import com.morethanheroic.swords.skill.smithing.view.response.service.working.SmithingStartResponseBuilder;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class StartSmithingController {

    @Autowired
    private SmithingStartResponseBuilder smithingStartResponseBuilder;

    @Autowired
    private RecipeDefinitionCache recipeDefinitionCache;

    @Autowired
    private SmithingService smithingService;

    @RequestMapping(value = "/skill/smithing/working/start", method = RequestMethod.POST)
    public Response startTanning(final UserEntity userEntity, @RequestBody @Valid final SmithingCreateRequest smithingCreateRequest) {
        final SmithingResult smithingResult = smithingService.smith(userEntity, recipeDefinitionCache.getDefinition(smithingCreateRequest.getRecipeId()));

        return smithingStartResponseBuilder.build(
                SmithingStartResponseBuilderConfiguration.builder()
                        .userEntity(userEntity)
                        .smithingResult(smithingResult)
                        .build()
        );
    }
}
