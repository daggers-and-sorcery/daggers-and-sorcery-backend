package com.morethanheroic.swords.skill.cooking.service.experience.domain;

import com.morethanheroic.swords.response.domain.PartialResponse;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class CookingRecipeExperienceListPartialResponse extends PartialResponse {

    private final List<CookingRecipeExperiencePartialResponse> cookingRecipeExperiences;
}
