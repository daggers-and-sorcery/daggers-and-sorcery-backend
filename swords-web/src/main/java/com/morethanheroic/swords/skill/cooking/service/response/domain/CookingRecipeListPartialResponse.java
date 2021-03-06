package com.morethanheroic.swords.skill.cooking.service.response.domain;

import com.morethanheroic.response.domain.PartialResponse;
import com.morethanheroic.swords.recipe.view.response.domain.RecipePartialResponse;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class CookingRecipeListPartialResponse extends PartialResponse {

    private List<RecipePartialResponse> recipes;
}
