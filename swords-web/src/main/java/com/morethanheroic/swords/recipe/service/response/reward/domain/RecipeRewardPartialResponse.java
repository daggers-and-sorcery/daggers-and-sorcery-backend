package com.morethanheroic.swords.recipe.service.response.reward.domain;

import com.morethanheroic.response.domain.PartialResponse;
import com.morethanheroic.swords.item.view.response.service.domain.response.ItemDefinitionPartialResponse;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class RecipeRewardPartialResponse extends PartialResponse {

    private final ItemDefinitionPartialResponse item;
    private final int amount;
}
