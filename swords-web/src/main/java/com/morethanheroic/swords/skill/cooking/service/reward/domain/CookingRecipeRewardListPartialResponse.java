package com.morethanheroic.swords.skill.cooking.service.reward.domain;

import com.morethanheroic.swords.response.domain.PartialResponse;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class CookingRecipeRewardListPartialResponse extends PartialResponse {

    private final List<CookingRecipeRewardPartialResponse> cookingRecipeIngredients;
}
