package com.morethanheroic.swords.skill.cooking.service.ingredient.service.domain;

import com.morethanheroic.swords.response.domain.PartialResponse;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class CookingRecipeIngredientListPartialResponse  extends PartialResponse {

    private final List<CookingRecipeIngredientPartialResponse> cookingRecipeIngredients;
}
