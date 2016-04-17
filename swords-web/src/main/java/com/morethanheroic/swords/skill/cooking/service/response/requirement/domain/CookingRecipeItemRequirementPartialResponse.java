package com.morethanheroic.swords.skill.cooking.service.response.requirement.domain;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CookingRecipeItemRequirementPartialResponse extends CookingRecipeRequirementPartialResponse {

    private final String itemName;
    private final int amount;
}
