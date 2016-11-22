package com.morethanheroic.swords.skill.fletching.view.controller;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.response.exception.ConflictException;
import com.morethanheroic.response.exception.NotFoundException;
import com.morethanheroic.swords.recipe.domain.RecipeDefinition;
import com.morethanheroic.swords.recipe.domain.RecipeType;
import com.morethanheroic.swords.recipe.service.cache.RecipeDefinitionCache;
import com.morethanheroic.swords.skill.fletching.domain.FletchingResult;
import com.morethanheroic.swords.skill.fletching.service.FletchingService;
import com.morethanheroic.swords.skill.fletching.view.request.domain.FletchingCreateRequest;
import com.morethanheroic.swords.skill.fletching.view.response.domain.FletchingCreateResponseBuilderConfiguration;
import com.morethanheroic.swords.skill.fletching.view.response.service.FletchingCreateResponseBuilder;
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
public class FletchingCreateController {

    private final FletchingService fletchingService;
    private final RecipeDefinitionCache recipeDefinitionCache;
    private final FletchingCreateResponseBuilder fletchingCreateResponseBuilder;

    @PostMapping("/skill/fletching/create")
    public Response create(UserEntity userEntity, @RequestBody @Valid FletchingCreateRequest fletchingCreateRequest) {
        log.info("Got a call for the fletching controller.");

        final FletchingResult fletchingResult = fletchingService.fletch(userEntity,
                recipeDefinitionCache.getDefinition(fletchingCreateRequest.getRecipeId()));

        return fletchingCreateResponseBuilder.build(
                FletchingCreateResponseBuilderConfiguration.builder()
                        .userEntity(userEntity)
                        .fletchingResult(fletchingResult)
                        .build()
        );
    }
}
