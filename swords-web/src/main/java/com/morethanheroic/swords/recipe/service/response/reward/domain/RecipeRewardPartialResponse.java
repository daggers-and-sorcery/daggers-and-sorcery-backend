package com.morethanheroic.swords.recipe.service.response.reward.domain;

import com.morethanheroic.response.domain.PartialResponse;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class RecipeRewardPartialResponse extends PartialResponse {

    private final String name;
    private final int amount;
}
