package com.morethanheroic.swords.recipe.view.response.experience.domain;

import com.morethanheroic.response.domain.PartialResponse;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class RecipeExperiencePartialResponse extends PartialResponse {

    private final String skill;
    private final int amount;
}
