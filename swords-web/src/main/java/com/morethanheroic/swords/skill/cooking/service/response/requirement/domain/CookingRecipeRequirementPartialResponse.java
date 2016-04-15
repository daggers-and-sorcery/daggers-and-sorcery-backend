package com.morethanheroic.swords.skill.cooking.service.response.requirement.domain;

import com.morethanheroic.response.domain.PartialResponse;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CookingRecipeRequirementPartialResponse extends PartialResponse {

    private final String skill;
    private final int amount;
}
