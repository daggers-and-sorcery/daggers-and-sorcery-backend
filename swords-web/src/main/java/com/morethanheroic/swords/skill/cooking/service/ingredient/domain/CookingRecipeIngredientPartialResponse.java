package com.morethanheroic.swords.skill.cooking.service.ingredient.domain;

import com.morethanheroic.swords.response.domain.PartialResponse;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CookingRecipeIngredientPartialResponse extends PartialResponse {

    private final int id;
    private final int amount;
}
