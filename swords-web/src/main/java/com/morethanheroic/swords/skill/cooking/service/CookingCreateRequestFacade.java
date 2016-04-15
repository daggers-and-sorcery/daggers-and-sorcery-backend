package com.morethanheroic.swords.skill.cooking.service;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.swords.recipe.service.cache.RecipeDefinitionCache;
import com.morethanheroic.swords.skill.cooking.domain.CookingResult;
import com.morethanheroic.swords.skill.cooking.service.response.CookingCreateResponseBuilder;
import com.morethanheroic.swords.skill.cooking.service.response.domain.configuration.CookingCreateResponseBuilderConfiguration;
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
        cookingCreateRequestValidator.validate(cookingCreateRequest);

        CookingResult cookingResult = cookingFacade.cook(userEntity, recipeDefinitionCache.getDefinition(cookingCreateRequest.getRecipeId()));

        return cookingCreateResponseBuilder.build(
                CookingCreateResponseBuilderConfiguration.builder()
                        .userEntity(userEntity)
                        .success(isSuccessfulCooking(cookingResult))
                        .build()
        );
    }

    private boolean isSuccessfulCooking(CookingResult cookingResult) {
        return  cookingResult == CookingResult.SUCCESSFUL;
    }
}
