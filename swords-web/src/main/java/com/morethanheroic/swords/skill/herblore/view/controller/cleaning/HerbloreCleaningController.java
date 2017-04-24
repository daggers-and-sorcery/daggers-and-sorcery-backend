package com.morethanheroic.swords.skill.herblore.view.controller.cleaning;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.swords.recipe.service.cache.RecipeDefinitionCache;
import com.morethanheroic.swords.skill.herblore.service.cleaning.HerbloreCleaningService;
import com.morethanheroic.swords.skill.herblore.service.cleaning.domain.CleaningResult;
import com.morethanheroic.swords.skill.herblore.view.request.HerbloreCraftRequest;
import com.morethanheroic.swords.skill.herblore.view.response.domain.configuration.cleaning.HerbloreCleaningResponseBuilderConfiguration;
import com.morethanheroic.swords.skill.herblore.view.response.service.cleaning.HerbloreCleaningResponseBuilder;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Handles requests for cleaning a herb.
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class HerbloreCleaningController {

    private final HerbloreCleaningService herbloreCleaningService;
    private final RecipeDefinitionCache recipeDefinitionCache;
    private final HerbloreCleaningResponseBuilder herbloreCleaningResponseBuilder;

    @PostMapping("/skill/herblore/cleaning/craft")
    public Response craft(final UserEntity userEntity, @RequestBody @Valid final HerbloreCraftRequest herbloreCraftRequest) {
        log.info("Got a call for the herblore cleaning controller.");

        final CleaningResult cleaningResult = herbloreCleaningService.clean(userEntity, recipeDefinitionCache.getDefinition(herbloreCraftRequest.getRecipeId()));

        return herbloreCleaningResponseBuilder.build(
                HerbloreCleaningResponseBuilderConfiguration.builder()
                        .userEntity(userEntity)
                        .cleaningResult(cleaningResult)
                        .build()
        );
    }
}
