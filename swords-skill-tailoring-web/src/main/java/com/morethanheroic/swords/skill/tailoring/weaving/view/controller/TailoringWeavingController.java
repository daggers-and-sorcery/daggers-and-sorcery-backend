package com.morethanheroic.swords.skill.tailoring.weaving.view.controller;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.swords.recipe.service.definition.cache.RecipeDefinitionCache;
import com.morethanheroic.swords.skill.tailoring.weaving.service.TailoringWeavingService;
import com.morethanheroic.swords.skill.tailoring.weaving.service.domain.WeavingResult;
import com.morethanheroic.swords.skill.tailoring.weaving.view.request.domain.TailoringWeavingRequest;
import com.morethanheroic.swords.skill.tailoring.weaving.view.response.service.TailoringWeavingResponseBuilder;
import com.morethanheroic.swords.skill.tailoring.weaving.view.response.service.domain.TailoringWeavingResponseBuilderConfiguration;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TailoringWeavingController {

    private final TailoringWeavingResponseBuilder tailoringWeavingResponseBuilder;
    private final TailoringWeavingService tailoringWeavingService;
    private final RecipeDefinitionCache recipeDefinitionCache;

    @PostMapping("/skill/tailoring/weaving/craft")
    public Response craft(final UserEntity userEntity, @RequestBody @Valid final TailoringWeavingRequest tailoringWeavingRequest) {
        log.info("Got a call for the tailoring weaving controller.");

        final WeavingResult weavingResult = tailoringWeavingService.create(userEntity, recipeDefinitionCache.getDefinition(tailoringWeavingRequest.getRecipeId()));

        return tailoringWeavingResponseBuilder.build(
                TailoringWeavingResponseBuilderConfiguration.builder()
                        .userEntity(userEntity)
                        .weavingResult(weavingResult)
                        .build()
        );
    }
}
