package com.morethanheroic.swords.skill.smithing.view.controller.smelting;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.swords.recipe.service.definition.cache.RecipeDefinitionCache;
import com.morethanheroic.swords.skill.smithing.domain.SmeltingResult;
import com.morethanheroic.swords.skill.smithing.service.SmeltingService;
import com.morethanheroic.swords.skill.smithing.view.request.domain.smelting.SmeltingCreateRequest;
import com.morethanheroic.swords.skill.smithing.view.response.domain.configuration.smelting.SmeltingStartResponseBuilderConfiguration;
import com.morethanheroic.swords.skill.smithing.view.response.service.smelting.SmeltingStartResponseBuilder;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class StartSmeltingController {

    @Autowired
    private SmeltingStartResponseBuilder smeltingStartResponseBuilder;

    @Autowired
    private RecipeDefinitionCache recipeDefinitionCache;

    @Autowired
    private SmeltingService smeltingService;

    @RequestMapping(value = "/skill/smithing/smelting/start", method = RequestMethod.POST)
    public Response startSmelting(final UserEntity userEntity, @RequestBody @Valid final SmeltingCreateRequest smeltingCreateRequest) {
        final SmeltingResult smeltingResult = smeltingService.smelt(userEntity, recipeDefinitionCache.getDefinition(smeltingCreateRequest.getRecipeId()));

        return smeltingStartResponseBuilder.build(
                SmeltingStartResponseBuilderConfiguration.builder()
                        .userEntity(userEntity)
                        .smeltingResult(smeltingResult)
                        .build()
        );
    }
}
