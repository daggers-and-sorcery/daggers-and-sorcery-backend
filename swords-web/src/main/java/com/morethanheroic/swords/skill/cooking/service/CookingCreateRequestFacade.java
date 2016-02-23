package com.morethanheroic.swords.skill.cooking.service;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.swords.recipe.service.cache.RecipeDefinitionCache;
import com.morethanheroic.swords.skill.cooking.CookingFacade;
import com.morethanheroic.swords.skill.cooking.service.domain.configuration.CookingCreateResponseBuilderConfiguration;
import com.morethanheroic.swords.skill.cooking.service.validator.CookingCreateRequestValidator;
import com.morethanheroic.swords.skill.cooking.view.request.CookingCreateRequest;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CookingCreateRequestFacade {

    private final CookingCreateRequestValidator cookingCreateRequestValidator;
    private final CookingCreateResponseBuilder cookingCreateResponseBuilder;
    private final CookingFacade cookingFacade;
    private final RecipeDefinitionCache recipeDefinitionCache;

    @Transactional
    public Response handleCookingCreateRequest(UserEntity userEntity, CookingCreateRequest cookingCreateRequest) {
        cookingCreateRequestValidator.validate(userEntity, cookingCreateRequest);

        cookingFacade.cook(userEntity, recipeDefinitionCache.getDefinition(cookingCreateRequest.getRecipeId()));

        return cookingCreateResponseBuilder.build(
                CookingCreateResponseBuilderConfiguration.builder()
                        .userEntity(userEntity)
                        .success(true)
                        .build()
        );
    }
}
