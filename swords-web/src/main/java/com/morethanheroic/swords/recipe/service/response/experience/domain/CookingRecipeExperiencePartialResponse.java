package com.morethanheroic.swords.recipe.service.response.experience.domain;

import com.morethanheroic.response.domain.PartialResponse;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CookingRecipeExperiencePartialResponse extends PartialResponse {

    private final String skill;
    private final int amount;
}
