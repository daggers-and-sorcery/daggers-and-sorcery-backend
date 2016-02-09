package com.morethanheroic.swords.skill.cooking.service.domain;

import com.morethanheroic.swords.response.domain.PartialResponse;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CookingRecipeRewardPartialResponse extends PartialResponse {

    private final int id;
    private final int amount;
}
