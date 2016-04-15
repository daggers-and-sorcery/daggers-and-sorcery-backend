package com.morethanheroic.swords.skill.cooking.service.reward.domain;

import com.morethanheroic.response.domain.PartialResponse;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CookingRecipeRewardPartialResponse extends PartialResponse {

    private final String name;
    private final int amount;
}
